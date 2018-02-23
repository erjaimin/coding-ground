package ca.etsmtl.simulation.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ca.etsmtl.simulation.models.BaseUsine;
import ca.etsmtl.simulation.models.Chemin;
import ca.etsmtl.simulation.models.Courrier;
import ca.etsmtl.simulation.models.Entree;
import ca.etsmtl.simulation.models.Entrepot;
import ca.etsmtl.simulation.models.Icone;
import ca.etsmtl.simulation.models.Sortie;
import ca.etsmtl.simulation.models.Usine;

/**
 * this class is responsible for parsing the input configuration files and extract the metadata from it
 *
 */
public class XMLController {

	private static Map<Integer, BaseUsine> usineMap;
	private static List<Chemin> cheminList;
    private static List<BaseUsine> usineMetadonnees;
    private static List<Courrier> courriers;

    /**
     * constructor
     */
	public XMLController() {

	    usineMetadonnees = new ArrayList<BaseUsine>();
	    usineMap = new HashMap<>();
	    cheminList = new ArrayList<Chemin>();
	    setCourriers(new ArrayList<Courrier>());
    }

	/**
	 * parses the input file
	 * @param fileName to be parsed
	 */
	public void readFile(String fileName) {

		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

            Node rootElement = doc.getFirstChild();
            NodeList nodeList = rootElement.getChildNodes();

            parseMetadata(nodeList);

            parseSimulationData(nodeList);
            
            attachObservers();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * parses the simulation data from configuration file
	 * @param nodeList
	 */
	private void parseSimulationData(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
		    Node node = nodeList.item(i);
		    if (node.getNodeName() == "simulation") {
		        NodeList childNodes = node.getChildNodes();
		        for (int j = 0; j < childNodes.getLength(); j++) {
		            Node childNode = childNodes.item(j);
		            if (childNode.getNodeName() == "usine") {
		            	String nodeId = childNode.getAttributes().getNamedItem("id").getNodeValue();
		            	String nodeType = childNode.getAttributes().getNamedItem("type").getNodeValue();
		            	Integer x = Integer.valueOf(childNode.getAttributes().getNamedItem("x").getNodeValue());
		            	Integer y = Integer.valueOf(childNode.getAttributes().getNamedItem("y").getNodeValue());
		            	if("entrepot".equals(nodeType)) {
		            		usineMap.put(Integer.parseInt(nodeId), new Entrepot(
		                            getUsineMetadonnees(nodeType),
		                            nodeId, x, y));
		            	}else {
		            		usineMap.put(Integer.parseInt(nodeId), new Usine(
		                            getUsineMetadonnees(nodeType),
		                            nodeId, x, y));
		            	}
		           } else if (childNode.getNodeName() == "chemins") {
		                NodeList chemins = childNode.getChildNodes();
		                for (int k = 0; k < chemins.getLength(); k++) {
		                    Node chemin = chemins.item(k);
		                    if (chemin.getNodeName() == "chemin") {
		                        cheminList.add(new Chemin(
		                                chemin.getAttributes().getNamedItem("de").getNodeValue(),
		                                chemin.getAttributes().getNamedItem("vers").getNodeValue()
		                        ));
		                    }
		                }
		            }
		        }
		    }
		}
	}

	/**
	 * parses the metadata from configuration files
	 * @param nodeList
	 */
	private void parseMetadata(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
		    Node node = nodeList.item(i);
		    if (node.getNodeName() == "metadonnees") {
		        NodeList metadonneesNodes = node.getChildNodes();
		        for (int j = 0; j < metadonneesNodes.getLength(); j++) {
		            Node usineNodes = metadonneesNodes.item(j);
		            if (usineNodes.getNodeName() == "usine") {
		                NodeList usineAttributes = usineNodes.getChildNodes();
		                String usineType = usineNodes.getAttributes().getNamedItem("type").getNodeValue();
		                BaseUsine usine = null;
		                if("entrepot".equals(usineType)) {
		                		usine = new Entrepot(usineType);
		                }else {
		                	    usine = new Usine(usineType);
		                }
		                parseUsineProperties(usineAttributes, usine);
		                usineMetadonnees.add(usine);
		            }
		        }
		    }
		}
	}

	/**
	 * parses the usine properties from configuration file
	 * @param usineAttributes
	 * @param usine
	 */
	private void parseUsineProperties(NodeList usineAttributes,
			BaseUsine usine) {
		for (int k = 0; k < usineAttributes.getLength(); k++) {
		    Node usineAttribute = usineAttributes.item(k);
		    switch (usineAttribute.getNodeName()) {
		        case "icones":
		            NodeList iconeList = usineAttribute.getChildNodes();
		            for (int w = 0; w < iconeList.getLength(); w++) {
		                Node iconeItem = iconeList.item(w);
		                if (iconeItem.getNodeName() == "icone") {
		                    Icone icone = new Icone(
		                            iconeItem.getAttributes().getNamedItem("type").getNodeValue(),
		                            iconeItem.getAttributes().getNamedItem("path").getNodeValue()
		                    );
		                    usine.addIcone(icone);
		                }
		            }
		            break;
		        case "sortie":
		            Sortie sortie = new Sortie(usineAttribute.getAttributes().getNamedItem("type").getNodeValue());
		            usine.setSortie(sortie);
		            break;
		        case "entree":
		            Entree entree = new Entree(usineAttribute.getAttributes().getNamedItem("type").getNodeValue());
		            if (usineAttribute.getAttributes().getNamedItem("quantite") != null) {
		                entree.setQuantite(Integer.valueOf(usineAttribute.getAttributes().getNamedItem("quantite").getNodeValue()));
		            }
		            if (usineAttribute.getAttributes().getNamedItem("capacite") != null) {
		                entree.setQuantite(Integer.valueOf(usineAttribute.getAttributes().getNamedItem("capacite").getNodeValue()));
		            }
		            usine.addEntree(entree);
		            break;
		        case "interval-production":
		            ((Usine)usine).setIntervalProduction(
		                    Integer.valueOf(usineAttribute.getChildNodes().item(0).getNodeValue())
		            );
		            break;

		    }
		}
	}

	/**
	 * As part of the observer pattern, adds all usines as observer for Entrepot
	 */
	private void attachObservers() {
		Collection<BaseUsine> baseUsines = usineMap.values();
		Optional<BaseUsine> entrepot = baseUsines.stream().filter(usine -> usine instanceof Entrepot).findFirst();
		if(entrepot.isPresent()) {
			Entrepot baseUsine = (Entrepot) entrepot.get();
			for(BaseUsine usine : baseUsines) {
				if(usine instanceof Usine) {
					baseUsine.addObserver((Usine)usine);
				}
			}
		}
	}

	/**
	 * returns {@link BaseUsine} for the usine type from metadata
	 * @param type
	 * @return
	 */
	public BaseUsine getUsineMetadonnees(String type) {
	    BaseUsine factory = new Usine();
	    for (BaseUsine usine : usineMetadonnees) {
	        if (type.equals(usine.getType())) {
	            factory = usine;
            }
        }
        return factory;
    }

	/**
	 * @return the usineList
	 */
	public static Map<Integer, BaseUsine> getUsineMap() {
		return usineMap;
	}

	/**
	 * @param usineList the usineList to set
	 */
	public static void setUsineMap(Map<Integer, BaseUsine> usineList) {
		XMLController.usineMap = usineList;
	}

	/**
	 * @return the cheminList
	 */
	public static List<Chemin> getCheminList() {
		return cheminList;
	}

	/**
	 * @param cheminList the cheminList to set
	 */
	public static void setCheminList(List<Chemin> cheminList) {
		XMLController.cheminList = cheminList;
	}

	/**
	 * @return the usineMetadonnees
	 */
	public static List<BaseUsine> getUsineMetadonnees() {
		return usineMetadonnees;
	}

	/**
	 * @param usineMetadonnees the usineMetadonnees to set
	 */
	public static void setUsineMetadonnees(List<BaseUsine> usineMetadonnees) {
		XMLController.usineMetadonnees = usineMetadonnees;
	}

	/**
	 * @return the courriers
	 */
	public static List<Courrier> getCourriers() {
		return courriers;
	}

	/**
	 * @param courriers the courriers to set
	 */
	public static void setCourriers(List<Courrier> courriers) {
		XMLController.courriers = courriers;
	}
}
