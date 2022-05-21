import javax.swing.*;
import java.awt.*;

// 인증코드 다이얼로그 창 클래스
public class DialogVerficationCode extends JDialog {
    private DVM dvm;
    private JPanel verifyPanel;
    private JButton okButton;
    private JLabel titleLabel;
    private JTextField jtf;

    public DialogVerficationCode(DVM dvm) {
        this.dvm = dvm;
        this.verifyPanel = new JPanel();
        initLayout();
    }
    public void initLayout() {
//        verifyPanel = new JPanel();
        titleLabel = new JLabel("인증코드 입력: ");
        okButton = new JButton("확인");
        jtf = new JTextField(10);

        jtf.setBounds(150, 100, 100, 20);
        jtf.setForeground(Color.BLACK);

        titleLabel.setSize(80, 80);
        titleLabel.setLocation(70, 70);

        verifyPanel.setLayout(null);
        verifyPanel.setSize(150, 150);
        verifyPanel.add(titleLabel, BorderLayout.WEST);
        verifyPanel.add(jtf, BorderLayout.EAST);

        add(verifyPanel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }

    public String getVerifyCodeField() {
        return this.jtf.getText().toString();
    }

    public void setVerifyCodeField(String text) {
        this.jtf.setText(text);
    }

    public JButton getOkButton() {
        return this.okButton;
    }

    public JPanel getVerifyPanel() {
        return this.verifyPanel;
    }

}
