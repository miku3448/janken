package oit.is.z3696.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  @Select("SELECT * from matchinfo")
  ArrayList<MatchInfo> selectAllMatchinfo();

  @Select("SELECT * FROM matchinfo WHERE isActive = true")
  ArrayList<MatchInfo> selectActiveMatches();

  @Select("SELECT * FROM matchinfo WHERE isActive = true and (user1=#{userId} or user2=#{userId})")
  ArrayList<MatchInfo> selectActiveMatchByUserId(int userId);

  @Insert("INSERT INTO matchinfo (user1, user2, user1Hand, isActive) VALUES (#{user1}, #{user2}, #{user1Hand}, #{isActive});")
  @Options(useGeneratedKeys = true, keyColumn = "matchId", keyProperty = "matchId")
  void insertMatchinfo(MatchInfo matchinfo);

  @Update("UPDATE matchinfo SET isActive = #{isActive} WHERE matchId = #{matchId}")
  void updateMatchinfo(MatchInfo matchinfo);

}
