package Model;

import java.util.List;
import java.util.ArrayList;

public class Schedule {


    private String name;
    private List<Task> tasks;

    public Schedule(String name) {
        this.name = name;
        this.tasks = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void setTaskNewName(String oldName, String NewName) {
        for (Task task : this.tasks) {
            if (oldName.equals(task.getName())) {
                task.setName(NewName);
            }
        }
    }


    // public void setTask(String name, String description) {
    //     tasks.add(new Task(name, description));
    // }

    @Override
    public String toString() {
        return "Schedule [tasks=" + tasks + "]";
    }

}