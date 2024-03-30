package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sist.dao.CustomerDAO;
import com.sist.vo.CustomerVO;

public class LoginFrame2 extends JFrame {
	private JLabel jlb_phone;
	private JTextField jtf_phone;
	private JLabel jlb_password;
	private JPasswordField jpf_password;
	private JButton[] btn_number;
	private JButton btn_delete;
	private JButton btn_confirm;
	private JButton btn_back;

	private String phone;
	private String password;
	
	private int focus =1;

	public LoginFrame2() {
		JPanel p_output = new JPanel();
		JPanel p_input = new JPanel();
		
		Font font2 = new Font("맑은 고딕", Font.BOLD, 40); //서체
		
		
		p_output.setLayout(new GridLayout(4,1));
		p_input.setLayout(new GridLayout(4,3));
		
		jlb_phone = new JLabel("  휴대폰 번호");
		jtf_phone = new JTextField(11);
		jlb_password = new JLabel("  비밀번호");
		jpf_password = new JPasswordField(4);
		
		p_output.add(jlb_phone);
		p_output.add(jtf_phone);
		p_output.add(jlb_password);
		p_output.add(jpf_password);
		
	    jlb_phone.setFont(font2);
	    jtf_phone.setFont(font2);
	    jlb_password.setFont(font2);
	    jpf_password.setFont(font2);
		p_output.setBackground(Color.WHITE);
		p_input.setBackground(Color.WHITE);
		
		
		btn_number = new JButton[10];
		
		
		for(int i=0; i<10;i++) {
			int phone = i;
			btn_number[i] = new JButton(phone+"");
			btn_number[i].setFont(font2);
	        btn_number[i].setBackground(Color.white);
			if(phone >0) {
				p_input.add(btn_number[phone]);
			}
			
			btn_number[i].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {			
					if(jtf_phone.getText().length()<11) {
						jtf_phone.setText(jtf_phone.getText()+e.getActionCommand());						
					} else if(jpf_password.getText().length()<4) {
						jpf_password.setText(jpf_password.getText()+e.getActionCommand());
					} 
				}
			});		
		}
		
		
		jtf_phone.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) { }
			
			@Override
			public void focusGained(FocusEvent e) { focus=1; }
		});

		jpf_password.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) { }
			
			@Override
			public void focusGained(FocusEvent e) { focus =2; }
		});
		
		btn_delete = new JButton("←");
		btn_delete.setFont(font2);
	    btn_delete.setBackground(new Color(153, 204, 255));
		btn_delete.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            // Check the focus status to determine which field to modify
		            if (focus==2) {
		                String delete = jpf_password.getText();
		                if (!delete.isEmpty()) {
		                    jpf_password.setText(delete.substring(0, delete.length() - 1));        
		                   
		                }
		            } else {
		                String delete = jtf_phone.getText();
		                if (!delete.isEmpty()) {
		                    jtf_phone.setText(delete.substring(0, delete.length() - 1));
		                }
		            }
		        }
		    });
		
		btn_confirm = new JButton("확인");
	    btn_confirm.setFont(font2);
	    btn_confirm.setBackground(new Color(255, 192, 203));
		btn_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				phone = jtf_phone.getText();
				password = jpf_password.getText();
				CustomerVO vo = new CustomerVO(phone, password);
				CustomerDAO dao = new CustomerDAO();
				int yescust = dao.Logincustomer(vo);
				IntroFrame.us = yescust;
				if(IntroFrame.us >100) {
					JOptionPane.showMessageDialog(null, "로그인에 성공하셨습니다.");			
					dispose();
					new RoomPayFrame2();
				} else {
					JOptionPane.showMessageDialog(null, "로그인에 실패하셨습니다.");
					jtf_phone.setText("");
					jpf_password.setText("");

				}
			}
		});
		
		btn_back = new JButton("뒤로가기");
		btn_back.setFont(font2);
		btn_back.setBackground(Color.white);
		
		btn_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Two_oneFrame();
				
			}
		});

		p_input.add(btn_delete);
		p_input.add(btn_number[0]);
		p_input.add(btn_confirm);
		JPanel p_center = new JPanel();
		p_center.setLayout(new GridLayout(2,1));
		p_center.add(p_output);
		p_center.add(p_input);
		
		setLayout(new BorderLayout());
		add(p_center,BorderLayout.CENTER);
		add(btn_back,BorderLayout.SOUTH);
		setBackground(Color.WHITE);
		setSize(700, 1000);
		setTitle("로그인");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
}
