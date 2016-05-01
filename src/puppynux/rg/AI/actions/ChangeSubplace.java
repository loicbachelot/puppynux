package puppynux.rg.AI.actions;

import puppynux.rg.AI.Consciousness;

/**
 * Created by loic on 21/04/16.
 */
public class ChangeSubplace implements Action {
    private String subplace1;
    private String subplace2;

    public ChangeSubplace(String subplace1, String subplace2) {
        this.subplace1 = subplace1;
        this.subplace2 = subplace2;
    }

    @Override
    public void use(Consciousness agent) throws ActionException {
        if (agent.getSubplacePosition().equals(subplace1)) {
            agent.setSubplacePosition(subplace2);
        }
        else if (agent.getSubplacePosition().equals(subplace2)) {
            agent.setSubplacePosition(subplace1);
        }
        else throw new ActionException("Agent tried to reach unable door");
    }

    @Override
    public String toString() {
        return "change subplace";
    }
}
