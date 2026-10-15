package oit.is.z3696.kaizi.janken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import oit.is.z3696.kaizi.janken.model.Janken;
import oit.is.z3696.kaizi.janken.model.Entry;

@Controller
public class JankenController {
  @Autowired
  private Entry entry;

  @GetMapping("/janken")
  public String janken(@RequestParam(required = false) String userHand, ModelMap model, Principal prin) {

    String loginUser = prin.getName(); // ログインユーザ情報
    this.entry.addUser(loginUser);
    model.addAttribute("login_user", loginUser);
    model.addAttribute("users", entry.getUsers());

    if (userHand != null) {
      Janken janken = new Janken(userHand);

      model.addAttribute("userHand", janken.getUserHand());
      model.addAttribute("cpuHand", janken.getCpuHand());
      model.addAttribute("result", janken.getResult());
    }

    return "janken.html";
  }

  @PostMapping("/janken")
  public String jankenPost(@RequestParam String userName, ModelMap model) {
    model.addAttribute("userName", userName);
    return "janken.html";
  }

}
