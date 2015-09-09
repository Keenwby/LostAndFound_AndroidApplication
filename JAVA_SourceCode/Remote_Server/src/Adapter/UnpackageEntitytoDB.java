package Adapter;

import Entities.Category;
import Entities.User;

public interface UnpackageEntitytoDB {

	public void unpackageUser(User user);
	
	public int checkUser(User user);
	
	public void updateUser(User user);
	
	public void unpackageCategory(Category cat);
	
	public void unpackageAnswer(Category cat);
	
	public void insertRes(Category cat, boolean flag);
	
	public int getResult(Category ansCat);
		
}
