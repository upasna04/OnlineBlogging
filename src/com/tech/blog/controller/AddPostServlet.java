package com.tech.blog.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tech.blog.dao.PostDao;
import com.tech.blog.entities.Post;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import com.tech.blog.helper.Helper;
import java.io.File;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

/**
 * Servlet implementation class AddPostServlet
 */@MultipartConfig
@WebServlet("/AddPostServlet")
public class AddPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPostServlet() {
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
		   response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out = response.getWriter()) {
	            /* TODO output your page here. You may use following sample code. */

	            int cid = Integer.parseInt(request.getParameter("cid"));
	            String pTitle = request.getParameter("pTitle");
	            String pContent = request.getParameter("pContent");
	            String pCode = request.getParameter("pCode");
	       
//	            getting currentuser id
	            HttpSession session = request.getSession();
	            User user = (User) session.getAttribute("currentUser");

//	            out.println("your post title is " + pTitle);
//	            out.println(part.getSubmittedFileName());
	            Post p = new Post(pTitle, pContent, pCode, null, null, cid, user.getId());
	            PostDao dao = new PostDao(ConnectionProvider.getConnection());
	            if (dao.savePost(p)) {

	                ;
	                out.println("done");
	            } else {
	                out.println("error");
	            }

	        }
	    }

	}


