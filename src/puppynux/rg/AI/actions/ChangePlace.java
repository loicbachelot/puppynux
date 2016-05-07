package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by loic on 03/05/16.
 */
public class ChangePlace implements Action {
    private String subplace = "";
    private String place = "";
    private int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSubplace(String subplace) {
        this.subplace = subplace;
    }

    public void setPlace(String place){
        this.place = place;
    }

    @Override
    public void use(Consciousness agent) throws ActionException {
        agent.setPlacePosition(place, subplace, position);
    }

    @Override
    public String toString() {
        return "change place";
    }
}
