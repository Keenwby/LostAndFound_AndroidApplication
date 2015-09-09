package ui.owner.search;

import Adapter.Category.CategoryEditor;
import Adapter.Category.CreateCategory;
import Entities.Category;
import ui.owner.Owner_EventCompleteActivity;

import com.example.lostandfound.R;

import DataBase.CreateCategoryTable;
import ui.shared.MainActivity;
import ws.local_client.UploadAnswer;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Owner_IdAuthFragment extends Fragment {

    private Button back;
    private Button submit;
    private TextView q1;
    private TextView q2;
    private TextView q3;
    private EditText a1;
    private EditText a2;
    private EditText a3;

    private String[] ans;

    private Owner_SearchFragment search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Owner_IdAuthFragment--onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("Owner_IdAuthFragment--onCreateView");

        View view = inflater.inflate(R.layout.fragment_owner__id_auth, container, false);

        getReferences(view);
        //Display Questions
        System.out.println(Owner_SearchActivity.ques[0]);
        q1.setText(Owner_SearchActivity.ques[0]);
        q2.setText(Owner_SearchActivity.ques[1]);
        q3.setText(Owner_SearchActivity.ques[2]);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction tr = fm.beginTransaction();
                search = new Owner_SearchFragment();
                tr.replace(R.id.search_content, search);
                tr.commit();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Get Answers
                ans = new String[3];
                ans[0] = a1.getText().toString();
                ans[1] = a2.getText().toString();
                ans[2] = a3.getText().toString();
                CreateCategory addAns = new CategoryEditor();
                addAns.addAnswers(Owner_SearchActivity.cat, Owner_SearchActivity.loc, Owner_SearchActivity.time, ans);
                addAns.addOwnerName(Owner_SearchActivity.cat, Owner_SearchActivity.loc,
                        Owner_SearchActivity.time, MainActivity.username);
                //Send to remote server
                UploadAnswer uploadAnswer = new UploadAnswer(Owner_SearchActivity.cat);
                uploadAnswer.execute();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (uploadAnswer.checkStatus() == true) {
                    Toast.makeText(getActivity().getBaseContext(), "Answer Uploaded Succeed!" + "Thanks you and please check your event in myProfile later!"
                            , Toast.LENGTH_LONG).show();
                    Intent submit = new Intent(getActivity(), Owner_EventCompleteActivity.class);
                    startActivity(submit);

                } else {
                    Toast.makeText(getActivity().getBaseContext(), "Poor network connection! " +
                            "Please upload again!", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        System.out.println("Owner_IdAuthFragment--onPause");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        System.out.println("Owner_IdAuthFragment--onResume");
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        System.out.println("Owner_IdAuthFragment--onStop");
    }

    public void getReferences(View view) {
        submit = (Button) view.findViewById(R.id.owner_IdAuthQuetionsSubmitButton);
        back = (Button) view.findViewById(R.id.owner_IdAuthQuetionsBackButton);
        q1 = (TextView) view.findViewById(R.id.owner_IdAuthQuetions_3TextView);
        q2 = (TextView) view.findViewById(R.id.owner_IdAuthQuetions_4TextView);
        q3 = (TextView) view.findViewById(R.id.owner_IdAuthQuetions_5TextView);
        a1 = (EditText) view.findViewById(R.id.owner_IdAuthQuetions_1EditText);
        a2 = (EditText) view.findViewById(R.id.owner_IdAuthQuetions_2EditText);
        a3 = (EditText) view.findViewById(R.id.owner_IdAuthQuetions_3EditText);
    }
}