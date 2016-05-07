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

    @Override
    public void use(Consciousness agent) throws ActionException {
        agent.setSubplacePosition(subplace, position);
    }

    @Override
    public String toString() {
        return "change subplace";
    }
}
