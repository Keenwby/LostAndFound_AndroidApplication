package ui.finder.upload;

import ui.shared.MainActivity;
import ui.shared.MyProfileActivity;

import com.example.lostandfound.R;

import Adapter.Category.CategoryEditor;
import Adapter.Category.EditCategoryDB;
import Entities.Category;
import ws.local_client.UploadCategory;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Finder_EventCompleteActivity extends ActionBarActivity {

	private Button myProfile;

	private Category cat = Finder_Upload_Activity.cat;
	private String loc = Finder_Upload_Activity.location;
	private String time = Finder_Upload_Activity.time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finder_event_complete);

		getReferences();

        //Save to local database
        new SavetoLocalDB().execute((Object[]) null);

        //Send to remote server
        UploadCategory uploadCategory = new UploadCategory(cat);
        uploadCategory.execute();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(uploadCategory.checkStatus() == true){
            Toast.makeText(getBaseContext(), "User Created Succeed!" + "Thanks you and please check your event in myProfile later!"
                    , Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getBaseContext(),"Poor network connection! " +
                    "Please upload again!",Toast.LENGTH_LONG).show();
        }

		myProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent back = new Intent(Finder_EventCompleteActivity.this, MyProfileActivity.class);
            	startActivity(back);
            }
        });
	}
	
	public void getReferences(){
		myProfile = (Button) findViewById(R.id.founder_myprofile_6Button);
	}

    private class SavetoLocalDB extends AsyncTask<Object, Object, Object>{
        EditCategoryDB editDB = new CategoryEditor();
        @Override
        protected Object doInBackground(Object... params) {
            editDB = new CategoryEditor();
            editDB.saveCattoDB(cat, loc, time, Finder_EventCompleteActivity.this, MainActivity.usrtype);
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            editDB.closeDB();
        }
    }; //end method AsyncTask

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_complete, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
