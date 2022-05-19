package dvmProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogOption extends JDialog {
    JButton printMenuBtn;
    JButton verificationCodeInpBtn;
    JPanel optionPanel;

    public DialogOption(){
        this.printMenuBtn = new JButton("메뉴 출력");
        this.verificationCodeInpBtn = new JButton("인증 코드 입력");
        this.optionPanel = new JPanel();
        initLayout();
    }
    private void initLayout(){
        optionPanel.add(printMenuBtn);
        optionPanel.add(verificationCodeInpBtn);
        add(optionPanel);
        setSize(300, 400);
        setVisible(false);
    }

    public JButton getPrintMenuBtn() {
        return printMenuBtn;
    }

    public void setPrintMenuBtn(JButton printMenuBtn) {
        this.printMenuBtn = printMenuBtn;
    }

    public JButton getVerificationCodeInpBtn() {
        return verificationCodeInpBtn;
    }

    public void setVerificationCodeInpBtn(JButton verificationCodeInpBtn) {
        this.verificationCodeInpBtn = verificationCodeInpBtn;
    }
}
