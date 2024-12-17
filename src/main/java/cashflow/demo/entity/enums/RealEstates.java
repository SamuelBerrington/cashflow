package cashflow.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RealEstates {
  Home21(1, "Дом 2/1"),
  Home32(2, "Дом 3/2"),
  Duplex(3, "Дуплекс"),
  FourPlex(4, "4-плекс"),
  EightPlex(5, "8-плекс"),
  Apartment12(6, "Мнкв 12"),
  Apartment24(7, "Мнкв 24"),
  Apartment60(8, "Мнкв 60");
  private final int id;
  private final String name;
}
