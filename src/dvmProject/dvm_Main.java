package dvmProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class dvm_Main 
{
	private JFrame frame;
	private JLabel title = new JLabel("Distributed Vending Machine-Team 3\n");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					dvm_Main window = new dvm_Main();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public dvm_Main() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setTitle("객지방 3조 분산자판기");
		frame.setBounds(100, 50, 600, 750);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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
		frame.getContentPane().add(enterVerificationCodeButton);

		JButton printMenuButton = new JButton("Print Menu");
		printMenuButton.setBounds(frame.getWidth()/2-150,frame.getHeight()/2-50, 300, 100);

		printMenuButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// Reset panel
				frame.getContentPane().removeAll();
				JPanel panel = new JPanel();
				panel.setBounds(20,100,frame.getWidth(), frame.getHeight());
				frame.getContentPane().add(panel);
				
				JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scrollPane.setBounds(20,100,frame.getWidth()-40,frame.getWidth()-120);
				GridLayout layout = new GridLayout(0,4,0,0);
				layout.setVgap(10);
				layout.setHgap(10);
				panel.setLayout(layout);
				frame.getContentPane().add(scrollPane);
				frame.getContentPane().add(title);
			    Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
			    for(int i = 0;i<5;i++)
			    {
			    	for(int j = 0;j<4;j++)
			    	{
			    		JLabel item = new JLabel(Integer.toString((i+1)*(j+1))+".\t ₩1,000", SwingConstants.CENTER);
			    		
			    		item.setHorizontalTextPosition(SwingConstants.CENTER);
			    		item.setVerticalTextPosition(SwingConstants.BOTTOM);
			    		item.setPreferredSize(new Dimension(100,100));
			    		File f = new File(".");
			    		try {
			    			Image img = new ImageIcon(f.getCanonicalPath()+"/src/dvmProject/image/"+Integer.toString((i+1)*(j+1))+".jpg").getImage();
			    			item.setIcon(new ImageIcon(img.getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			    		item.setFont(new Font("Serif", Font.PLAIN, 14));
			    		item.setPreferredSize(new Dimension(100,100));
			    		item.setForeground(Color.black);
			    		item.setBorder(border);
			    		panel.add(item);
			    	}
			    }
			    JTextField enterDrinkCodeTF = new JTextField();
			    enterDrinkCodeTF.setText("번호:");
			    enterDrinkCodeTF.setBounds(18, scrollPane.getY()+scrollPane.getHeight()+20, 450, 50);
			    
			    JTextField enterNumDrinkTF = new JTextField();
			    enterNumDrinkTF.setText("개수:");
			    enterNumDrinkTF.setBounds(18, scrollPane.getY()+scrollPane.getHeight()+20+50, 450, 50);
			    
			    JButton okButton = new JButton("확인");
			    okButton.setBounds(475, scrollPane.getY()+scrollPane.getHeight()+20, 100, 100);
			    
			    frame.getContentPane().add(enterDrinkCodeTF);
			    frame.getContentPane().add(enterNumDrinkTF);
			    frame.getContentPane().add(okButton);
			    frame.validate();
				frame.repaint();
			}
		});
		frame.getContentPane().add(printMenuButton);
		
	}

}
