
package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Schedule;
import Model.User;

@WebServlet("/ScheduleEdit")
public class ScheduleControllerEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Schedule oldScheduleNameObj = (Schedule) session.getAttribute("pointerSchedule");

        String oldScheduleName = oldScheduleNameObj.getName();
        String newScheduleName = request.getParameter("newScheduleName");

        User user = (User) session.getAttribute("user");

        user.setScheduleNewName(oldScheduleName, newScheduleName);

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