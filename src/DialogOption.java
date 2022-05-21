import javax.swing.*;

public class DialogOption extends JFrame { // 맨 처음 화면 보여주는 다이얼로그
    private DVM dvm;
    private JButton printMenuBtn;
    private JButton verificationCodeInpBtn;
    private JPanel optionPanel;

    public DialogOption() {
        this.printMenuBtn = new JButton("메뉴 출력");
        this.verificationCodeInpBtn = new JButton("인증 코드 입력");
        this.optionPanel = new JPanel();
        initLayout();
    }
    public void initLayout() {
        optionPanel.add(printMenuBtn);
        optionPanel.add(verificationCodeInpBtn);
        add(optionPanel);
        setSize(500, 500);
        setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public JButton getPrintMenuBtn() {
        return this.printMenuBtn;
    }

    public void setPrintMenuBtn(JButton printMenuBtn) {
        this.printMenuBtn = printMenuBtn;
    }

    public JButton getVerificationCodeInpBtn() {
        return this.verificationCodeInpBtn;
    }

    public void setVerificationCodeInpBtn(JButton verificationCodeInpBtn) {
        this.verificationCodeInpBtn = verificationCodeInpBtn;
    }




}
