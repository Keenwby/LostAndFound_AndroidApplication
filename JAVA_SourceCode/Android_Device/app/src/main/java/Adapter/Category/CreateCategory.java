package Adapter.Category;

import Entities.Category;

public interface CreateCategory {

	public Category initCategory(String name, int value);
	
	public void createItem(Category cat, String location, String time);
	
	public void addImage(Category cat, String location, String time, byte[] image_1, byte[] image_2);
	
	public void addQuestions(Category cat, String location, String time, String[] q);

    public void setFinderName(Category cat, String location, String time, String name);

    public void addAnswers(Category cat, String location, String time, String ans[]);

    public void addOwnerName(Category cat, String location, String time, String usrname);

}
