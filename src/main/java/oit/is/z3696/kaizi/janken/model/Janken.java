package oit.is.z3696.kaizi.janken.model;

public class Janken {
  private String userHand;
  private String cpuHand = "Gu";
  private String result;

  public Janken(String userHand) {
    this.userHand = userHand;

    if (userHand.equals(cpuHand)) {
      result = "Tie";
      return;
    }

    if (userHand.equals("Gu") && cpuHand.equals("Choki") ||
        userHand.equals("Choki") && cpuHand.equals("Pa") ||
        userHand.equals("Pa") && cpuHand.equals("Gu")) {
      result = "You Win!";
      return;
    }

    result = "You lose.";
    return;
  }

  public String getUserHand() {
    return userHand;
  }

  public String getCpuHand() {
    return cpuHand;
  }

  public String getResult() {
    return result;
  }
}
