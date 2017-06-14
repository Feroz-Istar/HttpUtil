package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class AddressFetch {
	 private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	 private static Gson gson1 = new Gson();

		public static void main(String args[]) throws IOException {
			Connection c = null;
			Statement stmt = null;
			ResultSet rs = null;
			HashMap<Integer,String> ids = new HashMap<>();
			try {
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://elt.talentify.in:5432/talentify", "postgres", "fca65afc-3f40");
		         stmt = c.createStatement();
		         rs  = stmt.executeQuery( "select id, addressline1|| ' ' ||addressline2 as address, pincode_id, address_geo_longitude, address_geo_latitude from  address ;" );
		         while ( rs.next() ) {
		             int id = rs.getInt("id");
		             ids.put(id,rs.getString("address"));
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
			
			
			try {
				Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection("jdbc:postgresql://elt.talentify.in:5432/talentify", "postgres", "fca65afc-3f40");
		        
			
			
			for(Integer key:ids.keySet()){
/*				if(key > 2800){
*/					String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+ URLEncoder.encode(ids.get(key), "UTF-8")+"&key=AIzaSyDWCTlfx3VSoXY7FXtp0Bi2L_9iCCkYvac";
					System.out.println("url ------------> "+url);

					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();
					con.setRequestMethod("GET");
					int responseCode = con.getResponseCode();
					if (responseCode == HttpURLConnection.HTTP_OK) {
						BufferedReader in = new BufferedReader(new InputStreamReader(
								con.getInputStream()));
						String inputLine;
						StringBuffer response = new StringBuffer();

						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						in.close();
						try {
							JSONObject  jsonObject = new JSONObject (response.toString());
							JSONArray results = jsonObject.getJSONArray("results");
					        JSONObject item = results.getJSONObject(0);//read first item of results
					        JSONObject geometry = item.getJSONObject("geometry");//location is inside geometry object
					        JSONObject location = geometry.getJSONObject("location");
					        double latitude = location.getDouble("lat");
					        double longitude = location.getDouble("lng");
					        
					         stmt = c.createStatement();
						         String query ="UPDATE pincode SET  longitude="+longitude+", lattiude="+latitude+" WHERE id="+key+";";
									System.out.println("response ------------> "+query);

						          stmt.executeUpdate(query);
						        

							
					        
					        
					        
					        
					        
					        
					        
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				/*}*/
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
			//System.out.println(gson.toJson(ids));
		}
}
