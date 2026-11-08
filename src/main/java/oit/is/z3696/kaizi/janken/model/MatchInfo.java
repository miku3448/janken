package oit.is.z3696.kaizi.janken.model;

public class MatchInfo {
  int matchId;
  int user1;
  int user2;
  String user1Hand;
  Boolean isActive = false;

  public int getMatchId() {
    return this.matchId;
  }

  public int getUser1() {
    return user1;
  }

  public void setUser1(int user1) {
    this.user1 = user1;
  }

  public int getUser2() {
    return user2;
  }

  public void setUser2(int user2) {
    this.user2 = user2;
  }

  public String getUser1Hand() {
    return user1Hand;
  }

  public void setUser1Hand(String user1Hand) {
    this.user1Hand = user1Hand;
  }

  public boolean getIsActive() {
    return isActive;
  }

  public void setIsActive() {
    this.isActive = !this.isActive;
  }

}
