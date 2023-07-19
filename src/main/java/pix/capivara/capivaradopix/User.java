package pix.capivara.capivaradopix;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  private long id;
  private String name;
  private Double investment;
  private Double profit;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getInvestment() {
    return investment;
  }

  public void setInvestment(Double investment) {
    this.investment = investment;
    this.profit = investment * 0.33;
  }

  public Double getProfit() {
    return profit;
  }

  private void setProfit(Double profit) {
    this.profit = profit;
  }

  void addProfit(Double additionalProfit) {
    this.setProfit(this.getProfit() + additionalProfit);
  }

}
