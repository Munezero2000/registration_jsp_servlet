package servlet;

import dao.StudentDAO;
import models.Student;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/StudentServlet")
public class StudentServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(StudentServlet.class.getName());
    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAO();
        LOGGER.log(Level.INFO, "StudentServlet initialized");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        LOGGER.log(Level.INFO, "Received POST request with parameters: firstName={0}, lastName={1}, email={2}",
                new Object[]{firstName, lastName, email});

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);

        studentDAO.createStudent(student);
        LOGGER.log(Level.INFO, "Student created: {0}", student);

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        req.setAttribute("students", students);

        LOGGER.log(Level.INFO, "Fetched students list: {0}", students);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}
