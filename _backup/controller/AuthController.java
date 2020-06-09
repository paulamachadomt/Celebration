
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
import Model.User;

@WebServlet("/Login")
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SistemaDB SistemaDB = new SistemaDB();

		String uname = request.getParameter("uname");
		String pass = request.getParameter("pass");
		User auth = new User(uname, pass);

		response.setContentType("text/html");

		if (SistemaDB.check(auth)) {

			auth = SistemaDB.getUser();

			List<Schedule> schedules = auth.getSchedules();

			HttpSession session = request.getSession();

			session.setAttribute("user", auth);
			session.setAttribute("schedules", schedules);
			session.setAttribute("pointerSchedule", schedules.get(0));
			session.setAttribute("tasks", schedules.get(0).getTasks());
			session.setAttribute("pointerTask", schedules.get(0).getTasks().get(0));
			session.setAttribute("newSchedule", null);
			session.setAttribute("newTask", null);

			response.sendRedirect("UI.jsp");

		} else {
			response.sendRedirect("index.jsp");
		}
	}
}
