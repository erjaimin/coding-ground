package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontServlet
 */
@WebServlet("/FrontServlet")
public class FrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FrontCommand command;
		command = getCommand(request);
		command.init(getServletContext(),request,response);
		command.process();
	}
	
	private FrontCommand getCommand(HttpServletRequest request) {
		try{
			return (FrontCommand) getCommandClass(request).newInstance();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	private Class<?> getCommandClass(HttpServletRequest request) {
		Class<?> result;
		final String commandClassName = "soen387.webpresentation.frontcontroller."+
		       (String)request.getParameter("command")+ "Command";
		try{
			result = Class.forName(commandClassName);
		}catch(ClassNotFoundException e){
			result = UnknownCommand.class;
		}
		return result;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}


}
