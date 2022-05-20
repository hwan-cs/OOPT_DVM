package dvmProject;

import javax.swing.*;

public class DialogConfirmPayment extends JDialog {
    DVM dvm;
    JPanel confirmPaymentPanel;
    JButton yesBtn;
    JButton noBtn;
    JTextArea NoticeTextArea;

    public DialogConfirmPayment(DVM dvm){
        this.dvm = dvm;
        this.yesBtn = new JButton("YES");
        this.noBtn = new JButton("NO");
        this.NoticeTextArea = new JTextArea("");
        this.confirmPaymentPanel = new JPanel();
    }

    public void settingTextArea(int choiceNum, String choiceDrinkName){
        NoticeTextArea.setText("Are you sure you want to purchase "+ choiceNum + " " + choiceDrinkName);
        initLayout();
    }

    private void initLayout(){
        confirmPaymentPanel.add(NoticeTextArea);
        confirmPaymentPanel.add(yesBtn);
        confirmPaymentPanel.add(noBtn);
        add(confirmPaymentPanel);
        setSize(300, 400);
    }
}
