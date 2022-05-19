package dvmProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JDialog {

	private DVM dvm;
	private int choiceDrinkNum = 0;
	private String choiceDrinkCode = "00";
	public Card card;
	public int haveToPay;
	String[] calcedDVM; //계산된 최단거리.


	DialogOption dialogOption;
	DialogPrintMenu dialogPrintMenu;
	DialogClosetDVM dialogClosetDVM;
	DialogVerificationCode dialogVerificationCode;

	public Controller(DVM dvm) {
		this.dvm = dvm;
		this.dialogOption = new DialogOption();
		this.dialogPrintMenu = new DialogPrintMenu(this.dvm);
		this.dialogClosetDVM = new DialogClosetDVM(this.dvm);
		this.dialogVerificationCode = new DialogVerificationCode(this.dvm);
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

	

	public void provideDrink() {
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
		dialogOption.setVisible(true);
		JButton printMenuBtn = this.dialogOption.getPrintMenuBtn();
		JButton inpVerificationCode = this.dialogOption.getVerificationCodeInpBtn();

		printMenuBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				printMenu();
			}
		});

		inpVerificationCode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//인증코드 입력 란
				inpVerificationCode();
			}
		});
	}

	public void inpVerificationCode() {
		dialogVerificationCode.setVisible(true);
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