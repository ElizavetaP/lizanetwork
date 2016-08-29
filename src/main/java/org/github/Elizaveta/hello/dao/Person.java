package org.github.Elizaveta.hello.dao;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private int id;
    private String sex;
    private String country;
    private String town;
    private String education;
    private String job;
    private Date birthday;
    private int photoID;

    public Person(String firstName, String lastName, String email, int id, String sex, String country,
                  String town, String education, String job, Date birthday, int photoID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.sex = sex;
        this.country = country;
        this.town = town;
        this.education = education;
        this.job = job;
        this.birthday = birthday;
        this.photoID = photoID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getID() {
        return id;
    }

    public String getSex() {
        return sex;
    }

    public String getCountry() {
        return country;
    }

    public String getTown() {
        return town;
    }

    public String getEducation() {
        return education;
    }

    public String getJob() {
        return job;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getPhotoID() {
        return photoID;
    }
}
