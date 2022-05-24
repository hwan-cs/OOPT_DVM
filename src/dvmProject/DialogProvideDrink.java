package dvmProject;

import javax.swing.*;

public class DialogProvideDrink extends JDialog 
{
    DVM dvm;
    JLabel noticeLbl;
    JButton returnBtn;
    JPanel provideDrinkPanel;
    
    public DialogProvideDrink(DVM dvm)
    {
        this.dvm = dvm;
        this.noticeLbl = new JLabel();
        this.returnBtn = new JButton("처음 화면으로 돌아가기");
        this.provideDrinkPanel = new JPanel();
    }

    public void settingLbl(int choiceDrinkNum, String drinkName)
    {
        String drinkNumStr = "<html>";
        for (int i=0; i<choiceDrinkNum; i++)
            drinkNumStr += drinkName + " 뿅!<br/>";
            
    	this.noticeLbl.setBounds(20,20, getWidth()-40, 100+30*choiceDrinkNum);
    	this.returnBtn.setBounds(20, getHeight()-100, getWidth()-40, 50);
        this.noticeLbl.setText(drinkNumStr + "나왔어요 ~~~~ </html>");
        initLayout();
    }

    private void initLayout()
    {
    	getContentPane().setLayout(null);
    	getContentPane().add(noticeLbl);
    	getContentPane().add(returnBtn);
//        provideDrinkPanel.add(noticeLbl);
//        provideDrinkPanel.add(returnBtn);
//        add(provideDrinkPanel);
        setSize(300, 400);
        setVisible(true);
    }
}