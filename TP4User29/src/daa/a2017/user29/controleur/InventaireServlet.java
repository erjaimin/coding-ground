package daa.a2017.user29.controleur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daa.a2017.user29.modele.DbConnection;
import daa.a2017.user29.modele.VoitureListe;

/**
 * Servlet implementation class InventaireServlet
 */
public class InventaireServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InventaireServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = getServletContext().getInitParameter(DbConnection.DB_USER);
		String password = getServletContext().getInitParameter(DbConnection.DB_PASSWORD);
		String connectionUrl = getServletContext().getInitParameter(DbConnection.DB_URL);
		// load car details from database
		VoitureListe inventaire = new VoitureListe(connectionUrl, username, password);
		inventaire.loadInventory();
		request.setAttribute("inventaire", inventaire.getVoitureListe());
		request.getRequestDispatcher("inventaire.jsp").forward(request, response);
	}
}
