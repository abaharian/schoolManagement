package com.baray.primitive;

import android.graphics.Bitmap;

/**
 * Created by Akram on 3/10/2017.
 */
public class Student {
    private String nationalCode;
    private String firstName;
    private String lastName;
    private String grade;
    private String classNo;
    private String photoPath;

    public Student() {
    }

    public Student(String nationalCode, String firstName, String lastName, String grade) {
        this.nationalCode = nationalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public Student(String nationalCode, String firstName, String lastName, String grade, String photoPath) {
        this.nationalCode = nationalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
        this.photoPath = photoPath;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    public String getClassNo() {
        return classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }
}
