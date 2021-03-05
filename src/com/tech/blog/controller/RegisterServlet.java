package com.tech.blog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tech.blog.dao.Userdao;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class RegisterServlet
 * 
 */
@MultipartConfig
@WebServlet("/RegisterServlet")

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		 response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out = response.getWriter()) {
	            /* TODO output your page here. You may use following sample code. */
	          

//	            fetch all form data
	            String check = request.getParameter("check");
	            if (check == null) {
	                out.println("box not checked");
	            } else {
	                //baki ka data yaha nikalna..
	                String name = request.getParameter("user_name");
	                String email = request.getParameter("user_email");
	                String password = request.getParameter("user_password");
	                String gender = request.getParameter("gender");
	                String about = request.getParameter("about");
	                //create user object and set all data to that object..
	                User user = new User(name, email, password, gender, about);

	                //create a user daao object..
	                Userdao dao = new Userdao(ConnectionProvider.getConnection());
	                if (dao.saveUser(user)) {
//	                save..
	                    out.println("done");
	                } else {
	                    out.println("error");
	                }
	            }
	         
	        }
	    }	
		
		
		
		
		
		
		
		
		
	}


