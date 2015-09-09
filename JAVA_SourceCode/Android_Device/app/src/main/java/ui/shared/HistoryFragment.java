package ui.shared;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import Adapter.Category.GetFromCategory;
import ui.finder.Finder_AnswerCheckActivity;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import com.example.lostandfound.R;

import java.util.ArrayList;

import Adapter.Category.CategoryEditor;
import Adapter.Category.CreateCategory;
import Adapter.Category.EditCategoryDB;
import Entities.Category;
import ui.owner.Owner_IdAuthResultActivity;
import ws.local_client.FinderCheck;
import ws.local_client.OwnerResult;


public class HistoryFragment extends Fragment {

    private ListView findListView;
    private ListView lostListView;

    private Category cat;
    private String catName;
    private String location;
    private String time;
    private ArrayList<String> FindList = new ArrayList<String>();
    private ArrayList<String> LostList = new ArrayList<String>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Owner_SearchFragment--onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("Owner_SearchFragment--onCreateView");

        View view = inflater.inflate(R.layout.fragment_history, container, false);

        findListView = (ListView) view.findViewById(R.id.findhistorylist);
        lostListView = (ListView) view.findViewById(R.id.losthistorylist);

        //Get history lists
        new GetFindListTask().execute((Object[]) null);
        new GetLostListTask().execute((Object[]) null);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, FindList);
        if(!FindList.equals(null))
            findListView.setAdapter(adapter);
        adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, LostList);
        if(!LostList.equals(null))
            lostListView.setAdapter(adapter);


        //Get the clicked item of Findhistory
        findListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //Get selected item
                String item = (String) findListView.getItemAtPosition(arg2);
                String[] res = item.split(":::");
                catName = res[0];
                location = res[1];
                time = res[2];
                //Create a new category
                CreateCategory newCat = new CategoryEditor();
                cat = newCat.initCategory(catName, Category.DEFAULT);
                newCat.createItem(cat, location, time);

                //Send to remote Server
                FinderCheck finderCheck = new FinderCheck(cat);
                finderCheck.execute();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(finderCheck.checkStatus()==true){

                    cat = finderCheck.getCat();
                    GetFromCategory cated = new CategoryEditor();
                    String[] ans = cated.getAnswers(cat, location, time);
                    //Check if ans[] is null
                    if(ans!=null){
                        //Pass Information
                        Intent submit = new Intent(getActivity(), Finder_AnswerCheckActivity.class);
                        submit.putExtra("Cat", cat);
                        submit.putExtra("loc", location);
                        submit.putExtra("time", time);
                        submit.putExtra("result", Finder_AnswerCheckActivity.PASS);
                        startActivity(submit);
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(),
                                "No one answers your questions currently. Please check it later. Thank you!",
                                Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Intent submit = new Intent(getActivity(), Finder_AnswerCheckActivity.class);
                    submit.putExtra("result", Finder_AnswerCheckActivity.FAIL);
                    startActivity(submit);
                }
            }

        });

        //Get the clicked item of losthistory
        lostListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //Get selected item
                String item = (String) lostListView.getItemAtPosition(arg2);
                String[] res = item.split(":::");
                catName = res[0];
                location = res[1];
                time = res[2];
                //Create a new category
                CreateCategory newCat = new CategoryEditor();
                cat = newCat.initCategory(catName, Category.DEFAULT);
                newCat.createItem(cat, location, time);

                //Send to remote Server
                OwnerResult ownerResult = new OwnerResult(cat);
                ownerResult.execute();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(ownerResult.checkStatus()==true){
                    //Pass Category
                    cat = ownerResult.getCat();
                    Intent submit = new Intent(getActivity(), Owner_IdAuthResultActivity.class);
                    submit.putExtra("cat", cat);
                    submit.putExtra("loc", location);
                    submit.putExtra("time", time);
                    submit.putExtra("result", Finder_AnswerCheckActivity.PASS);
                    startActivity(submit);

                }else {
                    Intent submit = new Intent(getActivity(), Owner_IdAuthResultActivity.class);
                    submit.putExtra("result", Finder_AnswerCheckActivity.FAIL);
                    startActivity(submit);
                }
            }

        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        System.out.println("Owner_SearchFragment--onPause");
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        System.out.println("Owner_SearchFragment--onResume");
    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        System.out.println("Owner_SearchFragment--onStop");
    }

    //Get data from database
    private class GetFindListTask extends AsyncTask<Object, Object, Cursor> {

        EditCategoryDB editDB = new CategoryEditor();

        // perform the database access
        @Override
        protected Cursor doInBackground(Object... params) {
            return editDB.getHistoryfromDB(CreateEventActivity.FINDER, getActivity());
        } // end method doInBackground

        // use the Cursor returned from the doInBackground method
        @Override
        protected void onPostExecute(Cursor cursor) {
            getAllItems(cursor, FindList);
            System.out.println("FindhistoryCursorLength: " + cursor.getCount());
            editDB.closeDB();
        } // end method onPostExecute
    } // end class GetContactsTask

    private class GetLostListTask extends AsyncTask<Object, Object, Cursor> {

        EditCategoryDB editDB = new CategoryEditor();

        // perform the database access
        @Override
        protected Cursor doInBackground(Object... params) {
            return editDB.getHistoryfromDB(CreateEventActivity.OWNER, getActivity());
        } // end method doInBackground

        // use the Cursor returned from the doInBackground method
        @Override
        protected void onPostExecute(Cursor cursor) {
            getAllItems(cursor, LostList);
            System.out.println("LosthistoryCursorLength: " + cursor.getCount());
            editDB.closeDB();
        } // end method onPostExecute
    } // end class GetContactsTask

    public void getAllItems(Cursor cursor, ArrayList<String> list){
        if (cursor.moveToFirst()) {
            do {
                StringBuilder item = new StringBuilder();

                item.append(cursor.getString(0) + ":::");
                item.append(cursor.getString(1) + ":::");
                item.append(cursor.getString(2) + ":::");

                list.add(item.toString());
            } while (cursor.moveToNext());
        }
        System.out.println(list.size());
    }
}