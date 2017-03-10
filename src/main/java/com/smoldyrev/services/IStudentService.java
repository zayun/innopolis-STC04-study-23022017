package com.smoldyrev.services;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.dao.StudentDao;
import com.smoldyrev.models.pojo.Student;

import java.util.List;

/**
 * Created by smoldyrev on 10.03.17.
 */
public interface IStudentService {

    List<Student> getAllStudents();

    Student getStudentById(int id);

    int deleteStudentOnId(int id);

    int updateStudentOnId(Student student);

    int insertStudent(Student student);

    List<Student> getStudentsByGroupId(int groupid);
}
