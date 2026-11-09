package oit.is.z3696.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  @Select("SELECT * from matches")
  ArrayList<Match> selectAllMatch();

  @Select("SELECT * FROM matches WHERE isActive = true")
  ArrayList<Match> selectActiveMatches();

  @Insert("INSERT INTO matches (user1, user2, user1Hand, user2Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{user2Hand}, #{isActive});")
  @Options(useGeneratedKeys = true, keyColumn = "matchId", keyProperty = "matchId")
  void insertMatch(Match match);

  @Update("UPDATE matches SET isActive = #{isActive} WHERE matchId = #{matchId}")
  void updateMatch(Match match);

}
