package cashflow.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DebtType {
  Mortgage("mortgage"),
  EducationDebt("educationDebt"),
  CarDebt("carDebt"),
  CreditCardDebt("creditCardDebt"),
  RetailDebt("retailDebt");

  String name;

  public static DebtType getDebtType(String name) {
    for (DebtType type : DebtType.values()) {
      if (type.getName().equals(name)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Invalid DebtType: " + name);
  }
}
