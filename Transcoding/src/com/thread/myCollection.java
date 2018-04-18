package thread;


import java.sql.*;


/**
 * @Author smile_dip
 * @Date 2017/4/18 12:09
 * @Describe 连接oracle
 */
public class myCollection {

    //数据库驱动对象
    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    //数据库连接地址(数据库名)jdbc:oracle:thin:@ip:1521:liferpt
    public static final String URL = "jdbc:oracle:thin:@10.135.100.105:1521:liferpt";
    //登陆名
    public static final String USER = "dmuser";
    //登陆密码
    public static final String PWD = "dmuser";
    //创建数据库连接对象
    private Connection con = null;

    /**
     * 获取数据库连接
     */
    public Connection getCon() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return con;
    }


    /**
     * 关闭所有资源
     */
    public void closeAll() {
        if (con != null)
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

}
