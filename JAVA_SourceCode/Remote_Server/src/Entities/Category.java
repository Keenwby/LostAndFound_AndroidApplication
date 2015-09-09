package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int VALUEABLE = 1 ;
	public static final int COMMON = 0 ;
	 public static final int DEFAULT = -1 ;
	
	private int id;
	private String name;
	private int value;
	private List<Item> items;
	
	//Constructor
	public Category(String name, int value){
		this.name = name;
		this.value = value;
		this.items = new ArrayList<Item>(0);
	}
	
	//Create an item
	public void createItem(String location, String time){
		Item item = new Item(location, time);
		items.add(item);
	}
	
	//Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	//Get Item by Index
	public Item getItemByIndex(int i) {
		if(items.size() != 0){
			return items.get(i);
		}else
			return null;
	}
	public String getLocation(int i){
		return getItemByIndex(i).getLocation();
	}
	public String getTime(int i){
		return getItemByIndex(i).getTime();
	}
	public String getFinderUsrName(int i){
		return getItemByIndex(i).getFinderUsrName();
	}
	public byte[] getImage_1(int i){
		return getItemByIndex(i).getImage_1();
	}
	public byte[] getImage_2(int i){
		return getItemByIndex(i).getImage_2();
	}
	public String[] getQuestions(int i){
		return getItemByIndex(i).getQuestions();
	}
	public String[] getAnswers(int i){
		return getItemByIndex(i).getAnswers();
	}
	public String getOwnerUsrName(int i){
		return getItemByIndex(i).getOwnerUsrName();
	}
	
	public void setImage(int i, byte[] image_1, byte[] image_2){
		getItemByIndex(i).setImage_1(image_1);
		getItemByIndex(i).setImage_2(image_2);
	}
	public void setQuestions(int i, String[] q){
		getItemByIndex(i).setQuestions(q);
	}
	public void setAnswers(int i, String[] ans){
		getItemByIndex(i).setAnswers(ans);
	}
	public void setFinderUsrName(int i, String finderName){
		getItemByIndex(i).setFinderUsrName(finderName);
	}
	
	/*
	//Get an item
	public Item getItemByName(String location, String time){
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getLocation().equalsIgnoreCase(location) &&
			   items.get(i).getTime().equalsIgnoreCase(time))
				return items.get(i);
		}
		return null;
	}
	
	public boolean findItem(String location, String time){
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).getLocation().equalsIgnoreCase(location) &&
			   items.get(i).getTime().equalsIgnoreCase(time))
				return true;
		}
		return false;
	}
	
	public void setImage(String location, String time, byte[] image_1, byte[] image_2){
		getItemByName(location, time).setImage_1(image_1);
		getItemByName(location, time).setImage_2(image_2);
	}
	
	public byte[] getImage_1(String location, String time){
		return getItemByName(location, time).getImage_1();
	}
	public byte[] getImage_2(String location, String time){
		return getItemByName(location, time).getImage_2();
	}
	public int getValue(String location, String time){
		return getItemByName(location, time).getValue();
	}
	
	public void setQuestions(String location, String time, String[] q){
		getItemByName(location, time).setQuestions(q);
	}
	public String[] getQuestions(String location, String time){
		return getItemByName(location, time).getQuestions();
	}
	
	//toString()
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", itemsnumber=" + items.size()
				+ "]";
	}*/
}
