package managerFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Manager_c_delete_dao {
    public void deleteCustomer(String phoneNumber, String checkPassword) {
        
    	
//		이 쿼리가 먼저 실행되어야 String sql = "update pay set cno = 100 where c_phone = phone";
//		회원을 삭제 할 수 있다. 

//		***customer row 삭제전에 pay table을 업데이트 해주는 트리거 생성***
//   	CREATE OR REPLACE TRIGGER update_pay_before_customer_delete
//    	BEFORE DELETE ON customer
//    	FOR EACH ROW
//    	BEGIN
//    	    UPDATE pay p
//    	    SET p.cno = 100
//    	    WHERE p.cno = :OLD.cno;
//    	END;
//    	/
    	
    	// 여기서 phoneNumber는 삭제할 회원의 전화번호입니다. 패스워드가 일치해야만 삭제가 가능합니다. 
        String sql= "DELETE FROM customer WHERE c_phone = '" + phoneNumber + "' and c_password = '"+checkPassword+"' ";
        
        try {
            String driver = "oracle.jdbc.driver.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            String username = "c##coin";
            String password = "coin";

            Class.forName(driver);
            
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement stmt = conn.createStatement()) {

                int re = stmt.executeUpdate(sql);

                if (re > 0) {
                	JOptionPane.showMessageDialog(null, "회원탈퇴에 성공하였습니다!");
                } else {
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다!!", "탈퇴실패!", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception e) {
                System.out.println("예외발생: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }
}