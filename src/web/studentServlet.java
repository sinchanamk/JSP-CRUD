package web;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Studentdao;
import model.student;




@WebServlet("/")
public class studentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Studentdao studentDAO;

    public void init() {
        studentDAO = new Studentdao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertstudent(request, response);
                    break;
                case "/delete":
                    deletestudent(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updatestudent(request, response);
                    break;
                default:
                    liststudent(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void liststudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < student > liststudent = studentDAO.selectAllstudents();
        request.setAttribute("liststudent", liststudent);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int usn = Integer.parseInt(request.getParameter("usn"));
        student existingstudent = studentDAO.selectstudent(usn);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        request.setAttribute("student", existingstudent);
        dispatcher.forward(request, response);

    }

    private void insertstudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        student newstudent = new student(name, email, country);
        studentDAO.insertstudent(newstudent);
        response.sendRedirect("list");
    }

    private void updatestudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        student book = new student(id, name, email, country);
        studentDAO.updatestudent(book);
        response.sendRedirect("list");
    }

    private void deletestudent(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDAO.deletestudent(id);
        response.sendRedirect("list");

    }
}