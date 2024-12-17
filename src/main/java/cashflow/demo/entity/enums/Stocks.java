package cashflow.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Stocks {
  OK4U(1, "OK4U", 0),
  ON2U(2, "ON2U", 0),
  MYT4U(3, "MYT4U", 0),
  BIG2(4, "2BIG", 10),
  GRO4US(5, "GRO4US", 0);
  private final int id;
  private final String name;
  private final int mounthlyIncome;
}
