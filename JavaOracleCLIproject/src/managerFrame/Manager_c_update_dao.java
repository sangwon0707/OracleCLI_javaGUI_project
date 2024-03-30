package managerFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Manager_c_update_dao {

    public void updateCustomerPassword(String phone, String newPassword) {
        String sql = "UPDATE customer SET c_password = '" + newPassword + "' WHERE c_phone = '" + phone + "'";

        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "c##coin";
        String password = "coin";

        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement(); // Added semicolon here
            int re = stmt.executeUpdate(sql);

            if (re > 0) {
                JOptionPane.showMessageDialog(null, "패스워드 변경이 성공하였습니다!");
            } else {
                JOptionPane.showMessageDialog(null, "패스워드 변경 실패!", "오류!", JOptionPane.ERROR_MESSAGE);
            }
            
            // Close the resources after use
            stmt.close();
            conn.close();
            
        } catch (Exception e) {
            // Show an error message
            JOptionPane.showMessageDialog(null, "Exception occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

