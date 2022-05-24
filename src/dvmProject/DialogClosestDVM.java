package dvmProject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Formatter;

public class DialogClosestDVM extends JDialog 
{ // 최단거리 DVM 알려주는 다이얼로그

    private DVM dvm;

    private JPanel dialogClosetDVMPanel;
    private JButton dialogClosetDVMConfirmBtn;
    private String[] calculatedDVMInfo;
    private JButton closestDVMBtn;
    private Formatter formatter;

    public JButton getDialogClosestDVMConfirmBtn() 
    {
        return dialogClosetDVMConfirmBtn;
    }

    public void setDialogClosestDVMConfirmBtn(JButton dialogClosetDVMConfirmBtn) 
    {
        this.dialogClosetDVMConfirmBtn = dialogClosetDVMConfirmBtn;
    }

    public DialogClosestDVM(DVM dvm)
    {
        this.dvm = dvm;
        this.calculatedDVMInfo = dvm.getCalcDVMInfo();
        
        this.dialogClosetDVMPanel = new JPanel();
        formatter = new Formatter();
        formatter.format("%s", this.calculatedDVMInfo[1] + this.calculatedDVMInfo[2]);
        this.dialogClosetDVMConfirmBtn = new JButton("<html>" + "DVM" + this.calculatedDVMInfo[0] + "<br/><br/>X:" + String.valueOf(this.calculatedDVMInfo[1]) + ", Y:" + String.valueOf(this.calculatedDVMInfo[2]) + "</html>");
        dialogClosetDVMConfirmBtn.setBounds(40, 150, getWidth()-90, 150); // 버튼의 크기, 위치 설정
        dialogClosetDVMConfirmBtn.setFont(new Font("Serif", Font.PLAIN, 24)); // 버튼의 폰트 설정
        
        getContentPane().setLayout(null);
        
        attach();
    }
    
    private void attach()
    {
        setSize(500, 500);
        getContentPane().add(dialogClosetDVMConfirmBtn);
//        this.dialogClosetDVMPanel.add(dialogClosetDVMConfirmBtn);
        //add(this.dialogClosetDVMPanel);
    }

    public void refresh() 
    {
        formatter = new Formatter();
        formatter.format("%s", this.calculatedDVMInfo[1] + this.calculatedDVMInfo[2]);
        this.calculatedDVMInfo = dvm.getCalcDVMInfo();
        this.dialogClosetDVMConfirmBtn.setText("<html>" + "DVM" + this.calculatedDVMInfo[0] + "<br/><br/>X:" + String.valueOf(this.calculatedDVMInfo[1]) + ", Y:" + String.valueOf(this.calculatedDVMInfo[2]) + "</html>");
        dialogClosetDVMConfirmBtn.setBounds(40, 150, getWidth()-90, 150); // 버튼의 크기, 위치 설정
        dialogClosetDVMConfirmBtn.setFont(new Font("Serif", Font.PLAIN, 24)); // 버튼의 폰트 설정
    }

}