package com.xumou.test.function.database;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 */
public class OracleTest {


    Connection connection;

    @Before
    public void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        String url = "jdbc:oracle:thin:@10.0.75.223:1521/testdb";
        String user = "sinosoft";
        String password = "sinosoft";
        connection = DriverManager.getConnection(url, user, password);
    }

    @Test
    public void test() throws SQLException {
        Statement statement = connection.createStatement();
        String sql =
                " INSERT INTO ES_INS_REC_20190130" +
                "   (未刷单原因," +
                "    执行月份," +
                "    业务月份," +
                "    唯一号," +
                "    姓名," +
                "    社会保障号," +
                "    缴费人员类别1," +
                "    险种," +
                "    单位登记证号," +
                "    商社名称," +
                "    商社编号," +
                "    开户状态," +
                "    所属社保局," +
                "    截止日," +
                "    缴费人员类别2," +
                "    缴费方式," +
                "    申报类型," +
                "    基数," +
                "    业务部," +
                "    业务代表)" +
                " VALUES" +
                "   (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";



    }

}