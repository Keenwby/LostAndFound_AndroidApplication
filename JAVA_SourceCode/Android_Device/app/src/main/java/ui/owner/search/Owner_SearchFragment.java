package ui.owner.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.lostandfound.R;
import com.example.lostandfound.R.id;
import com.example.lostandfound.R.layout;

import Adapter.Category.CategoryEditor;
import Adapter.Category.CreateCategory;
import Adapter.Category.EditCategoryDB;
import Adapter.Category.GetFromCategory;
import DataBase.CreateCategoryTable;
import Entities.Category;
import ui.finder.Finder_AnswerCheckActivity;
import ui.owner.Owner_GetContactInfoFragment;
import ui.owner.Owner_IdAuthPassFragment;
import ui.owner.Owner_IdAuthResultActivity;
import ui.owner.Owner_SelectaTagFragment;
import ui.shared.MainActivity;
import ws.local_client.DownloadCategory;
import ws.local_client.DownloadList;
import ws.local_client.OwnerResult;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Owner_SearchFragment extends Fragment {
	
	private ListView listView;
	
	private Owner_IdAuthFragment idau;
    private Category cat;
    private String location;
    private String time;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Owner_SearchFragment--onCreate"); 
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("Owner_SearchFragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_owner__search, container, false);
		
		listView = (ListView) view.findViewById(R.id.searchlist);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, Owner_SearchActivity.list);
        listView.setAdapter(adapter);
		
        //Get the clicked item
        listView.setOnItemClickListener(new OnItemClickListener(){   
            @Override   
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   
                    long arg3) {   
                //Get selected item
                String item = (String) listView.getItemAtPosition(arg2);   
                String[] res = item.split(":::");
                location = res[0];
                time = res[1];
                //Create a new category
                CreateCategory newCat = new CategoryEditor();
                cat = newCat.initCategory(Owner_SearchActivity.catname, Category.DEFAULT);
                newCat.createItem(cat, location, time);
                Toast.makeText(getActivity().getApplicationContext(),    
                        "You chose no. "+arg2+" Item: " + item,   
                        Toast.LENGTH_SHORT).show();

                //Send to remote Server
                DownloadCategory downloadCategory = new DownloadCategory(cat);
                downloadCategory.execute();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(downloadCategory.checkStatus()==true){
                  //Get Category
                    cat = downloadCategory.getCat();
                    //Save to shared properties
                    System.out.println(cat);
                    Owner_SearchActivity.cat = cat;
                    Owner_SearchActivity.loc = location;
                    Owner_SearchActivity.time = time;
                    if(cat.getValue() == Category.VALUEABLE){
                        //Save to local database
                        System.out.println(cat.equals(null));
                        new SavetoLocalDB().execute((Object[]) null);
                        //Get questions
                        GetFromCategory getQues = new CategoryEditor();
                        Owner_SearchActivity.ques = getQues.getQuestions(cat,location,time);
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction tr = fm.beginTransaction();
                        idau = new Owner_IdAuthFragment();
                        tr.replace(R.id.search_content, idau);
                        tr.commit();
                    }else{
                        //Send to remote Server
                        OwnerResult ownerResult = new OwnerResult(cat);
                        ownerResult.execute();

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cat = ownerResult.getCat();
                        Intent submit = new Intent(getActivity(), Owner_IdAuthResultActivity.class);
                        GetFromCategory getImage = new CategoryEditor();
                        Owner_IdAuthPassFragment.contactInfo = getImage.getFinderName(cat, location, time);
                        submit.putExtra("result", Finder_AnswerCheckActivity.PASS);
                        submit.putExtra("common", "COMMON");
                        startActivity(submit);
                    }
                }else {
                    Toast.makeText(getActivity().getBaseContext(), "Error! Please choose an item again!"
                            , Toast.LENGTH_LONG).show();
                }
            }   
               
        });
		
		return view;
	}
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
	}

    private class SavetoLocalDB extends AsyncTask<Object, Object, Object>{
        Context context = getActivity();
        EditCategoryDB editDB = new CategoryEditor();
        @Override
        protected Object doInBackground(Object... params) {
            editDB = new CategoryEditor();
            editDB.saveCattoDB(cat, location, time, context, MainActivity.usrtype);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            editDB.closeDB();
        }
    }; //end method AsyncTask
	
	@Override 
	public void onPause() { 
	// TODO Auto-generated method stub 
	super.onPause(); 
	System.out.println("Owner_SearchFragment--onPause"); 
	} 
	
	@Override 
	public void onResume() { 
	// TODO Auto-generated method stub 
	super.onResume(); 
	System.out.println("Owner_SearchFragment--onResume"); 
	} 
	
	@Override 
	public void onStop() { 
	// TODO Auto-generated method stub 
	super.onStop(); 
	System.out.println("Owner_SearchFragment--onStop");
	}
}
