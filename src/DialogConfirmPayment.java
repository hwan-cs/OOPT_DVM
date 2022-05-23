import javax.swing.*;
import java.awt.*;

public class DialogConfirmPayment extends JDialog {
    private DVM dvm;
    private JPanel confirmPaymentPanel;
    private JButton yesBtn;
    private JButton noBtn;
    private JTextArea NoticeTextArea;
    private JLabel purchase;
    private JLabel cardInfo;
    private JLabel cardBalance;

    public DialogConfirmPayment(DVM dvm){
        this.dvm = dvm;
        this.yesBtn = new JButton("YES");
        this.noBtn = new JButton("NO");
        this.NoticeTextArea = new JTextArea("");
        this.confirmPaymentPanel = new JPanel();
        this.purchase = new JLabel();
        this.cardInfo = new JLabel();
        this.cardBalance = new JLabel();
    }

    public void settingTextArea(String choiceDrinkName, int choiceNum, int totalPrice){
        this.purchase.setText(choiceDrinkName+" " + choiceNum+"개, "+totalPrice+"원"); // 왼쪽 상단에 무엇을 몇개 구매했고, 가격이 얼마인지 보여줌
        this.purchase.setBounds(20,50,300,100); // purchase의 크기, 위치 설정
        cardInfo.setText(this.dvm.cardPayment(totalPrice) ? "잔액이 충분합니다" : "잔액이 부족합니다"); // 카드 정보에 Text 설정함
        cardInfo.setBounds(0, getHeight()/2-200, getWidth(), 200); // 카드 정보 크기, 위치 설정
        cardInfo.setHorizontalAlignment(SwingConstants.CENTER); // 카드 정보 수평 정렬
        cardInfo.setFont(new Font("Serif", Font.PLAIN, 32)); // 카드 정보 폰트 설정

        dvm.getCard().setBalance(dvm.cardPayment(totalPrice) ? dvm.getCard().getBalance() - totalPrice : 0); //카드 잔액에서 구매한 음료 가격만큼 차감
        this.cardBalance.setText("결제 후 남은 잔액: " + dvm.getCard().getBalance() + "원"); // 카드 잔액 보여주는 라벨
        cardBalance.setBounds(0, getHeight()/2-100, getWidth(), 100); // 카드 잔액 크기, 위치 설정
        cardBalance.setHorizontalAlignment(SwingConstants.CENTER); // 카드 잔액 수평정렬
        cardBalance.setFont(new Font("Serif", Font.BOLD, 20)); // 카드 잔액 폰트 설정

//        NoticeTextArea.setText("Are you sure you want to purchase "+ choiceNum + " " + choiceDrinkName);

        initLayout();
    }

    public JButton getNoBtn() {
        return this.noBtn;
    }

    public JButton getYesBtn() { return this.yesBtn; }

    private void initLayout(){
        add(purchase);
        add(cardInfo);
        add(cardBalance);

        confirmPaymentPanel.add(NoticeTextArea);
        confirmPaymentPanel.add(yesBtn);
        confirmPaymentPanel.add(noBtn);
        add(confirmPaymentPanel);
        setSize(600, 750);
    }
}