package com.tech.blog.controller;

import java.io.IOException;
import javax.servlet.annotation.MultipartConfig;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tech.blog.dao.Userdao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LoginsServlet
 */
@MultipartConfig
@WebServlet("/LoginsServlet")
public class LoginsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
//          login 
//            fetch username and password from request
            String userEmail = request.getParameter("email");
            String userPassword = request.getParameter("password");

            Userdao dao = new Userdao(ConnectionProvider.getConnection());

            User u = dao.getUserByEmailAndPassword(userEmail, userPassword);
		
            if (u == null) {
                //login.................
//                error///
              // out.println("Invalid Details..try again");
            	 Message msg = new Message("Invalid Details ! try with another", "error", "alert-danger");
                 HttpSession s = request.getSession();
                 s.setAttribute("msg", msg);

                 response.sendRedirect("login_page.jsp");
            
                
            } else {
		
		
            	 HttpSession s = request.getSession();
                 s.setAttribute("currentUser", u);
                 response.sendRedirect("profile.jsp");

		
	             }

        }
	}
}
	