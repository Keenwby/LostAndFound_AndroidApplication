package ui.shared;

import com.example.lostandfound.R;

import Adapter.User.CreateUser;
import Adapter.User.EditUserDB;
import Adapter.User.UserEditor;
import Exception.Errors;
import Exception.MyException;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import Entities.User;
import ws.local_client.CheckUser;

public class LogInFragment extends Fragment {

    public final static int PASS = 1;
    public final static int FAIL = 0;
    public final static int NOTFOUND = -1;

	//UI Properties
	private Button submit;
	private EditText accountLogInEditText;
	private EditText pwLogInEditText;
	//Content Properties
    private User user;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("LogInFragment--onCreate"); 
	}

	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("LogInFragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_log_in, container, false);
		
		getReferences(view);
		
		submit.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
			    //Get Inputs
                getInputs();
                //Send to remote server
                CheckUser checkUser = new CheckUser(user);
                checkUser.execute();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Check pw
                System.out.println("status is" + checkUser.checkStatus());
                if(checkUser.checkStatus() == PASS){
                    MainActivity.username = user.getUsrname();
                    Intent submit = new Intent(getActivity(), CreateEventActivity.class);
                    startActivity(submit);
                }else if(checkUser.checkStatus() == FAIL){
                    Toast.makeText(getActivity().getBaseContext(),"Wrong password! " +
                            "Please check your password!",Toast.LENGTH_LONG).show();
                }else if(checkUser.checkStatus() == NOTFOUND){
                    Toast.makeText(getActivity().getBaseContext(), "Username not Found!" +
                            "Please check your username!", Toast.LENGTH_LONG).show();
                }
	        }
		});
			
		return view;
	}
	
	@Override 
	public void onPause() { 
	// TODO Auto-generated method stub 
	super.onPause(); 
	System.out.println("LogInFragment--onPause"); 
	} 
	
	@Override 
	public void onResume() { 
	// TODO Auto-generated method stub 
	super.onResume(); 
	System.out.println("LogInFragment--onResume"); 
	} 
	
	@Override 
	public void onStop() { 
	// TODO Auto-generated method stub 
	super.onStop(); 
	System.out.println("LogInFragment--onStop"); 
	} 

	public void getReferences(View view){
		
		accountLogInEditText = (EditText) view.findViewById(R.id.accountLogInEditText);
		pwLogInEditText = (EditText) view.findViewById(R.id.pwLogInEditText);
	    
	    submit = (Button) view.findViewById(R.id.logInSubmit_2Button);
	    
	}

    public void getInputs(){
        CreateUser createusr = new UserEditor();

        WifiManager wm = (WifiManager) getActivity().getSystemService(Context.WIFI_SERVICE);
        @SuppressWarnings("deprecation")
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        MainActivity.username = accountLogInEditText.getText().toString();
        user = createusr.createUser(accountLogInEditText.getText().toString(),
                pwLogInEditText.getText().toString(),
                null,ip);
        MainActivity.ip = ip;
    }
}
