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

    public AgentLoader (ConfigDialogInfo info, HashMap memory) {
        this.info = info;
        this.memory = memory;
    }

    public ConfigDialogInfo getInfo() {
        return info;
    }

    public HashMap<String, QMatrix> getMemory() {
        return memory;
    }
}
