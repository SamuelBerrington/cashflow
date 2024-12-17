package cashflow.demo.entity;

import jakarta.persistence.*;
import java.beans.ConstructorProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) // Используем JOINED для наследования
public abstract class Asset {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private int income;
  private int firstPayment;
  private int price;
  private int mortgage;

  @ManyToOne
  @JoinColumn(name = "player_id")
  private Player player;
}