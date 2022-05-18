package dvmProject;

import javax.swing.*;

public class DialogClosetDVM extends JDialog {
    DVM dvm;
    JTextArea DVMidTextArea;
    JTextArea DVMxLocTextArea;
    JTextArea DVMyLocTextArea;
    JPanel dialogClosetDVMPanel;
    JButton dialogClosetDVMConfirmBtn;
    String[] calculatedDVMInfo;

    public JButton getDialogClosetDVMConfirmBtn() {
        return dialogClosetDVMConfirmBtn;
    }

    public void setDialogClosetDVMConfirmBtn(JButton dialogClosetDVMConfirmBtn) {
        this.dialogClosetDVMConfirmBtn = dialogClosetDVMConfirmBtn;
    }

    public DialogClosetDVM(DVM dvm){
        this.dvm = dvm;
        this.calculatedDVMInfo = dvm.getCalcDVMInfo();
        this.DVMidTextArea = new JTextArea("DVM id:" + this.calculatedDVMInfo[0]);
        this.DVMxLocTextArea = new JTextArea("DVM x:" + this.calculatedDVMInfo[1]);
        this.DVMyLocTextArea = new JTextArea("DVM y:" + this.calculatedDVMInfo[2]);
        this.dialogClosetDVMPanel = new JPanel();
        this.dialogClosetDVMConfirmBtn = new JButton("계속");

        attach();
    }
    private void attach(){
        setSize(300, 400);
        this.dialogClosetDVMPanel.add(this.DVMidTextArea);
        this.dialogClosetDVMPanel.add(this.DVMxLocTextArea);
        this.dialogClosetDVMPanel.add(this.DVMyLocTextArea);
        this.dialogClosetDVMPanel.add(this.dialogClosetDVMConfirmBtn);
        add(this.dialogClosetDVMPanel);
    }
}
