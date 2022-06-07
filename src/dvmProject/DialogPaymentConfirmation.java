package dvmProject;

import javax.swing.*;
import java.awt.*;

public class DialogPaymentConfirmation extends JDialog 
{ // 결제 의사 확인 다이얼로그

    private DVM dvm;
    private JPanel payPanel;
    private JLabel titleLabel;
    private JLabel balanceLabel; // 잔액 보려고 넣은 임시 코드
    private JButton okBtn;
    private JButton noBtn;

    public DialogPaymentConfirmation(DVM dvm)
    {
        this.dvm = dvm;
        this.payPanel = new JPanel();
        this.titleLabel = new JLabel("                    결제 하시겠습니까?");
        this.balanceLabel = new JLabel(); // 잔액 보려고 넣은 임시 코드
        this.okBtn = new JButton("예");
        this.noBtn = new JButton("아니오");

		//PMD 빨간색 -> Overridable method called during object construction
		//웬만해선 constructor 안에서 메소드 호출 하지 말아라
        initOther();
        initLayout();
    }

    public void initLayout() 
    {
        this.setTitle("결제 의사 확인 창");
        this.setLayout(new BorderLayout());
        this.setSize(250, 250);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(false);
        add(this.titleLabel, BorderLayout.NORTH);
        add(this.balanceLabel, BorderLayout.CENTER); // 잔액 보려고 넣은 임시 코드
        add(this.payPanel, BorderLayout.SOUTH);
    }

    public void initOther() 
    {
        this.balanceLabel.setText("현재 잔액: " + String.valueOf(this.dvm.getCard().getBalance())); // 잔액 보려고 넣은 임시 코드
        this.payPanel.setLayout(new FlowLayout());
        this.payPanel.setSize(150, 150);
        this.payPanel.add(okBtn);
        this.payPanel.add(noBtn);
    }

    public JLabel getBalanceLabel() 
    {
        return this.balanceLabel;
    }

    public void setBalanceLabel(JLabel balanceLabel) 
    {
        this.balanceLabel = balanceLabel;
    }
    
    public JPanel getPayPanel() 
    {
        return this.payPanel;
    }

    public void setPayPanel(JPanel payPanel) 
    {
        this.payPanel = payPanel;
    }

    public JLabel getTitleLabel() 
    {
        return this.titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) 
    {
        this.titleLabel = titleLabel;
    }

    public JButton getOkBtn() 
    {
        return this.okBtn;
    }

    public void setOkBtn(JButton okBtn) 
    {
        this.okBtn = okBtn;
    }

    public JButton getNoBtn() 
    {
        return this.noBtn;
    }

    public void setNoBtn(JButton noBtn) 
    {
        this.noBtn = noBtn;
    }
}