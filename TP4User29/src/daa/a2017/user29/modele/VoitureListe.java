package daa.a2017.user29.modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * represents list of car details
 */
public class VoitureListe {

	private static final String QUERY = "SELECT * FROM VOITURES";
	private List<Voiture> voitureListe = new ArrayList<>();
	private String connectionUrl;
	private String username;
	private String password;
	
	
	/**
	 * creates an instance of {@link VoitureListe}
	 * @param connectionUrl
	 * @param username
	 * @param password
	 */
	public VoitureListe(String connectionUrl, String username, String password) {
		this.connectionUrl = connectionUrl;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the voitureListe
	 */
	public List<Voiture> getVoitureListe() {
		return voitureListe;
	}

	/**
	 * @param voitureListe
	 *            the voitureListe to set
	 */
	public void setVoitureListe(List<Voiture> voitureListe) {
		this.voitureListe = voitureListe;
	}

	/**
	 * 
	 * @param voiture
	 */
	public void addCar(Voiture voiture) {
		this.voitureListe.add(voiture);
	}

	/**
	 * Loads all stored cars and its details
	 * @param connectionUrl
	 * @param username
	 * @param password
	 */
	public void loadInventory() {
		Connection conection = DbConnection.createConection(connectionUrl, username, password);
		try {
			Statement statement = conection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY);
			while (resultSet.next()) {
				Voiture voiture = new Voiture();
				voiture.setId(Integer.parseInt(resultSet.getString("ID")));
				voiture.setMarque(resultSet.getString("MARQUE"));
				voiture.setModèle(resultSet.getString("MODELE"));
				voiture.setAnnée(Integer.parseInt(resultSet.getString("ANNEE")));
				voiture.setNbPorte(Integer.parseInt(resultSet.getString("NBPORTE")));
				addCar(voiture);
			}
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
