package ui.finder.upload;

import com.example.lostandfound.R;

import Adapter.Category.CategoryEditor;
import Adapter.Category.CreateCategory;
import ui.shared.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Finder_Upload_3Fragment extends Fragment {
	//UI Properties
	private Button submit;
	private EditText q1EditText;
	private EditText q2EditText;
	private EditText q3EditText;
	private Finder_Upload_2Fragment up_2;
	//Content Properties
	private String[] q;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("Finder_Upload_2_Fragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_finder__upload_3, container, false);
		
		getReferences(view);

		submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(q1EditText.getText().toString().length()!=0 ){
                    getQuestions();
                    //Upload questions to the pk-shared category object
                    uploadQuestions();
                    Intent back = new Intent(getActivity(), Finder_EventCompleteActivity.class);
                    startActivity(back);
                }else
                    Toast.makeText(getActivity().getBaseContext(), "Question cannot be empty. " +
                            "Please enter 1 question at least!", Toast.LENGTH_LONG).show();

            }
        });
		
		return view;
	}
	
	public void getReferences(View view){
		submit = (Button) view.findViewById(R.id.founder_Upload_3Submit_5Button);
		q1EditText = (EditText) view.findViewById(R.id.founder_Upload_3Quetions_1EditText);
		q2EditText = (EditText) view.findViewById(R.id.founder_Upload_3Quetions_2EditText);
		q3EditText = (EditText) view.findViewById(R.id.founder_Upload_3Quetions_3EditText);
	}
	
	public void getQuestions(){
		q = new String[3];
		q[0] = q1EditText.getText().toString();
		q[1] = q2EditText.getText().toString();
		q[2] = q3EditText.getText().toString();
	}
//Upload questions to the pk-shared category object 
	public void uploadQuestions(){
		//Add questions
		CreateCategory createcat = new CategoryEditor();
		createcat.addQuestions(Finder_Upload_Activity.cat, Finder_Upload_Activity.location, 
											Finder_Upload_Activity.time,q);
        createcat.setFinderName(Finder_Upload_Activity.cat, Finder_Upload_Activity.location,
                Finder_Upload_Activity.time, MainActivity.username);
	}
	
	@Override 
	public void onPause() { 
	// TODO Auto-generated method stub 
	super.onPause(); 
	System.out.println("Finder_Upload_2_Fragment--onPause"); 
	} 
	
	@Override 
	public void onResume() { 
	// TODO Auto-generated method stub 
	super.onResume(); 
	System.out.println("Finder_Upload_2_Fragment--onResume"); 
	} 
	
	@Override 
	public void onStop() { 
	// TODO Auto-generated method stub 
	super.onStop(); 
	System.out.println("Finder_Upload_2_Fragment--onStop");
	}
}
