package daa.a2017.user29.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daa.a2017.user29.modele.DbConnection;
import daa.a2017.user29.modele.Voiture;

/**
 * Servlet implementation class AjoutVoitureServlet
 */
public class AjoutVoitureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjoutVoitureServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ajoutVoiture.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = getServletContext().getInitParameter(DbConnection.DB_USER);
		String password = getServletContext().getInitParameter(DbConnection.DB_PASSWORD);
		String connectionUrl = getServletContext().getInitParameter(DbConnection.DB_URL);
		Voiture voiture = new Voiture(connectionUrl, username, password);
		try{
			voiture.setMarque(request.getParameter("marque"));
			voiture.setModèle(request.getParameter("modele"));
			voiture.setAnnée(Integer.parseInt(request.getParameter("annee")));
			voiture.setNbPorte(Integer.parseInt(request.getParameter("nbPorte")));
			// verify the car details
			if(!voiture.estvalide()){
				request.setAttribute("errorMsg", "Veuillez entrer des entrées valides.");
				request.getRequestDispatcher("ajoutVoiture.jsp").forward(request, response);
				return;
			}
			voiture.enregistrerVoiture();
			response.sendRedirect(request.getContextPath()+ "/Inventaire");
		}catch(NumberFormatException ex){
			request.setAttribute("errorMsg", "Veuillez entrer des entrées valides.");
			request.getRequestDispatcher("ajoutVoiture.jsp").forward(request, response);
		}	
	}
}
