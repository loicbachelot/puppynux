package puppynux.lb.env.place;

import puppynux.lb.env.subplaces.Subplace;

import java.util.HashMap;


/**
 * Created by loic on 07/03/16.
 */
public class House implements Place {
    private HashMap<String, Subplace> subplaces = new HashMap<>();

    public House(HashMap<String, Subplace> subplaces) {
        this.subplaces = subplaces;
    }

    public HashMap<String, Subplace> getSubplaces() {
        return subplaces;
    }

    public String getName(){
        return "House";
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("house:");
        for (Subplace subplace: subplaces.values()) {
            buffer.append(subplace.toString());
        }
        return buffer.toString();
    }
}
