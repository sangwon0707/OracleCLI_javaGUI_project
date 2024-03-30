package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.JTextField;

import com.sist.dao.WaitDAO;

public class WaitFrame extends JFrame {
   private JLabel jlb_phone;
   private JTextField jtf_phone;

   private JButton[] btn_number;
   private JButton btn_delete;
   private JButton btn_confirm;   
   
   private String phone;

   
   private int focus =1;
   
   
   public WaitFrame() {
      JPanel p_all = new JPanel();
      JPanel p_output = new JPanel();
      JPanel p_input = new JPanel();
      Font font = new Font("맑은 고딕", Font.BOLD, 40); //서체
      
      
//      setLayout(null);
//      p_all.setBounds(80,120, 500, 700);
      p_output.setLayout(new GridLayout(4,1));
      p_input.setLayout(new GridLayout(4,3));
      p_output.setBackground(Color.WHITE);
      p_input.setBackground(Color.WHITE);
      
      
      jlb_phone = new JLabel("  휴대폰 번호를 입력하세요.");
      jtf_phone = new JTextField(11);

      p_output.add(new JLabel());
      p_output.add(jlb_phone);
      p_output.add(jtf_phone);
      jlb_phone.setFont(font);
      jtf_phone.setFont(font);
      
      
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
               } 
            }
         });      
      }
            
      btn_delete = new JButton("←");
      btn_delete.setFont(font);
      btn_delete.setBackground(new Color(153, 204, 255));
        btn_delete.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  // Check the focus status to determine which field to modify
                      String delete = jtf_phone.getText();
                      if (!delete.isEmpty()) {
                          jtf_phone.setText(delete.substring(0, delete.length() - 1));
                      }
              }
          });
      
      btn_confirm = new JButton("확인");
      btn_confirm.setFont(font);
      btn_confirm.setBackground(new Color(255, 192, 203));
      btn_confirm.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            phone = jtf_phone.getText();
            if(phone.substring(0, 3).equals("010") && phone.length()==11) { //올바른 전화번호인지 확인
            	WaitDAO waitdao = new WaitDAO();
              waitdao. waitinsertmethod(phone);
               dispose();
               new IntroFrame();
            }else {
               JOptionPane.showMessageDialog(null, "휴대폰 번호를 알맞게 작성했는지 확인해 주세요");
               jtf_phone.setText("");
            }

         }
      });
      
      p_input.add(btn_delete);
      p_input.add(btn_number[0]);
      p_input.add(btn_confirm);
   
      
      
      p_all.setLayout(new GridLayout(2,1));
      p_all.add(p_output);
      p_all.add(p_input);
      add(p_all);
      setBackground(Color.WHITE);
      setSize(700, 1000);
      setTitle("대기(예약)");
      setVisible(true);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
   }
}
