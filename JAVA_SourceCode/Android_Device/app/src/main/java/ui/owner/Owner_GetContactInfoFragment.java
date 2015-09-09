package ui.owner;

import ui.shared.MyProfileActivity;

import com.example.lostandfound.R;
import com.example.lostandfound.R.id;
import com.example.lostandfound.R.layout;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Owner_GetContactInfoFragment extends Fragment {

	private Button myProfile;
    private TextView contInfoTextView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Owner_GetContactInfoFragment--onCreate");
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("Owner_GetContactInfoFragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_owner__get_contact_info, container, false);
		
		myProfile = (Button) view.findViewById(R.id.owner_GetContactInfoIns_myprofile_9Button);
        contInfoTextView = (TextView) view.findViewById(R.id.owner_GetContactInfoTextView);

        contInfoTextView.setText(Owner_IdAuthPassFragment.contactInfo);

		myProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent back = new Intent(getActivity(), MyProfileActivity.class);
            	startActivity(back);
            }
        });
		
		return view;
	}

	@Override 
	public void onPause() { 
	// TODO Auto-generated method stub 
	super.onPause(); 
	System.out.println("Owner_GetContactInfoFragment--onPause"); 
	} 
	
	@Override 
	public void onResume() { 
	// TODO Auto-generated method stub 
	super.onResume(); 
	System.out.println("Owner_GetContactInfoFragment--onResume"); 
	} 
	
	@Override 
	public void onStop() { 
	// TODO Auto-generated method stub 
	super.onStop(); 
	System.out.println("Owner_GetContactInfoFragment--onStop");
	}
}
