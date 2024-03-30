package project;
//준완성
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class MemberJoinFrame extends JFrame {
	 JLabel jlb_phone;
	private JTextField jtf_phone;
	private JLabel jlb_password;
	private JPasswordField jpf_password;
	private JLabel jlb_passwordCheck;
	private JPasswordField jpf_passwordCheck;
	private JButton[] btn_number;
	private JButton btn_delete;
	private JButton btn_confirm;
	
	private String phone;
	private String password;
	private String passwordCheck;
	
	private int focus=1;
	
	public int matchPassword() { //비밀번호와 비밀번호확인 칸의 내용 동일한지 확인
		int rightPW =0;
		if(jpf_password.getText().equals(jpf_passwordCheck.getText())
				&& !jpf_password.getText().equals("")) {
			rightPW =1;
			password = jpf_password.getText();
			phone = jtf_phone.getText();
			CustomerVO vo = new CustomerVO(phone, password);
			CustomerDAO dao = new CustomerDAO();
			int re = dao.insertCustomer(vo);
			if(re==1 && phone.substring(0, 3).equals("010") && phone.length()==11) {
				JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다.");
				new IntroFrame();
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "이미 사용중인 번호이거나, 입력하신 정보가 잘못되었습니다.");
			}
		}else {
			JOptionPane.showMessageDialog(null, "비밀번호를 확인해주세요");
			jpf_password.setText("");
			jpf_passwordCheck.setText("");
		}
		return rightPW;
	}
	
	public MemberJoinFrame() { //회원가입 창
		JPanel p_output = new JPanel();
		JPanel p_input = new JPanel();
		Font font = new Font("맑은 고딕", Font.BOLD, 20); //서체
		
		p_output.setLayout(new GridLayout(6,1));
		p_input.setLayout(new GridLayout(4,3,10,10));
		p_output.setBackground(Color.WHITE);
		p_input.setBackground(Color.WHITE);
		
		jlb_phone = new JLabel("휴대폰 번호");
		jtf_phone = new JTextField(11);
		jlb_password = new JLabel("비밀번호");
		jpf_password = new JPasswordField(4);
		jlb_passwordCheck = new JLabel("비밀번호 확인");
		jpf_passwordCheck = new JPasswordField(4);
		
		jlb_phone.setFont(font);
		jlb_password.setFont(font);
		jlb_passwordCheck.setFont(font);
		jtf_phone.setFont(font);
		jpf_password.setFont(font);
		jpf_passwordCheck.setFont(font);
		
		
		p_output.add(jlb_phone);
		p_output.add(jtf_phone);
		p_output.add(jlb_password);
		p_output.add(jpf_password);
		p_output.add(jlb_passwordCheck);
		p_output.add(jpf_passwordCheck);
		
		
		btn_number = new JButton[10];
		
		
		for(int i=0; i<10;i++) {
			int phone = i;
			btn_number[i] = new JButton(phone+"");
			btn_number[i].setFont(font);
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
					} else if(jpf_passwordCheck.getText().length()<4) {
						jpf_passwordCheck.setText(jpf_passwordCheck.getText()+e.getActionCommand());
					} 
				}
			});		
		}
		
		
		jtf_phone.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				focus=1;
				
			}
		});
		
		
		
		jpf_password.addFocusListener(new FocusListener() {
			
			
			@Override
			public void focusLost(FocusEvent e) {
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				focus =2;
			}
		});
		
		jpf_passwordCheck.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				focus=3;
			}
		});
		
		btn_delete = new JButton("←");
		btn_delete.setFont(font);
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
		            } else if(focus==1) {
		                String delete = jtf_phone.getText();
		                if (!delete.isEmpty()) {
		                    jtf_phone.setText(delete.substring(0, delete.length() - 1));
		                }
		            } else {
		            	String delete = jpf_passwordCheck.getText();
		            	if(!delete.isEmpty()) {
		            		jpf_passwordCheck.setText(delete.substring(0, delete.length() - 1));
		            	}
		            }
		        }
		    });
		
		btn_confirm = new JButton("확인");
		btn_confirm.setFont(font);
		btn_confirm.setBackground(new Color(255, 192, 203));
		btn_confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				matchPassword();
				
				System.out.println();
			}
		});
		
		p_input.add(btn_delete);
		p_input.add(btn_number[0]);
		p_input.add(btn_confirm);
	
		
		
		setLayout(new GridLayout(2,1));
		add(p_output);
		add(p_input);
		setTitle("회원 가입");
		setSize(500, 600);
		setVisible(true);
		setResizable(false);
		
		
		
	}

}
