package com.smoldyrev.models.pojo;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by bot on 23.02.17.
 */
@Entity
@Table(name = "\"Student\"", schema = "\"Main\"", catalog = "academ")
public class Student {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private Date birthdate;

    @Column
    private String sex;

    @Column
    private int id_group;

    @Column
    private String email;

    public Student(int id, String name, Date birthdate, String sex, int id_group,String email) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.sex = sex;
        this.id_group = id_group;
        this.email = email;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIdGroup() {
        return id_group;
    }

    public void setIdGroup(int id_group) {
        this.id_group = id_group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
