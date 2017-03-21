package com.smoldyrev.services;

import com.smoldyrev.models.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by smoldyrev on 21.03.17.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.login = :login and u.password = :password")
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);

}
