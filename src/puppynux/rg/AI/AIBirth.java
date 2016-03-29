package puppynux.rg.AI;

import org.apache.log4j.Logger;
import puppynux.lb.env.objects.Empty;
import puppynux.rg.GameEngine;

/**
 * Created by niamor972 on 16/02/16.
 * Parts of puppynux.rg.AI.
 * >
 */
public abstract class AIBirth {

    private final static Logger logger = Logger.getLogger(AIBirth.class);

    public static void generate (Consciousness agent, String place, String subplace, int state) {
        agent.setPlacePosition(place);
        agent.setSubplacePosition(subplace);
        generate(agent, state);
    }

    public static void generate (Consciousness agent, String place, String subplace) {
        agent.setPlacePosition(place);
        agent.setSubplacePosition(subplace);
        generate(agent);
    }

    /**
     * Used to generate randomly an Agent by setting his state
     * @param agent The agent to generate
     */
    public static void generate (Consciousness agent) {
        int state = (int) (Math.random() * 15);
        int [] coor = GameEngine.getCoordinate(state);
        while (GameEngine.getInstance().getEnvironmentManager().getCell(
                agent.getPlacePosition(), agent.getSubplacePosition(),coor[0], coor[1]) instanceof Empty)
        {
            state = (int) (Math.random() * 15);
            coor = GameEngine.getCoordinate(state);
        }
        generate(agent, state);
    }

    /**
     * Used to generate an Agent by setting his state to value
     * @param agent The agent to generate
     * @param value The state to attribute
     */
    public static void generate (Consciousness agent, int value) {
        logger.info("[BIRTH] Agent born on " + value);
        agent.setState(value);
    }

}
