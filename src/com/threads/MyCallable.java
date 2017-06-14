package com.threads;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<String>{
	private String url;
	
	 public MyCallable(String url) {
		// TODO Auto-generated constructor stub
		 this.url = url;
	 }

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
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
			System.out.println("response -- > "+response.toString());
		} else {
			System.out.println("GET request not worked");
		}
		
		
		return null;
	}

}
