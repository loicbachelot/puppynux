package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by loic on 03/05/16.
 */
public class ChangePlace extends Move {
    private String subplace = "";
    private String place = "";
    private int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSubplace(String subplace) {
        this.subplace = subplace;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    protected void modifyCoordinate(Consciousness agent) throws ActionException {
        agent.setPlacePosition(place, subplace, position);
    }


    public String getSubplace() {
        return subplace;
    }

    public String getPlace() {
        return place;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int hashCode() {
        int out = position;
        for (int i = 0; i < subplace.length(); i++) {
            out += (int) subplace.charAt(i);
        }
        return out;
    }

    @Override
    public boolean equals(Object action) {
        if(action instanceof ChangePlace) {
            return ((ChangePlace) action).getPlace().equals(place)
                    && ((ChangePlace) action).getSubplace().equals(subplace)
                    && ((ChangePlace) action).getPosition() == position;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "change place";
    }
}
