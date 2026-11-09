package oit.is.z3696.kaizi.janken.controller;

import java.security.Principal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3696.kaizi.janken.model.Janken;
import oit.is.z3696.kaizi.janken.model.Entry;
import oit.is.z3696.kaizi.janken.model.User;
import oit.is.z3696.kaizi.janken.model.UserMapper;
import oit.is.z3696.kaizi.janken.model.Match;
import oit.is.z3696.kaizi.janken.model.MatchInfo;
import oit.is.z3696.kaizi.janken.model.MatchMapper;
import oit.is.z3696.kaizi.janken.model.MatchInfoMapper;

@Controller
public class JankenController {
  @Autowired
  private Entry entry;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private MatchMapper matchMapper;

  @Autowired
  private MatchInfoMapper matchInfoMapper;

  @GetMapping("/janken")
  public String janken(ModelMap model, Principal prin) {

    String loginUser = prin.getName(); // ログインユーザ情報
    this.entry.addUser(loginUser);
    model.addAttribute("login_user", loginUser);

    ArrayList<User> users = userMapper.selectAllUser();
    model.addAttribute("users", users);

    ArrayList<Match> matches = matchMapper.selectAllMatch();
    model.addAttribute("matches", matches);

    ArrayList<MatchInfo> activeMatch = matchInfoMapper.selectActiveMatches();
    model.addAttribute("activeMatch", activeMatch);

    return "janken.html";
  }

  @GetMapping("/match")
  public String match(@RequestParam int id, ModelMap model, Principal prin) {

    String loginUser = prin.getName(); // ログインユーザ情報
    this.entry.addUser(loginUser);
    model.addAttribute("login_user", loginUser);

    User user2 = userMapper.selectById(id);
    model.addAttribute("user2", user2);

    return "match.html";
  }

  @GetMapping("/fight")
  public String fight(@RequestParam int id, @RequestParam(required = false) String userHand, ModelMap model,
      Principal prin) {
    String loginUser = prin.getName(); // ログインユーザ情報
    this.entry.addUser(loginUser);
    model.addAttribute("login_user", loginUser);

    User user1 = userMapper.selectByName(loginUser);
    User user2 = userMapper.selectById(id);
    model.addAttribute("user2", user2);

    // 試合中の試合を探す
    ArrayList<MatchInfo> activeMatch = matchInfoMapper.selectActiveMatchByUserId(user2.getUserId());

    if (activeMatch.isEmpty()) {
      // このMatchをmatchinfoテーブルへinsert
      MatchInfo matchinfo = new MatchInfo();
      matchinfo.setUser1(user1.getUserId());
      matchinfo.setUser2(user2.getUserId());
      matchinfo.setUser1Hand(userHand);
      matchinfo.setIsActive();

      matchInfoMapper.insertMatchinfo(matchinfo);
    } else {
      MatchInfo matchinfo = activeMatch.get(0);

      Janken janken = new Janken(matchinfo.getUser1Hand(), userHand);
      model.addAttribute("user1_hand", janken.getUserHand());
      model.addAttribute("user2_hand", janken.getCpuHand());
      model.addAttribute("result", janken.getResult());

      // このMatchをmatchテーブルへinsert
      Match match = new Match();
      match.setUser1(user1.getUserId());
      match.setUser2(user2.getUserId());
      match.setUser1Hand(janken.getUserHand());
      match.setUser2Hand(janken.getCpuHand());
      match.setIsActive(true);

      matchMapper.insertMatch(match);

      model.addAttribute("match", match);

      // matchinfoのisActiveを更新
      matchinfo.setIsActive();
      matchInfoMapper.updateMatchinfo(matchinfo);
    }

    return "wait.html";
  }
}
