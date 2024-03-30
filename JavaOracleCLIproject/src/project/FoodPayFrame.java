package project;

/*
문제점1 : +버튼을 클릭해서 상품을 선택할 수는 있는데
    선택한 상품에서 상품명, 단가, 수량 등을 뽑아오는걸 못하는중
문제점2 : 선택한 상품의 요소들을 db로 보내려고 할 때,
      여러 상품을 한번에 선택했을 경우 어떻게 출력되는지 확인해봐야
문제점3 : VO, DAO 다 완성이 안됐음
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sist.dao.OrderNumDAO;
import com.sist.dao.RoomPayDAO;
import com.sist.vo.FoodInfoVO;
import com.sist.vo.OrderNumVO;
import com.sist.vo.PayVO;



class FoodPayFrame extends JFrame{
   int foodCount; //선택한 음식 수량
   int foodTotal=0; //선택한 음식 가격 합계
   JTextField jtf_num[];
   JTable foodTable;
 
   public FoodPayFrame () {
      //JFrame foodFrame = new JFrame("음식 메뉴 선택");        
      OrderNumDAO onDAO = new OrderNumDAO();
      
      
   
      //각 메뉴를 선택하면 그에 해당하는 패널이 노출(나머지는 숨김)
        JPanel NorthJPanel = new JPanel();
        JButton btn_foodback = new JButton("뒤로가기");     
        NorthJPanel.add(btn_foodback);
        NorthJPanel.setLayout(new FlowLayout());
   
      //음식선택패널
      JPanel p_centerFood = new JPanel();
      p_centerFood.setLayout(null);
      
      
      //음식종류
      String foodName[] = {"삼다수","코카콜라","펩시콜라","칠성사이다","포카칩","양파링","꼬깔콘","새우깡","고구마깡"};
      //음식 가격
      int foodPrice[] = {1000,1200,1200,1300,2000,2200,2000,2100,2500};
      
      
      JButton btn_food[] = new JButton[foodName.length];
      JTextField jtf_num[] = new JTextField[foodName.length];
      JButton btn_minus[] = new JButton[foodName.length];
      JButton btn_plus[] = new JButton[foodName.length];
      JLabel jlb_won[] = new JLabel[foodName.length];
      JLabel jlb_Fname[] = new JLabel[foodName.length];
        
        
      btn_food[0] = new JButton(new ImageIcon("./image/water.jpg"));
      btn_food[1] = new JButton(new ImageIcon("./image/cocacola.jpeg"));
      btn_food[2] = new JButton(new ImageIcon("./image/pepsi.jpg"));
      btn_food[3] = new JButton(new ImageIcon("./image/chilsung.jpg"));
      btn_food[4] = new JButton(new ImageIcon("./image/poka.jpeg"));
      btn_food[5] = new JButton(new ImageIcon("./image/onion.jpeg"));
      btn_food[6] = new JButton(new ImageIcon("./image/corn.jpeg"));
      btn_food[7] = new JButton(new ImageIcon("./image/shrimp.jpeg"));
      btn_food[8] = new JButton(new ImageIcon("./image/gogu.jpeg"));
   
        
      for (int i = 0; i < foodName.length; i++) {
         if(i<3) {
            btn_food[i].setBounds(210 * i + 40, 40, 180, 150);
         }
         else if(i<6) {
            btn_food[i].setBounds((i - 3) * 210 + 40, 280, 180, 150);
            
         }
         else {
            btn_food[i].setBounds((i - 6) * 210 + 40, 520, 180, 150);
         }
           
           
         //메뉴 수량
         jtf_num[i] = new JTextField("0");
         jtf_num[i].setBounds(btn_food[i].getX() + 60, btn_food[i].getY() + 180, 60, 30);
            
         btn_minus[i] = new JButton("-");
         btn_minus[i].setBounds(btn_food[i].getX(), jtf_num[i].getY(), 50, 30);
            
         btn_plus[i] = new JButton("+");
         btn_plus[i].setBounds(btn_food[i].getX() + (180 - 50), jtf_num[i].getY(), 50, 30);
          
         //메뉴이름
         jlb_Fname[i] = new JLabel(foodName[i]);
         jlb_Fname[i].setBounds(btn_food[i].getX()+70, btn_food[i].getY() - 20, 115, 20);            
            
         jlb_won[i] = new JLabel(foodPrice[i] + "원");
         jlb_won[i].setBounds(btn_food[i].getX() + 70, jtf_num[i].getY() - 25, 100, 20);
             
         p_centerFood.add(jlb_Fname[i]);
         p_centerFood.add(btn_food[i]);
         p_centerFood.add(jtf_num[i]);
         p_centerFood.add(btn_minus[i]);
         p_centerFood.add(btn_plus[i]);
         p_centerFood.add(jlb_won[i]);
        }
                
   
        //선택한(+) 상품 목록이 담길 표.
        JPanel SouthJPanel = new JPanel();
        TextArea foodList = new TextArea("");
        
        String[] [] data = new String[0][0];
        String[] title = {"상품명","단가","수량","합계"};
        DefaultTableModel foodSelect = new DefaultTableModel(data, title);
        JTable foodTable = new JTable(foodSelect);
        JScrollPane scrollPane = new JScrollPane(foodTable);
        scrollPane = new JScrollPane(foodTable);
        scrollPane.setPreferredSize(new Dimension(500,170));
        SouthJPanel.add(scrollPane);
        
        
        
        //상품 목록 표 옆 결제,삭제버튼, 총합
        JPanel p_price = new JPanel();
        p_price.setLayout(new GridLayout(3, 1));
        
        //결제버튼 
        JButton jbt_foodPayment = new JButton("결제");
        jbt_foodPayment.setPreferredSize(new Dimension(100, 30)); // 버튼 크기 조절
        //선택한 음식 총 합계금액
        JLabel jlb_totalPrice = new JLabel("합계 : "+foodTotal+"원");
        //선택한 음식 내역 표에서 전체 삭제
        JButton jbt_allClear = new JButton("전체 삭제");
        
        p_price.add(jlb_totalPrice);
        p_price.add(jbt_allClear);
        p_price.add(jbt_foodPayment); 
        
        SouthJPanel.add(scrollPane, BorderLayout.CENTER);
        SouthJPanel.add(p_price, BorderLayout.EAST);
       
        
        
        
        //@전체 삭제 버튼 클릭시 
        jbt_allClear.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            foodSelect.setNumRows(0); // 주문 내역 초기화
               foodList.setText("");
               foodTotal = 0;
               for (int i = 0; i < foodName.length; i++) {
                   jtf_num[i].setText("0");
               }
         }
        });
   
        
        //@결제 버튼 클릭 시
        jbt_foodPayment.addActionListener(new ActionListener() {
         
           @Override
            public void actionPerformed(ActionEvent e) {
   
              // (수정필요) 선택한 상품 종류와 개수, 금액 출력
                StringBuilder foodOrderList = new StringBuilder("주문 내역:\n");
                //새로운 문자열이 계속 생성되는 것을 방지하기 위해 StringBuilder 사용    
                
                // (확인필요) 선택한 음식 정보를 담는 리스트
                ArrayList<String> foodInfoList = new ArrayList<String>();  // 추가
                
                for (int i = 0; i < foodTable.getRowCount(); i++) {
                    foodOrderList.append(foodTable.getValueAt(i, 0))
                            .append(" ")
                            .append(foodTable.getValueAt(i, 1))
                            .append(" X ")
                            .append(foodTable.getValueAt(i, 2))
                            .append("개\n");
   
                    
                }
                
                
                // (수정필요) 함수 호출할 떄 ArrayList를 변수로 보내서 함수 호출?
   
                foodOrderList.append("총 금액 : ").append(foodTotal).append("원\n");
   
                // 선택한 상품 종류와 개수, 금액 출력
                foodList.setText(foodOrderList.toString());
   
                // 상품을 선택하지 않고 결제하려고 할 때
                if (foodTotal == 0) {
                    JOptionPane.showMessageDialog(null, "상품을 선택해 주십시오.");
                } else {
                    // 주문이 확인되면 주문 내역을 다이얼로그로 출력
                    int answer = JOptionPane.showConfirmDialog(null, foodList.getText() + "결제하시겠습니까?", "주문 상품 확인", JOptionPane.YES_NO_OPTION);
   
                    // 주문이 확인되면 주문 내역 초기화
                    if (answer == JOptionPane.YES_OPTION) {  
                    	new IntroFrame();
   
                       //여기 넣지 말고  for문에 선언 .FoodPayDAO fpDao = new FoodPayDAO();                       
                       
                       //for문전에 선언!
                       //반복문 끝나면(한번에 여러 상품구매) pay테이블에서 마지막 ordernum을 가져와서 +1
                        // 그값을  int order = (값)넣어준다
                        OrderNumDAO onDAO = new OrderNumDAO();
                        int orderA = onDAO.orderNumber();
                       int order = orderA+1;  //주문번호 선언                      
                       
                       
                       System.out.println("실행됨");
                                              
                        for (int i = 0; i < foodTable.getRowCount(); i++) {
                
                           //int pno = 
                           
                            String selected_Fname = (String)foodTable.getValueAt(i, 0); //상품명
                            //int selected_Fprice = (int)foodTable.getValueAt(i, 1);    //단가는 pay테이블에 필요없어 삭제
                            //int selected_Fcount = Integer.parseInt(foodTable.getValueAt(i, 2));      //수량
                            int selected_Fcount = 0;
                            try {
                                selected_Fcount = Integer.parseInt(foodTable.getValueAt(i, 2).toString());
                            } catch (NumberFormatException a) {
                               a.printStackTrace(); // 또는 다른 오류 처리 방법을 선택하세요.
                                continue; // 오류 발생 시 현재 상품은 건너뜁니다.
                            }
                            int selected_Fsum = Integer.parseInt(foodTable.getValueAt(i, 1).toString().replace("원", "")) *
                                  Integer.parseInt(foodTable.getValueAt(i, 2).toString());    //각 상품 수량x단가
                            PayVO pvo = new PayVO(selected_Fsum, selected_Fcount);
                            FoodInfoVO fvo = new FoodInfoVO(selected_Fname);
                            OrderNumVO ovo = new OrderNumVO(order);
                        RoomPayDAO fpDao = new RoomPayDAO();
                        fpDao.insertFood(pvo, fvo, ovo);
   
                        }                      
                      
                        JOptionPane.showMessageDialog(null, "주문이 완료되었습니다.\n");
                        foodTotal = 0;
                        foodList.setText("");
                        foodSelect.setNumRows(0);
   
                        // 음식 수량 초기화
                        for (int i = 0; i < foodName.length; i++) {
                            jtf_num[i].setText("0");
                        }
                        dispose();
                        
                    }
                }
            }
        });
        
        
   
       
        // 각 상품 +,-버튼 수량 조절        
        for (int i = 0; i < foodName.length; i++) {
            int j = i;
                       
            // "-"버튼 눌렀을 때
            btn_minus[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   foodCount = Integer.parseInt(jtf_num[j].getText().trim());  //현재 수량
   
                    if (foodCount > 0) {
                        foodCount = foodCount - 1;
                        jtf_num[j].setText(Integer.toString(foodCount));
   
                        if (foodCount == 0) {
                            //수량이 0이 되었을 때 표에서 삭제.
                            for (int foodIndex = 0; foodIndex < foodSelect.getRowCount(); foodIndex++) {
                                if (foodSelect.getValueAt(foodIndex, 0).equals(foodName[j])) {
                                    foodSelect.removeRow(foodIndex);
                                    break;
                                }
                            }
                            btn_minus[j].setEnabled(false);
                        } else {
                            // 기존상품이 담겨있으면 행을 추가하지 않고 수량, 가격 변경
                            int foodIndexToUpdate = -1;
                            for (int foodIndex = 0; foodIndex < foodSelect.getRowCount(); foodIndex++) {
                                if (foodSelect.getValueAt(foodIndex, 0).equals(foodName[j])) {
                                    foodIndexToUpdate = foodIndex;
                                    break;
                                }
                            }
   
                            if (foodIndexToUpdate != -1) {
                               foodTotal = foodTotal - foodPrice[j];
                                foodSelect.setValueAt(Integer.toString(foodCount), foodIndexToUpdate, 2); // 수량 업데이트
                                foodSelect.setValueAt(Integer.toString(foodPrice[j] * foodCount) + "원", foodIndexToUpdate, 3); // 합계 업데이트
                            }
                        }
                        jlb_totalPrice.setText("합계 : " + foodTotal +"원");
                    }
                }
            });
            
            
            // "+"버튼 눌렀을 때
            btn_plus[i].addActionListener(new ActionListener() {
   
                @Override
                public void actionPerformed(ActionEvent e) {
                   foodCount = Integer.parseInt(jtf_num[j].getText().trim()); // 기존 수량 가져오기
                    foodCount = foodCount + 1;
                    jtf_num[j].setText(Integer.toString(foodCount));
                    btn_minus[j].setEnabled(true);
   
                    foodTotal = foodTotal + foodPrice[j]; // 수량만큼 가격 추가
   
                    // 동일상품 수량 누적
                    boolean sameFood = false;
                    for (int foodIndex = 0; foodIndex < foodSelect.getRowCount(); foodIndex++) {
                        if (foodSelect.getValueAt(foodIndex, 0).equals(foodName[j])) {
                            // 수량, 합계 업데이트
                            sameFood = true;
                            foodSelect.setValueAt(Integer.toString(foodCount), foodIndex, 2); // 수량 업데이트
                            foodSelect.setValueAt(Integer.toString(foodPrice[j] * foodCount) + "원", foodIndex, 3); // 합계 업데이트
                            break;
                        }
                    }
   
                    // 이미 추가된 상품이 아닌 다른 상품을 추가하는 경우
                    if (!sameFood) {
                        String inputStr[] = new String[5];
                        inputStr[0] = foodName[j];
                        inputStr[1] = foodPrice[j] + "원";
                        inputStr[2] = Integer.toString(foodCount);
                        inputStr[3] = foodPrice[j] * foodCount + "원";
                        inputStr[4] = foodTotal + "원";
                        foodSelect.addRow(inputStr);                  
                    }
                    jlb_totalPrice.setText("합계 : " + foodTotal +"원");
                }
            });
            
        }
        
        
        btn_foodback.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            System.out.println("뒤로가기");
            dispose();
            new IntroFrame();
            
         }
       });
        
        
        //폰트 조절
        for (int i = 0; i < foodName.length; i++) {
               btn_food[i].setFont(new Font("고딕", Font.BOLD, 12));
               btn_minus[i].setFont(new Font("고딕", Font.BOLD, 12));
               btn_plus[i].setFont(new Font("고딕", Font.BOLD, 12));
           }

           
        for (int i = 0; i < foodName.length; i++) {
               jlb_Fname[i].setFont(new Font("고딕", Font.PLAIN, 12));
               jtf_num[i].setFont(new Font("고딕", Font.PLAIN, 12));
               jlb_won[i].setFont(new Font("고딕", Font.PLAIN, 12));
        }

        jbt_foodPayment.setFont(new Font("고딕", Font.BOLD, 12));
        jlb_totalPrice.setFont(new Font("고딕", Font.PLAIN, 12));
        jbt_allClear.setFont(new Font("고딕", Font.BOLD, 12));
        
        
        //버튼 컬러 수정
        for (int i = 0; i < foodName.length; i++) {
               Color lightGray = new Color(240, 240, 240);
               btn_food[i].setBackground(lightGray);
               btn_minus[i].setBackground(lightGray);
               btn_plus[i].setBackground(lightGray);
        }
        
        Color lightGray = new Color(240, 240, 240);
        jbt_foodPayment.setBackground(lightGray);
        jlb_totalPrice.setBackground(lightGray);
        jbt_allClear.setBackground(lightGray);

        NorthJPanel.setBackground(lightGray);
        p_centerFood.setBackground(lightGray);
        SouthJPanel.setBackground(lightGray);
        
        btn_foodback.setBackground(lightGray);
        
   
   
        NorthJPanel.setBackground(Color.WHITE);
        p_centerFood.setBackground(Color.WHITE);
        SouthJPanel.setBackground(Color.WHITE);
        jlb_totalPrice.setBackground(Color.WHITE); 
        
        add(NorthJPanel, BorderLayout.NORTH);
        add(p_centerFood, BorderLayout.CENTER);
        add(SouthJPanel, BorderLayout.SOUTH);
        setFont(new Font("고딕", Font.BOLD, 12));
        setSize(700, 1000);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
}