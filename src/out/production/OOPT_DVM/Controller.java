package dvmProject;

import DVM_Client.DVMClient;

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
	DialogConfirmPayment dialogConfirmPayment;
	DialogProvideDrink dialogProvideDrink;

	public Controller(DVM dvm) {
		this.dvm = dvm;
		this.dialogOption = new DialogOption();
		this.dialogPrintMenu = new DialogPrintMenu(this.dvm);
		this.dialogClosetDVM = new DialogClosetDVM(this.dvm);
		this.dialogVerificationCode = new DialogVerificationCode(this.dvm);
		this.dialogConfirmPayment = new DialogConfirmPayment(this.dvm);
		this.dialogProvideDrink = new DialogProvideDrink(this.dvm);
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
				dvm.setChoiceDrinkCode(choiceDrinkCode);
				dvm.setChoiceDrinkNum(choiceDrinkNum);
				dvm.createNetwork();

				try {
					dvm.network.checkOtherDVMDrinkExists();
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("error!!!");
				}

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
				dialogClosetDVM.setVisible(false);
				confirmPayment();
			}
		});
	}

	

	public void provideDrink() {
		JButton returnBtn = this.dialogProvideDrink.returnBtn;
		dialogProvideDrink.settingTextArea(this.choiceDrinkNum, this.dvm.getDrinkList()[Integer.parseInt(choiceDrinkCode)-1].getName());
		dialogProvideDrink.setVisible(true);
		returnBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogProvideDrink.setVisible(false);
			}
		});
	}

	public void confirmPayment() {
		JButton yesBtn = dialogConfirmPayment.yesBtn;
		JButton noBtn = dialogConfirmPayment.noBtn;

		dialogConfirmPayment.settingTextArea(choiceDrinkNum, dvm.getDrinkList()[Integer.parseInt(choiceDrinkCode)-1].getName());
		dialogConfirmPayment.setVisible(true);

		yesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dvm.getCalcDVMInfo()[0].equals("03")){
					//우리 시스템일 때 내부 계산 후
					dialogConfirmPayment.setVisible(false);
					provideDrink();
				}
				else{
					//외부 시스템일때 recheckStock
				}
			}
		});

		noBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogConfirmPayment.setVisible(false);
				dialogPrintMenu.setChoiceDrinkNum(0);
				dialogPrintMenu.setChoiceDrinkCode("00");
				choiceDrinkCode = "00";
				choiceDrinkNum = 0;
				dvm.setChoiceDrinkCode(choiceDrinkCode);
				dvm.setChoiceDrinkNum(choiceDrinkNum);
				dialogPrintMenu.refresh();
			}
		});
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