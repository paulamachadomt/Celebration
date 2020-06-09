package Model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String login;
    private String pass;

    private List<Schedule> schedules;

    public User() {

    }

    // create a new user
    public User(String name, String login, String pass) {

        this.name = name;
        this.login = login;
        this.pass = pass;
        this.schedules = new ArrayList<>();
        schedules.add(new Schedule("Compras"));
        schedules.add(new Schedule("Tarefas"));
        schedules.get(0).getTasks().add(new Task("TESTE TASK NAME 1", "TESTE TASK DESCRIPTION 1"));
        schedules.get(0).getTasks().add(new Task("TESTE TASK NAME 2", "TESTE TASK DESCRIPTION 2"));
        schedules.get(1).getTasks().add(new Task("TESTE TASK NAME 1", "TESTE TASK DESCRIPTION 1"));
        schedules.get(1).getTasks().add(new Task("TESTE TASK NAME 2", "TESTE TASK DESCRIPTION 2"));
    }

    // load an user
    public User(String name, String login, String pass, List<Schedule> schedules) {
        this.name = name;
        this.login = login;
        this.pass = pass;
        this.schedules = schedules;
    }

    // auth user
    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Schedule getSchedules(String name) {
        Schedule returnSchedule = null;
        for (Schedule schedule : this.schedules) {
            if (name.equals(schedule.getName())) {
                returnSchedule = schedule;
            }
        }
        return returnSchedule;
    }

    public void setScheduleNewName(String oldName, String NewName) {
        for (Schedule schedule : this.schedules) {
            if (oldName.equals(schedule.getName())) {
                schedule.setName(NewName);
            }
        }
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
    public void setSchedules() {
        this.schedules = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User [login=" + login + ", name=" + name + ", pass=" + pass + ", schedules=" + schedules + "]";
    }

}