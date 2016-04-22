package puppynux.lb.env;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import puppynux.lb.env.objects.SubplaceDoor;
import puppynux.lb.env.place.Garden;
import puppynux.lb.env.subplaces.Grass;
import puppynux.lb.env.place.House;
import puppynux.lb.env.place.Place;
import puppynux.lb.env.subplaces.*;
import puppynux.lb.env.objects.Ball;
import puppynux.lb.env.objects.Cell;
import puppynux.lb.env.objects.Table;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class EnvFactory {


    public static HashMap<String, Place> Factory(String path) {
        HashMap<String, Place> environment = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String type;
        String placePath;

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = builder.parse(new File("src/resources/environment/" + path));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element root = document.getDocumentElement();

        NodeList rootNodes = root.getChildNodes();
        int nbRootNodes = rootNodes.getLength();

        for (int i = 0; i < nbRootNodes; i++) {
            if (rootNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element env = (Element) rootNodes.item(i);
                NodeList places = env.getElementsByTagName("place");
                int nbPlaces = places.getLength();
                for (int j = 0; j < nbPlaces; j++) {
                    Element place = (Element) places.item(j);
                    type = place.getAttribute("type");
                    placePath = place.getFirstChild().getNodeValue();
                    environment.put(type, placeFactory(placePath, type));
                }
            }
        }
        return environment;
    }
    public static Place placeFactory(String path, String placeType) {
        HashMap<String, Subplace> subplaces = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String objecttype, subplacetype;
        int x = 0, y = 0;
        //int state = 0;
        Subplace subplace = null;
        Cell cell;

        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = null;
        try {
            document = builder.parse(new File("src/resources/environment/"+path));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element root = document.getDocumentElement();

        NodeList rootNodes = root.getChildNodes();
        int nbRootNodes = rootNodes.getLength();

        for (int i = 0; i < nbRootNodes; i++)
            if (rootNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {

                Element room = (Element) rootNodes.item(i);
                NodeList objects = room.getElementsByTagName("object");
                int nbObjects = objects.getLength();
                subplacetype = room.getAttribute("type");
                subplace = createSubplace(subplacetype, i); //creation de la piece vide
                if (nbRootNodes > 1) {
                    if (i == nbRootNodes - 1) {
                        Cell nextDoor = new SubplaceDoor(0);
                        Cell previousDoor = new SubplaceDoor(i - 1);
                        subplace.setCells(2, 0, previousDoor);
                        subplace.setCells(1, 3, nextDoor);
                    } else if (i == 0) {
                        Cell nextDoor = new SubplaceDoor(i+1);
                        Cell previousDoor = new SubplaceDoor(nbRootNodes - 1);
                        subplace.setCells(2, 0, previousDoor);
                        subplace.setCells(1, 3, nextDoor);
                    } else {
                        Cell nextDoor = new SubplaceDoor(i+1);
                        Cell previousDoor = new SubplaceDoor(i - 1);
                        subplace.setCells(2, 0, previousDoor);
                        subplace.setCells(1, 3, nextDoor);
                    }

                }

                for (int j = 0; j < nbObjects; j++) {
                    Element object = (Element) objects.item(j);
                    objecttype = object.getAttribute("type");
                    NodeList attributes = object.getElementsByTagName("attribute");
                    int nbAttributes = attributes.getLength();

                    for (int k = 0; k < nbAttributes; k++) {
                        Element attribute = (Element) attributes.item(k);
                        switch (attribute.getAttribute("type")) {
                            case "x":
                                x = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                break;
                            case "y":
                                y = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                break;
//                                case "state":
//                                    state = Integer.parseInt(attribute.getTextContent());
//                                    break;
                        }
                    }
                    cell = creatCell(objecttype);
                    subplace.setCells(x, y, cell);//remplissage de la piece
                    x = y = 0;
                }
                subplaces.put(subplacetype, subplace);
            }

        // here to add Place
        switch (placeType) {
            case "House":
                return new House(subplaces);
            case "Garden":
                return new Garden(subplaces);
            default:
                return null;
        }
    }

    public static Cell creatCell(String type) {
        switch (type) {
            case "Table":
                return new Table();
            case "Ball":
                return new Ball();
            default:
                return null;
        }
    }

    public static Subplace createSubplace(String type, int number) {
        //here to add subplace
        switch (type) {
            case "LivingRoom":
                return new LivingRoom(number);
            case "Kitchen":
                return new Kitchen(number);
            case "Road":
                return new Road(number);
            case "Grass":
                return new Grass(number);
            default:
                return null;
        }

    }

}
