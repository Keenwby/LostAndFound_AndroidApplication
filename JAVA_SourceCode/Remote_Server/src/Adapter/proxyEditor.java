package Adapter;

import DataBase.DBInsert;
import DataBase.DBRead;
import DataBase.DBUpdate;
import Entities.Category;
import Entities.User;

public abstract class proxyEditor {
	
	public Category packageItemList(Category cat){
		String catName = cat.getName();
		DBRead read = new DBRead();
		return read.getAllItems(catName);
	}
	
	public Category packageCategory(Category cat) {
		// TODO Auto-generated method stub
		String location = cat.getLocation(0);
		String time = cat.getTime(0);
		String catName = cat.getName();
		DBRead read = new DBRead();
		return read.getItem(catName, location, time);
	}
	
	public Category packageAnswer(Category cat){
		String location = cat.getLocation(0);
		String time = cat.getTime(0);
		String catName = cat.getName();
		DBRead read = new DBRead();
		return read.getAnswer(catName, location, time);
	}
	//Insert check result
	public void insertRes(Category cat, boolean flag){
		String location = cat.getLocation(0);
		String time = cat.getTime(0);
		String catName = cat.getName();
		DBInsert in = new DBInsert();
		in.insertRes(catName, location, time, flag);
	}
	//Get result
	public int getResult(Category cat){
		String location = cat.getLocation(0);
		String time = cat.getTime(0);
		String catName = cat.getName();
		DBRead read = new DBRead();
		return read.getRes(catName, location, time);
	}
	
	public Category packageCategorywithContactInfo(Category cat){
		String location = cat.getLocation(0);
		String time = cat.getTime(0);
		String catName = cat.getName();
		String finderName = cat.getFinderUsrName(0);
		DBRead read = new DBRead();
		return read.getItemwithContactInfo(catName, location, time, finderName);
	}
	
	//Unpackage
	public void unpackageUser(User user){
		String usrname = user.getUsrname();
		String password = user.getPassword();
		String cont = user.getCont();
		String ip = user.getIP();
		DBInsert insert = new DBInsert();
		System.out.println(usrname + " " + password + " " + cont + " " + ip);
		insert.insertUser(usrname, password, cont, ip);		
	}
	
	public int checkUser(User user){
		String usrname = user.getUsrname();
		String password = user.getPassword();
		DBRead checkUsr = new DBRead();
		System.out.println(usrname + " " + password + " ");
		return checkUsr.checkUser(usrname, password);
	}
	
	public void updateUser(User user){
		String usrname = user.getUsrname();
		String password = user.getPassword();
		String cont = user.getCont();
		String ip = user.getIP();
		DBUpdate upUser = new DBUpdate();
		System.out.println(usrname + " " + password + " " + cont + " " + ip);
		upUser.updateUser(usrname, password, cont, ip);
	}
	
	public void unpackageCategory(Category cat){
		String name = cat.getName();
		String kind = cat.getValue() == Category.VALUEABLE ? "Valuable" : "Common";	
		String location = cat.getLocation(0);
		String time =  cat.getTime(0);
		String findername = cat.getFinderUsrName(0);
		byte[] image_1 = cat.getImage_1(0);
		byte[] image_2 = cat.getImage_2(0);
		String[] questions = cat.getQuestions(0);
		DBInsert insert = new DBInsert();
		System.out.println(name + " " + kind + " " + location + " " + time + " " + findername + " " +
						   image_1.equals(null) + " " + image_2.equals(null) + " " + questions[0] + " " + 
						   questions[1] + " " + questions[2]);
		insert.insertCategory(name, kind, location, time, findername, image_1, image_2, questions);
 	}
	
	public void unpackageAnswer(Category cat){
		
		String name = cat.getName();
		String kind = cat.getValue() == Category.VALUEABLE ? "Valuable" : "Common";	
		String location = cat.getLocation(0);
		String time =  cat.getTime(0);
		String ownername = cat.getOwnerUsrName(0);
		String[] questions = cat.getQuestions(0);
		String[] answers = cat.getAnswers(0);
		DBInsert insert = new DBInsert();
		insert.insertAnswers(name, kind, location, time, ownername, questions, answers);
	}
}
