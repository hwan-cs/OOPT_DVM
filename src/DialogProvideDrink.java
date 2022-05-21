import javax.swing.*;

public class DialogProvideDrink extends JDialog {
    DVM dvm;
    JTextArea noticeTextArea;
    JButton returnBtn;
    JPanel provideDrinkPanel;
    public DialogProvideDrink(DVM dvm){
        this.dvm = dvm;
        this.noticeTextArea = new JTextArea();
        this.returnBtn = new JButton("처음 화면으로 돌아가기");
        this.provideDrinkPanel = new JPanel();
    }

    public void settingTextArea(int choiceDrinkNum, String drinkName){
        String drinkNumStr = "";
        for (int i=0; i<choiceDrinkNum; i++){
            drinkNumStr += drinkName + " 뿅!\n";
        }
        this.noticeTextArea.setText(drinkNumStr + "나왔어요 ~~~~ ");
        initLayout();
    }

    private void initLayout(){
        provideDrinkPanel.add(noticeTextArea);
        provideDrinkPanel.add(returnBtn);
        add(provideDrinkPanel);
        setSize(300, 400);
        setVisible(true);
    }
}
