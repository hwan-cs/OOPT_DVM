import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JDialog {

	private DVM dvm;
	private int choiceDrinkNum = 0;
	private String choiceDrinkCode = "00";
	private Card card;
	private int haveToPay;
	private String[] calcedDVM; //계산된 최단거리.

	private DialogVerficationCode vcDialog;
	private DialogOption opDialog;
	private DialogClosetDVM dialogClosetDVM;
	private DialogPrintMenu dialogPrintMenu;



	public Controller(DVM dvm) {
		this.dvm = dvm;
		this.vcDialog = new DialogVerficationCode(this.dvm);
		this.opDialog = new DialogOption(this.dvm);
		this.dialogClosetDVM = new DialogClosetDVM(this.dvm);
		this.dialogPrintMenu = new DialogPrintMenu(this.dvm);
		printOption();
	}

	public void printMenu() {
		dialogPrintMenu.setVisible(true);
		JButton printMenuConfirmBtn = dialogPrintMenu.getConfirmBtn();
		printMenuConfirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printClosestDVMInfo();
				choiceDrinkNum = dialogPrintMenu.getChoiceDrinkNum();
				choiceDrinkCode = dialogPrintMenu.getChoiceDrinkCode();
				dialogPrintMenu.setVisible(false);
			}
		});
	}


	public void inpVerificationCode() {
		// TODO implement here
		vcDialog.setVisible(true);
		JButton okButton = vcDialog.getOkButton();
		final String[] verifyCode = {vcDialog.getVerifyCodeField()};

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifyCode[0] = vcDialog.getVerifyCodeField();
//				String verifyCode = jtf.getText();
				boolean flag = dvm.checkVerificationCode(verifyCode[0]);
				System.out.println("입력한 인증코드 : " + verifyCode[0]);
				if(!flag) {
					JOptionPane.showMessageDialog(vcDialog, "인증코드가 다릅니다.","Message", JOptionPane.ERROR_MESSAGE);
					vcDialog.setVerifyCodeField("");
				} else {
					provideDrink();
				}
			}
		});
	}

	public void printClosestDVMInfo() {
		dialogClosetDVM.setVisible(true);
		JButton printClosetDVMInfoConfirmBtn = dialogClosetDVM.getDialogClosetDVMConfirmBtn();
		printClosetDVMInfoConfirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dvm.getCalcDVMInfo()[0].equals("3")){
					//우리 시스템의 재고 파악
					//아직 미구현상태임
					//

				}
				else{
					//다른 DVM의 재고 파악
					//아직 미구현상태임
					//
				}
			}
		});
	}

	public void provideDrink() { // 음료 제공, 음료 제공시 해당 음료를 구매한 개수만큼 기존 재고에서 차감
		// TODO implement here
	}

	public void confirmPayment(DVM dvm) {

	}

	public void selectDrink() {
		// TODO implement here
	}

	public void selectOption() {
		//굳이 필요 할까요 ...?
	}

	public void printSendInfo() {
		// TODO implement here
	}

	public void printOption() {
		opDialog.setVisible(true);
		JButton printMenuBtn = this.opDialog.getPrintMenuBtn();
		JButton verificationCodeInpBtn = this.opDialog.getVerificationCodeInpBtn();

		printMenuBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printMenu();
			}
		});

		verificationCodeInpBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inpVerificationCode();
			}
		});
	}

	public String getDrinkCode() {
		return this.choiceDrinkCode;
	}

	public int getDrinkNum() {
		// TODO implement here
		return this.choiceDrinkNum;
	}

	public void setHaveToPay() {
		// TODO implement here
	}

	public int getHaveToPay() {
		// TODO implement here
		return 0;
	}
}