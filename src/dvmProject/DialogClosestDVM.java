package dvmProject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
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
        setSize(500, 500);
        this.dialogClosetDVMPanel = new JPanel();
        formatter = new Formatter();
        formatter.format("%s", this.calculatedDVMInfo[1] + this.calculatedDVMInfo[2]);
        this.dialogClosetDVMConfirmBtn = new JButton("<html>" + "DVM " + this.calculatedDVMInfo[0] + "<br/><br/>X:" + String.valueOf(this.calculatedDVMInfo[1]) + ", Y:" + String.valueOf(this.calculatedDVMInfo[2]) + "</html>");
        dialogClosetDVMConfirmBtn.setBounds(40, 150, getWidth()-90, 150); // 버튼의 크기, 위치 설정
        dialogClosetDVMConfirmBtn.setFont(new Font("Serif", Font.PLAIN, 24)); // 버튼의 폰트 설정

		File f = new File(".");
		try 
		{
			Image img = new ImageIcon(f.getCanonicalPath()+"/src/dvmProject/image/vm_image.png").getImage();
			dialogClosetDVMConfirmBtn.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dialogClosetDVMConfirmBtn.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().setLayout(null);
        
        attach();
        refresh();
    }
    
    private void attach()
    {
        getContentPane().add(dialogClosetDVMConfirmBtn);
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