package puppynux.lb.env;

import puppynux.lb.env.place.Place;

import java.util.HashMap;

/**
 * Created by loic on 07/03/16.
 */
public class EnvironmentData {
    private HashMap<String, Place> environment;
    private HashMap<String, HashMap<String, RMatrix>> envMatrix;

    public EnvironmentData() {
        environment = new HashMap<>();
        envMatrix = new HashMap<>();
    }

    public void load (String path) {
        environment.putAll(EnvFactory.Factory(path));
    }

    public void addMatrixSubplace(String subplaceString, String placeString, RMatrix matrix){
        if(!envMatrix.containsKey(placeString)){
            envMatrix.put(placeString, new HashMap<String, RMatrix>());
        }
        envMatrix.get(placeString).put(subplaceString,matrix);
    }

    public RMatrix getRMatrix(String placeString, String subplaceString){
        return envMatrix.get(placeString).get(subplaceString);
    }

    public HashMap<String, Place> getEnvironment() {
        return environment;
    }
}
