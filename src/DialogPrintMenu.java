
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogPrintMenu extends JDialog {
    private int drinkListlength;
    private int choiceDrinkNum = 0; // 음료 버튼 눌렀을 때 음료 선택 개수
    private String choiceDrinkCode = ""; // 음료 버튼 눌렀을 때 음료 코드
    private DVM dvm;
    private JPanel printMenuPanel;
    private JButton[] drinkBtns;
    private JButton confirmBtn;
    private JTextArea drinkNumText;
    private JTextArea drinkCodeText;
    private JPanel bottomPanel;

    public DialogPrintMenu(DVM dvm){
        this.dvm = dvm;
        this.drinkListlength = dvm.getDrinkList().length;
        this.printMenuPanel = new JPanel(new GridLayout(5, 4));
        this.drinkBtns = new JButton[this.drinkListlength];
        this.confirmBtn = new JButton("확인");
        this.bottomPanel = new JPanel(new FlowLayout());
        this.drinkCodeText = new JTextArea("음료 코드: ");
        this.drinkNumText = new JTextArea("음료 개수: ");

        init();
        createButtonAndText();
        attachBtnAndText();
        clickListener();
    }

    public void init() {
        setTitle("메뉴 선택");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(false);
        setSize(650, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public JButton[] getDrinkBtns() {
        return drinkBtns;
    }

    public void setDrinkBtns(JButton[] drinkBtns) {
        this.drinkBtns = drinkBtns;
    }

    public JTextArea getDrinkNumText() {
        return drinkNumText;
    }

    public void setDrinkNumText(JTextArea drinkNumText) {
        this.drinkNumText = drinkNumText;
    }

    public JTextArea getDrinkCodeText() {
        return drinkCodeText;
    }

    public void setDrinkCodeText(JTextArea drinkCodeText) {
        this.drinkCodeText = drinkCodeText;
    }

    public JButton getConfirmBtn() {
        return confirmBtn;
    }

    public void setConfirmBtn(JButton confirmBtn) {
        this.confirmBtn = confirmBtn;
    }

    public String getChoiceDrinkCode() {
        return choiceDrinkCode;
    }

    public void setChoiceDrinkCode(String choiceDrinkCode) {
        this.choiceDrinkCode = choiceDrinkCode;
    }

    public int getChoiceDrinkNum() {
        return choiceDrinkNum;
    }

    public void setChoiceDrinkNum(int choiceDrinkNum) {
        this.choiceDrinkNum = choiceDrinkNum;
    }

    public void createButtonAndText(){ // 음료 버튼, 텍스트필드 생성
        for (int i = 0; i < this.drinkListlength; i++){
            this.drinkBtns[i] = new JButton(dvm.getDrinkList()[i].getDrinkCode() + "." + dvm.getDrinkList()[i].getName());
        }
    }

    public void attachBtnAndText(){
        for (int i=0; i<this.drinkListlength; i++)
            this.printMenuPanel.add(drinkBtns[i]);

        this.printMenuPanel.add(drinkCodeText);
        this.printMenuPanel.add(drinkNumText);
        this.printMenuPanel.add(confirmBtn);

        add(printMenuPanel);
        setVisible(false);
        setSize(300, 400);
    }

    public void refresh(){
        drinkCodeText.setText("음료 코드: "+ choiceDrinkCode);
        drinkNumText.setText("음료 개수: "+ choiceDrinkNum);
    }

    public void clickListener() {
        for (int i = 0; i < drinkBtns.length; i++) {
            int finalI = i;

            drinkBtns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String dCode = dvm.getDrinkList()[finalI].getDrinkCode(); // 음료코드
                    if (choiceDrinkCode.equals(dCode)) {
                        choiceDrinkNum++;
                    } else {
                        choiceDrinkCode = dCode;
                        choiceDrinkNum = 1;
                    }
                    refresh();
                }
            });
        }

    }

}
