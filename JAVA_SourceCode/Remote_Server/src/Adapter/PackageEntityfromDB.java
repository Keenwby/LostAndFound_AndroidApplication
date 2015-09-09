package Adapter;

import Entities.Category;
import Entities.User;

public interface PackageEntityfromDB {
	
	public Category packageCategory(Category cat);
	//Get a category name and package all items
	public Category packageItemList(Category cat);
	
	public Category packageAnswer(Category cat);
	
	public Category packageCategorywithContactInfo(Category cat);
	
}
