package puppynux.gui;

import puppynux.gui.data.PuppyDialogInfo;

/**
 * Created by niamor972 on 10/03/16.
 * Parts of puppynux.gui.
 * >
 */
public interface PuppyDialog {

    void setSendData(boolean sendData);
    PuppyDialogInfo showDialog();
    void setVisible(boolean b);
    //TO-DO handle exception with controller
    void initInfo();// throws Exception;
}
