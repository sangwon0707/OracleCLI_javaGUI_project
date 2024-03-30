package managerFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.*;

class ManagerLogin extends JFrame{
	String userName = "manager";
	String passWord = "1q2w3e";

	public ManagerLogin(){
	Font font1 = new Font("menlo",Font.BOLD, 20);
	Font font2 = new Font("menlo",Font.BOLD, 14);
	
	JLabel jlb_manager_username = new JLabel("로그인");
	jlb_manager_username.setFont(font2);
	
	JTextField jtf_manager_username = new JTextField(10);
	jtf_manager_username.setBorder(new LineBorder(Color.DARK_GRAY, 2));
	
	
	JLabel jlb_manager_passWord = new JLabel("비밀번호");
	jlb_manager_passWord.setFont(font2);
	
	JPasswordField jtf_manager_passWord = new JPasswordField(10);
	jtf_manager_passWord.setBorder(new LineBorder(Color.DARK_GRAY, 2));
	
	
	JButton btn_manager_login = new JButton("확인");
	btn_manager_login.setFont(font2);	
	
	JPanel p_manager_login_compnent = new JPanel(){
		@Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor( getBackground() );
            g.fillRect(0, 0, getWidth(), getHeight());
        }
	};
	p_manager_login_compnent.setOpaque(false); // background of parent will be painted first
	p_manager_login_compnent.setBackground( new Color(250, 250, 250, 150) );
	
	JPanel p_manager_login = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon backgroundIcon = new ImageIcon("../TeamProject3/ManagerLoginBackground3.png");
            Image backgroundImage = backgroundIcon.getImage();
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            g.setColor( getBackground() );
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    };
    p_manager_login.setOpaque(false); // background of parent will be painted first
    p_manager_login.setBackground( new Color(200, 200, 200, 50) );
    
    p_manager_login_compnent.setBounds(100, 140, 300, 110);
    jlb_manager_username.setBounds(30, 20, 100, 25);
	jtf_manager_username.setBounds(100, 20, 150, 25);
	jlb_manager_passWord.setBounds(30, 50, 100, 25);
	jtf_manager_passWord.setBounds(100, 50, 150, 25);
	btn_manager_login.setBounds(120, 85, 80, 20);
	
	p_manager_login.add(p_manager_login_compnent);
	p_manager_login_compnent.add(jlb_manager_username);
	p_manager_login_compnent.add(jtf_manager_username);
	p_manager_login_compnent.add(jlb_manager_passWord);
	p_manager_login_compnent.add(jtf_manager_passWord);
	p_manager_login_compnent.add(btn_manager_login);
	p_manager_login_compnent.setLayout(null);
	p_manager_login.setLayout(null);
	getContentPane().add(p_manager_login);
	
	setTitle("노래방 관리자 로그인");
	setSize(500,300);
	setResizable(false);
	setLocation(500,200);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	btn_manager_login.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (jtf_manager_username.getText().equals(userName) && jtf_manager_passWord.getText().equals(passWord)){
				JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다!");
				dispose();
				new ManagerFrame();
			}else {
				JOptionPane.showMessageDialog(null, "아이디/패스워드를 다시 확인해주세요!", "오류!", JOptionPane.ERROR_MESSAGE);
				jtf_manager_username.setText("");
				jtf_manager_passWord.setText("");
				jtf_manager_username.requestFocus();
			}
				
		}
	});
	
	}
}

class ManagerFrame extends JFrame{
	JTable table_room;
	JTable table_cust;
	JTable table_pay;
	JTable table_cust_info;
	JTable table_pay_info;
	
	Vector<String> colNames_c;
	Vector<Vector<String>> rowData_c;
	
	Vector<String> colNames_pay;
	Vector<Vector<String>> rowData_pay;	
	
	Vector<String> colNames_room;
	Vector<Vector<String>> rowData_room;
	
	Vector<String> colNames_c_info;
	Vector<Vector<String>> rowData_c_info;
	
	Vector<String> colNames_pay_info;
	Vector<Vector<String>> rowData_pay_info;
	
	
	JButton[] roomButtons = new JButton[12];
	JLabel lb_room;
	JLabel lb_cust;
	JLabel lb_pay;
	
	private void printReceiptToFile(JTable table) {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();

	    // Create a file chooser
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Save Receipt");
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

	    int userSelection = fileChooser.showSaveDialog(this);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        java.io.File fileToSave = fileChooser.getSelectedFile();

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
	            writer.write("[영수증 출력]");
	            writer.newLine();
	            int totalSalePrice = 0; 
	            for (int i = 0; i < model.getRowCount(); i++) {
	            	for (int j = 0; j < model.getColumnCount(); j++) {
	            	    Object cellValue = model.getValueAt(i, j);
	            	    String value = (cellValue != null) ? cellValue.toString() : ""; // Null check
	            	    writer.write(value + " ");
	            	    if (j == 6) {
	            	        try {
	            	            int salePrice = Integer.parseInt(value);
	            	            totalSalePrice += salePrice;
	            	        } catch (NumberFormatException e) {
	            	            System.out.println("Not a valid integer: " + value);
	            	        }
	            	    }
	            	}
	                writer.newLine();
	            }

	            writer.write("총결제 금액: " + totalSalePrice +"원");
	            writer.newLine();
	            writer.write("- 이용해주셔서 감사합니다. -");
	            writer.newLine();
	            writer.write("- 끝까지가는 노래방-");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        fileChooser.resetChoosableFileFilters();
	        fileChooser.rescanCurrentDirectory();

	        // Close the file chooser dialog manually
	        fileChooser.setDialogType(JFileChooser.DIRECTORIES_ONLY);
	        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        fileChooser.setCurrentDirectory(fileToSave);
	        fileChooser.setDialogTitle("Select Directory");
	        fileChooser.setSelectedFile(fileToSave);
	        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
	        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    }
	}
	
	public void resizeColumnWidth(JTable table) {
	    final TableColumnModel columnModel = table.getColumnModel();
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        int width = 10; // Min width
	        for (int row = 0; row < table.getRowCount(); row++) {
	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	            Component comp = table.prepareRenderer(renderer, row, column);
	            width = Math.max(comp.getPreferredSize().width +1 , width);
	        }
	        columnModel.getColumn(column).setPreferredWidth(width);
	    }
	}
	
	private void refreshCustomerTable() {
	    DefaultTableModel model = (DefaultTableModel) table_cust.getModel();
	    model.setRowCount(0);

	    Manager_c_dao manager_c_dao = new Manager_c_dao();
	    ArrayList<Manager_c_vo> customerList = manager_c_dao.listCustomer();

	    for (Manager_c_vo customer : customerList) {
	        Vector<String> row = new Vector<>();
	        row.add(String.valueOf(customer.getCno()));
	        row.add(customer.getC_phone());
	        row.add(customer.getC_password());
	        row.add(String.valueOf(customer.getC_total_minutes()));
	        model.addRow(row);
	    }
	}
	
	public void refreshCustomerTable(String phone){
		DefaultTableModel model = (DefaultTableModel) table_cust_info.getModel();
	    model.setRowCount(0);
	    
		Manager_c_search_dao manager_c_search_dao = new Manager_c_search_dao();
		ArrayList<Manager_custInfo_vo> custInfo = manager_c_search_dao.SearchCustomer(phone);
		for (Manager_custInfo_vo customer : custInfo) {
            Vector<String> row = new Vector<>();
            row.add(String.valueOf(customer.getCno()));
            row.add(customer.getC_phone());
            row.add(customer.getC_password());
            row.add(String.valueOf(customer.getC_total_minutes()));
            model.addRow(row);    
    	    
	    }
	}
	
	
	private void refreshPayTable() {
	    DefaultTableModel model2 = (DefaultTableModel) table_pay.getModel();
	    model2.setRowCount(0);

	    Manager_pay_dao manager_pay_dao = new Manager_pay_dao();
	  	ArrayList<Manager_pay_vo> paymentList = manager_pay_dao.listPay();

	  	for (Manager_pay_vo payment : paymentList) {
	  	    Vector<String> row = new Vector<>();
	  	    row.add(String.valueOf(payment.getPno()));
	  	    row.add(String.valueOf(payment.getOrder_num()));
	  	    row.add(payment.getPt_type());
	  	    row.add(String.valueOf(payment.getCno()));
	  	    row.add(payment.getF_name());
	  	    row.add(String.valueOf(payment.getP_cnt()));
	  	    row.add(String.valueOf(payment.getRno()));
	  	    row.add(String.valueOf(payment.getP_minute()));
	  	    row.add(payment.getPaytime());
	  	    row.add(payment.getEndtime());
	  	    row.add(String.valueOf(payment.getSaleprice()));
	        model2.addRow(row);
	  	}
	}
		
	public ManagerFrame(){
		//회원 : 정보 조회//	
		colNames_c = new Vector<String>();
		rowData_c = new Vector<Vector<String>>(); 
		colNames_c.add("회원번호");
		colNames_c.add("회원전화");
		colNames_c.add("비밀번호");
		colNames_c.add("총이용시간");
		
		//회원 : 테이블에 로우데이터 넣고 뿌려주기//
		Manager_c_dao manager_c_dao = new Manager_c_dao();
	    ArrayList<Manager_c_vo> customerList = manager_c_dao.listCustomer();
	
	    for (Manager_c_vo customer : customerList) {
	        Vector<String> row = new Vector<>();
	        row.add(String.valueOf(customer.getCno()));
	        row.add(customer.getC_phone());
	        row.add(customer.getC_password());
	        row.add(String.valueOf(customer.getC_total_minutes()));
	        rowData_c.add(row);
	    }
		
	    //결제 : 정보 조회//	
	  	colNames_pay = new Vector<String>();
	  	rowData_pay = new Vector<Vector<String>>(); 
	  	colNames_pay.add("결제번호");
	  	colNames_pay.add("주문번호");
	  	colNames_pay.add("결제타입");
	  	colNames_pay.add("회원번호");
	  	colNames_pay.add("음식명");
	  	colNames_pay.add("수량");
	  	colNames_pay.add("방번호");
	  	colNames_pay.add("이용시간");
	  	colNames_pay.add("결제시각");
	  	colNames_pay.add("종료시각");
	  	colNames_pay.add("결제금액");
	  	
	  	//결제 : 테이블에 뿌려주기
	  	Manager_pay_dao manager_pay_dao = new Manager_pay_dao();
	  	ArrayList<Manager_pay_vo> paymentList = manager_pay_dao.listPay();
	
	  	for (Manager_pay_vo payment : paymentList) {
	  	    Vector<String> row = new Vector<>();
	  	    row.add(String.valueOf(payment.getPno()));
	  	    row.add(String.valueOf(payment.getOrder_num()));
	  	    row.add(payment.getPt_type());
	  	    row.add(String.valueOf(payment.getCno()));
	  	    row.add(payment.getF_name());
	  	    row.add(String.valueOf(payment.getP_cnt()));
	  	    row.add(String.valueOf(payment.getRno()));
	  	    row.add(String.valueOf(payment.getP_minute()));
	  	    row.add(payment.getPaytime());
	  	    row.add(payment.getEndtime());
	  	    row.add(String.valueOf(payment.getSaleprice()));
	  	    rowData_pay.add(row);
	  	}
	    
	  	//개별 방 조회
	  	colNames_room = new Vector<String>();
	  	rowData_room = new Vector<Vector<String>>(); 
	  	colNames_room.add("방번호");  //띄어쓰기 아님 투명 특수문
	  	colNames_room.add("결제");
	  	colNames_room.add("종료");
	  	colNames_room.add("회원번호");
	  	colNames_room.add("남은시간");  	
	  	
	  	//개별 방 뿌려주기
	  	int rno = 3;
		Manager_roomInfo_dao mrid = new Manager_roomInfo_dao();
	    ArrayList<Manager_roomInfo_vo> roomlist = mrid.listRoom(rno);
	    
	  	for (Manager_roomInfo_vo room : roomlist) {
	  	    Vector<String> row = new Vector<>();
	  	    row.add(String.valueOf(room.getRno()));
	  	    row.add(room.getPaytime());
	  	    row.add(room.getEndtime());
	  	    row.add(String.valueOf(room.getCno()));
	  	    row.add(String.valueOf(room.getMinutes_left()));
	  	    rowData_room.add(row);
	  	}
	  	
		//개별회원 : 정보 조회//	
		  	colNames_c_info = new Vector<String>();
		  	rowData_c_info = new Vector<Vector<String>>(); 
		  	colNames_c_info.add("회원번호");
		  	colNames_c_info.add("회원전화");
		  	colNames_c_info.add("비밀번호");
		  	colNames_c_info.add("총이용시간");
		  	
		//개별회원 : 개별회원 뿌려주기//
		  	Manager_custInfo_dao mcidao = new Manager_custInfo_dao();
		  	ArrayList<Manager_custInfo_vo> custInfolist = mcidao.infoCustomer(1);
		    for (Manager_custInfo_vo c : custInfolist) {
		          Vector<String> row = new Vector<>();
		          row.add(String.valueOf(c.getCno()));
		          row.add(c.getC_phone());
		          row.add(c.getC_password());
		          row.add(String.valueOf(c.getC_total_minutes()));
		          rowData_c_info.add(row);
		    }   
	  	
		//개별페이 : 오더기준 조회//	
		  	colNames_pay_info = new Vector<String>();
		  	rowData_pay_info = new Vector<Vector<String>>(); 
		  	colNames_pay_info.add("결제순서");
		  	colNames_pay_info.add("회원번호");
		  	colNames_pay_info.add("방번호");
		  	colNames_pay_info.add("이용시");
		  	colNames_pay_info.add("음식명");
		  	colNames_pay_info.add("수량");
		  	colNames_pay_info.add("결제금액");
		  	colNames_pay_info.add("결제시각");
		  	
		//개별페이: 개별페이 뿌려주기//
		  	Manager_pay_DisplayOrder_dao mpd_dao = new Manager_pay_DisplayOrder_dao();
		  	ArrayList<Manager_pay_DisplayOrder_vo> payInfolist = mpd_dao.listPay(1);
		  	for (Manager_pay_DisplayOrder_vo pay : payInfolist) {
		            Vector<String> row = new Vector<>();
	  	            row.add(String.valueOf(pay.getRowNum()));
	  	            row.add(String.valueOf(pay.getCno()));
	  	            row.add(String.valueOf(pay.getRno()));
	  	            row.add(String.valueOf(pay.getP_minute()));
	  	            row.add(pay.getF_name());
	  	            row.add(String.valueOf(pay.getP_cnt()));
	  	            row.add(String.valueOf(pay.getSaleprice()));
	  	            row.add(pay.getPaytime());
	  	            rowData_pay_info.add(row);	       
		    }   
	  	
		  	//메뉴바 생성
		  	JMenu receiptPrintMenu = new JMenu("파일");
	        JMenuItem printReceiptItem = new JMenuItem("영수증 프린트");
	        printReceiptItem.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                printReceiptToFile(table_pay_info);
	            }
	        });
	        receiptPrintMenu.add(printReceiptItem);
	        JMenuBar menuBar = new JMenuBar();
	        menuBar.add(receiptPrintMenu);
	        setJMenuBar(menuBar);


		  	
		//테이블 생성
	  	table_room = new JTable(rowData_room, colNames_room);
		table_cust = new JTable(rowData_c, colNames_c);
		table_pay = new JTable(rowData_pay, colNames_pay);
		table_cust_info = new JTable(rowData_c_info, colNames_c_info);
		table_pay_info = new JTable(rowData_pay_info, colNames_pay_info);
		
		//상단 제목 라벨 생성 및 배치
		lb_room = new JLabel("「 방관리 」");
		lb_cust = new JLabel("「 회원관리 」");
		lb_pay = new JLabel("「 결제관리 」");
		Font labelFont = new Font("Arial", Font.PLAIN, 20);
	    lb_room.setFont(labelFont);
	    lb_cust.setFont(labelFont);
	    lb_pay.setFont(labelFont);
		lb_room.setHorizontalAlignment(JLabel.CENTER);
	    lb_cust.setHorizontalAlignment(JLabel.CENTER);
	    lb_pay.setHorizontalAlignment(JLabel.CENTER);
	    	   
	    
	    //배경패널 및 메인 패널
	    JPanel p_manager_background = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.setColor( getBackground() );
	            g.fillRect(0, 0, getWidth(), getHeight());
	        }
	    };
	    p_manager_background.setLayout(null);
	    p_manager_background.setOpaque(false);
	    p_manager_background.setBackground(new Color(17, 38, 79));

	    JPanel p_manager_main = new JPanel();
	    p_manager_main.setLayout(null);

	    //로그아웃 버튼
	  	JButton btn_manager_logout = new JButton("로그아웃");
	  	p_manager_main.add(btn_manager_logout);
	  	btn_manager_logout.setBounds(20, 880, 80, 30);
	    
	    //방테이블 패널
		JPanel p_roomInfo = new JPanel(); //개별 방 정보 패
		p_roomInfo.setLayout(new GridLayout(1,1));
		JScrollPane jsp_roomInfo = new JScrollPane(table_room);
		p_roomInfo.add(jsp_roomInfo);
		
		//고객테이블 패널
		JPanel p_cust = new JPanel();
		p_cust.setLayout(new GridLayout(1,1));
		JScrollPane jsp_cust = new JScrollPane(table_cust);
		p_cust.add(jsp_cust);
		
		//고객 개별 검색 패널
		JPanel p_custInfo = new JPanel(); //개별 회원 정보
		p_custInfo.setLayout(new GridLayout(1,1));
		JScrollPane jsp_custInfo = new JScrollPane(table_cust_info);
		p_custInfo.add(jsp_custInfo);
		
		//페이테이블 패널
		JPanel p_pay = new JPanel();
		p_pay.setLayout(new GridLayout(1,1));
		JScrollPane jsp_pay = new JScrollPane(table_pay);
		p_pay.add(jsp_pay);
		
		//개별페이 패널
		JPanel p_payInfo = new JPanel(); //개별 결제 기록 
		p_payInfo.setLayout(new GridLayout(1,1));
		JScrollPane jsp_payInfo = new JScrollPane(table_pay_info);
		p_payInfo.add(jsp_payInfo);
		
		//룸버튼 생성 및 패널에 넣기
		JPanel func_room = new JPanel();
		func_room.setLayout(new GridLayout(2,6));
		for (int i = 0; i < 12; i++) {
	        roomButtons[i] = new JButton("Room " + (i + 1));
	        func_room.add(roomButtons[i]); //1번~10번룸은 p_room_top
	    }
		
		//회원검색 버튼&필드
		JButton btn_cust_new = new JButton("신규가입");
		JButton btn_cust_search = new JButton("회원찾기");	
		JButton btn_cust_update = new JButton("정보변경");	
		JButton btn_cust_delete = new JButton("회원탈퇴");
		JTextField jtf_cust_phone = new JTextField(10);
		JTextField jtf_cust_password = new JTextField(10);		
		
		//회원 검색 패널 라벨
		JPanel func_cust = new JPanel();
		func_cust.add(new JLabel("회원전화"));
		func_cust.add(jtf_cust_phone);
		func_cust.add(new JLabel("비밀번호"));
		func_cust.add(jtf_cust_password);
		JPanel p_cust_set = new JPanel();
		p_cust_set.setLayout(new GridLayout(1,4));
		p_cust_set.add(btn_cust_new);
		p_cust_set.add(btn_cust_search);
		p_cust_set.add(btn_cust_update);
		p_cust_set.add(btn_cust_delete);
		func_cust.add(p_cust_set);
		
		//결제 패널/버튼/텍스트필드 생성
		JPanel func_pay = new JPanel();
		JButton btn_pay_periodSearch = new JButton("결제 찾기");	
		JButton btn_pay_custSearch = new JButton("회원 찾기");	
		JTextField jtf_pay_begin = new JTextField(10); //조회 시작위치 값 
		JTextField jtf_pay_end = new JTextField(10); //조회 끝위치 값
		JTextField jtf_pay_cust_phone = new JTextField(19); //회원 폰번호값 

		//결제 조회 기간으로 찾기
		JLabel notice_date = new JLabel("( 날짜 입력 형식: 11월 23일 2023년 입력시, 23/11/2023 )");
		notice_date.setBounds(70, 829, 450, 30);
		func_pay.add(new JLabel("조회 시작")); 
		func_pay.add(jtf_pay_begin);
		func_pay.add(new JLabel("조회 마침"));
		func_pay.add(jtf_pay_end);
		func_pay.add(btn_pay_periodSearch);
		
		//최하단 결제 조회 전회번호로 찾기  
		JPanel p_pay_set = new JPanel();
		p_pay_set.setLayout(new GridLayout(1,2));
		func_pay.add(new JLabel("회원 결제 찾기(전화번호)")); //phone number
		func_pay.add(jtf_pay_cust_phone);
		func_pay.add(btn_pay_custSearch);
		func_cust.add(p_cust_set);	
	
		
		//메인패널
		add(p_manager_background);
		p_manager_background.setBounds(0,0,940,990);
		p_manager_background.add(p_manager_main);
		p_manager_main.setBounds(20,20,890,920);
		//관리제목
		p_manager_main.add(lb_cust);
		p_manager_main.add(lb_room);
		p_manager_main.add(lb_pay);
		lb_room.setBounds(10, 10, 100, 25);
		lb_cust.setBounds(450, 10, 100, 25);
		lb_pay.setBounds(20, 410, 100, 25);
		//회원관리
		p_manager_main.add(p_cust);
		JLabel lb_custInfo = new JLabel("** 회원조회결과 **");
		p_manager_main.add(lb_custInfo);
		p_manager_main.add(p_custInfo);
		p_manager_main.add(func_cust);
		p_cust.setBounds(450, 40, 420, 200);
		lb_custInfo.setBounds(450, 240, 420,30);
		p_custInfo.setBounds(450, 270, 420, 50);
		func_cust.setBounds(450, 325, 420, 100);
		resizeColumnWidth(table_cust);
		resizeColumnWidth(table_cust_info);

		//결제관리
		p_manager_main.add(notice_date);
		p_manager_main.add(p_pay);
		JLabel lb_payInfo = new JLabel("** 결제조회결과 **");
		p_manager_main.add(lb_payInfo);
		p_manager_main.add(p_payInfo);
		p_manager_main.add(func_pay);
		p_pay.setBounds(20, 440, 850, 200);
		lb_payInfo.setBounds(20, 640, 420,30);
		p_payInfo.setBounds(20, 670, 850, 150);
		func_pay.setBounds(390, 825, 500, 100);
		resizeColumnWidth(table_pay);
		resizeColumnWidth(table_pay_info);

		//방관리
		p_manager_main.add(p_roomInfo);
		p_manager_main.add(func_room);
		p_roomInfo.setBounds(20, 40, 410, 200);
		func_room.setBounds(20, 250, 410, 150);
		resizeColumnWidth(table_room);
		
		btn_cust_new.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String phone = jtf_cust_phone.getText();
		        String newPassword = jtf_cust_password.getText();
	
		        Manager_c_new_dao newDao = new Manager_c_new_dao();
		        newDao.newCustomer(phone, newPassword);
		        refreshCustomerTable();
		        refreshCustomerTable(phone);
		        jtf_cust_phone.setText("");
		        jtf_cust_password.setText("");
		    }
		});
		
		btn_cust_search.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Manager_c_search_dao manager_c_search_dao = new Manager_c_search_dao();
		        ArrayList<Manager_custInfo_vo> custInfo = manager_c_search_dao.SearchCustomer(jtf_cust_phone.getText());
	
	/*요기===>*/	DefaultTableModel model = (DefaultTableModel) table_cust_info.getModel(); //<== 요기가 바뀜;
		        model.setRowCount(0);
	
		        for (Manager_custInfo_vo customer : custInfo) {
	    	            Vector<String> row = new Vector<>();
	    	            row.add(String.valueOf(customer.getCno()));
	    	            row.add(customer.getC_phone());
	    	            row.add(customer.getC_password());
	    	            row.add(String.valueOf(customer.getC_total_minutes()));
	    	            model.addRow(row);    
	    	    
	    	    }
		        jtf_cust_phone.setText("");
		        jtf_cust_password.setText("");
		    }
		});
		
		btn_cust_update.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String phone = jtf_cust_phone.getText();
		        String newPassword = jtf_cust_password.getText();
	
		        Manager_c_update_dao updateDao = new Manager_c_update_dao();
		        updateDao.updateCustomerPassword(phone, newPassword);
		        refreshCustomerTable();
		        refreshCustomerTable(phone);
		        jtf_cust_phone.setText("");
		        jtf_cust_password.setText("");
		    }
		});
		
		btn_cust_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String phone = jtf_cust_phone.getText();
		        String checkPassword = jtf_cust_password.getText();
		        Manager_c_delete_dao deleteDao = new Manager_c_delete_dao();
		        deleteDao.deleteCustomer(phone, checkPassword);
		        refreshCustomerTable();
		        jtf_cust_phone.setText("");
		        jtf_cust_password.setText("");
			}
		});
		
		btn_pay_custSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String phone = jtf_pay_cust_phone.getText();
				Manager_pay_searchCustomer_dao mpscd = new Manager_pay_searchCustomer_dao();
		        ArrayList<Manager_pay_vo> payList = mpscd.listPayCustomer(phone);
				DefaultTableModel model = (DefaultTableModel) table_pay.getModel();
		        model.setRowCount(0);
		        
			  	for (Manager_pay_vo payment : payList) {
			  	    Vector<String> row = new Vector<>();
			  	    row.add(String.valueOf(payment.getPno()));
			  	    row.add(String.valueOf(payment.getOrder_num()));
			  	    row.add(payment.getPt_type());
			  	    row.add(String.valueOf(payment.getCno()));
			  	    row.add(payment.getF_name());
			  	    row.add(String.valueOf(payment.getP_cnt()));
			  	    row.add(String.valueOf(payment.getRno()));
			  	    row.add(String.valueOf(payment.getP_minute()));
			  	    row.add(payment.getPaytime());
			  	    row.add(payment.getEndtime());
			  	    row.add(String.valueOf(payment.getSaleprice()));
			  	    model.addRow(row);
			  	}
			  	jtf_pay_begin.setText("");
			  	jtf_pay_end.setText("");
		        jtf_pay_cust_phone.setText("");
			}
		}); 
		
		btn_pay_periodSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String periodBegin = jtf_pay_begin.getText();
				String periodEnd = jtf_pay_end.getText();
				Manager_pay_searchPeriod_dao mpspd = new Manager_pay_searchPeriod_dao();
		        ArrayList<Manager_pay_vo> payList = mpspd.listPayPeriod(periodBegin, periodEnd);
				DefaultTableModel model = (DefaultTableModel) table_pay.getModel();
		        model.setRowCount(0);
		        
			  	for (Manager_pay_vo payment : payList) {
			  	    Vector<String> row = new Vector<>();
			  	    row.add(String.valueOf(payment.getPno()));
			  	    row.add(String.valueOf(payment.getOrder_num()));
			  	    row.add(payment.getPt_type());
			  	    row.add(String.valueOf(payment.getCno()));
			  	    row.add(payment.getF_name());
			  	    row.add(String.valueOf(payment.getP_cnt()));
			  	    row.add(String.valueOf(payment.getRno()));
			  	    row.add(String.valueOf(payment.getP_minute()));
			  	    row.add(payment.getPaytime());
			  	    row.add(payment.getEndtime());
			  	    row.add(String.valueOf(payment.getSaleprice()));
			  	    model.addRow(row);
			  	}
			  	jtf_pay_begin.setText("");
			  	jtf_pay_end.setText("");
		        jtf_pay_cust_phone.setText("");
			}
		});
		
		
		
		for (int j = 0; j < roomButtons.length; j++) {
		    final int buttonIndex = j;  // Create a final variable
	
		    roomButtons[j].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		            int rno = buttonIndex + 1;  // Use the final variable
		            Manager_roomInfo_dao mrid = new Manager_roomInfo_dao();
		            ArrayList<Manager_roomInfo_vo> roomlist = mrid.listRoom(rno);
		            DefaultTableModel model = (DefaultTableModel) table_room.getModel();
		            model.setRowCount(0);
	
		            for (Manager_roomInfo_vo room1 : roomlist) {
		                Vector<String> row = new Vector<>();
		                row.add(String.valueOf(room1.getRno()));
		                row.add(room1.getPaytime());
		                row.add(room1.getEndtime());
		                row.add(String.valueOf(room1.getCno()));
		                row.add(String.valueOf(room1.getMinutes_left()));
		                //실제로 남은 시간이 있는 방만 출력
//		                if(room1.getEndtime() <= System.currentTimeMillis()) {
//		                    row.add("00");
//		                } else {
//		                    row.add(String.valueOf(room1.getMinutes_left()));
//		                }
		                model.addRow(row);
		            }
		        }
		    });
		}	
		
		table_cust.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        @Override
	        public void valueChanged(ListSelectionEvent event) {
	            if (!event.getValueIsAdjusting()) {
	                // 선택된 행 얻기
	                int selectedRow = table_cust.getSelectedRow(); 
	                if (selectedRow != -1) {
	                    Object cnoValue = table_cust.getValueAt(selectedRow, 0);
	                    String selectedCno = cnoValue.toString();                    
	                    // 선택된 pno를 사용하여 데이터베이스 쿼리 수행
	                    Manager_custInfo_dao mcidao = new Manager_custInfo_dao();
	                    ArrayList<Manager_custInfo_vo> custInfolist = mcidao.infoCustomer(Integer.parseInt(selectedCno));;
	                    DefaultTableModel model = (DefaultTableModel) table_cust_info.getModel();
	        	        model.setRowCount(0);
	        	        
	        		    for (Manager_custInfo_vo c : custInfolist) {
	        		          Vector<String> row = new Vector<>();
	        		          row.add(String.valueOf(c.getCno()));
	        		          row.add(c.getC_phone());
	        		          row.add(c.getC_password());
	        		          row.add(String.valueOf(c.getC_total_minutes()));
		        	          model.addRow(row);
	        		    }   
	                    
	                }
	    	    }
	            
	            }
	        
	    });
		
		
		table_pay.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        @Override
	        public void valueChanged(ListSelectionEvent event) {
	            if (!event.getValueIsAdjusting()) {
	                int selectedRow = table_pay.getSelectedRow(); 
	                if (selectedRow != -1) {
	                    Object onoValue = table_pay.getValueAt(selectedRow, 1);
	                    String selectedOno = onoValue.toString();                    
	                    Manager_pay_DisplayOrder_dao mpd_dao = new Manager_pay_DisplayOrder_dao();
	                    ArrayList<Manager_pay_DisplayOrder_vo> payInfolist = mpd_dao.listPay(Integer.parseInt(selectedOno));;
	                    DefaultTableModel model = (DefaultTableModel) table_pay_info.getModel();
	        	        model.setRowCount(0);
	                    for (Manager_pay_DisplayOrder_vo pay : payInfolist) {
	        	            Vector<String> row = new Vector<>();
	        	            row.add(String.valueOf(pay.getRowNum()));
	        	            row.add(String.valueOf(pay.getCno()));
	        	            row.add(String.valueOf(pay.getRno()));
	        	            row.add(String.valueOf(pay.getP_minute()));
	        	            row.add(pay.getF_name());
	        	            row.add(String.valueOf(pay.getP_cnt()));
	        	            row.add(String.valueOf(pay.getSaleprice()));
	        	            row.add(pay.getPaytime());
	        	            model.addRow(row);
	        	        }
	                }
	    	    }
	            
	            }
	        
	    });

		btn_manager_logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					dispose(); // 현재 프레임을 종료합니다.
					new ManagerLogin(); // 새로운 프레임을 생성합니다.
				}
			}
		});
		
		
		setTitle("노래방 관리자");
		setSize(930,1010);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
}

public class ManagerFrameMain {
	public static void main(String[] args) {
		new ManagerLogin();
		//new ManagerFrame();
	}

}
