package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.enrtities.Note;
import com.helper.FactoryProvider;

import java.util.*;


public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public SaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			PrintWriter out = response.getWriter();
			
			
			SessionFactory sf = FactoryProvider.getFactory();
			Session s = sf.openSession();
			Transaction t = s.beginTransaction();
			Note note =new Note(title, content, new java.util.Date());
			
			s.save(note);
			
			t.commit();
			s.close();
			
			
			response.sendRedirect("success.jsp");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
