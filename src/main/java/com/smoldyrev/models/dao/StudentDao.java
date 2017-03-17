package com.smoldyrev.models.dao;

import com.smoldyrev.common.exceptions.UserDAOException;
import com.smoldyrev.models.connector.AcademConnector;
import com.smoldyrev.models.pojo.Student;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
@Service
public class StudentDao {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("STUDENTS");

    private static Logger logger = Logger.getLogger(StudentDao.class);

    private static String SQL_ALL_STUDENTS = "SELECT * FROM \"Main\".\"Student\"";

    private static String SQL_STUDENTS_GROUP = "SELECT * FROM \"Main\".\"Student\" WHERE id_group = ?";

    private static String SQL_FIND_STUDENT = "SELECT * FROM \"Main\".\"Student\" WHERE id =?";

    private static String SQL_DELETE_STUDENT = "DELETE FROM \"Main\".\"Student\" WHERE id = ?";

    private static String SQL_UPDATE_STUDENT = "UPDATE \"Main\".\"Student\"\n" +
            "\tSET id=?, name=?, birthdate=?, sex=?, id_group=?, email = ?" +
            "\tWHERE id=?";

    private static String SQL_INSERT_STUDENT = "INSERT INTO \"Main\".\"Student\"(\n" +
            "\t name, birthdate, sex, id_group, email)\n" +
            "\tVALUES (?, ?, ?, ?,?);";

    public static List<Student> getAllStudents(){
        List<Student> studentsList = new ArrayList<>();
        try(Connection connection = AcademConnector.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_ALL_STUDENTS);

            while(resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("birthdate"),
                        resultSet.getString("sex"),
                        resultSet.getInt("id_group"),
                        resultSet.getString("email")
                );
                studentsList.add(student);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return studentsList;
    }

    public static int deleteStudent(int id) {
        int count = 0;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_STUDENT)) {
            preparedStatement.setInt(1, id);

            count = preparedStatement.executeUpdate();
            logger.debug(id+" student was deleted");
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public static int updateStudent(Student student){

        int count = 0;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STUDENT)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setDate(3, new Date(student.getBirthdate().getTime()));
            preparedStatement.setString(4, student.getSex());
            preparedStatement.setInt(5, student.getIdGroup());
            preparedStatement.setString(6, student.getEmail());
            preparedStatement.setInt(7, student.getId());


            count = preparedStatement.executeUpdate();
            logger.debug(student.getId()+" student was update"+student.getIdGroup());
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public static int insertStudent(Student student){

        int count = 0;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_STUDENT)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setDate(2, new Date(student.getBirthdate().getTime()));
            preparedStatement.setString(3, student.getSex());
            preparedStatement.setInt(4, student.getIdGroup());
            preparedStatement.setString(5, student.getEmail());

            count = preparedStatement.executeUpdate();
            logger.debug(student.getId()+" student was insert"+student.getIdGroup());
        } catch (SQLException e) {
            logger.error(e);
        }
        return count;
    }

    public static Student getStudentById(int id) throws UserDAOException {

        logger.debug(id);
        Student student = null;
        try(Connection connection = AcademConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_STUDENT)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find"+id);
                student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("birthdate"),
                        resultSet.getString("sex"),
                        resultSet.getInt("id_group"),
                        resultSet.getString("email"));
            }else{
                logger.debug(id+" not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return student;
    }

    public static List<Student> getStudentsByGroup(int groupid){
        List<Student> studentsList = new ArrayList<>();
        try(Connection connection = AcademConnector.getConnection()){
            PreparedStatement preparedStatement= connection.prepareStatement(SQL_STUDENTS_GROUP);
            preparedStatement.setInt(1,groupid);
            ResultSet resultSet = preparedStatement.executeQuery();


            while(resultSet.next()) {
                logger.debug(resultSet.getString("name"));

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDate("birthdate"),
                        resultSet.getString("sex"),
                        resultSet.getInt("id_group"),
                        resultSet.getString("email")
                );
                studentsList.add(student);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return studentsList;
    }

    public static List<Student> getAllStudentsHiber() {

        EntityManager em = FACTORY.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> from = cq.from(Student.class);

        cq.select(from);
        TypedQuery<Student> q = em.createQuery(cq);
        List<Student> students = q.getResultList();
//
//
//
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
//        Root<Student> root = criteriaQuery.from(Student.class);
//        criteriaQuery.select(root);
//
//        List<Student> students = em.createQuery(criteriaQuery).getResultList();
        for (Student st:
             students) {
            System.out.println("///////"+st.getName());
        }
        return students;
    }
}
