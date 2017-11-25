package com.invoices.mvc;

/**
 * This class represents an item to be added in Invoice 
 *
 */
public class InvoiceItem {
	private Address factAdresse;
	private Address livrAdresse;
	private String quantité;
	private String produit;
	private String prixUnitaire;
	private String formattedSubtotal;
	/**
	 * @param factNomClient
	 * @param factAdresse
	 * @param livrNomClient
	 * @param livrAdresse
	 * @param quantité
	 * @param produit
	 * @param prixUnitaire
	 */
	public InvoiceItem(Address factAdresse, Address livrAdresse,
			String quantité, String produit, String prixUnitaire) {
		super();
		this.factAdresse = factAdresse;
		this.livrAdresse = livrAdresse;
		this.quantité = quantité;
		this.produit = produit;
		this.prixUnitaire = prixUnitaire;
	}
	
	/**
	 * @return the factAdresse
	 */
	public Address getFactAdresse() {
		return factAdresse;
	}
	/**
	 * @param factAdresse the factAdresse to set
	 */
	public void setFactAdresse(Address factAdresse) {
		this.factAdresse = factAdresse;
	}
	
	/**
	 * @return the livrAdresse
	 */
	public Address getLivrAdresse() {
		return livrAdresse;
	}
	/**
	 * @param livrAdresse the livrAdresse to set
	 */
	public void setLivrAdresse(Address livrAdresse) {
		this.livrAdresse = livrAdresse;
	}
	/**
	 * @return the quantité
	 */
	public String getQuantité() {
		return quantité;
	}
	/**
	 * @param quantité the quantité to set
	 */
	public void setQuantité(String quantité) {
		this.quantité = quantité;
	}
	/**
	 * @return the produit
	 */
	public String getProduit() {
		return produit;
	}
	/**
	 * @param produit the produit to set
	 */
	public void setProduit(String produit) {
		this.produit = produit;
	}
	/**
	 * @return the prixUnitaire
	 */
	public String getPrixUnitaire() {
		return prixUnitaire;
	}
	/**
	 * @param prixUnitaire the prixUnitaire to set
	 */
	public void setPrixUnitaire(String prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	/**
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return (Double.parseDouble(getQuantité()) * Double.parseDouble(getPrixUnitaire()));
	}
	
	/**
	 * @return the formattedSubtotal
	 */
	public String getFormattedSubtotal() {
		return InvoiceUtils.getFormattedValue(getSubtotal());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvoiceItem [factAdresse=" + factAdresse + ", livrAdresse=" + livrAdresse + ", quantité=" + quantité
				+ ", produit=" + produit + ", prixUnitaire=" + prixUnitaire + "]";
	}
	
	
}
