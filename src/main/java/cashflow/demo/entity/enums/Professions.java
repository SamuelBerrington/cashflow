package cashflow.demo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Professions {
  Secretary(1,"Секретарь",2500,710,140,460,400,0,80,60,50,570,38000,0,4000,2000,1000),
  Nurse(2,"Медсестра",3100,480,170,600,400,30,100,90,50,710,47000,6000,5000,3000,1000),
  PoliceOfficer(3,"Полицейский",3000,520,160,580,400,0,100,60,50,690,46000,0,5000,2000,1000),
  AutoMechanic(4,"Автомеханик",2000,670,110,360,300,0,60,60,50,450,31000,0,3000,2000,1000),
  TruckDriver(5,"Водитель",2500,750,140,460,400,0,80,60,50,570,38000,0,4000,2000,1000),
  Manager(6,"Менеджер",4600,400,240,910,700,60,120,90,50,1000,75000,12000,6000,3000,1000),
  Doctor(7,"Врач",13200,400,640,3420,1900,750,380,270,50,2880,202000,150000,19000,9000,1000),
  Engineer(8,"Инженер",4900,400,250,1050,700,60,140,120,50,1090,75000,12000,7000,4000,1000),
  Pilot(9,"Пилот",9500,400,480,2350,1330,0,300,660,50,2210,143000,0,15000,22000,1000),
  StreetCleaner(10,"Дворник",1600,560,70,280,200,0,60,60,50,300,20000,0,4000,2000,1000),
  Teacher(11,"Учитель",3300,400,180,630,500,60,100,90,50,760,50000,12000,5000,3000,1000),
  Lawyer(12,"Адвокат",7500,400,380,1830,1100,390,220,180,50,1650,115000,78000,11000,6000,1000);

  private int id;
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

  public static Professions getProfessionByName(String name){
    for(Professions p : Professions.values()){
      if(p.name.equals(name)){
        return p;
      }
    }
    throw new IllegalArgumentException("Нет такой профессии");
  }

  public static Professions getProfessionById(int profession) {
    for(Professions p : Professions.values()){
      if(p.id == profession){
        return p;
      }
    }
    throw new IllegalArgumentException("Нет такой профессии");
  }
}
