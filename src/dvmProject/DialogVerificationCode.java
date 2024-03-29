package dvmProject;

import javax.swing.*;
import java.awt.*;

// 인증코드 다이얼로그 창 클래스
public class DialogVerificationCode extends JDialog 
{
    private DVM dvm;
    private JPanel verifyPanel;
    private JButton okButton;
    private JLabel titleLabel;
    private JTextField verifyCodeField;

    public DialogVerificationCode(DVM dvm) 
    {
        this.dvm = dvm;
        this.verifyPanel = new JPanel();
		//PMD 빨간색 -> Overridable method called during object construction
		//웬만해선 constructor 안에서 메소드 호출 하지 말아라
        initLayout();
    }
    
    public void initLayout() 
    {
//        verifyPanel = new JPanel();
        titleLabel = new JLabel("인증코드 입력: ");
        okButton = new JButton("확인");
        verifyCodeField = new JTextField(10);

        verifyCodeField.setBounds(150, 100, 100, 20);
        verifyCodeField.setForeground(Color.BLACK);

        titleLabel.setSize(80, 80);
        titleLabel.setLocation(70, 70);

        verifyPanel.setLayout(null);
        verifyPanel.setSize(150, 150);
        verifyPanel.add(titleLabel, BorderLayout.WEST);
        verifyPanel.add(verifyCodeField, BorderLayout.EAST);

        add(verifyPanel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);
        okButton.setPreferredSize(new Dimension(50,50));
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }

    public String getVerifyCodeField() 
    {
        return this.verifyCodeField.getText().toString();
    }

    public void setVerifyCodeField(String text) 
    {
        this.verifyCodeField.setText(text);
    }

    public JButton getOkButton() 
    {
        return this.okButton;
    }

    public JPanel getVerifyPanel() 
    {
        return this.verifyPanel;
    }

}