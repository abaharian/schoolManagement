package com.baray.primitive;

/**
 * Created by Akram on 4/3/2017.
 */
public class Course {
    private String name;
    private String teacherName;
    private String lastUpdate;

    public Course() {
    }

    public Course(String name, String teacherName, String lastUpdate) {
        this.name = name;
        this.teacherName = teacherName;
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
