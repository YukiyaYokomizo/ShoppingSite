//package jp.co.aforce.servlet;
//
//import java.io.IOException;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
///**
// * Servlet implementation class LoginServlet
// */
//@WebServlet("/login.action")
//public class LoginServlet extends HttpServlet {
//		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			LoginAction action=new LoginAction();
//			String pass;
//			try {
//				pass=action.execute(request, response);
//				response.sendRedirect(request.getContextPath()+"/views/"+pass);
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//	}
//
//}
