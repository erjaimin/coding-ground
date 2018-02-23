package ca.etsmtl.simulation.views;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import ca.etsmtl.simulation.controllers.XMLController;
import ca.etsmtl.simulation.models.BaseUsine;
import ca.etsmtl.simulation.models.Chemin;
import ca.etsmtl.simulation.models.Courrier;
import ca.etsmtl.simulation.models.Entrepot;
import ca.etsmtl.simulation.models.Icone;
import ca.etsmtl.simulation.models.Usine;

public class PanneauPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Map<Integer, BaseUsine> usineMap = XMLController.getUsineMap();
		if(usineMap != null) {
			for(Chemin chemin : XMLController.getCheminList()) {
				BaseUsine srcUsine = usineMap.get(Integer.parseInt(chemin.getDe()));
				BaseUsine targetUsine = usineMap.get(Integer.parseInt(chemin.getVers()));
				g.drawLine(srcUsine.getX()+16, srcUsine.getY()+16, targetUsine.getX()+16, targetUsine.getY()+16);
			}
			usineMap.values().stream().forEach(usine -> {
				g.drawImage(getImageIcon(usine), usine.getX(), usine.getY(), null);
			});
			List<Courrier> courriers = XMLController.getCourriers();
			for(Courrier courrier : courriers) {
				g.drawImage(getImageIcon(courrier), courrier.getCurrentX(), courrier.getCurrentY(), null);
			}
		}	
	}

	/**
	 * loads the image of courrier
	 * @param courrier
	 * @return {@link Image} of courrier
	 */
	private Image getImageIcon(Courrier courrier) {
		ImageIcon icon = new ImageIcon("src/ressources/"+courrier.getType()+".png");
		return icon.getImage();
	}

	/**
	 * calculate the status based on elapsed time
	 * @param baseUsine
	 * @return {@link Image}
	 */
	private Image getImageIcon(BaseUsine baseUsine) {
		int status = 0;
		if(baseUsine instanceof Usine) {
			Usine usine = (Usine) baseUsine;
			status = (100 *usine.getStartTime()) / usine.getIntervalProduction();
		}else {
			Entrepot entrepot = (Entrepot) baseUsine;
			status = (100 * entrepot.getCurrEntree().getQuantite()) / entrepot.getEntree().get(0).getQuantite();
		}
		return getStatusIcon(baseUsine.getIcones(), status);
	}

	/**
	 * loads the factory/warehouse image based on the production status
	 * @param icones
	 * @param status
	 * @return {@link Image}
	 */
	private Image getStatusIcon(List<Icone> icones, int status) {
		String type = (status > 0 && status <= 33) ? "un-tier" : ((status > 33 && status <= 66) ? "deux-tiers" : ((status > 66 && status <= 100) ? "plein" : "vide"));
		for(Icone icon : icones) {
			if(icon.getType().equals(type)) {
				return new ImageIcon(icon.getPath()).getImage();
			}
		}
		return null;
	}
	

}