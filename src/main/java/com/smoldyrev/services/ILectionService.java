package com.smoldyrev.services;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.dao.LectionDAO;
import com.smoldyrev.models.pojo.Lection;

import java.util.List;

/**
 * Created by smoldyrev on 10.03.17.
 */
public interface ILectionService {

    List<Lection> getAllLections();

    int deleteLectioOnId(int id);

    int updateLectionOnId(Lection lection);

    Lection getLectionOnId(int id);

    int insertLection(Lection lection);

    List<Lection> getNearedLection();
}
