package ui.shared;

import com.example.lostandfound.R;

import Adapter.User.CreateUser;
import Adapter.User.EditUserDB;
import Adapter.User.UserEditor;
import Entities.User;
import ws.local_client.UploadUser;

import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import android.text.format.Formatter;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SignUpFragment extends Fragment {
	//UI Properties
	private Button submit;
	private EditText accountSignUpEditText;
	private EditText pwSignUpEditText;
	private EditText pwrSignUpEditText;
	private EditText contInfoSignUpEditText;
	//Content Properties
	private User user;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("SignUpFragment--onCreateView"); 
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("SignUpFragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
		
		getReferences(view);
		
		submit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
			//1. Check inputs
			if(accountSignUpEditText.getText().toString().length()!=0){
				if(pwSignUpEditText.getText().toString().equals
						(pwrSignUpEditText.getText().toString())){
                    if(contInfoSignUpEditText.getText().toString().length()!= 0){
                        //Get Inputs
                        getInputs();

                        //Send to remote server
                        UploadUser uploadUser = new UploadUser(user);
                        uploadUser.execute();

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(uploadUser.checkStatus() == true){
                            Toast.makeText(getActivity().getBaseContext(),"User Created Succeed!"
                                    ,Toast.LENGTH_LONG).show();
                            Intent submit = new Intent(getActivity(), CreateEventActivity.class);
                            startActivity(submit);
                        }else{
                            Toast.makeText(getActivity().getBaseContext(),"Poor network connection! " +
                                    "Please upload again!",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getActivity().getBaseContext(),"Contact information cannot be empty. " +
                                "Please enter a phone number of email address!",Toast.LENGTH_LONG).show();
                    }

				}
				else{
	                //1.2 False
					//Reset pw and pwr to make the user retype password
					Toast.makeText(getActivity().getBaseContext(), "Password not match, please"
							+ " retype your password!", Toast.LENGTH_LONG).show();
					pwSignUpEditText.setText("");
					pwrSignUpEditText.setText("");
					contInfoSignUpEditText.setText("");
					}
				}else{
                Toast.makeText(getActivity().getBaseContext(),"Username cannot be empty. " +
                        "Please enter a username!",Toast.LENGTH_LONG).show();
            }
                accountSignUpEditText.setText("");
                pwSignUpEditText.setText("");
                pwrSignUpEditText.setText("");
                contInfoSignUpEditText.setText("");
	        } // end method onClick
		});
			
		return view;
	}

	public void getReferences(View view){
		
		accountSignUpEditText = (EditText) view.findViewById(R.id.accountSignUpEditText);
		pwSignUpEditText = (EditText) view.findViewById(R.id.pwSignUpEditText);
		pwrSignUpEditText = (EditText) view.findViewById(R.id.pwrSignUpEditText);
		contInfoSignUpEditText = (EditText) view.findViewById(R.id.contInfoSignUpEditText);
	    
		submit = (Button) view.findViewById(R.id.signUpSubmit_2Button);
	    
	}
	
	public void getInputs(){
		CreateUser createusr = new UserEditor();

        WifiManager wm = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        @SuppressWarnings("deprecation")
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        MainActivity.username = accountSignUpEditText.getText().toString();
        user = createusr.createUser(MainActivity.username,
                pwrSignUpEditText.getText().toString(),
                contInfoSignUpEditText.getText().toString(),ip);
        MainActivity.ip = ip;

	}

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        System.out.println("SignUpFragment--onPause");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        System.out.println("SignUpFragment--onResume");
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        System.out.println("SignUpFragment--onStop");
    }
}
