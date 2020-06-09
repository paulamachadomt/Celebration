
package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Schedule;
import Model.Task;
import Model.User;

@WebServlet("/TaskEdit")
public class TaskControllerEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        Schedule schedulePointer = (Schedule) session.getAttribute("pointerSchedule");
        List<Task> tasks = (List<Task>) session.getAttribute("tasks");
        Task oldTaskNameObj = (Task) session.getAttribute("pointerTask");

        String oldTaskName = oldTaskNameObj.getName();
        String newTaskName = request.getParameter("newTaskName");

        for (int i = 0; i < user.getSchedules().size(); i++) {
            if (user.getSchedules().get(i).getName().equals(schedulePointer.getName())) {
                for (int j = 0; j < tasks.size(); j++) {
                    if (oldTaskName.equals(tasks.get(i).getName())) {
                        user.getSchedules().get(i).setTaskNewName(oldTaskName, newTaskName);
                    }
                }
            }
        }

        session.setAttribute("user", user);
        session.setAttribute("schedules", user.getSchedules());
        session.setAttribute("pointerSchedule", user.getSchedules().get(0));
        session.setAttribute("tasks", user.getSchedules().get(0).getTasks());
        session.setAttribute("pointerTasks", user.getSchedules().get(0).getTasks().get(0));
        session.setAttribute("newSchedule", null);
        session.setAttribute("newTask", null);

        response.sendRedirect("index.jsp");
    }
}