package ui.owner.search;

import com.example.lostandfound.R;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.Category.CategoryEditor;
import Adapter.Category.CreateCategory;
import Adapter.Category.GetFromCategory;
import Entities.Category;
import ws.local_client.DownloadList;

public class Owner_SearchActivity extends ActionBarActivity {

	private Owner_SearchFragment search;
	
	protected static String catname;
    protected static String loc;
    protected static String time;
    protected static ArrayList<String> list;
    protected static Category cat;
    protected static String[] ques;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owner__search);
		Intent getCat = getIntent();
		catname = getCat.getStringExtra("CatNameSearch");
        //Send to remote Server
        Category cat = new Category(catname, Category.DEFAULT);
        DownloadList downloadList = new DownloadList(cat);
        downloadList.execute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Get list
        if(downloadList.checkStatus()==true){
            cat = downloadList.getList();
            GetFromCategory get = new CategoryEditor();
            this.list = get.getList(cat);
        }else{
            Toast.makeText(getBaseContext(), "Error! Please go back and choose a category again!"
                    , Toast.LENGTH_LONG).show();
        }
        setDefaultFragment();
	}
	
	private void setDefaultFragment(){
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction tr = fm.beginTransaction();
		search = new Owner_SearchFragment();
		tr.replace(R.id.search_content, search);
		tr.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.owner__search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
