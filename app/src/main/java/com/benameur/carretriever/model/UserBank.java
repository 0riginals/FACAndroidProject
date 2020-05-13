package com.benameur.carretriever.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Paul VINOT et Théo TURQUIN
 */
public class UserBank implements Serializable {
    private List<User> userList;

    public UserBank(){
    }

    public UserBank(List<User> u) {
        userList = u;
    }

    public void addUser(User u) {
        userList.add(u);
    }

    public boolean checkPseudo(User u) {
        for(int i = 0; i < userList.size(); i++)  {
            if(u.getPseudo().equals(userList.get(i).getPseudo()))
                return true;
        }
        return false;
    }

    public User findUser(String pseudo) {
        for(int i = 0; i < userList.size(); i++) {
            if(pseudo.equals(userList.get(i).getPseudo())) {
                return userList.get(i);
            }
        }
        return null;
    }

    public void updateUser(User u) {
        for(int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getPseudo().equals(u.getPseudo())) {
                userList.get(i).setHasCar(u.isHasCar());
                userList.get(i).setCar(u.getCar());
            }
        }
    }

    public boolean checkUser(User u) {
        for(int i = 0; i < userList.size(); i++){
            if(u.getPseudo().equals(userList.get(i).getPseudo())  && u.getPassword().equals(userList.get(i).getPassword()))
                return true;
        }
        return false;
    }
}
