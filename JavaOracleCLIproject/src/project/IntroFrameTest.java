package project;
//준완성
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Timer;

import com.sist.dao.IntroDAO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class IntroFrame extends JFrame{
	
	int x =65; //왼쪽 패널 시작 x점
	int y = 80; //왼쪽 패널 시작 y점
	int width = 250;
	int height = 400;
	int usingCnt; //사용중 방 개수
	int vacantCnt; //빈 방 개수
	int waitCnt; //대기인원
	int waitNum; //다음에 들어갈 예약자 번호
	
	JLabel jlb_using = new JLabel("<HTML><center>빈방<br>"+usingCnt+"</center></HTML>");
	JLabel jlb_vacant = new JLabel("<HTML><center>빈방<br>"+vacantCnt+"</center></HTML>");
	JLabel jlb_waitcnt = new JLabel("<HTML><center>대기 인원<br>"+waitCnt+"</center></HTML>");
	boolean waitWindowCalled =false;
	boolean goWindowCalled =false;
	JButton btn_join;
	JButton btn_food;
	public static int us;
	
	//방 개수 가져와서 셋텍스트
	public int UpdateUsingandVacantRoom() { 
		IntroDAO dao = new IntroDAO();
		usingCnt = dao.countUsingRoom();	 
		jlb_using.setText("<HTML><center>사용중<br>"+usingCnt+"</center></HTML>");
		jlb_vacant.setText("<HTML><center>빈방<br>"+(12-usingCnt)+"</center></HTML>");
		
		if(usingCnt==12) {
			jlb_waitcnt.setVisible(true);
		} else {
			jlb_waitcnt.setVisible(false);
			
		}
		return usingCnt;
	}
//	================대기자 업데이트 관련===============================
	public int UpdateWaiting() {
		IntroDAO dao = new IntroDAO();
		int waitCnt = dao.countWaiting();
			jlb_waitcnt.setText("대기 인원: "+waitCnt+"명");	
		return waitCnt;
	}

	public void goWindow() { //기본 창
		goWindowCalled =true;
		JPanel p_vacant = new JPanel(); //빈방 패널
  		JPanel p_using = new JPanel(); //사용중 패널
  		JPanel p_center = new JPanel(); //가운데 패널
  		JPanel p_south = new JPanel(); //아래 패널
  		JPanel p_north = new JPanel(); //위 패널
  		JPanel p_wait = new JPanel(); //대기자 수 띄우는 패널
  		
  		p_using.setBackground(new Color(153, 204, 255));
  		p_vacant.setBackground(new Color(217, 72, 68));
  		p_center.setBackground(Color.WHITE);
  		p_south.setBackground(Color.WHITE);
  		p_north.setBackground(Color.WHITE);
  		p_wait.setBackground(Color.WHITE);
  		
  		
  		jlb_waitcnt = new JLabel("대기 인원: "+waitCnt+"명");
  		btn_join = new JButton("회원 가입");
  		btn_food = new JButton("간식 결제");
  		
  		btn_join.setBackground(new Color(240, 240, 240));
  		btn_food.setBackground(new Color(240,240,240));
  		
  		
  		IntroDAO dao = new IntroDAO();
		usingCnt = dao.countUsingRoom();
  		vacantCnt = 12-usingCnt;
  		jlb_vacant = new JLabel("<HTML><center>빈방<br>"+vacantCnt+"</center></HTML>"); //빈 방 개수
  		jlb_using = new JLabel("<HTML><center>사용중<br>"+usingCnt+"</center></HTML>"); //사용중 방 개수
		
		Font font = new Font("맑은 고딕", Font.BOLD, 50);
		Font font2 = new Font("맑은 고딕", Font.BOLD, 40);
		
		
		
		jlb_vacant.setFont(font);
		jlb_using.setFont(font);
		btn_food.setFont(font2);
		btn_join.setFont(font2);
		jlb_waitcnt.setFont(font2);
		
		
		
		p_vacant.setPreferredSize(new Dimension(100, 260));
		p_vacant.add(jlb_vacant);

		p_using.setPreferredSize(new Dimension(100, 260));
		p_using.add(jlb_using);
		
		p_wait.add(jlb_waitcnt);
		jlb_waitcnt.setVisible(false);
		
		p_center.setLayout(null);
		p_vacant.setBounds(x, y , width , height);
		p_using.setBounds(x+300, y, width, height);
		btn_join.setBounds(x, y+550, width, 150);
		btn_food.setBounds(x+300, y+550, width, 150);
		p_wait.setBounds(x, y+430, 550, 100);

		p_center.add(p_vacant);
		p_center.add(p_using);
		p_center.add(p_wait);
		p_center.add(btn_join);
		p_center.add(btn_food);
		
		JLabel jlb_intro = new JLabel("화면을 터치해주세요");

		jlb_intro.setFont(font);
		p_south.add(jlb_intro);
		
		add(p_north,BorderLayout.NORTH);		
		add(p_center, BorderLayout.CENTER);
		add(p_south, BorderLayout.SOUTH);
		
		
		setTitle("KKJ CONO");
		setSize(700, 1000);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		btn_join.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("회원 가입 연결");
				new MemberJoinFrame();
			}
		});
		
		btn_food.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("간식 결제 화면 연결");
				dispose();
				new FoodPayFrame();
			}
		});
		
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(usingCnt!=12 ){
					dispose();
					new Two_oneFrame();
				}else {
					dispose();
					new WaitFrame();
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		}
//	===============================================	
	
  	public IntroFrame() { //프레임 생성자
  		
  		UpdateUsingandVacantRoom(); //빈방 수 세기
  		UpdateWaiting(); //대기자 수 세기
  		goWindow(); //기본화면 불러오기
  			
  		
  	   // 타이머를 통한 주기적인 업데이트
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	UpdateUsingandVacantRoom(); 
            	UpdateWaiting(); //사용중인 방 개수가 12
            	 
            }
        });
        timer.start();   
  	}
}

public class IntroFrameTest {
	public static void main(String[] args) {
		new IntroFrame();
	}

}
