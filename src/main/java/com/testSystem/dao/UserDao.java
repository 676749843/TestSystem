package com.testSystem.dao;

import org.springframework.data.domain.Pageable;
import com.testSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.List;

@Repository
@Table(name = "t_all_user")
public interface UserDao extends JpaRepository<User, String> {
    @Query(value = "select t from User t ")
    List<User> selectUserInfo(Pageable pageable);

    @Query(value = "select t from User t  where t.authority=3")
    List<User> selDeUser();


    @Query(value = "select count(*) from t_all_user ", nativeQuery = true)
    int countUser();
    @Query(value = "select t.* from t_all_user t where t.username=:username", nativeQuery = true)
    User findByUserName(@Param("username")String username);
    @Query(value = "select t from User t where t.userName=:username")
    User findOne(String username);

    @Transactional
    @Modifying
    @Query(value = "delete  from User t where t.userName=:username")
    int delUser(@Param("username") String username);

    @Query(value = "select count(*) from t_all_user where username = :username and passwd = :passwd", nativeQuery = true)
    int countUserByNameAndPassWd(@Param("username") String username,@Param("passwd") String passwd);

    @Transactional
    @Modifying
    @Query(value = "update t_all_user set passwd = :passwd where username = :username",nativeQuery = true)
    int updatePassword(@Param("username") String username,@Param("passwd") String passwd);

    @Query(value = "select passwd from t_all_user where username = :username", nativeQuery = true)
    String getPasswordByUserName(@Param("username") String username);
}
