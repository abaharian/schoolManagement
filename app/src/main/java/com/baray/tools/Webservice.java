package com.baray.tools;

import com.baray.primitive.Course;
import com.baray.primitive.Role;
import com.baray.primitive.Student;

import java.util.ArrayList;

/**
 * Created by Akram on 3/8/2017.
 */
public class Webservice {
    private class loginEntry{
        String username;
        String password;
        Role role;

        public loginEntry(String username, String password, Role role){
            this.username = username;
            this.password = password;
            this.role = role;
        }
    }

    private loginEntry[] users = {
            new loginEntry("student", "123456", Role.STUDENT),
            new loginEntry("teacher", "123456", Role.TEACHER),
            new loginEntry("manager", "123456", Role.MANAGER)
    };

    public Role login(String username, String password){
        for(loginEntry e : users){
            if(e.username.equals(username) && e.password.equals(password))
                return e.role;
        }
        return Role.NONE;
    }

    public ArrayList<Course> getCourses(Student s){
        ArrayList<Course> array = new ArrayList<Course>();
        array.add(new Course("ریاضی 1", "آقای احمدزاده", "95/12/03"));
        array.add(new Course("فیزیک 1", "آقای محمدرضا ورزی", "95/12/07"));
        array.add(new Course("معارف اسلامی", "اقای رستخیز", "95/12/21"));
        array.add(new Course("زیست شناسی", "آقای دکتر پور اریا", "95/12/21"));
        array.add(new Course("جبر و احتمال", "آقای احمدلو", "96/01/14"));
        array.add(new Course("حساب دیفرانسیل و انتگرال", "آقای مهدی نوریان", "96/01/18"));
        array.add(new Course("شیمی آلی", "آقای یحیی نوریان", "96/01/25"));
        array.add(new Course("عربی", "آقای زادسر", "96/02/05"));
        return array;
    }
}
