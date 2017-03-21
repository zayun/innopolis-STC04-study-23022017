package com.smoldyrev.services;

import com.smoldyrev.models.pojo.Lection;
import com.smoldyrev.models.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by smoldyrev on 21.03.17.
 */
@Repository
@Transactional
public interface LectionRepository extends JpaRepository<Lection, Integer> {

//    @Query("select l from Lection l where l. = :id_group")
    List<Student> findNearedLections(@Param("id_group") Integer id_group);

}

