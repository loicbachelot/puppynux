package puppynux.lb.env.place;

import puppynux.lb.env.subplaces.Subplace;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by loic on 07/03/16.
 */
public interface Place extends Serializable {
    HashMap<String, Subplace> getSubplaces();
    String getName();
}
