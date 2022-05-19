import javax.swing.*;

public class DialogOption extends JDialog {
    private DVM dvm;
    private JButton printMenuBtn;
    private JButton verificationCodeInpBtn;
    private JPanel optionPanel;

    public DialogOption(DVM dvm) {
        this.dvm = dvm;
        this.printMenuBtn = new JButton("메뉴 출력");
        this.verificationCodeInpBtn = new JButton("인증코드 입력");
        this.optionPanel = new JPanel();
        initLayout();
    }
    public void initLayout() {
        setTitle("This is DVM3");
        setSize(300, 400);
        setVisible(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        optionPanel.add(printMenuBtn);
        optionPanel.add(verificationCodeInpBtn);
        add(optionPanel);

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
