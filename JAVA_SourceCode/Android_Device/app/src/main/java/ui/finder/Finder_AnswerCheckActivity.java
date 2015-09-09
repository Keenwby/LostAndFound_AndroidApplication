package ui.finder;

import Adapter.Category.CategoryEditor;
import Adapter.Category.GetFromCategory;
import Entities.Category;
import ui.owner.Owner_IdAuthResultActivity;
import ws.local_client.FinderResult;

import com.example.lostandfound.R;
import com.example.lostandfound.R.id;
import com.example.lostandfound.R.layout;
import com.example.lostandfound.R.menu;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Finder_AnswerCheckActivity extends ActionBarActivity implements OnClickListener{

	public static final int PASS = 1;
	public static final int FAIL = 0;

    private Category cat;
    private String loc;
    private String time;
    private int res;

	private Button pass;
	private Button fail;
    private CheckBox checkBox_1;
    private CheckBox checkBox_2;
    private CheckBox checkBox_3;
    private TextView textView_1;
    private TextView textView_2;
    private TextView textView_3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finder__answer_check);

        getReferences();

		fail.setOnClickListener(this);
		pass.setOnClickListener(this);

        Intent getCat = getIntent();
        cat = (Category) getCat.getSerializableExtra("Cat");
        loc = getCat.getStringExtra("loc");
        time = getCat.getStringExtra("time");

        GetFromCategory cated = new CategoryEditor();
        String[] ques = cated.getQuestions(cat, loc, time);
        String[] ans = cated.getAnswers(cat, loc, time);

        setDisplay(ques, ans);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.founder_AnswerCheckFailButton:
            res = FAIL;
            FinderResult fail = new FinderResult(res, cat);
            fail.execute();
            Toast.makeText(getBaseContext(), "Thank you very much! " +
                    "Please remember to check other possible owners later!", Toast.LENGTH_LONG).show();
			break;
		case R.id.founder_AnswerCheckPassButton:
            if(checkBox_1.isChecked() && checkBox_2.isChecked() && checkBox_3.isChecked()){
                res = PASS;
                FinderResult pass = new FinderResult(res, cat);
                pass.execute();
                Toast.makeText(getBaseContext(), "Thank you very much! " +
                        "The owner may contact you later!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getBaseContext(), "Please make sure all answers are correct! ", Toast.LENGTH_LONG).show();
                checkBox_1.setChecked(false);
                checkBox_2.setChecked(false);
                checkBox_3.setChecked(false);
            }
			break;
		}


	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.finder__answer_check, menu);
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

    public void getReferences(){
        fail = (Button) findViewById(R.id.founder_AnswerCheckFailButton);
        pass = (Button) findViewById(R.id.founder_AnswerCheckPassButton);
        checkBox_1 = (CheckBox) findViewById(R.id.founder_AnswerCheckcheckBox1);
        checkBox_2 = (CheckBox) findViewById(R.id.founder_AnswerCheckcheckBox2);
        checkBox_3 = (CheckBox) findViewById(R.id.founder_AnswerCheckcheckBox3);
        textView_1 = (TextView) findViewById(R.id.founder_AnswerCheckTextView1);
        textView_2 = (TextView) findViewById(R.id.founder_AnswerCheckTextView2);
        textView_3 = (TextView) findViewById(R.id.founder_AnswerCheckTextView3);
    }

    public void setDisplay(String[] ques, String[] ans){
        if(ans!=null){
            checkBox_1.setText(ans[0]);
            checkBox_2.setText(ans[1]);
            checkBox_3.setText(ans[2]);
        }else{
            Toast.makeText(getApplicationContext(),
                    "No one answers your questions currently. Please check it later. Thank you!",
                    Toast.LENGTH_SHORT).show();
        }
        textView_1.setText(ques[0]);
        textView_2.setText(ques[1]);
        textView_3.setText(ques[2]);
    }
}


