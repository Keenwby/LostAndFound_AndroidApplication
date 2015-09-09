package Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String location;
	private String time;
	private String finderUsrName;
	private byte[] image_1;
	private byte[] image_2;
	private String[] question;
	private String[] answer;
	private String ownerUsrName;
	
	//Constructors
	protected Item(String location, String time){
		this.location = location;
		this.time = time;
	}
	
	//Getters & Setters
	protected int getId() {
		return id;
	}
	protected void setId(int id) {
		this.id = id;
	}
	protected String getLocation() {
		return location;
	}
	protected void setLocation(String location) {
		this.location = location;
	}
	protected String getTime() {
		return time;
	}
	protected void setTime(String time) {
		this.time = time;
	}
	
	protected String getFinderUsrName() {
		return finderUsrName;
	}

	protected void setFinderUsrName(String name) {
		this.finderUsrName = name;
	}
	protected byte[] getImage_1() {
		return image_1;
	}
	protected void setImage_1(byte[] image_1) {
		this.image_1 = image_1;
	}
	protected byte[] getImage_2() {
		return image_2;
	}
	protected void setImage_2(byte[] image_2) {
		this.image_2 = image_2;
	}
	protected String[] getQuestions() {
		return question;
	}
	protected void setQuestions(String[] ques) {
		this.question = new String[3];
		for(int i = 0; i < ques.length; i++){
			this.question[i] = ques[i];
		}
	}
	protected String[] getAnswers() {
		return this.answer;
	}

	protected void setAnswers(String[] ans) {
		this.answer = new String[3];
		for(int i = 0; i < ans.length; i++){
			this.answer[i] = ans[i];
		}
	}
	
	public String getOwnerUsrName() {
		return ownerUsrName;
	}

	public void setOwnerUsrName(String name) {
		this.ownerUsrName = name;
	}
	
	
	//toString()
	@Override
	public String toString() {
		return "Item [id=" + id + ", location=" + location + ", time=" + time
				+  "]";
	}
}
