package com.controller;

import java.io.IOException;
//import java.io.PrintWriter;


import javax.servlet.ServletException;

import com.common.ApplicationException;
import com.service.RecognitionService;

public class CalculateCommand extends FrontCommand {

	@Override
	public void process() throws ServletException, IOException {
		RecognitionService service = new RecognitionService();
		String contractNumber = request.getParameter("num");
		
		try {
			service.calculateRevenueRecognitions(Integer.parseInt(contractNumber));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		forward("/calculateDoneView.jsp");

	}

}
