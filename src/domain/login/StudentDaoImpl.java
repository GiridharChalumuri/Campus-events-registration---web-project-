package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DbManager;



public class StudentDaoImpl implements StudentDao {

	static Connection conn;
	static PreparedStatement ps;
	DbManager db = new DbManager();


	@Override
	public Student validateStudent(Login login) {
		// TODO Auto-generated method stub
		Student student = new Student();
		try{
			conn = db.getConnection();
			ps =conn.prepareStatement("select * from Student where Email=? and UserPassword=?");
			ps.setString(1, login.getUsername());
			ps.setString(2, login.getPassword());

			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				student.setEmail(rs.getString(1));
				student.setUserPassword(rs.getString(2));
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return student;
	}

}
