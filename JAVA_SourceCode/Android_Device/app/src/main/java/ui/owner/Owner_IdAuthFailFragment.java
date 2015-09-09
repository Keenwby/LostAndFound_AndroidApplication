package ui.owner;

import Adapter.Category.CreateCategory;
import ui.shared.CreateEventActivity;
import ui.shared.MyProfileActivity;

import com.example.lostandfound.R;
import com.example.lostandfound.R.id;
import com.example.lostandfound.R.layout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Owner_IdAuthFailFragment extends Fragment {

	private Button myProfile;
	private Button requestAgain;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Owner_IdAuthFailFragment--onCreate"); 
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("Owner_IdAuthFailFragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_owner__id_auth_fail, container, false);
		
		myProfile = (Button) view.findViewById(R.id.owner_IdAuthFailmyProfileButton);
		requestAgain = (Button) view.findViewById(R.id.owner_IdAuthFailRequstButton);
		
		myProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent back = new Intent(getActivity(), MyProfileActivity.class);
            	startActivity(back);
            }
        });
		
		requestAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), CreateEventActivity.class);
            	startActivity(submit);
            }
        });
		
		return view;
	}
	
	@Override 
	public void onPause() { 
	// TODO Auto-generated method stub 
	super.onPause(); 
	System.out.println("Owner_IdAuthFailFragment--onPause"); 
	} 
	
	@Override 
	public void onResume() { 
	// TODO Auto-generated method stub 
	super.onResume(); 
	System.out.println("Owner_IdAuthFailFragment--onResume"); 
	} 
	
	@Override 
	public void onStop() { 
	// TODO Auto-generated method stub 
	super.onStop(); 
	System.out.println("Owner_IdAuthFailFragment--onStop");
	}
}
