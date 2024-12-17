package cashflow.demo.entity;

import lombok.Data;

@Data
public class ProfessionResponse {

  private String name;
  private int salary;
  private int savings;
  private int childExpenses;

  //Расходы
  private int tax;
  private int mortgage;
  private int educationLoan;
  private int carLoan;
  private int creditCardLoan;
  private int retailLoan;
  private int otherExpenses;

  //пассивы
  private int mortgageLiability;
  private int educationLoanCost;
  private int carLoanCost;
  private int creditCardDebt;
  private int retailDebt;

}
