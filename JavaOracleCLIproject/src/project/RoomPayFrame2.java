package project;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;  // 추가
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.sist.dao.RoomPayDAO;
import com.sist.dao.RoomTimeDAO;
import com.sist.vo.PayVO;
import com.sist.vo.RoomTimeVO;

class RoomPayFrame2 extends JFrame {
    int[] min = {10, 45, 60, 90, 120, 180};
    int[] pay = {4000, 6000, 7000, 11000, 14000, 20000};
    JButton[] room = new JButton[12];
    JButton[] btn_min = new JButton[12];
    int minu;
    int sale;
    


    public RoomPayFrame2() {
        setTitle("방선택 및 결제");
        JPanel p_south_south = new JPanel();
        p_south_south.setBackground(Color.white);
        JLabel jlb_roomnum = new JLabel("방번호");
        JLabel jlb_payment = new JLabel("요금을 선택하세요");
        JButton btn_payment = new JButton("결제");
        btn_payment.setBackground(new Color(240,240,240));
        JButton btn_back = new JButton("뒤로가기");
        btn_back.setBackground(new Color(240,240,240));
        
        jlb_roomnum.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        jlb_payment.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        btn_payment.setFont(new Font("맑은 고딕", Font.BOLD, 10));
        btn_back.setFont(new Font("맑은 고딕", Font.BOLD, 10));
		
		
   
        JPanel buttonPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        buttonPanel.setBackground(Color.white);
        RoomTimeDAO dao = new RoomTimeDAO();
        ArrayList<RoomTimeVO> list = dao.getTimeLeft();

        // 12개의 다른 크기의 버튼 추가
        for (int i = 0; i < 12; i++) {
            boolean found = false;  // 해당 방의 정보를 찾았는지 여부를 나타내는 플래그
            String rsize = "소";
			String mic = "유선";
			if (i > 9) {
				rsize = "대";
			}
			if ((i >= 5 && i <= 8) || i == 10) {
				mic = "무선";
			}
            if (list.size() > 0) { // list에 정보가 있을 때
                for (RoomTimeVO r : list) {
                    if (i + 1 == r.getRno()) {
                        found = true;
                        if (r.getMin_left() == 0 && r.getSec_left() == 0) {
                            // 시간이 모두 0이면 기본 문구로 설정
                            room[i] = new JButton((i + 1) + "번 방 " + " 방크기:" + rsize + " 마이크:" + mic);
                            room[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
                            room[i].setBackground(new Color(240,240,240));
                            room[i].setEnabled(true);
                        } else {
                            room[i] = new JButton(i + 1 + "번 방 "+ String.format("%02d : %02d", r.getMin_left(),r.getSec_left() ));
                            room[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
                            room[i].setBackground(new Color(153, 204, 255));
                            room[i].setEnabled(false);
                        }
                        break;
                    }
                }
            }

            // 해당 방의 정보를 찾지 못했을 경우 기본 문구로 설정
            if (!found) {
                room[i] = new JButton((i + 1) + "번 방 " + " 방크기:" + rsize + " 마이크:" + mic);
                room[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
                room[i].setBackground(new Color(240,240,240));
                room[i].setEnabled(true);
            }

            room[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jlb_roomnum.setText(e.getActionCommand());
                }
            });

            buttonPanel.add(room[i]);
        }

        JLabel jlb_top = new JLabel("요금결제");
		jlb_top.setFont(new Font("맑은 고딕", Font.BOLD, 25));

		JLabel jlb_mention = new JLabel("원하는 방을 터치하면 이용시간 선택이 가능합니다");
		jlb_mention.setHorizontalAlignment(JLabel.CENTER);
		jlb_mention.setFont(new Font("맑은 고딕", Font.BOLD, 25));

        JPanel p_center = new JPanel();
        p_center.setBackground(Color.white);
        p_center.setLayout(new BorderLayout());
        p_center.add(buttonPanel, BorderLayout.CENTER);
        p_center.add(jlb_mention, BorderLayout.SOUTH);

        JPanel p_pay = new JPanel();
        p_pay.setBackground(Color.white);
        p_pay.setLayout(new GridLayout(3, 2, 10, 10));
        p_pay.setBorder(new TitledBorder(new LineBorder(Color.black, 5), "회원요금")); // 테두리

        p_south_south.add(jlb_roomnum);
        p_south_south.add(jlb_payment);
        p_south_south.add(btn_payment);
        p_south_south.add(btn_back);

        JPanel p_south = new JPanel();
        p_south.setBackground(Color.white);
        p_south.setLayout(new BorderLayout());
        p_south.add(p_pay, BorderLayout.CENTER);
        p_south.add(p_south_south, BorderLayout.SOUTH);

        add(p_center, BorderLayout.CENTER);
        add(p_south, BorderLayout.SOUTH);

        
        setSize(700, 1000);
//        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//==========================================================================
   
        if(IntroFrame.us>100) {
                p_pay.removeAll();

                for (int j = 0; j < min.length; j++) {
                	int a = j;
                    btn_min[j] = new JButton(min[j] + "분 - " + pay[j] + "원");
                    btn_min[j].setBackground(new Color(240,240,240));
                    p_pay.add(btn_min[j]);
                    String gottext = btn_min[j].getText();
                    btn_min[j].addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                        	minu = min[a];
                        	sale = pay[a];
                        	
                            jlb_payment.setText(gottext);
                        }
                    });
                }

                setVisible(true);
                p_pay.repaint();
        }
//==========================================================================
       if(IntroFrame.us==100) {
                p_pay.removeAll();

                for(int i=0; i < min.length; i++) {
                	int a = i;
					btn_min[i+min.length]=new JButton(min[i] + "분 - " + (int)Math.round(pay[i] * 1.2) +"원" );
					btn_min[i+min.length].setBackground(new Color(240,240,240));
					p_pay.add(btn_min[i+min.length]);
					String gottext = btn_min[i+min.length].getText();
					btn_min[i+min.length].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							
							int startindex = gottext.indexOf("-");
							String won = gottext.substring(startindex+1);
							
							minu = min[a];
                        	sale = (int)(pay[a]*1.2);
							
							jlb_payment.setText(gottext);
						}
					});
				}
                setVisible(true);
                p_pay.repaint();
       }

        btn_payment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int i;
				String firstChar = jlb_roomnum.getText().substring(0, 2);
				if(firstChar.charAt(1) == '번') {
					i = Integer.parseInt(firstChar.substring(0, 1))-1;
				}else {
					i = Integer.parseInt(firstChar)-1;					
				}
                    
//          여기서부터 수정
                int cno = IntroFrame.us;
                int rno = i+1;
                int minute = minu;
                int saleprice = sale;
                System.out.println(cno);
                System.out.println(rno);
                System.out.println(minute);
                System.out.println(saleprice);
                PayVO vo = new PayVO(cno, rno, minute, saleprice);
                RoomPayDAO payDao = new RoomPayDAO();
                int rs =payDao.insertPay(vo);
                if(rs == 1) {
                	room[i].setBackground(new Color(153, 204, 255));
                    room[i].setEnabled(false);  
                JOptionPane.showMessageDialog(null, jlb_roomnum.getText() + jlb_payment.getText() + " \n결제가 완료되었습니다.");
                dispose();
                new IntroFrame();
                }
                else{
                	JOptionPane.showMessageDialog(null, "결제에 실패하였습니다.");
                }
            }
        });

        // 타이머를 통한 주기적인 업데이트
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1초마다 데이터 업데이트 메서드 호출
                updateRoomButtons();
            }
        });
        timer.start(); // 타이머 시작  
        
       btn_back.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			new Two_oneFrame();
			
		}
	});
        
    }
    
    
    
    

    // Timer 클래스를 이용하여 1초마다 데이터를 업데이트하는 메서드 추가
    private void updateRoomButtons() {
        // RoomTimeDAO 객체 생성
        RoomTimeDAO dao = new RoomTimeDAO();
        // 새로운 데이터를 가져오기
        ArrayList<RoomTimeVO> updatedList = dao.getTimeLeft();

        // 업데이트된 데이터로 버튼 텍스트 갱신
        for (int i = 0; i < 12; i++) {
            boolean found = false;  // 해당 방의 정보를 찾았는지 여부를 나타내는 플래그
            String rsize = "소";
			String mic = "유선";
			if (i > 9) {
				rsize = "대";
			}
			if ((i >= 5 && i <= 8) || i == 10) {
				mic = "무선";
			}
            if (updatedList.size() > 0) {
                for (RoomTimeVO r : updatedList) {
                    if (i + 1 == r.getRno()) {
                        found = true;  
                        if (r.getMin_left() == 0 && r.getSec_left() == 0) {
                            // 시간이 모두 0이면 기본 문구로 설정
                            room[i].setText((i + 1) + "번 방 " + " 방크기:" + rsize + " 마이크:" + mic);
                            room[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
                            room[i].setBackground(new Color(240,240,240));
                            room[i].setEnabled(true);
                        } else {
                            // 버튼 텍스트를 업데이트된 시간 정보로 설정
                            room[i].setText(i + 1 + "번 방 "+ String.format("%02d : %02d", r.getMin_left(),r.getSec_left()));
                            room[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
                            room[i].setBackground(new Color(153, 204, 255));
                            room[i].setForeground(Color.BLACK);
                            room[i].setEnabled(false);
                        }
                        break;
                    }
                }
            }

            // 해당 방의 정보를 찾지 못했을 경우 기본 문구로 설정
            if (!found) {
                room[i].setText((i + 1) + "번 방 " + " 방크기:" + rsize + " 마이크:" + mic);
                room[i].setFont(new Font("맑은 고딕", Font.BOLD, 20));
                room[i].setBackground(new Color(240,240,240));
                room[i].setEnabled(true);
            }
        }
    }
}

