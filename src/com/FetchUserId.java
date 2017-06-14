package com;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class FetchUserId {
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public static void main(String args[]) throws IOException {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Integer> ids = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://elt.talentify.in:5432/talentify", "postgres", "fca65afc-3f40");
	         stmt = c.createStatement();
	         rs  = stmt.executeQuery( "SELECT id FROM course;" );
	         while ( rs.next() ) {
	             int id = rs.getInt("id");
	             ids.add(id);
	         }

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
				if(stmt != null){
					stmt.close();
				}
				if (c != null) {
					c.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
		System.out.println(gson.toJson(ids));
	}
}
