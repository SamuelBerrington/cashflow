package cashflow.demo.service;

import cashflow.demo.entity.dto.AssetDeleteRequest;
import cashflow.demo.entity.dto.PlayerRequest;
import cashflow.demo.entity.dto.PlayerResponse;
import cashflow.demo.entity.dto.AssetRequest;
import cashflow.demo.entity.Business;
import cashflow.demo.entity.Player;
import cashflow.demo.entity.RealEstate;
import cashflow.demo.entity.Stock;
import cashflow.demo.entity.enums.AssetType;
import cashflow.demo.entity.enums.DebtType;
import cashflow.demo.entity.factory.AssetFactory;
import cashflow.demo.entity.factory.BusinessFactory;
import cashflow.demo.entity.factory.RealEstateFactory;
import cashflow.demo.entity.factory.StockFactory;
import cashflow.demo.repository.BusinessRepository;
import cashflow.demo.repository.PlayerRepository;
import cashflow.demo.repository.RealEstateRepository;
import cashflow.demo.repository.StockRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PlayerService {

  @Autowired
  private PlayerMapper playerMapper;
  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private RealEstateRepository realEstateRepository;
  @Autowired
  private BusinessRepository businessRepository;
  @Autowired
  private StockRepository stockRepository;

  AssetFactory factory;


  public PlayerResponse createPlayer(PlayerRequest playerRequest) {
    Player player = playerMapper.playerRequestToPlayer(playerRequest);
    List<Player> players = playerRepository.findAllByName(player.getName());
    if (!players.isEmpty()) {

      //      playerRepository.deleteAllById(players.stream().map(p -> p.getId().toString())
      //      .collect(Collectors.toList()));
    } else {
      player = playerRepository.save(player);
    }
    return playerMapper.playerToPlayerResponse(player);
  }

  public PlayerResponse getPlayer(String name) {
    Player player = playerRepository.findByName(name);
    return playerMapper.playerToPlayerResponse(player);
  }

  @Transactional
  public PlayerResponse addAssetToPlayer(AssetRequest assetRequest, String playerName) {
    Player player = playerRepository.findByName(playerName);
    if (player == null) {
      throw new IllegalArgumentException("Player not found with name: " + playerName);
    }
    AssetType assetType = AssetType.getAssetType(assetRequest.getAssetType());
    switch (assetType) {
      case REAL_ESTATE -> {
        return addEstateToPlayer(assetRequest, player);
      }
      case STOCK -> {
        return addStockToPlayer(assetRequest, player);
      }
      case BUSINESS -> {
        return addBusinessToPlayer(assetRequest, player);
      }
      default -> throw new IllegalArgumentException("Asset type not supported: " + assetType);
    }
  }

  private PlayerResponse addBusinessToPlayer(AssetRequest assetRequest, Player player) {
    factory = new BusinessFactory(assetRequest);
    Business business = (Business) factory.createAsset(assetRequest, player);
    businessRepository.save(business);
    business.setPlayer(player);
    player.getOwnBusinesses().add(business);

    return playerMapper.playerToPlayerResponse(player);
  }

  private PlayerResponse addStockToPlayer(AssetRequest assetRequest, Player player) {
    factory = new StockFactory(assetRequest);
    Stock stock = (Stock) factory.createAsset(assetRequest, player);
    stockRepository.save(stock);
    stock.setPlayer(player);
    player.getOwnStocks().add(stock);

    return playerMapper.playerToPlayerResponse(player);
  }


  private PlayerResponse addEstateToPlayer(AssetRequest assetRequest, Player player) {
    factory = new RealEstateFactory(assetRequest);
    RealEstate realEstate = (RealEstate) factory.createAsset(assetRequest, player);
    realEstateRepository.save(realEstate);
    realEstate.setPlayer(player);
    player.getOwnRealEstates().add(realEstate);

    return playerMapper.playerToPlayerResponse(player);
  }

  @Transactional
  public PlayerResponse removeAsset(AssetDeleteRequest assetDeleteRequest, String playerName) {
    String assetId = assetDeleteRequest.getAssetId().toString();
    AssetType assetType = AssetType.getAssetType(assetDeleteRequest.getAssetType());
    Player player = playerRepository.findByName(playerName);
    switch (assetType) {
      case REAL_ESTATE -> {
        Optional<RealEstate> realEstateOpt = realEstateRepository.findById(assetId);
        RealEstate realEstate = realEstateOpt.orElse(null);
        player.getOwnRealEstates().remove(realEstate);
      }
      case STOCK -> {
        Optional<Stock> stockOpt = stockRepository.findById(assetId);
        Stock stock = stockOpt.orElse(null);
        player.getOwnStocks().remove(stock);
      }
      case BUSINESS -> {
        Optional<Business> businessOpt = businessRepository.findById(assetId);
        Business business = businessOpt.orElse(null);
        player.getOwnBusinesses().remove(business);
      }
      default -> throw new IllegalArgumentException("Asset type not supported: " + assetType);
    }

    return playerMapper.playerToPlayerResponse(player);
  }

  @Transactional
  public PlayerResponse removeAll(String name) {
    Player player = playerRepository.findByName(name);
    player.getOwnRealEstates().clear();
    player.getOwnStocks().clear();
    player.getOwnBusinesses().clear();
    return playerMapper.playerToPlayerResponse(player);
  }

  @Transactional
  public PlayerResponse addChild(String name) {
    Player player = playerRepository.findByName(name);
    int children = player.getChildren();
    if (children < 3) {
      children += 1;
    }
    player.setChildren(children);
    return playerMapper.playerToPlayerResponse(player);
  }

  @Transactional
  public PlayerResponse deleteChild(String name) {
    Player player = playerRepository.findByName(name);
    int children = player.getChildren();
    if (children > 0) {
      children -= 1;
    }
    player.setChildren(children);
    return playerMapper.playerToPlayerResponse(player);
  }

  @Transactional
  public PlayerResponse payOff(String debtTypeStr, String name) {
    Player player = playerRepository.findByName(name);
    DebtType debtType = DebtType.getDebtType(debtTypeStr);
    switch (debtType) {
      case Mortgage -> player.setPayOffMortgage(player.getProfession().getMortgageLiability());
      case EducationDebt ->
          player.setPayOffEducationDebt(player.getProfession().getEducationLoanCost());
      case CarDebt -> player.setPayOffCarDebt(player.getProfession().getCarLoanCost());
      case CreditCardDebt ->
          player.setPayOffCreditCardDebt(player.getProfession().getCreditCardDebt());
      case RetailDebt -> player.setPayOffRetailDebt(player.getProfession().getRetailDebt());
      default -> throw new IllegalArgumentException("Asset type not supported: " + debtTypeStr);
    }
    return playerMapper.playerToPlayerResponse(player);
  }

  @Transactional
  public PlayerResponse takeCredit(int credit, String playerName) {
    Player player = playerRepository.findByName(playerName);
    if (credit % 1000 != 0) {
      throw new IllegalArgumentException("Credit cannot exceed 1000");
    }
    player.setBankDebt(player.getBankDebt() + credit);
    player.setBankLoan(player.getBankLoan() + credit / 10);
    return playerMapper.playerToPlayerResponse(player);
  }

  @Transactional
  public PlayerResponse payCredit(int credit, String playerName) {
    Player player = playerRepository.findByName(playerName);
    if (credit % 1000 != 0) {
      throw new IllegalArgumentException("Credit cannot exceed 1000");
    }
    player.setBankDebt(player.getBankDebt() - credit);
    player.setBankLoan(player.getBankLoan() - credit / 10);
    return playerMapper.playerToPlayerResponse(player);
  }
}
