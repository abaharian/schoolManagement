package com.baray.tools;

import com.baray.primitive.Role;

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
}
