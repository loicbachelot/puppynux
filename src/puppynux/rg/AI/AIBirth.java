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

    /**
     * Used to generate an Agent by setting his state to value specifying the Place and Subplace position
     *
     * @param agent An instance of the Agent to generate
     * @param place The Place to born the Agent
     * @param subplace The Subplace to born the Agent
     * @param state The state to attribute
     */
    private static void generate (Consciousness agent, String place, String subplace, int state) {
        agent.setPlacePosition(place, subplace, state);
        logger.info("[BIRTH] Agent born in the " + subplace + " [" + place + "], on cell " + state);
    }

    /**
     * Used to generate randomly an Agent by setting his state
     *
     * @param agent The agent to generate
     */
    public static void generate (Consciousness agent) {
        //TODO make cleaner
        int state = (int) (Math.random() * 15);
        int [] coor = GameEngine.getCoordinate(state);
        while (!(GameEngine.getInstance().getEnvironmentManager().getCell(
                "House", "LivingRoom",coor[0], coor[1]) instanceof Empty))
        {
            state = (int) (Math.random() * 15);
            coor = GameEngine.getCoordinate(state);
        }
        generate(agent, "House", "LivingRoom", state);
    }
}
