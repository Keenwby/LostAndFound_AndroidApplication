package ui.shared;

import ui.finder.Finder_SelectaTagFragment;
import ui.owner.Owner_SelectaTagFragment;

import com.example.lostandfound.R;
import com.example.lostandfound.R.id;
import com.example.lostandfound.R.layout;
import com.example.lostandfound.R.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CreateEventActivity extends ActionBarActivity implements OnClickListener{

	private Button iFound;
	private Button iLost;
	private Button myProfile;
	
	private Fragment Owner_SelectaTagActivity;
	private Fragment Finder_SelectaTagFragment;

    public static final String FINDER = "FINDER";
    public static final String OWNER = "OWNER";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event);
		
		iFound = (Button) findViewById(R.id.iFound_3Button);
		iLost = (Button) findViewById(R.id.iLost_3Button);
		myProfile = (Button) findViewById(R.id.myprofile_3Button);
		
		iFound.setOnClickListener(this);
		iLost.setOnClickListener(this);
		myProfile.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_event, menu);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction tr = fm.beginTransaction();
		
		switch(v.getId()){
		
		case R.id.iFound_3Button:
			if(Finder_SelectaTagFragment == null)
				Finder_SelectaTagFragment = new Finder_SelectaTagFragment();
            MainActivity.usrtype = FINDER;
			tr.replace(R.id.eventcontent, Finder_SelectaTagFragment);
			break;
			
		case R.id.iLost_3Button:
			if(Owner_SelectaTagActivity == null)
				Owner_SelectaTagActivity = new Owner_SelectaTagFragment();
            MainActivity.usrtype = OWNER;
			tr.replace(R.id.eventcontent, Owner_SelectaTagActivity);
			break;
		
		case R.id.myprofile_3Button:
			startActivity(new Intent(CreateEventActivity.this, MyProfileActivity.class));
			break;
		}
		tr.commit();
		
	}
}
