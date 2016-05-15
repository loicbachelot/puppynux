package puppynux.wr.gui.data;

import java.io.Serializable;

/**
 * Created by niamor972 on 10/03/16.
 * Parts of puppynux.wr.gui.
 * Interface for all DialogInfo
 */
public interface PuppyDialogInfo extends Serializable {

    Object getInfo();
    Choices getChoice();
}
