package daa.a2017.user29.modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * represents car model
 *
 */
public class Voiture {
	/**
	 * data members
	 */
	private int id;
	private String marque;
	private String modèle;
	private int année;
	private int nbPorte;
	private String connectionUrl;
	private String username;
	private String password;
	private static final String INSERT_QUERY = "INSERT INTO VOITURES (Marque,Modele,Annee,NbPorte)"
										+" VALUES (?, ?, ?, ?)";
	private static final String DELETE_QUERY = "DELETE FROM VOITURES WHERE id=?";
	private static final String SELECT_QUERY = "SELECT * FROM VOITURES WHERE id=?";
	
	private static final String UPDATE_QUERY ="UPDATE VOITURES SET marque=?, modele=?, annee=?, NbPorte=? "+
                                              "WHERE id=?";
	/**
	 * creates an instance of {@link Voiture}
	 */
	public Voiture(){}
	
	/**
	 * creates an instance of {@link Voiture}
	 * @param connectionUrl
	 * @param username
	 * @param password
	 */
	public Voiture(String connectionUrl, String username, String password) {
		this.connectionUrl = connectionUrl;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}
	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}
	/**
	 * @return the modèle
	 */
	public String getModèle() {
		return modèle;
	}
	/**
	 * @param modèle the modèle to set
	 */
	public void setModèle(String modèle) {
		this.modèle = modèle;
	}
	/**
	 * @return the année
	 */
	public int getAnnée() {
		return année;
	}
	/**
	 * @param année the année to set
	 */
	public void setAnnée(int année) {
		this.année = année;
	}
	/**
	 * @return the nbPorte
	 */
	public int getNbPorte() {
		return nbPorte;
	}
	/**
	 * @param nbPorte the nbPorte to set
	 */
	public void setNbPorte(int nbPorte) {
		this.nbPorte = nbPorte;
	}
	
	/**
	 * returns true if valid otherwise false
	 * @return
	 */
	public boolean estvalide(){
		return this.getModèle() != "" && this.getMarque() != "";
	}
	
	/**
	 * saves car details in the database
	 */
	public void enregistrerVoiture(){
		Connection conection = DbConnection.createConection(connectionUrl, username, password);
		try {
			PreparedStatement statement = conection.prepareStatement(INSERT_QUERY);
			statement.setString(1, this.getMarque());
			statement.setString(2, this.getModèle());
			statement.setInt(3, this.getAnnée());
			statement.setInt(4, this.getNbPorte());
			statement.execute();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * reads car details in the database
	 */
	public void obtenirVoiture(){
		Connection conection = DbConnection.createConection(connectionUrl, username, password);
		try {
			PreparedStatement statement = conection.prepareStatement(SELECT_QUERY);
			statement.setInt(1, this.getId());
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				this.setMarque(resultSet.getString("MARQUE"));
				this.setModèle(resultSet.getString("MODELE"));
				this.setAnnée(Integer.parseInt(resultSet.getString("ANNEE")));
				this.setNbPorte(Integer.parseInt(resultSet.getString("NBPORTE")));
			}
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * removes car details in the database
	 */
	public void supprimeVoiture(){
		Connection conection = DbConnection.createConection(connectionUrl, username, password);
		try {
			PreparedStatement statement = conection.prepareStatement(DELETE_QUERY);
			statement.setInt(1, this.getId());
			statement.execute();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * edits car details in the database
	 */
	public void modifierVoiture(){
		Connection conection = DbConnection.createConection(connectionUrl, username, password);
		try {
			PreparedStatement statement = conection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, this.getMarque());
			statement.setString(2, this.getModèle());
			statement.setInt(3, this.getAnnée());
			statement.setInt(4, this.getNbPorte());
			statement.setInt(5, this.getId());
			statement.execute();
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
