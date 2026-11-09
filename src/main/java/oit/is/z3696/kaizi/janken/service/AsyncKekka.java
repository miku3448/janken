package oit.is.z3696.kaizi.janken.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z3696.kaizi.janken.model.Match;
import oit.is.z3696.kaizi.janken.model.MatchMapper;

@Service
public class AsyncKekka {

  @Autowired
  private MatchMapper matchMapper;

  private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

  @Async
  public void updata(SseEmitter emitter) throws IOException {
    try {
      while (true) {

        logger.info("試合結果を確認中");

        ArrayList<Match> activeMatches = matchMapper.selectActiveMatches();

        if (!activeMatches.isEmpty()) {

          emitter.send(activeMatches);

          for (Match match : activeMatches) {
            match.setIsActive(false);
            matchMapper.updateMatch(match);
          }

          // 結果を送信したら終了
          emitter.complete();
          return;
        }

        TimeUnit.SECONDS.sleep(1);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
