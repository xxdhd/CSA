package CSA;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.jdi.connect.spi.Connection;

public class Mysqltest {

	public static void main(String[] args) throws ClassNotFoundException,SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&character=utf8&useSSL=true";
		String username = "root";
		String passw = "mysql";
		
		java.sql.Connection connection =DriverManager.getConnection(url,username,passw);
		Statement statement = ((java.sql.Connection) connection).createStatement();
		String sql_createtabel = "create table NewSdept(SNO VARCHAR(20),Name VARCHAR(10),Age Integer,College VARCHAR(30));";
		boolean res0 = statement.execute(sql_createtabel);
		
		String sql_insert = "insert into NewSdept VALUES ('s001','老大',20,'计算机学院');";
		res0 = statement.execute(sql_insert);
		
		sql_insert = "insert into NewSdept VALUES ('s002','老二',19,'计算机学院');";
		res0 = statement.execute(sql_insert);
		
		sql_insert = "insert into NewSdept VALUES ('s003','老三',18,'计算机学院');";
		res0 = statement.execute(sql_insert);
		
		
		sql_insert = "insert into NewSdept VALUES ('s004','老四',17,'计算机学院');";
		res0 = statement.execute(sql_insert);
		
		String sql = "select * from NewSdept;";
		ResultSet res = statement.executeQuery(sql);
		while(res.next()) {
			System.out.println("SNO="+res.getObject("SNO"));
			System.out.println("Name="+res.getObject("Name"));
			System.out.println("Age="+res.getObject("Age"));
			System.out.println("College="+res.getObject("College"));
		}
		
		String sql_delete = "delete from NewSdept where sno='s004';";
		res0 = statement.execute(sql_delete);
		
		
		
		sql = "select * from NewSdept where sno='s003';";
		res = statement.executeQuery(sql);
		while(res.next()) {
			System.out.println("SNO="+res.getObject("SNO"));
			System.out.println("Name="+res.getObject("Name"));
			System.out.println("Age="+res.getObject("Age"));
			System.out.println("College="+res.getObject("College"));
		}
		
		String sql_alter = "update NewSdept set College='通信学院' where SNO='s001';";
		res0 = statement.execute(sql_alter);
		res.close();
		statement.close();
		connection.close();
	}
}
