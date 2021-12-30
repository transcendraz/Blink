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

	private final static String addProfile = "INSERT INTO Users(lname, fname) VALUES(lname,fname)";

	private final static String grabProfileData = "SELECT * FROM Users WHERE userId=?";

	private final static String getNumOnlineUsers = "SELECT * FROM Users WHERE state=?";

	private final static String getSuccessRate = "SELECT * FROM Stats WHERE ID=?";

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

	public void addProfile(String lname, String fname) {
		try {
			ps = conn.prepareStatement(addProfile);
			ps.setString(1, lname);
			ps.setString(2, fname);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String grabProfileData(String userId) {
		String result = "";
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(grabProfileData);
			ps.setString(1, userId);
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
			ps.setString(1, true);
			rs = ps.executeQuery();
			while(rs.next()) {
				numUsers++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numOnlineUsers;
	}

	// TODO test and debug this!
	// Returns the success rate
	public float getSuccessRate() {
		float successRate = 0;
		int totalMatches = 0;
		int successfulMatches = 0;
		try {
			st = conn.createStatement();
			ps = conn.prepareStatement(getSuccessRate);
			// first row = total matches, second row = successful matches
			ps.setString(1, 1);
			rs = ps.executeQuery();
			totalMatches = rs.getString(2);
			ps.setString(1, 2);
			rs = ps.executeQuery();
			successfulMatches = rs.getString(2);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (totalMatches > 0) {
			successRate = successfulMatches/totalMatches;
		} else {
			successRate = 0;
		}
		return successRate;
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
