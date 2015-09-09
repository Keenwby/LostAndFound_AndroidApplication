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
	public int getItemsNum(){
        return items.size();
    }
    public String getLocandTimebyIndex(int i){
        return items.get(i).getLocation() + ":::" + items.get(i).getTime();
    }

	//Get an item
	public Item getItem(String location, String time){
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
		getItem(location, time).setImage_1(image_1);
		getItem(location, time).setImage_2(image_2);
	}
	
	public byte[] getImage_1(String location, String time){
		return getItem(location, time).getImage_1();
	}
	public byte[] getImage_2(String location, String time){
		return getItem(location, time).getImage_2();
	}
	
	public void setQuestions(String location, String time, String[] q){
		getItem(location, time).setQuestions(q);
	}
	public String[] getQuestions(String location, String time){
		return getItem(location, time).getQuestions();
	}
    public void setAnswers(String location, String time, String[] ans){
        getItem(location, time).setAnswers(ans);
    }
    public String[] getAnswers(String location, String time){
        return getItem(location, time).getAnswers();
    }
	public void setFinderName(String location, String time, String ip){
        getItem(location, time).setFinderUsrName(ip);
    }
    public String getFinderName(String location, String time){
        return getItem(location, time).getFinderUsrName();
    }
    public void setOwnerName(String location, String time, String name){
        getItem(location, time).setOwnerUsrName(name);
    }
    public String getOwnerName(String location, String time){
        return getItem(location, time).getOwnerUsrName();
    }

	//toString()
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", itemsnumber=" + items.size()
				+ "]";
	}
}
