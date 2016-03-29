package puppynux.lb.env;

import org.jdom2.*;
import org.jdom2.output.*;
import puppynux.lb.env.objects.Cell;
import puppynux.lb.env.place.Place;
import puppynux.lb.env.subplaces.Subplace;

import java.io.FileOutputStream;

/**
 * Created by loic on 19/03/16.
 */
public class EnvSave {

    public static void Save(String envName, EnvironmentData env) {
        Element racine = new Element("env");
        Document document = new Document(racine);
        Element places = new Element("places");
        racine.addContent(places);
        for (Place place : env.getEnvironment().values()) {
            Element placeSave = new Element("place");
            Attribute type = new Attribute("type", place.getName());
            placeSave.setAttribute(type);
            placeSave.setText(place.getName() + envName + ".xml");
            places.addContent(placeSave);
            savePlace(place, envName);
        }
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            String path = "src/sauvegardes/environment/" + envName + ".xml";
            sortie.output(document, new FileOutputStream(path));
        } catch (java.io.IOException e) {
        }
    }

    public static void savePlace(Place place, String envName) {
        Element racine = new Element("env");
        Document document = new Document(racine);
        for (Subplace subPlace : place.getSubplaces().values()) {
            Element room = new Element("room");
            Attribute type = new Attribute("type", subPlace.getName());
            room.setAttribute(type);
            Element list = new Element("list");
            Cell[][] cells = subPlace.getCells();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (cells[i][j].getType() != "Empty") {
                        Cell cell = cells[i][j];
                        Element object = new Element("object");
                        Attribute typeObject = new Attribute("type", cell.getType());
                        object.setAttribute(typeObject);
                        //for the x
                        Element attributex = new Element("attribute");
                        Attribute x = new Attribute("type", "x");
                        attributex.setAttribute(x);
                        attributex.setText(String.valueOf(i));
                        object.addContent(attributex);
                        //for the y
                        Element attributey = new Element("attribute");
                        Attribute y = new Attribute("type", "y");
                        attributey.setAttribute(y);
                        attributey.setText(String.valueOf(j));
                        object.addContent(attributey);
                        //add the object in the list
                        list.addContent(object);
                    }
                }

            }
            room.addContent(list);
            racine.addContent(room);
        }

        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            String path = "src/sauvegardes/environment/" + place.getName() + envName + ".xml";
            sortie.output(document, new FileOutputStream(path));
        } catch (java.io.IOException e) {
        }
    }
}
