package com.complexobject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseRankPOJO implements Serializable {

	private Integer id;
	private String name;
	private String description;
	private String imageURL;
	private List<StudentRankPOJO> allStudentRanks = new ArrayList<StudentRankPOJO>();
	
	public CourseRankPOJO(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public List<StudentRankPOJO> getAllStudentRanks() {
		return allStudentRanks;
	}
	public void setAllStudentRanks(List<StudentRankPOJO> allStudentRanks) {
		this.allStudentRanks = allStudentRanks;
	}
	
	
}
