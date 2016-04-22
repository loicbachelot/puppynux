package puppynux.lb.testunit;

import puppynux.lb.env.EnvironmentManager;
import puppynux.lb.env.place.Place;
import puppynux.lb.env.subplaces.Subplace;


/**
 * Created by loic on 06/03/16.
 */
public class EnvState {
    public static void main(String[] args) {
        EnvironmentManager env = new EnvironmentManager();
        env.createEnvironment();
        for (Place place : env.getEnvironment().values()) {
            for (Subplace subplace : place.getSubplaces().values()) {
                System.out.println(subplace.toString());
                System.out.println(env.getRMatrix(place.getName(),subplace.getName()).toString());
            }
        }

    }
}
