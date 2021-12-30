package Blink;

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

	private final static String addProfile = "UPDATE Users SET fname = ?, lname = ? WHERE username=?";
	private final static String grabProfileData = "SELECT * FROM Users WHERE username=?";
	private final static String getNumOnlineUsers = "SELECT * FROM Users WHERE state=?";
	private final static String getSuccessRate = "SELECT * FROM Stats WHERE ID=?";
	private final static String newLoginCheck = "SELECT * FROM Users WHERE username=?";
	private final static String newLogin = "INSERT INTO Users (username,fname,lname,state,password,email,profileImg) VALUES(?,?,?,?,?,?,?)";
	
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
			// I need to do this.
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
	
	public String newLogin(String username, String password) {
		String result = "";
		if(username.length()==0) {
			result = "<br> Please enter a username";
		}else if(password.length()==0) {
			result = "<br> Please enter a password" ;
 		}else {
 			try {
 				st = conn.createStatement();
 				ps = conn.prepareStatement(newLoginCheck);
 				ps.setString(1, username);
 				rs = ps.executeQuery();
 				if(rs.next()) {
 					result = "<br> This username already exist. Please pick another one.";
 				}else {
 					System.out.println("user is added: " + username);
 					ps = conn.prepareStatement(newLogin);
 					ps.setString(1, username); 
 					ps.setString(2, "");
 					ps.setString(3, "");
 					ps.setInt(4,1);
 					ps.setString(5,password);
 					ps.setString(6,"");
 					ps.setString(7,"");
 					
 					ps.executeUpdate();
 					result = "";
 				}
 			}catch(SQLException e) {
 				e.printStackTrace();
 			}
 		}
		return result;
	}

	// connects to the database
	public static boolean setConn() {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BlinkData?user=root&password=Yudeveloper1506!&useSSL=false");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}
}

