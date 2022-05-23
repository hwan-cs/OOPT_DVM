import DVM_Client.DVMClient;
import Model.Message;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// DVMClient에서 msg는 json 타입이다. 제공해준 converter로 json으로 변환 후 인자로 전달
// DVMClient에서 run을 실행하면 msg가 서버로 전송된다. 그 후 Client가 스스로 종료함

public class Controller extends JDialog {

	private DVM dvm;
	private int choiceDrinkNum = 0;
	private String choiceDrinkCode = "00";
	private Card card;
	private int haveToPay;
	private String[] calcedDVM; //계산된 최단거리.

//	private DialogPaymentConfirmation dialogConfirmPayment;
	private DialogVerficationCode dialogVerificationCode;
	private DialogOption dialogPrintOption;
	private DialogClosetDVM dialogClosetDVM;
	private DialogPrintMenu dialogPrintMenu;
	private DialogProvideDrink dialogProvideDrink;
	private DialogConfirmPayment dialogConfirmPayment;
	private JButton printClosetDVMInfoConfirmBtn;

	public Controller(DVM dvm) {
		this.dvm = dvm;
		this.dialogPrintOption = new DialogOption();
		this.dialogPrintMenu = new DialogPrintMenu(this.dvm);
		this.dialogClosetDVM = new DialogClosetDVM(this.dvm);
		this.dialogVerificationCode = new DialogVerficationCode(this.dvm);
		this.dialogConfirmPayment = new DialogConfirmPayment(this.dvm);
		this.dialogProvideDrink = new DialogProvideDrink(this.dvm);
		this.printClosetDVMInfoConfirmBtn = new JButton();
		printOption();
	}

	public void printMenu() {
		dialogPrintMenu.setVisible(true);
		JButton printMenuConfirmBtn = dialogPrintMenu.getConfirmBtn();

		printMenuConfirmBtn.addActionListener(new ActionListener() { // 확인버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				choiceDrinkNum = dialogPrintMenu.getChoiceDrinkNum();
				choiceDrinkCode = dialogPrintMenu.getChoiceDrinkCode();
				dvm.setChoiceDrinkCode(choiceDrinkCode);
				dvm.setChoiceDrinkNum(choiceDrinkNum);
				dvm.createNetwork();

				synchronized (this) {
					try {
						dvm.getNetwork().checkOtherDVMDrinkExists();
						this.wait(500);
						if(dvm.getConfirmedDVMList() != null) {
							ArrayList<Message> tempList = (ArrayList<Message>) (dvm.getConfirmedDVMList().clone());

							dvm.getConfirmedDVMList().clear();

							dvm.getNetwork().checkOtherDVMStock(tempList);
							this.wait(500);
//							dvm.calcClosestDVMLoc(); // 계산헀음
//							dialogClosetDVM.refresh(); // 리프레쉬
//
//							printClosestDVMInfo();
						} else { // null
//							dvm.calcClosestDVMLoc(); // 계산헀음
//							dialogClosetDVM.refresh(); // 리프레쉬
//
//							printClosestDVMInfo();
						}
						dvm.calcClosestDVMLoc(); // 계산헀음
						dialogClosetDVM.refresh(); // 리프레쉬
						dialogPrintMenu.dispose();
						printClosestDVMInfo();
					} catch (Exception ex) {
						ex.printStackTrace();
						System.out.println("error!!!");
					}
				}
//					dialogPrintMenu.setVisible(false);
			}
		});
	}

	public void inpVerificationCode() {
		// TODO implement here
		dialogVerificationCode.setVisible(true);
		JButton okButton = dialogVerificationCode.getOkButton();
		final String[] verifyCode = {dialogVerificationCode.getVerifyCodeField()};

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verifyCode[0] = dialogVerificationCode.getVerifyCodeField();
//				String verifyCode = jtf.getText();
				boolean flag = dvm.checkVerificationCode(verifyCode[0]);
				System.out.println("입력한 인증코드 : " + verifyCode[0]);
				if(!flag) {
					JOptionPane.showMessageDialog(dialogVerificationCode, "인증코드가 다릅니다.","Message", JOptionPane.ERROR_MESSAGE);
					dialogVerificationCode.setVerifyCodeField("");
				} else {
					provideDrink();
				}
			}
		});
	}

	public void printClosestDVMInfo() {
//		if(dvm.getCalcDVMInfo()[0].equals("")) {
//			JOptionPane.showMessageDialog(null, "음료가 있는 DVM이 존재하지 않습니다.");
//		} else {
			this.dialogPrintMenu.setVisible(false);
			this.dialogClosetDVM.setVisible(true);

			int totalPrice = dvm.getCurrentSellDrink().get(this.choiceDrinkCode).getPrice() * this.choiceDrinkNum;
			this.printClosetDVMInfoConfirmBtn = dialogClosetDVM.getDialogClosetDVMConfirmBtn();
			this.printClosetDVMInfoConfirmBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//					int input = JOptionPane.showConfirmDialog(null,choiceDrinkCode+" " +choiceDrinkNum+"개를 "+totalPrice+"원을 지불하고 구매 합니다.");
//					if(input == JOptionPane.OK_OPTION) { // ok 버튼 누르면 다음 플로우로 진행 -> 결제 의사 묻기
						dialogClosetDVM.dispose();
						confirmPayment(choiceDrinkCode, choiceDrinkNum, totalPrice);
//					} else if(input == JOptionPane.NO_OPTION) { // no 버튼 누르면
						dialogClosetDVM.setVisible(false); // 최단거리 dvm 보여주는 창 안보이게 설정
//					}
				}
			});
//		}
	}

	public void provideDrink() { // 음료 제공, 음료 제공시 해당 음료를 구매한 개수만큼 기존 재고에서 차감
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

	public void confirmPayment(String drinkCode, int drinkNum, int totalPrice) {
		JButton yesBtn = dialogConfirmPayment.getYesBtn();
		JButton noBtn = dialogConfirmPayment.getNoBtn();

		String choiceDrinkName = dvm.getDrinkList()[Integer.parseInt(drinkCode)-1].getName();

		dialogConfirmPayment.settingTextArea(choiceDrinkName, drinkNum, totalPrice);
		dialogConfirmPayment.setVisible(true);

		yesBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogConfirmPayment.dispose();
				if (dvm.getCalcDVMInfo()[0].equals("03")){
					//우리 시스템일 때 내부 계산 후
//					dialogConfirmPayment.setVisible(false);
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
				dialogConfirmPayment.dispose();
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
		dialogPrintOption.setVisible(true);
		JButton printMenuBtn = this.dialogPrintOption.getPrintMenuBtn();
		JButton verificationCodeInpBtn = this.dialogPrintOption.getVerificationCodeInpBtn();

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
