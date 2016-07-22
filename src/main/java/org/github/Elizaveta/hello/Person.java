package org.github.Elizaveta.hello;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private int ID;
    private String sex;
    private String country;
    private String town;
    private String education;
    private String job;
    private Date birthday;
    private int photo_id;

    public Person(String firstName, String lastName, String email, int ID, String sex, String country,
                  String town, String education, String job, Date birthday, int photo_id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.ID = ID;
        this.sex = sex;
        this.country = country;
        this.town = town;
        this.education = education;
        this.job = job;
        this.birthday = birthday;
        this.photo_id = photo_id;
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
        return ID;
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

    public int getPhoto_id() {
        return photo_id;
    }
}
