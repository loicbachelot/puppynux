package puppynux.lb.env;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import puppynux.lb.env.objects.*;
import puppynux.lb.env.place.Garden;
import puppynux.lb.env.subplaces.Grass;
import puppynux.lb.env.place.House;
import puppynux.lb.env.place.Place;
import puppynux.lb.env.subplaces.*;


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
        int x = 0, y = 0, x1 = 0, y1 = 0;
        String orientation = "";
        String subplacename = "";
        String placename = "";
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
            document = builder.parse(new File("src/resources/environment/" + path));
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


                for (int j = 0; j < nbObjects; j++) {
                    Element object = (Element) objects.item(j);
                    objecttype = object.getAttribute("type");
                    NodeList attributes = object.getElementsByTagName("attribute");
                    int nbAttributes = attributes.getLength();

                    if(objecttype.equals("SubplaceDoor")){
                        for (int k = 0; k < nbAttributes; k++) {
                            Element attribute = (Element) attributes.item(k);
                            switch (attribute.getAttribute("type")) {
                                case "x":
                                    x = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                                case "y":
                                    y = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                                case "x1":
                                    x1 = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                                case "y1":
                                    y1 = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                                case "orientation":
                                    orientation = attribute.getFirstChild().getNodeValue();
                                    break;
                                case "subplace":
                                    subplacename = attribute.getFirstChild().getNodeValue();
                                    break;
                            }
                        }
                        cell = creatSubplaceDoor(orientation, subplacename, x1, y1);
                        subplace.setCells(x, y, cell);//remplissage de la piece
                        x = y = 0;
                    }
                    else if(objecttype.equals("PlaceDoor")){
                        for (int k = 0; k < nbAttributes; k++) {
                            Element attribute = (Element) attributes.item(k);
                            switch (attribute.getAttribute("type")) {
                                case "x":
                                    x = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                                case "y":
                                    y = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                                case "x1":
                                    x1 = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                                case "y1":
                                    y1 = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                                case "orientation":
                                    orientation = attribute.getFirstChild().getNodeValue();
                                    break;
                                case "place":
                                    placename = attribute.getFirstChild().getNodeValue();
                                    break;
                                case "subplace":
                                    subplacename = attribute.getFirstChild().getNodeValue();
                                    break;
                            }
                        }
                        cell = creatPlaceDoor(orientation, placename, subplacename, x1, y1);
                        subplace.setCells(x, y, cell);//remplissage de la piece
                        x = y = 0;
                    }
                    else {
                        for (int k = 0; k < nbAttributes; k++) {
                            Element attribute = (Element) attributes.item(k);
                            switch (attribute.getAttribute("type")) {
                                case "x":
                                    x = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                                case "y":
                                    y = Integer.parseInt(attribute.getFirstChild().getNodeValue());
                                    break;
                            }
                        }
                        cell = creatCell(objecttype);
                        subplace.setCells(x, y, cell);//remplissage de la piece
                        x = y = 0;
                    }
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

    public static Cell creatPlaceDoor(String orientation, String place, String subplace, int x, int y) {
        if (orientation.equals("leftDoor")) {
            return new PlaceLeftDoor(place, subplace, x, y);
        } else {
            return new PlaceRightDoor(place, subplace, x, y);
        }
    }

    public static Cell creatSubplaceDoor(String orientation, String subplace, int x, int y) {
        if (orientation.equals("TopDoor")) {
            return new SubplaceTopDoor(subplace, x, y);
        } else {
            return new SubplaceDownDoor(subplace, x, y);
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
