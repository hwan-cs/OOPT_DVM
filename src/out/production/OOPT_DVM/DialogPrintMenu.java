package dvmProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogPrintMenu extends JDialog {
    int drinkListlength;
    DVM dvm;
    JPanel printMenuPanel;
    JButton[] drinkBtns;
    JButton confirmBtn;
    JTextArea drinkNumText;
    JTextArea drinkCodeText;
    String choiceDrinkCode = "";
    int choiceDrinkNum = 0;

    public DialogPrintMenu(DVM dvm){
        this.dvm = dvm;
        drinkListlength = dvm.getDrinkList().length;
        printMenuPanel = new JPanel();
        drinkBtns = new JButton[this.drinkListlength];
        drinkCodeText = new JTextArea("음료 코드: ");
        drinkNumText = new JTextArea("음료 개수: ");
        confirmBtn = new JButton("확인");

        createButton();
        attachBtnAndText();
        clickListener();
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

    public void createButton(){
        for (int i=0; i<this.drinkListlength; i++){
            drinkBtns[i] = new JButton(dvm.getDrinkList()[i].getDrinkCode()+ "." +dvm.getDrinkList()[i].getName());
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

    public void clickListener(){
        for(int i=0; i < drinkBtns.length; i++){
            int finalI = i;
            drinkBtns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(choiceDrinkCode.equals(dvm.getDrinkList()[finalI].getDrinkCode())){
                        choiceDrinkNum++;
                    }
                    else{
                        choiceDrinkCode = dvm.getDrinkList()[finalI].getDrinkCode();
                        choiceDrinkNum = 1;
                    }
                    drinkCodeText.setText("음료 코드: "+ choiceDrinkCode);
					drinkNumText.setText("음료 개수: "+ choiceDrinkNum);
                }
            });
        }

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
