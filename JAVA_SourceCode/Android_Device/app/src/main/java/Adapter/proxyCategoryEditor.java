package Adapter;

import DataBase.EditCategoryTable;
import Entities.Category;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public abstract class proxyCategoryEditor {

	private Category category;
	EditCategoryTable editcattb;
	
	//CreateCategory
	public Category initCategory(String name, int value){
		return new Category(name, value);
	}
	
	public void createItem(Category cat, String location, String time){
		cat.createItem(location, time);
	}
	
	public void addImage(Category cat, String location, String time, byte[] image_1, byte[] image_2){
		cat.setImage(location, time, image_1, image_2);
	}
	
	public void addQuestions(Category cat, String location, String time, String[] q){
		cat.setQuestions(location, time, q);
	}
    public void setFinderName(Category cat, String location, String time, String name){
        cat.setFinderName(location, time, name);
    }
    public void addAnswers(Category cat, String location, String time, String ans[]){
        cat.setAnswers(location, time, ans);
    }
    public void addOwnerName(Category cat, String location, String time, String usrname){
        cat.setOwnerName(location, time, usrname);
    }
	
	//GetFromCategory
    public ArrayList<String> getList(Category cat){
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < cat.getItemsNum(); i++){
            list.add(i, cat.getLocandTimebyIndex(i));
        }
        return list;
    }

	public byte[] getImage_1(Category cat, String location, String time){
		return cat.getImage_1(location, time);
	}

	public byte[] getImage_2(Category cat, String location, String time){
		return cat.getImage_2(location, time);
	}
	
	public String[] getQuestions(Category cat, String location, String time){
		return cat.getQuestions(location, time);
	}
    public String[] getAnswers(Category cat, String location, String time) {
        return cat.getAnswers(location, time);
    }
    public String getFinderName(Category cat, String location, String time){
        return cat.getFinderName(location, time);
    }

	//EditCaegoryDB
	public void saveCattoDB(Category cat, String location, String time, Context context, String usrtype){
		category = cat;
		String[] q = category.getQuestions(location, time);
		editcattb = new EditCategoryTable(context);
		editcattb.insertRecord(category.getName(), category.getValue(), location, time, 
							  category.getImage_1(location, time),
                              category.getImage_2(location, time), q[0], q[1], q[2], usrtype);
	}
	
	public Cursor getHistoryfromDB(String usrtype, Context context){
		editcattb = new EditCategoryTable(context);
		editcattb.open();
		return editcattb.getLocandTimeofCategory(usrtype);
	}
	
	public void closeDB(){
		editcattb.close();
	}
}
