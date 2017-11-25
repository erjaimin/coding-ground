package com.invoices.mvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class InvoicesController
 */
@MultipartConfig
public class InvoicesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InvoiceDao invoiceDao;
	private BufferedReader bufferedReader;
	private String line = "";
	private int noOfInvoices;

	/**
	 * Default constructor.
	 */
	public InvoicesController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		invoiceDao = new InvoiceDao();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = null;
		try{
			// Don't display the initial form if invoices are already processed
			if(invoiceDao.getInvoiceMap().isEmpty()){
				if(request.getContentType() != null){
					String noPremiereFacture = request.getParameter("noPremiereFacture");
					String dateFacture = request.getParameter("dateFacture");
					Part filePart = request.getPart("donneesFacturation");
					String fileName = getSubmittedFileName(filePart);
					if (!checkIfBlank(noPremiereFacture) && !checkIfBlank(dateFacture)
							&& !checkIfBlank(fileName) && invoiceDao.getInvoiceMap().isEmpty()) {
						invoiceDao.setNuméroPremièreFacture(Long.parseLong(noPremiereFacture));
						invoiceDao.setDateFacturation(dateFacture);
						processFile(filePart.getInputStream());
						request.setAttribute("invoiceMap", invoiceDao.getInvoiceMap());
						rd = request.getRequestDispatcher("invoicesList.jsp");
					}else{
						request.setAttribute("errorMessage", "Tous les champs sont obligatoires.");
						rd = request.getRequestDispatcher("index.jsp");
					}
				}else{
					rd = request.getRequestDispatcher("index.jsp");
				}
			}else{
				request.setAttribute("invoiceMap", invoiceDao.getInvoiceMap());
				rd = request.getRequestDispatcher("invoicesList.jsp");
			}
		} catch (NumberFormatException e) {
			request.setAttribute("errorMessage", "Veuillez entrer un numéro de facture valide.");
			rd = request.getRequestDispatcher("index.jsp");
		}	
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * returns true if the given value is null or empty
	 * @param value
	 * @return
	 */
	private boolean checkIfBlank(String value) {
		return value == null || value.isEmpty();
	}

	/**
	 * Reads the input stream and parses the invoice item
	 * @param inputStream
	 */
	private void processFile(InputStream inputStream) {
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = bufferedReader.readLine()) != null) {
				// use comma as separator
				String[] invoiceFields = line.split(InvoiceUtils.FILE_DELIMETER);
				// skip the header row
				if (noOfInvoices != 0 && invoiceFields.length == 15) {
					Address factAddress = new Address(invoiceFields[0], invoiceFields[1], invoiceFields[2],
							invoiceFields[3], invoiceFields[4], invoiceFields[5]);
					Address livrAddress = new Address(invoiceFields[6], invoiceFields[7], invoiceFields[8],
							invoiceFields[9], invoiceFields[10], invoiceFields[11]);
					invoiceDao.addInvoiceItem(new InvoiceItem(factAddress, livrAddress, invoiceFields[12],
							invoiceFields[13], invoiceFields[14]));
				}
				++noOfInvoices;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the uploaded file name from multipart header
	 * @param filePart
	 * @return filename
	 */
	private static String getSubmittedFileName(Part filePart) {
		for (String content : filePart.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
				return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
			}
		}
		return null;
	}
}
