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
	private DialogPaymentConfirmation dialogPaymentConfirmation;

	public Controller(DVM dvm) {
		this.dvm = dvm;
		this.dialogPrintOption = new DialogOption();
		this.dialogPrintMenu = new DialogPrintMenu(this.dvm);
		this.dialogClosetDVM = new DialogClosetDVM(this.dvm);
		this.dialogVerificationCode = new DialogVerficationCode(this.dvm);
		this.dialogConfirmPayment = new DialogConfirmPayment(this.dvm);
		this.dialogProvideDrink = new DialogProvideDrink(this.dvm);
		this.dialogPaymentConfirmation = new DialogPaymentConfirmation(this.dvm);
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

							dvm.getNetwork().checkOtherDVMStock(tempList); // 재고 존재하는지 다른 DVM에 메세지 보냄
							this.wait(500);
						} else { // null
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

			int totalPrice = 0;

			if(dvm.getCalcDVMInfo()[0].equals("Team3")) {
				totalPrice = dvm.getCurrentSellDrink().get(this.choiceDrinkCode).getPrice() * this.choiceDrinkNum;
			} else {
				totalPrice = dvm.getDrinkList()[Integer.parseInt(choiceDrinkCode) - 1].getPrice() * this.choiceDrinkNum;
			}

			int t = totalPrice;
			JButton printClosetDVMInfoConfirmBtn = dialogClosetDVM.getDialogClosetDVMConfirmBtn();
			printClosetDVMInfoConfirmBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
//					int input = JOptionPane.showConfirmDialog(null,choiceDrinkCode+" " +choiceDrinkNum+"개를 "+totalPrice+"원을 지불하고 구매 합니다.");
//					if(input == JOptionPane.OK_OPTION) { // ok 버튼 누르면 다음 플로우로 진행 -> 결제 의사 묻기
						dialogClosetDVM.dispose();
						confirmPayment(choiceDrinkCode, choiceDrinkNum, t);
//					} else if(input == JOptionPane.NO_OPTION) { // no 버튼 누르면
						dialogClosetDVM.setVisible(false); // 최단거리 dvm 보여주는 창 안보이게 설정
						dialogPaymentConfirmation.setVisible(true);
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
				dialogPaymentConfirmation.dispose();
				dialogProvideDrink.dispose();
			}
		});
	}

	public void confirmPayment(String drinkCode, int drinkNum, int totalPrice) {

//		JButton yesBtn = dialogConfirmPayment.getYesBtn();
//		JButton noBtn = dialogConfirmPayment.getNoBtn();
//
//		String choiceDrinkName = dvm.getDrinkList()[Integer.parseInt(drinkCode)-1].getName();
//
//		dialogConfirmPayment.settingTextArea(choiceDrinkName, drinkNum);
//		dialogConfirmPayment.setVisible(true);
//		dialogPaymentConfirmation = new DialogPaymentConfirmation(this.dvm);
//		dialogPaymentConfirmation.setVisible(true);
		JButton okBtn = dialogPaymentConfirmation.getOkBtn();
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				dialogConfirmPayment.dispose();
				if (dvm.getCalcDVMInfo()[0].equals("Team3")){ //우리 시스템일 때 내부 계산 후
					dialogPaymentConfirmation.dispose();
//					dialogConfirmPayment.setVisible(false);

					provideDrink();
					dvm.getCard().setBalance(dvm.getDrinkList()[Integer.parseInt(drinkCode) - 1].getPrice() * drinkNum);
					dialogPaymentConfirmation.getBalanceLabel().setText("현재 잔액: " + String.valueOf(dvm.getCard().getBalance()));
				} else if(dvm.getCalcDVMInfo()[0].equals(dvm.getConfirmedDVMList().get(0).getSrcId())){
					// 외부 시스템일때 recheckStock
//					ArrayList<Message> infoList = new ArrayList<>();
					ArrayList<Message> tempList = (ArrayList<Message>) (dvm.getConfirmedDVMList().clone());
					dvm.getConfirmedDVMList().clear();
//					Message msg = new Message();
					Message msg = tempList.get(0);

					msg.setSrcId(tempList.get(0).getDstID());
					msg.setDstID(tempList.get(0).getSrcId());
					msg.getMsgDescription().setItemCode(drinkCode);
					msg.getMsgDescription().setItemNum(drinkNum);

//					msg.setMsgDescription(msgDesc);
					tempList.clear();
					tempList.add(msg);
					dvm.getNetwork().checkOtherDVMStock(tempList);

					dialogPaymentConfirmation.dispose();

					// 다이얼로그(인증코드, 좌표 보여주는) 추가해야 됨
					String vCode = dvm.getVerificationCode();
					dvm.getNetwork().sendSoldDrinkInfo(msg.getSrcId(), msg.getDstID(), vCode);
				}
			}
		});

		dialogPaymentConfirmation.getNoBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogPaymentConfirmation.setVisible(false);
				dialogPrintMenu.setChoiceDrinkNum(0);
				dialogPrintMenu.setChoiceDrinkCode("00");
				choiceDrinkCode = "00";
				choiceDrinkNum = 0;
				dvm.setChoiceDrinkCode(choiceDrinkCode);
				dvm.setChoiceDrinkNum(choiceDrinkNum);
				dialogPrintMenu.refresh();
				dialogPaymentConfirmation.dispose();
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
