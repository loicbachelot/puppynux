package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by loic on 21/04/16.
 */
public class ChangeSubplace implements Action {
    private String subplace = "";
    private int position = -1;

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSubplace(String subplace) {
        this.subplace = subplace;
    }

    public String getSubplace() {
        return subplace;
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
        if(action instanceof ChangeSubplace) {
            return ((ChangeSubplace) action).getSubplace().equals(subplace)
                    && ((ChangeSubplace) action).getPosition() == position;
        }
        else{
            return false;
        }
    }

    @Override
    public void use(Consciousness agent) throws ActionException {
        agent.setSubplacePosition(subplace, position);
    }

    @Override
    public String toString() {
        return "change subplace";
    }
}
