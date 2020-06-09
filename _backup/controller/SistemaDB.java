package Controller;

import Model.User;

import java.util.ArrayList;
import java.util.List;

// I'm exist to serv like an virtual DB.
public class SistemaDB {

    static List<User> users; //my db

    static User loggedUser; //user logged 

    public SistemaDB() {
        users = new ArrayList<>();
        //mount db
        users.add(new User("Rafael Lapa Valgas","qwe","123"));
        users.add(new User("Pedro","pedreiro","123"));
        users.add(new User("Paula","pmtmachado","123"));
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    protected boolean check(User auth) {
        boolean pass = false;
		for (User userDB : this.users){
            if (auth.getLogin().equals(userDB.getLogin())){
                if (auth.getPass().equals(userDB.getPass())){
                    loggedUser = userDB;
                    pass = true;
                }
            }
        }
		return pass;
    }

    public User getUser(){
        return loggedUser;
    }
    
}