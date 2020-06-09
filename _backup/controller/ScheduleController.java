
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

@WebServlet("/Schedule")
public class ScheduleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<Schedule> sessionSchedules = (List<Schedule>) session.getAttribute("schedules");

        if (request.getParameter("schedule") instanceof String) {

            String requestNameSchedule = request.getParameter("schedule");

            for (Schedule schedule : sessionSchedules) {

                if (schedule.getName().equals(requestNameSchedule)) {

                    session.setAttribute("pointerSchedule", schedule);
                    session.setAttribute("tasks", schedule.getTasks());
                }
            }
        }
        response.sendRedirect("index.jsp");
    }
}