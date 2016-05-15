package puppynux.wr.gui;

import puppynux.wr.gui.data.PuppyDialogInfo;

/**
 * Created by niamor972 on 10/03/16.
 * Parts of puppynux.wr.gui.
 * >
 */
public interface PuppyDialog {

    PuppyDialogInfo showDialog();
    void setVisible(boolean b);
    //TO-DO handle exception with controller
    void initInfo(int config);// throws Exception;
    void initInfo();
}
