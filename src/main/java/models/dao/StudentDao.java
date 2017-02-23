package models.dao;

import common.exceptions.UserDAOException;
import models.connector.AcademConnector;
import models.pojo.Student;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
public class StudentDao {

    private static Logger logger = Logger.getLogger(StudentDao.class);

    private static String SQL_ALL_STUDENTS = "SELECT * FROM \"Main\".\"Student\"";

    private static String SQL_FIND_STUDENT = "SELECT * FROM \"Main\".\"Student\" WHERE id =?";

    private static String SQL_DELETE_STUDENT = "DELETE FROM \"Main\".\"Student\" WHERE id = ?";

    private static String SQL_UPDATE_STUDENT = "UPDATE \"Main\".\"Student\"\n" +
            "\tSET id=?, name=?, birthdate=?, sex=?, id_group=?\n" +
            "\tWHERE id=?";

    private static String SQL_INSERT_STUDENT = "INSERT INTO \"Main\".\"Student\"(\n" +
            "\t name, birthdate, sex, id_group)\n" +
            "\tVALUES (?, ?, ?, ?);";

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
                        resultSet.getInt("id_group")
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
            preparedStatement.setInt(6, student.getId());

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
                        resultSet.getInt("id_group"));
            }else{
                logger.debug(id+" not found");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDAOException();
        }
        return student;
    }

}
