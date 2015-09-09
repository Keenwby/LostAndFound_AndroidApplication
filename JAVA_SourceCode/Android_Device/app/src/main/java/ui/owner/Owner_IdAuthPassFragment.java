package ui.owner;

import Adapter.Category.CategoryEditor;
import Adapter.Category.GetFromCategory;
import Entities.Category;
import ui.shared.MyProfileActivity;

import com.example.lostandfound.R;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;

public class Owner_IdAuthPassFragment extends Fragment {

	private Button notMine;
	private Button isMine;
    private ImageView imageView_1;
    private ImageView imageView_2;
	
	private Owner_GetContactInfoFragment info;
    private Category cat;
    private String location;
    private String time;
    private byte[] image_1;
    private byte[] image_2;
    public static String contactInfo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Owner_IdAuthPassFragment--onCreate"); 
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("Owner_IdAuthPassFragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_owner__id_auth_pass, container, false);
		
		notMine = (Button) view.findViewById(R.id.owner_IdAuthPassBack_8Button);
		isMine = (Button) view.findViewById(R.id.owner_IdAuthPassNext_8Button);
        imageView_1 = (ImageView) view.findViewById(R.id.owner_IdAuthPassimageView1);
        imageView_2 = (ImageView) view.findViewById(R.id.owner_IdAuthPassimageView2);

        //Get from previous activity
        Intent get = getActivity().getIntent();
        cat = (Category)get.getSerializableExtra("cat");
        location = get.getStringExtra("loc");
        time = get.getStringExtra("time");
//        System.out.println(get.getStringExtra("common"));
        /*if(get!=null){
            if(!get.getStringExtra("common").equals("COMMON")){*/
                GetFromCategory getImage = new CategoryEditor();
                contactInfo = getImage.getFinderName(cat, location, time);


        /*image_1 = getImage.getImage_1(cat, location, time);
        image_2 = getImage.getImage_2(cat, location, time);

        System.out.println("cat == null" + cat.equals(null));
        System.out.println("image_1 == null" + image_1.equals(null));*/


        FragmentManager fm = getFragmentManager();
        FragmentTransaction tr = fm.beginTransaction();
        info = new Owner_GetContactInfoFragment();
        tr.replace(R.id.result_content, info);
        tr.commit();

       /* ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        Bitmap bmp = BitmapFactory.decodeByteArray(image_1, 0, image_1.length);
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outPut);
        imageView_1.setImageBitmap(bmp);*/

        //imageView_1.setImageBitmap(displayImage(image_1));
        //imageView_2.setImageBitmap(displayImage(image_2));

		
		notMine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent back = new Intent(getActivity(), MyProfileActivity.class);
            	startActivity(back);
            }
        });
		
		isMine.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	FragmentManager fm = getFragmentManager();
        		FragmentTransaction tr = fm.beginTransaction();
        		info = new Owner_GetContactInfoFragment();
        		tr.replace(R.id.result_content, info);
        		tr.commit();
   
            }
        });
		
		return view;
	}

    public Bitmap displayImage(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length, null);
        /*Context context = getActivity();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(context.getResources(),bitmap);
        Drawable drawable = bitmapDrawable;*/
        return bitmap;
    }
	
	@Override 
	public void onPause() { 
	// TODO Auto-generated method stub 
	super.onPause(); 
	System.out.println("Owner_IdAuthPassFragment--onPause"); 
	} 
	
	@Override 
	public void onResume() { 
	// TODO Auto-generated method stub 
	super.onResume(); 
	System.out.println("Owner_IdAuthPassFragment--onResume"); 
	} 
	
	@Override 
	public void onStop() { 
	// TODO Auto-generated method stub 
	super.onStop(); 
	System.out.println("Owner_IdAuthPassFragment--onStop");
	}
}
