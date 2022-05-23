
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Formatter;

public class DialogClosetDVM extends JDialog { // 최단거리 DVM 알려주는 다이얼로그

    private DVM dvm;

    private JPanel dialogClosetDVMPanel;
    private JButton dialogClosetDVMConfirmBtn;
    private String[] calculatedDVMInfo;
    private JButton closestDVMBtn;
    private Formatter formatter;
    private Border border = BorderFactory.createLineBorder(Color.GRAY, 1); // 보더 생성



    public JButton getDialogClosetDVMConfirmBtn() {
        return dialogClosetDVMConfirmBtn;
    }

    public void setDialogClosetDVMConfirmBtn(JButton dialogClosetDVMConfirmBtn) {
        this.dialogClosetDVMConfirmBtn = dialogClosetDVMConfirmBtn;
    }

    public DialogClosetDVM(DVM dvm){
        this.dvm = dvm;
        this.calculatedDVMInfo = dvm.getCalcDVMInfo();

        this.dialogClosetDVMPanel = new JPanel();
        formatter = new Formatter();
        formatter.format("%s", this.calculatedDVMInfo[1] + this.calculatedDVMInfo[2]);
        this.dialogClosetDVMConfirmBtn = new JButton("<html>" + this.calculatedDVMInfo[0] + "<br/><br/>X:" + formatter.toString() + "</html>");
        dialogClosetDVMConfirmBtn.setBounds(40, 150, getWidth()-80, 170); // 버튼의 크기, 위치 설정
        dialogClosetDVMConfirmBtn.setFont(new Font("Serif", Font.PLAIN, 24)); // 버튼의 폰트 설정
        attach();
    }
    private void attach(){
        setSize(600, 750);
        this.dialogClosetDVMPanel.add(dialogClosetDVMConfirmBtn);
        add(this.dialogClosetDVMPanel);
    }

    public void refresh() {
        formatter = new Formatter();
        formatter.format("%s", this.calculatedDVMInfo[1] + this.calculatedDVMInfo[2]);
        this.calculatedDVMInfo = dvm.getCalcDVMInfo();
        this.dialogClosetDVMConfirmBtn.setText("<html>" + "DVM" + this.calculatedDVMInfo[0] + "<br/><br/>X:" + String.valueOf(this.calculatedDVMInfo[1]) + ", Y:" + String.valueOf(this.calculatedDVMInfo[1]) + "</html>");
        dialogClosetDVMConfirmBtn.setBounds(40, 150, getWidth()-80, 170); // 버튼의 크기, 위치 설정
        dialogClosetDVMConfirmBtn.setFont(new Font("Serif", Font.PLAIN, 24)); // 버튼의 폰트 설정
    }

}
