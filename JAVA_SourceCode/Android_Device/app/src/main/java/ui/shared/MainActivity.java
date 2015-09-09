package ui.shared;

import com.example.lostandfound.R;
import com.example.lostandfound.R.id;
import com.example.lostandfound.R.layout;
import com.example.lostandfound.R.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	private Button signUp;
	private Button logIn;
    public static String username;
    public static String ip;
    public static String usrtype;

	private Fragment LogInFragment;
	private Fragment SignUpFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		signUp = (Button) findViewById(R.id.signUpButton);
		logIn = (Button) findViewById(R.id.logInButton);
		
		signUp.setOnClickListener(this);
		logIn.setOnClickListener(this);
            
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
		
		case R.id.signUpButton:
			if(SignUpFragment == null)
				SignUpFragment = new SignUpFragment();
			tr.replace(R.id.maincontent, SignUpFragment);
			break;
			
		case R.id.logInButton:
			if(LogInFragment == null)
				LogInFragment = new LogInFragment();
			tr.replace(R.id.maincontent, LogInFragment);
			break;
		}
		tr.commit();
	}
    @Override
    public void onResume() {
        super.onResume();
        System.out.println("MainActivity--onResume");
    }
}
