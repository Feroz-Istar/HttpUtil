package com;

import java.io.UnsupportedEncodingException;

public class TestEncode {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String hh ="\u003c?xml version\u003d\"1.0\" encoding\u003d\"UTF-8\" standalone\u003d\"yes\"?\u003e\u003cvideo_lesson\u003e    \u003cid\u003e4\u003c/id\u003e    \u003cvideo_thumb_url\u003e/video/android_images/courses_files/2758f918-0f2c-43f6-8ec0-78963df4878a.png\u003c/video_thumb_url\u003e    \u003cvideo_url\u003e/video/android_images/courses_files/4c5187c0-b69e-4cc4-98a5-49f81653cb3a.mp4\u003c/video_url\u003e\u003c/video_lesson\u003e";
	String y =new String(hh.getBytes("ISO-8859-1"));
	System.out.println(y);
	
	}

}
