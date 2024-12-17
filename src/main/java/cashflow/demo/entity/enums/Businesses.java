package cashflow.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Businesses {
  Land10(1, "10 акров", false),
  Land20(2, "20 акров", false),
  GoldenCoin(3, "Монета", false),
  CertificateOfDeposit(4, "Депозит", true),
  Company(5, "Компания", false),
  PizzaBusiness(6, "Пиццерия", true),
  BoardingHouse(7, "Пансионат", true),
  CarWash(8, "Автомойка", true),
  Laundry(9, "Прачечная", true),
  PinBall(10, "Пинбол", true),
  Phones(11, "Телефоны", true),
  Mall(12, "ТЦ", true),
  Partner(13, "Партнер", true);
  private final int id;
  private final String name;
  private final boolean showInIncomes;
}
