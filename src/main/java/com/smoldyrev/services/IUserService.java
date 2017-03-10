package com.smoldyrev.services;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.dao.UserDAO;
import com.smoldyrev.models.pojo.User;

/**
 * Created by smoldyrev on 10.03.17.
 */
public interface IUserService {
    User authorise(String login, String password) throws UserDAOException;

    boolean registration(String login, String password, String email) throws UserDAOException;
}
