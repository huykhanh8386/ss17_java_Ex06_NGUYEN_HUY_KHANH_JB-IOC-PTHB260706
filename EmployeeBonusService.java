package Ex06;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
public class EmployeeBonusService {
    public void calculateAndPrintBonus(int empId) {
        String sql = "{call get_employee_bonus(?, ?, ?)}";
        try (
                Connection con = ConnectDB.openConnection();
                CallableStatement cs = con.prepareCall(sql)
        ) {
            cs.setInt(1, empId);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.execute();
            String fullName = cs.getString(2);
            BigDecimal bonus = cs.getBigDecimal(3);
            if ("NOT_FOUND".equals(fullName)) {
                System.out.println("[KHÔNG TÌM THẤY] Nhân viên với ID = " + empId + " không tồn tại trong hệ thống!");
            } else {
                System.out.println("[PHIẾU TÍNH THƯỞNG] Nhân viên ID #" + empId);
                System.out.println(" - Họ và tên  : " + fullName);
                System.out.printf(" - Mức thưởng : %,.2f VND%n", bonus);
            }
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}