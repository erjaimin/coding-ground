package daa.a2017.user29.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daa.a2017.user29.modele.DbConnection;
import daa.a2017.user29.modele.Voiture;

/**
 * Servlet implementation class SupprimeVoitureServlet
 */
public class SupprimeVoitureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimeVoitureServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = getServletContext().getInitParameter(DbConnection.DB_USER);
		String password = getServletContext().getInitParameter(DbConnection.DB_PASSWORD);
		String connectionUrl = getServletContext().getInitParameter(DbConnection.DB_URL);
		Voiture voiture = new Voiture(connectionUrl, username, password);
		// delete car with the specified id
		voiture.setId(Integer.parseInt(request.getParameter("id")));
		voiture.supprimeVoiture();
		response.sendRedirect(request.getContextPath()+ "/Inventaire");
	}
}
