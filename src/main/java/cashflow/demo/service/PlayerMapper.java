package cashflow.demo.service;

import cashflow.demo.entity.ProfessionResponse;
import cashflow.demo.entity.dto.PlayerRequest;
import cashflow.demo.entity.dto.PlayerResponse;
import cashflow.demo.entity.Business;
import cashflow.demo.entity.Player;

import cashflow.demo.entity.RealEstate;
import cashflow.demo.entity.Stock;
import cashflow.demo.entity.enums.Professions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

  PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

  @Mapping(target = "profession", expression = "java(getProfession(request.getProfession()))")
  @Mapping(target = "auditor", expression = "java(findAuditorByName(request.getName()))")
  @Mapping(target = "children", expression = "java(setDefaultValue())")
  @Mapping(target = "bankDebt", expression = "java(setDefaultValue())")
  @Mapping(target = "bankLoan", expression = "java(player.getBankDebt()/10)")
  Player playerRequestToPlayer(PlayerRequest request);

  default Player findAuditorByName(String auditorName) {
    // Здесь должен быть код для поиска Player по имени auditorName
    // Например, вызов сервиса или репозитория
    // return playerService.findByName(auditorName);
    return null; // Заглушка
  }

  @Mapping(target = "profession", expression = "java(professionsToProfessionResponse(player" +
                                               ".getProfession(), player))")
  @Mapping(target = "passiveIncome", expression = "java(calculatePassiveIncome(player))")
  @Mapping(target = "totalIncome", expression = "java(calculateTotalIncome(player) + " +
                                                "playerResponse.getPassiveIncome())")
  @Mapping(target = "expense", expression = "java(calculateExpense(player, playerResponse" +
                                            ".getProfession()))")
  @Mapping(target = "monthlyIncome", expression = "java(calculateMonthlyIncome(playerResponse" +
                                                  ".getTotalIncome(),playerResponse.getExpense()))")
  @Mapping(target = "childExpenses", expression = "java(player.getChildren()*player.getProfession" +
                                                  "().getChildExpenses())")
  PlayerResponse playerToPlayerResponse(Player player);


  @Mapping(target = "mortgage", expression = "java(player.getPayOffMortgage() == 0 ? profession" +
                                             ".getMortgage() : 0)")
  @Mapping(target = "educationLoan", expression = "java(player.getPayOffEducationDebt() == 0 ? " +
                                                  "profession.getEducationLoan() : 0)")
  @Mapping(target = "carLoan", expression = "java(player.getPayOffCarDebt() == 0 ? profession" +
                                            ".getCarLoan() : 0)")
  @Mapping(target = "creditCardLoan", expression = "java(player.getPayOffCreditCardDebt() == 0 ? " +
                                                   "profession.getCreditCardLoan() : 0)")
  @Mapping(target = "retailLoan", expression = "java(player.getPayOffRetailDebt() == 0 ? " +
                                               "profession.getRetailLoan() : 0)")

  @Mapping(target = "mortgageLiability", expression = "java(profession.getMortgageLiability() - " +
                                                      "player.getPayOffMortgage())")
  @Mapping(target = "educationLoanCost", expression = "java(profession.getEducationLoanCost() - " +
                                                      "player.getPayOffEducationDebt())")
  @Mapping(target = "carLoanCost", expression = "java(profession.getCarLoanCost() - player" +
                                                ".getPayOffCarDebt())")
  @Mapping(target = "creditCardDebt", expression = "java(profession.getCreditCardDebt() - player" +
                                                   ".getPayOffCreditCardDebt())")
  @Mapping(target = "retailDebt", expression = "java(profession.getRetailDebt() - player" +
                                               ".getPayOffRetailDebt())")
  @Mapping(target = "name", source = "profession.name")
  ProfessionResponse professionsToProfessionResponse(Professions profession, Player player);

  default int calculateTotalIncome(Player player) {
    return player.getProfession().getSalary();


  }

  default int calculateExpense(Player player, ProfessionResponse profession) {
    return profession.getTax() +
           profession.getMortgage() +
           profession.getEducationLoan() +
           profession.getCarLoan() +
           profession.getCreditCardLoan() +
           profession.getRetailLoan() +
           profession.getOtherExpenses() +
           player.getBankLoan() +
           player.getChildren() * player.getProfession().getChildExpenses();
  }

  default int calculatePassiveIncome(Player player) {
    return calculateBusiness(player) + calculateRealEstates(player) + calculateStocks(player);
  }

  private int calculateRealEstates(Player player) {
    return player.getOwnRealEstates()
        .stream()
        .mapToInt(RealEstate::getIncome) // Преобразуем список в поток int
        .sum(); // Суммируем значения
  }

  private int calculateBusiness(Player player) {
    return player.getOwnBusinesses()
        .stream()
        .mapToInt(Business::getIncome) // Преобразуем список в поток int
        .sum(); // Суммируем значения
  }

  private int calculateStocks(Player player) {
    return player.getOwnStocks()
        .stream()
        .mapToInt(Stock::getIncome) // Преобразуем список в поток int
        .sum(); // Суммируем значения
  }

  default int calculateMonthlyIncome(int totalIncome, int expense) {
    return totalIncome - expense;
  }

  default Professions getProfession(String profession) {
    return Professions.getProfessionById(Integer.parseInt(profession));
  }

  default int setDefaultValue() {
    return 0;
  }
}