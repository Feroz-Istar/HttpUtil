package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.complexobject.ComplexObject;
import com.complexobject.TaskSummaryPOJO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DBConnection {
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static SimpleDateFormat sss = new SimpleDateFormat("yyyy-MM-dd");
public static void main(String args[]) throws IOException, ParseException{

	String url = "http://elt.talentify.in:8080/t2c/user/457/complex";
	URL obj = new URL(url);
	HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	con.setRequestMethod("GET");
	int responseCode = con.getResponseCode();
	if (responseCode == HttpURLConnection.HTTP_OK) { // success
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		ComplexObject complexObject = gson.fromJson(response.toString(), ComplexObject.class);
		int count =0;
		
		for(TaskSummaryPOJO taskSummaryPOJO:complexObject.getTasks()){
			if(sss.parse(sss.format(taskSummaryPOJO.getDate())).compareTo(sss.parse(sss.format(new Date())))==0){
				count++;
				System.out.println(taskSummaryPOJO.getItemType());
			}
		}
		
		System.out.println(count);
}
}}
