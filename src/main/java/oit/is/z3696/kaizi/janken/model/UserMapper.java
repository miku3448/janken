package oit.is.z3696.kaizi.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("SELECT * from users")
  ArrayList<User> selectAllUser();

  // userIdからuserを探してくる
  @Select("SELECT * from users where id = #{id}")
  User selectById(int id);

  // userNameからuserを探してくる
  @Select("SELECT * from users where userName = #{userName}")
  ArrayList<User> selectAllByUserName(String userName);

  // userNameを指定してuserを追加（Idは自動設定）
  @Insert("INSERT INTO users (userName) VALUES (#{userName});")
  @Options(useGeneratedKeys = true, keyColumn = "userId", keyProperty = "userId")
  void insertUser(User user);

}
