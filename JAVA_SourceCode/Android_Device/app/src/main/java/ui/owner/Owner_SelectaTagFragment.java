package ui.owner;

import Entities.Category;
import ui.owner.search.Owner_SearchActivity;
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

public class Owner_SelectaTagFragment extends Fragment {
	
	private Button myProfile;
	
	private Button watch;
	private Button handbag;
	private Button earphone;
	private Button bankcard;
	private Button camera;
	private Button cellphone;
	private Button laptop;
	
	private Button scissors;
	private Button ball;
	private Button stationery;
	private Button keys;
	private Button book;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Owner_SelectaTagFragment--onCreateView"); 
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("Owner_SelectaTagFragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_owner__selectatag, container, false);
		
		getReferences(view);
		
		addButtonListener();
		
		return view;
	}
	
	public void getReferences(View view){
		this.myProfile = (Button) view.findViewById(R.id.myprofile_4Button);
		this.watch = (Button) view.findViewById(R.id.owner_Tagwatch);
		this.handbag = (Button) view.findViewById(R.id.owner_Taghandbag);
		this.earphone = (Button) view.findViewById(R.id.owner_Tagearphone);
		this.bankcard = (Button) view.findViewById(R.id.owner_Tagbankcard);
		this.camera = (Button) view.findViewById(R.id.owner_Tagcamera);
		this.cellphone = (Button) view.findViewById(R.id.owner_Tagcellphone);
		this.laptop = (Button) view.findViewById(R.id.owner_Taglaptop);	
		this.scissors = (Button) view.findViewById(R.id.owner_Tagscissors);
		this.ball = (Button) view.findViewById(R.id.owner_Tagball);
		this.stationery = (Button) view.findViewById(R.id.owner_Tagstationery);
		this.keys = (Button) view.findViewById(R.id.owner_Tagkeys);
		this.book = (Button) view.findViewById(R.id.owner_Tagbook);	
	}
	
	private void addButtonListener(){
		
		myProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), MyProfileActivity.class);
            	startActivity(submit);
            }
        });

		
		watch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "watch");

            	startActivity(submit);
            }
        });
		
		handbag.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "handbag");

            	startActivity(submit);
            }
        });
		
		earphone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "earphone");

            	startActivity(submit);
            }
        });
		
		bankcard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "bankcard");

            	startActivity(submit);
            }
        });
		
		camera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "camera");

            	startActivity(submit);
            }
        });
		
		cellphone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "cellphone");

            	startActivity(submit);
            }
        });
		
		laptop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "laptop");

            	startActivity(submit);
            }
        });
		
		scissors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "scissors");

            	startActivity(submit);
            }
        });
		
		ball.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "ball");

            	startActivity(submit);
            }
        });
		
		stationery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "stationery");

            	startActivity(submit);
            }
        });
		
		keys.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "keys");

            	startActivity(submit);
            }
        });
		
		book.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent submit = new Intent(getActivity(), Owner_SearchActivity.class);
            	submit.putExtra("CatNameSearch", "book");

            	startActivity(submit);
            }
        });
	}
	
	public void onPause() { 
	// TODO Auto-generated method stub 
	super.onPause(); 
	System.out.println("Owner_SelectaTagFragment--onPause"); 
	} 
	
	@Override 
	public void onResume() { 
	// TODO Auto-generated method stub 
	super.onResume(); 
	System.out.println("Owner_SelectaTagFragment--onResume"); 
	} 
	
	@Override 
	public void onStop() { 
	// TODO Auto-generated method stub 
	super.onStop(); 
	System.out.println("Owner_SelectaTagFragment--onStop"); 
	} 
}
