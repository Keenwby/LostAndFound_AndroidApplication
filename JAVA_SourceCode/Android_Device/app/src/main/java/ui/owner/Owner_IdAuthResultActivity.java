package ui.owner;

import ui.finder.Finder_AnswerCheckActivity;

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

public class Owner_IdAuthResultActivity extends ActionBarActivity {

	private Fragment Owner_IdAuthResFrament;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_owner__id_auth_resurlt);
		
		Intent result = getIntent();
		
		setFragment(result.getIntExtra("result", 0));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.owner__id_auth_resurlt, menu);
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
	
	private void setFragment(int res){
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction tr = fm.beginTransaction();
		
		switch(res){
			case Finder_AnswerCheckActivity.PASS:

				Owner_IdAuthResFrament = new Owner_IdAuthPassFragment();
				tr.replace(R.id.result_content, Owner_IdAuthResFrament);
				break;
			case Finder_AnswerCheckActivity.FAIL:
				Owner_IdAuthResFrament = new Owner_IdAuthFailFragment();
				tr.replace(R.id.result_content, Owner_IdAuthResFrament);
				break;
		}
		
		tr.commit();
	}
}
