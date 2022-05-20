package dvmProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * 
 */
public class Controller 
{

	private JFrame frame;
	private JLabel title = new JLabel("Distributed Vending Machine-Team 3\n");
	private DVM dvm = new DVM();
	private int selectedNumDrink;
	private String selectedDrinkName;
	private int selectedDrinkID;
	private int selectedDrinkPrice;
	private String closestDVMID;
	private double closestDVMDistance;
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Controller window = new Controller();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public Controller() 
	{
		initialize();
	}

	private void initialize() 
	{
		frame = new JFrame();
		frame.setTitle("객지방 3조 분산자판기");
		frame.setBounds(100, 50, 600, 750);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		selectOption();
	}

	/**
	 * 
	 */
	public int choiceDrinkNum;

	/**
	 * 
	 */
	public String choiceDrinkCode;

	/**
	 * 
	 */
	public Card card;

	/**
	 * 
	 */
	public int haveToPay;

	/**
	 * @return
	 */
	public void printMenu() 
	{
		// Reset panel
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel();
		panel.setBounds(20,100,frame.getWidth(), frame.getHeight());
		frame.getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
		        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20,100,frame.getWidth()-60,frame.getWidth()-100);
		GridLayout layout = new GridLayout(7,4,0,0);
		layout.setVgap(10);
		layout.setHgap(10);
		panel.setLayout(layout);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(title);
	    Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
	    int counter = 1;
	    for(int i = 0;i<5;i++)
	    {
	    	for(int j = 0;j<4;j++)
	    	{
	    		JLabel item = new JLabel("<html>"+Integer.toString(counter)+".\t"+dvm.getDrinkList()[counter-1].getName()+"<br/>"+
	    				dvm.getDrinkList()[counter-1].getPrice()+"원</html>", SwingConstants.CENTER);
	    		
	    		item.setHorizontalTextPosition(SwingConstants.CENTER);
	    		item.setVerticalTextPosition(SwingConstants.BOTTOM);
	    		item.setPreferredSize(new Dimension(100,100));
	    		File f = new File(".");
	    		try {
	    			Image img = new ImageIcon(f.getCanonicalPath()+"/src/dvmProject/image/"+Integer.toString(counter)+".jpg").getImage();
	    			item.setIcon(new ImageIcon(img.getScaledInstance(65, 65, Image.SCALE_DEFAULT)));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		counter++;
	    		item.setFont(new Font("Serif", Font.PLAIN, 12));
	    		item.setPreferredSize(new Dimension(100,100));
	    		item.setForeground(Color.black);
	    		item.setBorder(border);
	    		panel.add(item);
	    	}
	    }
	    JTextField enterDrinkCodeTF = new JTextField();
	    enterDrinkCodeTF.setBounds(45, scrollPane.getY()+scrollPane.getHeight()+10, 425, 50);
	    
	    JLabel enterDrinkCodeLbl = new JLabel("번호:");
	    enterDrinkCodeLbl.setBounds(18, scrollPane.getY()+scrollPane.getHeight()+10, 30, 50);
	    
	    JTextField enterNumDrinkTF = new JTextField();
	    enterNumDrinkTF.setBounds(45, scrollPane.getY()+scrollPane.getHeight()+20+40, 425, 50);
	    
	    JLabel enterNumDrinkLbl = new JLabel("개수: ");
	    enterNumDrinkLbl.setBounds(18, scrollPane.getY()+scrollPane.getHeight()+20+40, 30, 50);
	    
	    JButton okButton = new JButton("확인");
	    okButton.setBounds(475, scrollPane.getY()+scrollPane.getHeight()+10, 90, 100);
	    okButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(enterDrinkCodeTF.getText().matches("^[1-9]\\d*$") && enterNumDrinkTF.getText().matches("^[1-9]\\d*$"))
				{
					if(Integer.parseInt(enterDrinkCodeTF.getText()) < 21)
					{
						for (int i = 0;i<dvm.getDrinkList().length;i++)
						{
							if(dvm.getDrinkList()[i].getDrinkCode().equals(enterDrinkCodeTF.getText()))
							{
								selectedDrinkName = dvm.getDrinkList()[i].getName();
								selectedDrinkPrice = dvm.getDrinkList()[i].getPrice();
							}
						}
						selectedNumDrink = Integer.parseInt(enterNumDrinkTF.getText());
						selectedDrinkID = Integer.parseInt(enterDrinkCodeTF.getText());
						printClosestDVMInfo();
					}
					else
					{						
						JOptionPane.showMessageDialog(null, "음료 코드는 1부터 20까지 입력해주세요");
					}
				}
				else
				{						
					JOptionPane.showMessageDialog(null, "숫자를 입력하세요");
				}
			};
		});
	    frame.getContentPane().add(enterDrinkCodeLbl);
	    frame.getContentPane().add(enterNumDrinkLbl);
	    frame.getContentPane().add(enterDrinkCodeTF);
	    frame.getContentPane().add(enterNumDrinkTF);
	    frame.getContentPane().add(okButton);
	    frame.validate();
		frame.repaint();
	}

	/**
	 * @return
	 */
	public void printClosestDVMInfo() 
	{
		//Reset Panel
		frame.getContentPane().removeAll();
		JPanel panel = new JPanel();
		panel.setBounds(20,100,frame.getWidth(), 250);
		frame.getContentPane().add(panel);
		
		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
		        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20,100,frame.getWidth()-60,250);
		BoxLayout layout = new BoxLayout(panel, BoxLayout.X_AXIS);
		panel.setLayout(layout);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(title);
	    Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
	    
	    //예시 DVM들, 모두 음료가 있다고 가정
	    ArrayList listOfDVM = new ArrayList<String[]>();
	    String[] example1 = new String[]{"VM_04", "34", "11"};
	    String[] example2 = new String[]{"VM_05", "66", "22"};
	    String[] example3 = new String[]{"VM_02", "55", "37"};
	    String[] example4 = new String[]{"VM_01", "71", "28"};
	 
	    listOfDVM.add(example1);
	    listOfDVM.add(example2);
	    listOfDVM.add(example3);
	    listOfDVM.add(example4);
		//예시를 위해 사용한 임의의 DVM배열입니다
	    
	    String[] closestDVM = dvm.calcClosestDVMLoc(listOfDVM);
	    
	    int x = Integer.parseInt(closestDVM[1]);
		int y = Integer.parseInt(closestDVM[2]);

		int xd = (int) Math.pow((dvm.getAddress()[0] - x), 2);
		int yd = (int) Math.pow((dvm.getAddress()[1] - y), 2);
		double d = Math.sqrt(yd+xd);
		
		Formatter formatter = new Formatter();
        formatter.format("%.2f", d);
        
        this.closestDVMID = closestDVM[0];
        this.closestDVMDistance= Double.parseDouble(formatter.toString());
	    JButton closestDVMBtn = new JButton("<html>" + closestDVM[0] + "<br/><br/>Distance: "+formatter.toString()+"m</html>");
	    
	    closestDVMBtn.setFont(new Font("Serif", Font.PLAIN, 24));
		File f = new File(".");
		try {
			Image img = new ImageIcon(f.getCanonicalPath()+"/src/dvmProject/image/vm_image.png").getImage();
			closestDVMBtn.setIcon(new ImageIcon(img.getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		closestDVMBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				selectDrink();
			};
		});
		
	    panel.add(closestDVMBtn);
	    
	    frame.revalidate();
	    frame.repaint();
	}

	/**
	 * @return
	 */
	public void provideDrink() 
	{
		// TODO implement here
	
	}

	/**
	 * @return
	 */
	public void confirmPayment() 
	{

		
	}

	/**
	 * @return
	 */
	public void selectDrink() 
	{
		// TODO implement here
		int input = JOptionPane.showConfirmDialog(null,this.selectedDrinkName+" " +this.selectedNumDrink+"개를 "+this.selectedDrinkPrice*this.selectedNumDrink+"원을 지불하고 구매 합니다.");
		if (input == JOptionPane.OK_OPTION)
		{
			frame.getContentPane().removeAll();
//			JPanel panel = new JPanel();
//			panel.setBounds(20,100,frame.getWidth(), 250);
//			frame.getContentPane().add(panel);
			
			JLabel purchase = new JLabel(this.selectedDrinkName+" " + this.selectedNumDrink+"개, "+this.selectedDrinkPrice*this.selectedNumDrink+"원");
			purchase.setBounds(20,50,300,100);
			frame.getContentPane().add(purchase);
			
			JLabel cardInfo = new JLabel();
			cardInfo.setText(dvm.cardPayment(this.selectedDrinkPrice*this.selectedNumDrink) ? "잔액이 충분합니다" : "잔액이 부족합니다");
			cardInfo.setBounds(frame.getWidth()/2-130, frame.getHeight()/2-200, 300, 200);
			cardInfo.setFont(new Font("Serif", Font.PLAIN, 32));
			//카드 잔액에서 차감
			dvm.getCard().setBalance(dvm.cardPayment(this.selectedDrinkPrice*this.selectedNumDrink) ? this.selectedDrinkPrice*this.selectedNumDrink : 0);
			
			JLabel cardBalance = new JLabel("결제 후 남은 잔액: " + dvm.getCard().getBalance()+ "원");
			cardBalance.setBounds(frame.getWidth()/2 - 120, frame.getHeight()/2-100, 300, 100);
			cardBalance.setFont(new Font("Serif", Font.BOLD, 20));
			
			frame.getContentPane().add(cardInfo);
			frame.getContentPane().add(cardBalance);
//			JLabel cardNumLbl = new JLabel("Card Num");
//			cardNumLbl.setBounds(20,140,100,100);
//			frame.getContentPane().add(cardNumLbl);
			
			//나중에 refactor 아마도
//			JTextField cardNum1 = new JTextField();
//			cardNum1.setBounds(18,200, 120, 40);
//			JTextField cardNum2 = new JTextField();
//			cardNum2.setBounds(148,200, 120, 40);
//			JTextField cardNum3 = new JTextField();
//			cardNum3.setBounds(278,200, 120, 40);
//			JTextField cardNum4 = new JTextField();
//			cardNum4.setBounds(408,200, 120, 40);
//			frame.getContentPane().add(cardNum1);
//			frame.getContentPane().add(cardNum2);
//			frame.getContentPane().add(cardNum3);
//			frame.getContentPane().add(cardNum4);
			
//			JLabel validUntilLbl = new JLabel("Valid Until");
//			validUntilLbl.setBounds(60, 300, 100,100);
//			frame.getContentPane().add(validUntilLbl);
//			
//			JTextField validUntilTF = new JTextField();
//			validUntilTF.setBounds(58, 360, 120,50);
//			frame.getContentPane().add(validUntilTF);
//			
//			JLabel cvcLbl = new JLabel("CVC");
//			cvcLbl.setBounds(220, 300, 100,100);
//			frame.getContentPane().add(cvcLbl);
//			
//			JTextField cvcTF = new JTextField();
//			cvcTF.setBounds(218, 360, 120, 50);
//			frame.getContentPane().add(cvcTF);
//			
//			JLabel pwLbl = new JLabel("PW");
//			pwLbl.setBounds(380, 300, 100,100);
//			frame.getContentPane().add(pwLbl);
//			
//			JTextField pwTF = new JTextField();
//			pwTF.setBounds(378, 360, 120, 50);
//			frame.getContentPane().add(pwTF);
//			
//			
			JButton okButton = new JButton("확인");
			okButton.setBounds(50, 550, frame.getWidth()-100, 50);
			okButton.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					displayVerificationCode();
				};
			});
			frame.getContentPane().add(okButton);
//			
			frame.getContentPane().add(title);
//		    Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
//		    
		    frame.revalidate();
		    frame.repaint();
		}
	}

	/**
	 * @return
	 */
	public void selectOption() 
	{
		// TODO implement here
		frame.getContentPane().removeAll();
		
		title.setBounds(20, 20, 350, 60);
		title.setFont(new Font("Serif", Font.BOLD, 14));
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBackground(Color.black);
		title.setForeground(new Color(126, 171, 85));
		title.setOpaque(true);
		frame.getContentPane().add(title);
		
		JButton enterVerificationCodeButton = new JButton("Enter Verification Code");
		enterVerificationCodeButton.setBounds(frame.getWidth()/2-150,frame.getHeight()/2-150, 300, 100);
		enterVerificationCodeButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				enterVerificationCode();
			};
		});
		frame.getContentPane().add(enterVerificationCodeButton);

		JButton printMenuButton = new JButton("Print Menu");
		printMenuButton.setBounds(frame.getWidth()/2-150,frame.getHeight()/2-50, 300, 100);

		printMenuButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				printMenu();
			}
		});

		frame.getContentPane().add(printMenuButton);
		frame.revalidate();
		frame.repaint();
	}

	/**
	 * @return
	 */
	public void printSendInfo() 
	{
		// TODO implement here
	
	}

	/**
	 * @return
	 */
	public void printOption() 
	{
		// TODO implement here
	
	}

	/**
	 * @return
	 */
	public String inpVerificationCode() 
	{
		// TODO implement here
		String[] letters = new String[] {"1", "2", "3", "4", "5","6","7","8","9","a","b","c","d","e","f","g","h","i",
				"j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		
		String result = "";
		Random generator = new Random();
		for(int i = 0;i<10;i++)
			result += letters[generator.nextInt(letters.length)];
		
		return result;
	}
	
	public void displayVerificationCode()
	{
		frame.getContentPane().removeAll();
		
		JLabel yourVerificationCodeLbl = new JLabel("Your verification code is: "+ inpVerificationCode());
		yourVerificationCodeLbl.setBounds(40, frame.getHeight()/2-250, frame.getWidth()-80, 120);
		yourVerificationCodeLbl.setFont(new Font("Serif", Font.BOLD, 20));
		yourVerificationCodeLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
	    yourVerificationCodeLbl.setBorder(border);
	    yourVerificationCodeLbl.setForeground(Color.black);
	    yourVerificationCodeLbl.setBackground(Color.cyan);
	    frame.getContentPane().add(yourVerificationCodeLbl);
	    
	    JLabel retrieveDrinksFromLbl = new JLabel("Retrieve your drink(s) from: ");
	    retrieveDrinksFromLbl.setBounds(40, frame.getHeight()/2-120, frame.getWidth()-80, 120);
	    retrieveDrinksFromLbl.setForeground(Color.black);
	    retrieveDrinksFromLbl.setBackground(Color.cyan);
	    retrieveDrinksFromLbl.setFont(new Font("Serif", Font.BOLD, 20));
	    retrieveDrinksFromLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    retrieveDrinksFromLbl.setBorder(border);
	    frame.getContentPane().add(retrieveDrinksFromLbl);
	    
	    JLabel closestDVMLbl = new JLabel("<html>" + this.closestDVMID + "<br/><br/>Distance: "+this.closestDVMDistance+"m</html>");
	    
	    closestDVMLbl.setFont(new Font("Serif", Font.PLAIN, 24));
		File f = new File(".");
		try {
			Image img = new ImageIcon(f.getCanonicalPath()+"/src/dvmProject/image/vm_image.png").getImage();
			closestDVMLbl.setIcon(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		closestDVMLbl.setBounds(80, frame.getHeight()/2+20, frame.getWidth()-160, 150);
		closestDVMLbl.setBorder(border);
		
		frame.getContentPane().add(closestDVMLbl);
		frame.getContentPane().add(title);
		
		JButton goToMainMenuBtn = new JButton("Go to main menu");
		goToMainMenuBtn.setBounds(40, frame.getHeight()/2 + 200, frame.getWidth()-80, 80);
		goToMainMenuBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				selectOption();
			}
		});
		frame.getContentPane().add(goToMainMenuBtn);
		
		frame.revalidate();
		frame.repaint();
	}
	
	public void enterVerificationCode()
	{
		frame.getContentPane().removeAll();
		
		JTextField enterVerificationCodeTF = new JTextField();
		enterVerificationCodeTF.setBounds(220, 135, 330, 50);
	    frame.getContentPane().add(enterVerificationCodeTF);
		
	    JLabel enterVerificationCodeLbl = new JLabel("Enter your verification code: ");
	    enterVerificationCodeLbl.setBounds(40, 100, 200, 120);
		enterVerificationCodeTF.setHorizontalAlignment(SwingConstants.CENTER);
	    frame.getContentPane().add(enterVerificationCodeLbl);

	    
	    JLabel yourBeverageIsLbl = new JLabel("Your beverage is: ");
	    yourBeverageIsLbl.setBounds(40, frame.getHeight()/2, frame.getWidth()-80, 120);
	    Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
	    yourBeverageIsLbl.setBorder(border);
	    yourBeverageIsLbl.setFont(new Font("Serif", Font.BOLD, 16));
	    yourBeverageIsLbl.setHorizontalAlignment(SwingConstants.CENTER);
	    frame.getContentPane().add(yourBeverageIsLbl);
	    frame.getContentPane().add(title);
	    
	    JButton okButton = new JButton("확인");
	    okButton.setBounds(40, 210, frame.getWidth()-80, 50);
	    okButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//Check validation code
			};
		});
	    frame.getContentPane().add(okButton);
	    
		JButton goToMainMenuBtn = new JButton("Go to main menu");
		goToMainMenuBtn.setBounds(40, frame.getHeight()/2 + 220, frame.getWidth()-80, 50);
		goToMainMenuBtn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				selectOption();
			}
		});
		frame.getContentPane().add(goToMainMenuBtn);
	    frame.revalidate();
	    frame.repaint();
	}
	/**
	 * @return
	 */
	public String getDrinkCode() 
	{
		// TODO implement here
		return "";
	}

	/**
	 * @return
	 */
	public int getDrinkNum() 
	{
		// TODO implement here
		return 0;
	}

	/**
	 * @return
	 */
	public void setHaveToPay() 
	{
		// TODO implement here
		
	}

	/**
	 * @return
	 */
	public int getHaveToPay() 
	{
		// TODO implement here
		return 0;
	}

}