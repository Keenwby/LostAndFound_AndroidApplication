package DataBase;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Entities.Category;

public class DBRead extends DBConnection{
	
	private Connection conn = null;
	private Statement st = null;
	
	public Category getAllItems(String catName){
		OpenDB();
		Category category = new Category(catName, Category.DEFAULT);
		try{
			// Get the category id
			ResultSet res = st.executeQuery("SELECT * FROM CATEGORY WHERE CategoryName = '"+catName + "';");
			int categoryID = -1;
			while(res.next()){
				categoryID = res.getInt("id");
			}
			// Get all corresponding items
			res = st.executeQuery("SELECT * FROM ITEM WHERE categoryID = '"+categoryID +"';");
			while(res.next()){
				category.createItem(res.getString("Location"), res.getString("Time"));
			}
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
		return category;
	}
	
	public Category getItem(String catName, String location, String time){

		OpenDB();
		Category cat = null;
		int value = Category.DEFAULT;
		
		try{
			// Get the CategoryID
			ResultSet res = st.executeQuery("SELECT * FROM CATEGORY WHERE CategoryName = '"+catName + "';");
			int categoryID = -1;
			//Get kind
			String kind = null;
			while(res.next()){
				categoryID = res.getInt("id");
				kind = res.getString("KindName");
			}
			System.out.println("categoryID= " + categoryID);
			if(kind.equalsIgnoreCase("Valuable"))
				value = Category.VALUEABLE;
			if(kind.equalsIgnoreCase("Common"))
				value = Category.COMMON;
			//Create a new Category
			cat = new Category(catName, value);
			cat.createItem(location, time);
			//System.out.println("location= " + location + "time= "+ time);
			//Get Images
			res = st.executeQuery("SELECT * FROM ITEM WHERE Location = '"+location +"' AND Time = '" + time 
					+"' AND categoryID = '" + categoryID +"';");
			int itemID = -1;
			while(res.next()){
				itemID = res.getInt("id");
			}
			//System.out.println("itemid= " + itemID);
			res = st.executeQuery("SELECT * FROM IMAGES WHERE itemID = '"+itemID +"';");
			while(res.next()){
				Blob blob1 = res.getBlob("Image_1");
				Blob blob2 = res.getBlob("Image_2");
				int blob1Length = (int)blob1.length();
				int blob2Length = (int)blob2.length();
				cat.setImage(0, blob1.getBytes(1, blob1Length), blob2.getBytes(1, blob2Length));
				blob1.free(); // Release blob1
				blob2.free(); // Release blob2
			}
			res = st.executeQuery("SELECT * FROM QUESTIONS WHERE itemID = '"+itemID +"';");
			while(res.next()){
				String[] questions = new String[3];
				questions[0] = res.getString("Question_1");
				questions[1] = res.getString("Question_2");
				questions[2] = res.getString("Question_3");
				cat.setQuestions(0, questions);
				//System.out.println(questions[0]);
			}
			res.close();
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
		return cat;
	}
	
	public Category getAnswer(String catName, String location, String time){
		OpenDB();
		Category cat = null;
		int value = Category.DEFAULT;
		
		try{
			// Get the CategoryID
			ResultSet res = st.executeQuery("SELECT * FROM CATEGORY WHERE CategoryName = '"+catName + "';");
			int categoryID = -1;
			//Get kind
			String kind = null;
			while(res.next()){
				categoryID = res.getInt("id");
				kind = res.getString("KindName");
			}
			System.out.println("categoryID= " + categoryID);
			if(kind.equalsIgnoreCase("Valuable"))
				value = Category.VALUEABLE;
			if(kind.equalsIgnoreCase("Common"))
				value = Category.COMMON;
			//Create a new Category
			cat = new Category(catName, value);
			cat.createItem(location, time);
			//System.out.println("location= " + location + "time= "+ time);
			//Get Images
			res = st.executeQuery("SELECT * FROM ITEM WHERE Location = '"+location +"' AND Time = '" + time 
					+"' AND categoryID = '" + categoryID +"';");
			int itemID = -1;
			while(res.next()){
				itemID = res.getInt("id");
			}
			System.out.println("itemid= " + itemID);
			res = st.executeQuery("SELECT * FROM QUESTIONS WHERE itemID = '"+itemID +"';");
			int finderid = -1;
			while(res.next()){
				finderid = res.getInt("userID");
			}
			System.out.println("finderid: " + finderid);
			
			res = st.executeQuery("SELECT * FROM ANSWERS WHERE FinderID = '"+finderid +"';");
			while(res.next()){
				String[] ans = new String[3];
				ans[0] = res.getString("Answer_1");
				ans[1] = res.getString("Answer_2");
				ans[2] = res.getString("Answer_3");
				String[] questions = new String[3];
				questions[0] = res.getString("Question_1");
				questions[1] = res.getString("Question_2");
				questions[2] = res.getString("Question_3");
				cat.setQuestions(0, questions);
				cat.setAnswers(0, ans);
				System.out.println(questions[0]);
				System.out.println(ans[0]);
			}
			res.close();
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
		return cat;
	}
	
	public int getRes(String catName, String location, String time){
		OpenDB();
		int result = -1;
		try{
			
			// Get the category id
			ResultSet res = st.executeQuery("SELECT * FROM CATEGORY WHERE CategoryName = '"+catName+"';");
			int categoryID = -1;
			while(res.next()){
				categoryID = res.getInt("id");
			}
			res = st.executeQuery("SELECT * FROM ITEM WHERE Location = '"+location +"' AND Time = '" + time 
					+"' AND categoryID = '" + categoryID +"';");

			while(res.next()){
				result = res.getInt("Correct");
			}
			System.out.println("result= " + result);
			res.close();
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
		return result;
	}
	
	public Category getItemwithContactInfo(String catName, String location, String time, String finderName){
		OpenDB();
		Category category = null;
		int value = Category.DEFAULT;
		
		try{
			// Get the CategoryID
			ResultSet res = st.executeQuery("SELECT * FROM CATEGORY WHERE CategoryName = '"+catName + "';");
			int categoryID = -1;
			//Get kind
			String kind = null;
			while(res.next()){
				categoryID = res.getInt("id");
				kind = res.getString("KindName");
			}
			System.out.println("categoryID= " + categoryID);
			if(kind.equalsIgnoreCase("Valuable"))
				value = Category.VALUEABLE;
			if(kind.equalsIgnoreCase("Common"))
				value = Category.COMMON;
			//Create a new Category
			category = new Category(catName, value);
			category.createItem(location, time);
			//Get Images
			res = st.executeQuery("SELECT * FROM ITEM WHERE Location = '"+location +"' AND Time = '" + time 
					+"' AND categoryID = '" + categoryID +"';");
			int itemID = -1;
			while(res.next()){
				itemID = res.getInt("id");
			}
			System.out.println("itemid= " + itemID);
			res = st.executeQuery("SELECT * FROM IMAGES WHERE itemID = '"+itemID +"';");
			while(res.next()){
				Blob blob1 = res.getBlob("Image_1");
				Blob blob2 = res.getBlob("Image_2");
				int blob1Length = (int)blob1.length();
				int blob2Length = (int)blob2.length();
				category.setImage(0, blob1.getBytes(1, blob1Length), blob2.getBytes(1, blob2Length));
				blob1.free(); // Release blob1
				blob2.free(); // Release blob2
			}
			res = st.executeQuery("SELECT * FROM USER WHERE UserName = '"+finderName +"';");
			while(res.next()){
				String contactInfo = res.getString("Contact");
				category.setFinderUsrName(0, contactInfo);
			}
			res.close();
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
		return category;
	}
	
	public int checkUser(String usrname, String password){
		
		OpenDB();
		int result = -99;
		try{
			// Get the CategoryID
			ResultSet res = st.executeQuery("SELECT * FROM USER WHERE UserName = '"+usrname + "';");
			int usrID = -1;
			String pw = null;
			while(res.next()){
				usrID = res.getInt("id");
				pw = res.getString("Password");
			}
			System.out.println("usrID= " + usrID);		
			if(usrID == DBInsert.NOTFOUND){
				result = DBInsert.NOTFOUND;
			}
			System.out.println("pw= " + pw);
			if(!pw.equals(null) && pw.equals(password)){
				result = DBInsert.PASS;
			}else{
				result = DBInsert.FAIL;
			}
			res.close();
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
		return result;
	}
	
	public void OpenDB(){
		try {
			conn = getConnection(URL);
			st = conn.createStatement();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeDB(){
		try {
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
