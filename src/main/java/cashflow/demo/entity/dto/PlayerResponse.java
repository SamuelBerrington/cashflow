package cashflow.demo.entity.dto;

import cashflow.demo.entity.Business;
import cashflow.demo.entity.ProfessionResponse;
import cashflow.demo.entity.RealEstate;
import cashflow.demo.entity.Stock;
import cashflow.demo.entity.enums.Businesses;
import cashflow.demo.entity.enums.Professions;
import cashflow.demo.entity.enums.RealEstates;
import cashflow.demo.entity.enums.Stocks;
import java.util.List;
import lombok.Data;

@Data
public class PlayerResponse {

  private String name;
  private ProfessionResponse profession;
  private PlayerResponse auditor;

  private int passiveIncome;
  private int totalIncome;
  private int expense;
  private int monthlyIncome;

  private int children;
  private int childExpenses;

  private List<Business> ownBusinesses;
  private List<RealEstate> ownRealEstates;
  private List<Stock> ownStocks;

  private int bankLoan;
  private int bankDebt;

  private List<Businesses> business = List.of(Businesses.values());
  private List<RealEstates> realEstates = List.of(RealEstates.values());
  private List<Stocks> stocks = List.of(Stocks.values());


}
