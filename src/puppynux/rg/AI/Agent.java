package puppynux.rg.AI;

import puppynux.gui.data.ConfigDialogInfo;

/**
 * Created by niamor972 on 16/02/16.
 * Parts of puppynux.rg.AI.
 * >
 */
public class Agent extends Consciousness {

    public Agent () {
        super(new ConfigDialogInfo("test", "test", 0.8, 1, 0, 10, 1000));
    }

    public Agent (ConfigDialogInfo info) {
        super(info);
    }

}