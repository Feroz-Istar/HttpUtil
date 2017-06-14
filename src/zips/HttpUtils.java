package zips;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.complexobject.ComplexObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HttpUtils {
	static String course_ids ="[5,6,7,8,9,10,12,13,14,16,19,20,31,37,51]";
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	public static void main(String args[]) throws IOException {
		ArrayList<String> error_course_zip = new ArrayList<>();
		ArrayList<Double> cal = new ArrayList<>();
		ArrayList<Double> ids = gson.fromJson(course_ids, ArrayList.class);
		for(Double course:ids){
		String url = "http://cdn.talentify.in//courseZIPs/"+course.intValue()+".zip";
		System.out.println(url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			Double d = ((double) con.getContentLength() / 1000000);
			error_course_zip.add(course.toString()+" size is "+d);
			cal.add(d);

		}else{
			error_course_zip.add(course.toString()+" not present");
		}
		}
		
		for(String val:error_course_zip){
			System.out.println(val);
		}
		Double sum=0.0;
		for(Double d:cal){
			sum = sum+d;
		}
		System.out.println("total size of course is --> "+sum);
	}
}