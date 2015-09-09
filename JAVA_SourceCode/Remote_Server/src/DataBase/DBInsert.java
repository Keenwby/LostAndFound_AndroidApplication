package DataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInsert extends DBConnection{
	
	public static final int PASS = 1;
	public static final int FAIL = 0;
	public static final int NOTFOUND = -1;
	
	private Connection conn = null;
	private Statement st = null;
	
	public void insertUser(String usrname, String password, String cont, String ip){
		OpenDB();
		try{
			st.execute("INSERT INTO USER (UserName, Password, Contact, User_IP)"
					+ "VALUES('"+usrname+"', '"+password+"', '"+cont+"', '"+ip+"');");
		}catch(Exception e){
			System.out.println(e);
		}	
		closeDB();
	}
	
	public void insertCategory(String name, String kind, String location, String time,
							  String findername, byte[] image_1, byte[] image_2, String[] questions){
		OpenDB();
		int userID = -1;
		try{
			//Get the userID
			ResultSet res = st.executeQuery("SELECT * FROM USER WHERE UserName = '"+ findername +"';");
			while(res.next()){
				userID = res.getInt("id");
			}
			//Check if the database already has this category
			res = st.executeQuery("SELECT * FROM CATEGORY WHERE CategoryName = '"+ name
					+ "' AND KindName = '" + kind +"';");
			// If the database does not have this category
			if(!res.next()){
				st.execute("INSERT INTO CATEGORY (CategoryName, KindName)" + "VALUES('"+name+"', '"+kind+"');",Statement.RETURN_GENERATED_KEYS);
				int categoryID = getKey(st);
				st.execute("INSERT INTO ITEM (categoryID, Location, Time)" + "VALUES('"+categoryID+"', '"+location+"', '"+time+"');",Statement.RETURN_GENERATED_KEYS);
				int itemID = getKey(st);
				st.execute("INSERT INTO IMAGES (itemID, Image_1, Image_2)" + "VALUES('"+itemID+"', '"+image_1+"', '"+image_2+"');");
				st.execute("INSERT INTO QUESTIONS (itemID, userID, Question_1, Question_2, Question_3)" + "VALUES('"+itemID+"', '"+userID+"', '"+questions[0]+"', "
						+ "'"+questions[1]+"', '"+questions[2]+"');");
				System.out.println("New category inserted into the database");
			}
			//If the database has this category
			else{
				res = st.executeQuery("SELECT * FROM CATEGORY WHERE CategoryName = '"+ name + "' AND KindName = '" + kind +"';");
				int categoryID = 0;
				while(res.next()){
					categoryID = res.getInt("id");
				}
				st.execute("INSERT INTO ITEM (categoryID, Location, Time)" + "VALUES('"+categoryID+"', '"+location+"', '"+time+"');",Statement.RETURN_GENERATED_KEYS);
				int itemID = getKey(st);
				st.execute("INSERT INTO IMAGES (itemID, Image_1, Image_2)" + "VALUES('"+itemID+"', '"+image_1+"', '"+image_2+"');");
				st.execute("INSERT INTO QUESTIONS (itemID, userID, Question_1, Question_2, Question_3)" + "VALUES('"+itemID+"', '"+userID+"', '"
						+questions[0]+"', '"+questions[1]+"', '"+questions[2]+"');");
				System.out.println("New item added into a existing category");
			}
			res.close();
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
	}
	
	public void insertAnswers(String name, String kind, String location, String time, 
							  String ownername, String question[], String[] answers){
		OpenDB();
		int userID = -1;
		try{
			// Get the userID
			ResultSet res = st.executeQuery("SELECT * FROM USER WHERE UserName = '"+ownername+"';");
			while(res.next()){
				userID = res.getInt("id");
			}
			System.out.println(userID + " " + ownername);
			
			// Get the category id
			res = st.executeQuery("SELECT * FROM CATEGORY WHERE CategoryName = '"+name
					+ "' AND KindName = '" + kind +"';");
			int categoryID = -1;
			while(res.next()){
				categoryID = res.getInt("id");
			}
			res = st.executeQuery("SELECT * FROM ITEM WHERE Location = '"+location +"' AND Time = '" + time 
					+"' AND categoryID = '" + categoryID +"';");
			int itemID = -1;
			while(res.next()){
				itemID = res.getInt("id");
			}
			System.out.println(itemID);
			res = st.executeQuery("SELECT * FROM QUESTIONS WHERE itemID = '"+itemID +"';");
			int finderid = -1;
			while(res.next()){
				finderid = res.getInt("userID");
			}
			System.out.println("finderid: " + finderid);
			
			st.execute("INSERT INTO ANSWERS (userID, Answer_1, Answer_2, Answer_3, Question_1, "
										+ "Question_2, Question_3, FinderID)" + "VALUES('"
										+userID+"', '"+answers[0]+"', '"+answers[1]+"', '"+answers[2] +"', '"
										+question[0]+"', '"+question[1]+"', '"+question[2]+"', '"+finderid+"');");
			System.out.println(answers[0]);
			System.out.println(question[0]);
			res.close();
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
	}
	
	public void insertRes(String catName, String location, String time, boolean flag){
		OpenDB();
	
		try{
			
			// Get the category id
			ResultSet res = st.executeQuery("SELECT * FROM CATEGORY WHERE CategoryName = '"+catName+"';");
			int categoryID = -1;
			while(res.next()){
				categoryID = res.getInt("id");
			}
			if (flag == true)
				st.executeUpdate("UPDATE ITEM SET Correct = '" + PASS + "' WHERE Location = '"+location +"' AND Time = '" + time 
					+"' AND categoryID = '" + categoryID +"';");
			if (flag == false)
				st.executeUpdate("UPDATE ITEM SET Correct = '" + FAIL + "' WHERE Location = '"+location +"' AND Time = '" + time 
						+"' AND categoryID = '" + categoryID +"';");
			res.close();
		}catch(Exception e){
			System.out.println(e);
		}
		closeDB();
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
	
	public int getKey(Statement statement){
		int id = -1;
		try{
			ResultSet res = statement.getGeneratedKeys();
			if(res.next()){
				id = res.getInt(1);
			}
			if(id==-1)
				System.out.println("Fail to get autoGenerateKey!");
			res.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return id;
	}
}
