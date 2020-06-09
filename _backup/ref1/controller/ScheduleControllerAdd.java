
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

@WebServlet("/ScheduleAdd")
public class ScheduleControllerAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        user.getSchedules().add(new Schedule(request.getParameter("newSchedule")));

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