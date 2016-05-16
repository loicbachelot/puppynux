package puppynux.rg.AI;

import puppynux.wr.gui.data.ConfigDialogInfo;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by niamor on 5/15/16.
 */
public class AgentLoader implements Serializable {
    private ConfigDialogInfo info;
    private HashMap<String, QMatrix> memory;
    private HashMap<String, Integer> actionMap;

    public AgentLoader (ConfigDialogInfo info, HashMap<String, QMatrix> memory, HashMap<String, Integer> actionMap) {
        this.info = info;
        this.memory = memory;
        this.actionMap = actionMap;
    }

    public ConfigDialogInfo getInfo() {
        return info;
    }

    public HashMap<String, QMatrix> getMemory() {
        return memory;
    }

    public HashMap<String, Integer> getActionMap() {
        return actionMap;
    }
}
