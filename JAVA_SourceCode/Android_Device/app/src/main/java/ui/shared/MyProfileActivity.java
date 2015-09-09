package ui.shared;

import com.example.lostandfound.R;
import Adapter.Category.EditCategoryDB;
import Adapter.Category.CategoryEditor;

import Adapter.User.CreateUser;
import Adapter.User.EditUserDB;
import Adapter.User.UserEditor;
import DataBase.CreateUserTable;
import Entities.User;
import ui.owner.search.Owner_SearchActivity;
import ui.owner.search.Owner_SearchFragment;
import ws.local_client.UpdateUser;
import ws.local_client.UploadUser;

import android.content.Context;
import android.database.Cursor;
import android.net.wifi.WifiManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyProfileActivity extends ActionBarActivity {
	//UI Properties
	private Button back;
	private Button submit;
    private Button check;
	private TextView myprofileUserNameTextView;
	private EditText editmyprofilePasswordEditText;
	private EditText editmyprofileRePasswordEditText;
	private EditText editmyprofileContactInfoEditText;
    private HistoryFragment history;

	//Content Properties
	private User user;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
		getReferences();

		myprofileUserNameTextView.setText(MainActivity.username);

        //Edit Myprofile
		submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (editmyprofilePasswordEditText.getText().toString().length() != 0) {
                    if (editmyprofilePasswordEditText.getText().toString().equals
                            (editmyprofileRePasswordEditText.getText().toString())) {
                        getInputs();
                        //Send to remote server
                        UpdateUser updateUser = new UpdateUser(user);
                        updateUser.execute();

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if(updateUser.checkStatus() == true){
                            Toast.makeText(getBaseContext(),"User Updated Succeed!"
                                    ,Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getBaseContext(),"Poor network connection! " +
                                    "Please update again!",Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(getBaseContext(), "Password not match, please"
                                + " retype your password!", Toast.LENGTH_LONG).show();
                        editmyprofilePasswordEditText.setText("");
                        editmyprofileRePasswordEditText.setText("");
                        editmyprofileContactInfoEditText.setText("");
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Password cannot be empty, and please"
                            + " enter your password!", Toast.LENGTH_LONG).show();
                }
            }
        });

        //Check
        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction tr = fm.beginTransaction();
                history = new HistoryFragment();
                tr.replace(R.id.history_list, history);
                tr.commit();
            }
        });
        //Check
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent back = new Intent(MyProfileActivity.this, CreateEventActivity.class);
                startActivity(back);
            }
        });
		}

	public void getReferences(){ 
		myprofileUserNameTextView = (TextView) findViewById(R.id.myprofileUserNameTextView);
		editmyprofilePasswordEditText = (EditText) findViewById(R.id.editmyprofilePasswordEditText);
		editmyprofileRePasswordEditText = (EditText) findViewById(R.id.editmyprofileRePasswordEditText);
		editmyprofileContactInfoEditText = (EditText) findViewById(R.id.editmyprofileContactInfoEditText);
		back = (Button) findViewById(R.id.editmyProfileBack_0Button);
		submit = (Button) findViewById(R.id.editmyprofileSubmit_0Button);
        check = (Button) findViewById(R.id.editmyprofileCheck_0Button);
	}
	
	public void getInputs(){
		CreateUser createusr = new UserEditor();
		user = createusr.createUser(MainActivity.username,editmyprofilePasswordEditText.getText().toString(),
						editmyprofileContactInfoEditText.getText().toString(), MainActivity.ip);
	}

}
