package com.smoldyrev.models.dao;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.connector.AcademConnector;
import com.smoldyrev.models.pojo.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by smoldyrev on 23.02.17.
 */

@Component
public class UserDAO implements UserDetailsService{

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("STUDENTS");

    private static Logger logger = Logger.getLogger(UserDAO.class);

    private static final String SQL_FIND_USER = "SELECT * FROM \"Main\".\"User\"\n" +
            "WHERE login = ? AND password = ?";

    private static final String SQL_CREATE_USER = "INSERT INTO \"Main\".\"User\"(login, password, role, email) VALUES (?,?,?,?)";

    private static final String SQL_USER_ID = "SELECT * FROM \"Main\".\"User\"\n" +
            "WHERE id = ?";

    public User getUserByLoginAndPassword(String login, String password) throws UserDAOException {

//        EntityManager em = FACTORY.createEntityManager();
//
//        em.getTransaction().begin();
//        User user = em.createQuery("select u from User as u " +
//                "where login = :login and password = :password", User.class)
//                .setParameter("login", login)
//                .setParameter("password", password)
//                .getSingleResult();
//        em.getTransaction().commit();
//        em.close();
//
//        return user;

        logger.debug(login + " " + password);
        User user = new User();
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find");
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("email")
                );
            }else{
                logger.debug(login + " " + password + " not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return user;
    }

    public static boolean registrationUser(String login, String password, String email) throws UserDAOException {
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, email);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(login + " " + password + " not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public static User getUserById(int id) throws UserDAOException {

        User user = null;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_USER_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find");
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("role"),
                        resultSet.getString("email")
                );
            }else{
                logger.debug(id + " not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        System.out.println("Getting access details from employee dao !!");
//
//        // Ideally it should be fetched from database and populated instance of
//        // #org.springframework.security.core.userdetails.User should be returned from this method
////        UserDetails user = new User(username, "password", true, true, true, true, new GrantedAuthority[]{ new GrantedAuthorityImpl("ROLE_USER") });
//        UserDetails user = null;
//        try {
//            user = getUserByLoginAndPassword(username, "1");
//        } catch (UserDAOException e) {
//            e.printStackTrace();
//        }
//        return user;


        EntityManager em = FACTORY.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("login"), username)
                )
        );
        List<User> users = em.createQuery(criteriaQuery).getResultList();

        System.out.println("/////z/////"+users.get(0).getLogin()+"//////////");
        return users.get(0);
    }


}
