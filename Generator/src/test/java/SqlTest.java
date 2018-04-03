import org.junit.Test;

import java.sql.*;

public class SqlTest {
    @Test
    public void  testPro1(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///db_6","root","rootroot");
            String sql = "{call p_1()}";
            CallableStatement callableStatement = conn.prepareCall(sql);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("prod_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    /*有返回值的存储过程*/
    public void testPro2(){
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql:///db_6","root","rootroot");
                String sql = "{call p_2(?,?)}";
                CallableStatement callableStatement = conn.prepareCall(sql);
                callableStatement.registerOutParameter(1, Types.DECIMAL);
                callableStatement.registerOutParameter(2,Types.DECIMAL);
                callableStatement.executeUpdate();
                float max = callableStatement.getFloat(1);
                float min = callableStatement.getFloat(2);
                System.out.println(max);
                System.out.println(min);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testPro3(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///db_6","root","rootroot");
            String sql = "{call p_3(?,?)}";
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setString(1,"ANV02");
            callableStatement.registerOutParameter(2,Types.DECIMAL);
            callableStatement.executeUpdate();
            float price = callableStatement.getFloat(2);
            System.out.println(price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testPro4(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql:///db_6","root","rootroot");
            String sql = "{call p_5(?,?)}";
            CallableStatement callableStatement = conn.prepareCall(sql);
            callableStatement.setString(1,"ANV02");
            callableStatement.setString(2,"13");
            callableStatement.executeUpdate();
            float price = callableStatement.getFloat(2);
            System.out.println(price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void findByIdTest(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL","scott","tiger");
            String sql = "select * from emp";
            CallableStatement callableStatement = conn.prepareCall(sql);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("ename"));
            }
            conn.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
