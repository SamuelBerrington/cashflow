package cashflow.demo.entity;

import cashflow.demo.entity.enums.Professions;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Data
@Entity
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private Professions profession;
  @OneToOne(cascade = CascadeType.ALL)
  private Player auditor;

  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  @Fetch(FetchMode.JOIN)
  private List<RealEstate> ownRealEstates = new ArrayList<>();
  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  @Fetch(FetchMode.JOIN)
  private List<Stock> ownStocks = new ArrayList<>();
  @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
  @Fetch(FetchMode.JOIN)
  private List<Business> ownBusinesses = new ArrayList<>();

  private int payOffMortgage;
  private int payOffEducationDebt;
  private int payOffCarDebt;
  private int payOffCreditCardDebt;
  private int payOffRetailDebt;

  private int bankDebt;
  private int bankLoan;
  private int children;

}
