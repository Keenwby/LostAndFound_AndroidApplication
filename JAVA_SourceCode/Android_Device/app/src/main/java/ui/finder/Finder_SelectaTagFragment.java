package ui.finder;

import ui.finder.upload.Finder_Upload_Activity;

import com.example.lostandfound.R;
import com.example.lostandfound.R.id;
import com.example.lostandfound.R.layout;

import Adapter.Category.CategoryEditor;
import Adapter.Category.CreateCategory;
import Entities.Category;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Finder_SelectaTagFragment extends Fragment {

	//UI Properties
	private Button submit;
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
	private EditText createEditText;
	private RadioButton valueable;
	private RadioButton common;
	//Content properties
	private CreateCategory createCat;
	private String newCatName;
	private int newCatValue;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("Finder_SelectaTagFragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_finder_selectatag, container, false);
		
		getReferences(view);
		
		addButtonListener();
		
		return view;
	}
	
	public void getReferences(View view){
		this.submit = (Button) view.findViewById(R.id.finderSelectaTagSubmit_4Button);
		this.watch = (Button) view.findViewById(R.id.finder_Tagwatch);
		this.handbag = (Button) view.findViewById(R.id.finder_Taghandbag);
		this.earphone = (Button) view.findViewById(R.id.finder_Tagearphone);
		this.bankcard = (Button) view.findViewById(R.id.finder_Tagbankcard);
		this.camera = (Button) view.findViewById(R.id.finder_Tagcamera);
		this.cellphone = (Button) view.findViewById(R.id.finder_Tagcellphone);
		this.laptop = (Button) view.findViewById(R.id.finder_Taglaptop);	
		this.scissors = (Button) view.findViewById(R.id.finder_Tagscissors);
		this.ball = (Button) view.findViewById(R.id.finder_Tagball);
		this.stationery = (Button) view.findViewById(R.id.finder_Tagstationery);
		this.keys = (Button) view.findViewById(R.id.finder_Tagkeys);
		this.book = (Button) view.findViewById(R.id.finder_Tagbook);
		this.createEditText = (EditText) view.findViewById(R.id.finder_createaTagEditText);
		this.valueable = (RadioButton) view.findViewById(R.id.finder_valuablePropertyRadioButton);
		this.common = (RadioButton) view.findViewById(R.id.finder_commonPropertyRadioButton);
	}
	
	private void addButtonListener(){

		//When creating a new Category
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

                if(createEditText.getText().toString().length() != 0){
                    //Get Catname
                    newCatName = createEditText.getText().toString();
                    //Get Kind
                    if(valueable.isChecked()) {
                        newCatValue = Category.VALUEABLE;
                    }
                    if(common.isChecked()) {
                        newCatValue = Category.COMMON;
                    }
                    //Initiate a new Category and pass to next Activity
                    createCat = new CategoryEditor();
                    Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
                    submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
                    startActivity(submit);
                }else
                    Toast.makeText(getActivity().getBaseContext(), "If you want to create a new Category, " +
                            "category name cannot be empty", Toast.LENGTH_LONG).show();

			}
		});
		//Valuable Categories
		watch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "watch";
				newCatValue = Category.VALUEABLE;			
				//Initiate a new Category 'watch' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		
		handbag.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "handbag";
				newCatValue = Category.VALUEABLE;
				//Initiate a new Category 'handbag' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		
		earphone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "earphone";
				newCatValue = Category.VALUEABLE;
				//Initiate a new Category 'earphone' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		
		bankcard.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "bankcard";
				newCatValue = Category.VALUEABLE;
				//Initiate a new Category 'bankcard' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		
		camera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "camera";
				newCatValue = Category.VALUEABLE;
				//Initiate a new Category 'camera' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		
		laptop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "laptop";
				newCatValue = Category.VALUEABLE;
				//Initiate a new Category 'laptop' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		
		cellphone.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "cellphone";
				newCatValue = Category.VALUEABLE;
				//Initiate a new Category 'cellphone' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		//Common Categories
		scissors.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "scissors";
				newCatValue = Category.COMMON;
				//Initiate a new Category 'scissors' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});

		ball.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "ball";
				newCatValue = Category.COMMON;
				//Initiate a new Category 'ball' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		
		stationery.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "stationery";
				newCatValue = Category.COMMON;
				//Initiate a new Category 'stationery' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		
		keys.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "keys";
				newCatValue = Category.COMMON;
				//Initiate a new Category 'keys' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
		
		book.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				newCatName = "book";
				newCatValue = Category.COMMON;
				//Initiate a new Category 'book' and pass to next Activity
				createCat = new CategoryEditor();
				Intent submit = new Intent(getActivity(), Finder_Upload_Activity.class);
				submit.putExtra("Cat", createCat.initCategory(newCatName, newCatValue));
				startActivity(submit);
			}
		});
	}
	
	@Override 
	public void onPause() { 
	// TODO Auto-generated method stub 
	super.onPause(); 
	System.out.println("Finder_SelectaTagFragment--onPause"); 
	} 
	
	@Override 
	public void onResume() { 
	// TODO Auto-generated method stub 
	super.onResume(); 
	System.out.println("Finder_SelectaTagFragment--onResume"); 
	} 
	
	@Override 
	public void onStop() { 
	// TODO Auto-generated method stub 
	super.onStop(); 
	System.out.println("Finder_SelectaTagFragment--onStop"); 
	} 
}
