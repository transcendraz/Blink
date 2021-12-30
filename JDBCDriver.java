import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.google.gson.Gson;

import javafx.util.Pair;

public class JDBCDriver {

	private static Connection conn = null;
	private static Statement st = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static String check = null;
	private static String userToCheck = null;

	// Database Queries

	private final static String addProfile = "UPDATE 'Users' SET 'fname' = ?, 'lname' = ? WHERE userId=?";
	private final static String grabProfileData = "SELECT * FROM Users WHERE userId=?";
	private final static String getNumOnlineUsers = "SELECT * FROM Users WHERE state=?";
	private final static String getSuccessRate = "SELECT * FROM Stats WHERE ID=?";
	private final static String getSurv = "SELECT * FROM Survey WHERE ID=?";

	//stuff to add events
	
	public JDBCDriver() {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} finally {

		}
	}
	
	public void addProfile(String username, String lname, String fname) {
		try {
			ps = conn.prepareStatement(addProfile);
			ps.setString(1, lname);
			ps.setString(2, fname);
			ps.setString(3, username);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String grabProfileData(String username) {
		String result = "";
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(grabProfileData);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while(rs.next()) {
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				System.out.println("fname: " + fname + ", " + "lname: " + lname);
				result = fname + "," + lname;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// TODO test and debug this!
	// Returns the current number of online users
	public int getNumOnlineUsers() {
		int numOnlineUsers = 0;
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(getNumOnlineUsers);
			// Only grab users who have a state "true" -- online
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			while(rs.next()) {
				numOnlineUsers++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numOnlineUsers;
	}

	// TODO test and debug this!
	// Returns the success rate
	public double getSuccessRate() {
		// first row = total matches, second row = successful matches
		double successRate = 0;
		int totalMatches = 0;
		int successfulMatches = 0;
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(getSuccessRate);
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			while(rs.next()) {
				totalMatches = rs.getInt("matches");
			}
			
			ps.setInt(1, 2);
			rs = ps.executeQuery();
			while(rs.next()) {
				successfulMatches = rs.getInt("matches");
			}
			System.out.println("222");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		if (totalMatches > 0) {
			successRate = successfulMatches/totalMatches;
		} else {
			successRate = 0;
		}
		return successRate;

	}

	public String getSurvey(int id) {
		String temp = "";
		
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(getSurv);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				temp = temp + rs.getString(2) + '|';
				temp = temp + rs.getString(3) + '|';
				temp = temp + rs.getInt(4) + '|';
				temp = temp + rs.getString(5) + '|';
				temp = temp + rs.getString(6) + '|';
				temp = temp + rs.getString(7) + '|';
				temp = temp + rs.getString(8) + '|';
				temp = temp + rs.getString(9) + '|';
				temp = temp + rs.getString(10) + '|';
				temp = temp + rs.getString(11) + '|';
				temp = temp + rs.getString(12) + '|';
				temp = temp + rs.getString(13);				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		System.out.println(temp);
		return temp;
	}
	
	// connects to the database
	public static boolean setConn() {

		try {
			conn = DriverManager.getConnection(
					//VERY IMPORTANT => this will depend on ur database 
					//(tony u should have password as root >_> almost made me spend a few hours debugging)
					"jdbc:mysql://localhost/blinkdata?user=root&password=root&useSSL=false");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}


}