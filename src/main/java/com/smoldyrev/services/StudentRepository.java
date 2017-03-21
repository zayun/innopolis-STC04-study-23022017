package com.smoldyrev.services;

import com.smoldyrev.models.pojo.Student;
import com.smoldyrev.models.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by smoldyrev on 21.03.17.
 */
@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select s from Student s where s.id_group = :id_group")
    List<Student> findByGroup(@Param("id_group") Integer id_group);
}
