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

//	TextField drinkCodeTf;	//음료 코드 텍스트 창
//	TextField drinkNumTf;	//음료 개수 텍스트 창

	JButton[] drinkBtns;	//printMenuBtn
	JButton confirmBtn;

	public Controller(DVM dvm) {
		this.dvm = dvm;
		this.vcDialog = new DialogVerficationCode(this.dvm);
		this.opDialog = new DialogOption(this.dvm);
		createDrinkMenuBtn(this.dvm);	//create btn
//		initLayout();
		printOption();

//		createTextField();
	}
	public void initLayout() {
		setSize(500, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE); // x 버튼을 눌렀을때 종료
	}
//	private void createTextField(){
//		this.drinkCodeTf = new TextField("음료 코드: "+ this.choiceDrinkCode);
//		this.drinkNumTf = new TextField("음료 개수: "+ this.choiceDrinkNum);
//		this.drinkCodeTf.setSize(100 ,10);
//		this.drinkNumTf.setSize(100, 10);
//	}
	public void printMenu() {
		// 메뉴는 그리드 레이아웃 프레임
		// 선택한 것 알려주는 jlabel, 확인 버튼은 flowlayout?
		System.out.println("flag_printMenu()");

		JFrame printMenuFrame = new JFrame("메뉴선택");	//메뉴 선택창 객체 생성
		printMenuFrame.setLayout(new BorderLayout());
		printMenuFrame.setSize(700, 700);


		JPanel printMenuPanel = new JPanel();
		printMenuPanel.setLayout(new GridLayout(4, 5)); // 한 행에 5개씩, 총 4행

		for (JButton jButton: this.drinkBtns){
			printMenuPanel.add(jButton);	//버튼들을 패널에 추가한다.
		}

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());

		JLabel select1 = new JLabel("선택한 음료:");
		JLabel selecDrinkCode = new JLabel("00");
		JLabel select2 = new JLabel("개수:");
		JLabel selecDrinkNum = new JLabel("0");
		JButton confirmBtn = new JButton("확인");

		bottomPanel.add(select1);
		bottomPanel.add(selecDrinkCode);
		bottomPanel.add(select2);
		bottomPanel.add(selecDrinkNum);
		bottomPanel.add(confirmBtn);
		printMenuFrame.add(printMenuPanel, BorderLayout.NORTH);
		printMenuFrame.add(bottomPanel, BorderLayout.SOUTH);
		printMenuFrame.setSize(700, 700);
		printMenuFrame.setLocationRelativeTo(null);
		printMenuFrame.setVisible(true);
		printMenuFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// 메뉴에 있는 음료버튼 눌렀을 때 버튼 리스너
		for(int i = 0; i < drinkBtns.length; i++) {
			int currentIndex = i;
			drinkBtns[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String codeText = drinkBtns[currentIndex].getText().toString();
					if(!selecDrinkCode.getText().equals(codeText.split("\\.")[0])) {
						selecDrinkNum.setText("0");
						selecDrinkCode.setText(codeText.split("\\.")[0].toString());
					} else {
						String numText = selecDrinkNum.getText().toString();
						int numTextToInt = Integer.parseInt(numText);
						++numTextToInt;
						selecDrinkNum.setText(String.valueOf(numTextToInt));
					}
//					String numText = selecDrinkNum.getText().toString();
				}
			});
		}

		confirmBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				dispose();
				testFrame(selecDrinkCode.getText(), Integer.parseInt(selecDrinkNum.getText()));
				selecDrinkCode.setText("00");
				selecDrinkNum.setText("0");
			}
		});

	}
	public void testFrame(String selecDrinkCode, int selecDrinkNum) {
		JFrame frame = new JFrame("Frame for Test");

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		JLabel codeLabel = new JLabel("선택한 음료코드: " + selecDrinkCode);
		codeLabel.setFont(this.getFont().deriveFont(30.0f));

		JLabel numLabel = new JLabel("개수: " + String.valueOf(selecDrinkNum));
		numLabel.setFont(this.getFont().deriveFont(30.0f));

		panel.add(codeLabel);
		panel.add(numLabel);

		frame.add(panel);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	private void createDrinkMenuBtn(DVM dvm){
		this.drinkBtns = new JButton[20];
//		this.confirmBtn = new JButton("확인");
		for (int i=0; i < drinkBtns.length; i++){
			drinkBtns[i] = new JButton(dvm.getDrinkList()[i].drinkCode+ "." +dvm.getDrinkList()[i].getName().toString());
		}
	}

	private void drinkBtnListener(JButton[] jButtons, DVM dvm){
		for(int i=0; i < drinkBtns.length; i++){
			int finalI = i;
			jButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(choiceDrinkCode.equals(dvm.getDrinkList()[finalI].getDrinkCode())){
						choiceDrinkNum++;
					}
					else{
						choiceDrinkCode = dvm.getDrinkList()[finalI].getDrinkCode();
						choiceDrinkNum = 1;
					}
//					drinkCodeTf.setText("음료 코드: "+ choiceDrinkCode);
//					drinkNumTf.setText("음료 개수: "+ choiceDrinkNum);
				}
			});
		}
	}

	private void confirmBtnListener(JFrame jFrame, JButton jButton, DVM dvm){
		jButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//확인 버튼 다음
				//
				//최단거리 계산
				calcedDVM = dvm.getCalcDVMInfo();
				//
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

	public void printClosestDVMInfo(String[] calcedDVM) {
		JFrame printClosestDVMInfoFrame = new JFrame("최단거리 계산된 DVM출력");
		JPanel printClosestDVMInfoPanel = new JPanel();

		JButton calcedDVMBtn = new JButton("continue");
		TextField calcedDVM_id = new TextField("DVM: id: " + calcedDVM[0]);	//계산된 DVMid
		TextField calcedDVM_x = new TextField("x: " + calcedDVM[1]);	//계산된 DVM_x좌표
		TextField calcedDVM_y = new TextField("y: " + calcedDVM[2]);	//계산된 DVM_y좌표

		printClosestDVMInfoPanel.add(calcedDVM_id);
		printClosestDVMInfoPanel.add(calcedDVM_x);
		printClosestDVMInfoPanel.add(calcedDVM_y);
		printClosestDVMInfoPanel.add(calcedDVMBtn);

		printClosestDVMInfoFrame.add(printClosestDVMInfoPanel);


		calcedDVMBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmPayment(dvm);
			}
		});

		//문제점 함수 안에서 함수 호출시 리턴되면서 다꺼져버림,,,,, dialog마다 새로만들 필요성 존재함.
	}



	public void provideDrink() { // 음료 제공, 음료 제공시 해당 음료를 구매한 개수만큼 기존 재고에서 차감
		// TODO implement here
	}

	public void confirmPayment(DVM dvm) {
		//setbalance있어야됨!

		String drinkName = dvm.getDrinkList()[Integer.parseInt(getDrinkCode())+1].getName();
		int drinkNum = this.getDrinkNum();

		TextField noticeTf = new TextField("구매함?" + drinkName + drinkNum);	//notice
		JButton jButton = new JButton("확인");
		JPanel jp2 = new JPanel();
		JFrame jf = new JFrame("결제 의사 확인");

		jp2.add(noticeTf);
		jp2.add(jButton);
		jf.add(jp2);
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