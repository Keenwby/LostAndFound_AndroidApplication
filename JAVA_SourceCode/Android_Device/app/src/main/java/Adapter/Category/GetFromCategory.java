package Adapter.Category;

import java.util.ArrayList;

import Entities.Category;

public interface GetFromCategory {

    public ArrayList<String> getList(Category cat);

	public byte[] getImage_1(Category cat, String location, String time);

	public byte[] getImage_2(Category cat, String location, String time);
	
	public String[] getQuestions(Category cat, String location, String time);

    public String[] getAnswers(Category cat, String location, String time);

    public String getFinderName(Category cat, String location, String time);

}
