package ui.finder.upload;

import java.io.ByteArrayOutputStream;

import com.example.lostandfound.R;

import Adapter.Category.CategoryEditor;
import Adapter.Category.CreateCategory;
import Entities.Category;
import ui.shared.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Finder_Upload_2Fragment extends Fragment{
	//UI Properties
	private Button next;
	private Button upload_1;
	private Button upload_2;
	private ImageView imageView_1;
	private ImageView imageView_2;
	private Finder_Upload_1Fragment up_1;
	private Finder_Upload_3Fragment up_3;
	//Content Properties
	private int selectImage;
	private byte[] image_1;
	private byte[] image_2;
    private int image_num = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("Finder_Upload_2_Fragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_finder__upload_2, container, false);
		
		getReferences(view);
		
		next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(image_num == 2){
                    //Upload images to the pk-shared category object
                    uploadImage();
                    //If the item is a valuable one, head to Uploaad_3
                    if(Finder_Upload_Activity.cat.getValue() == Category.VALUEABLE){
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction tr = fm.beginTransaction();
                        up_3 = new Finder_Upload_3Fragment();
                        tr.replace(R.id.upload_content, up_3);
                        tr.commit();
                    }
                    //If the item is a common one, head to EventComplete
                    if(Finder_Upload_Activity.cat.getValue() == Category.COMMON){
                        String q[] = {" ", " ", " "};
                        CreateCategory createcat = new CategoryEditor();
                        createcat.addQuestions(Finder_Upload_Activity.cat, Finder_Upload_Activity.location,
                                Finder_Upload_Activity.time,q);
                        createcat.setFinderName(Finder_Upload_Activity.cat, Finder_Upload_Activity.location,
                                Finder_Upload_Activity.time, MainActivity.username);
                        Intent complete = new Intent(getActivity(), Finder_EventCompleteActivity.class);
                        startActivity(complete);
                    }
                }
                else{
                    Toast.makeText(getActivity(), "Please upload 2 images!", Toast.LENGTH_LONG).show();
                }
            }
        });
		
		//Set Upload Buttons
		upload_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                image_num++;
            	selectImage = 1;
            	openCamera();
            }
        });
		
		upload_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                image_num++;
            	selectImage = 2;
            	openCamera();
            }
        });
		
		return view;
	}
	
	public void getReferences(View view){
		next = (Button) view.findViewById(R.id.founder_Upload_2Submit_5Button);
		upload_1 = (Button) view.findViewById(R.id.founder_Upload_Photos1_5Button);
		upload_2 = (Button) view.findViewById(R.id.founder_Upload_Photos2_5Button);
		imageView_1 = (ImageView)view.findViewById(R.id.founder_Upload_2PhotosimageView1);
		imageView_2 = (ImageView)view.findViewById(R.id.founder_Upload_2PhotosimageView2);
	}
	
	//Take photos
	public void openCamera(){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
	//Get photos
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bp = (Bitmap) data.getExtras().get("data");
        displayImage(bp);
    }
	
	//Set photos and display
	public void displayImage(Bitmap bp){
        if(selectImage == 1){
            ByteArrayOutputStream bos1 = new ByteArrayOutputStream();
            bp.compress(Bitmap.CompressFormat.PNG, 100, bos1);
            image_1 = bos1.toByteArray();
            System.out.println("**************"+image_1);
            imageView_1.setImageBitmap(bp);
        }else{
            ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
            bp.compress(Bitmap.CompressFormat.PNG, 100, bos2);
            image_2 = bos2.toByteArray();
            System.out.println("**************"+image_2);
            imageView_2.setImageBitmap(bp);
        }
    }
	
	//Upload images to the pk-shared category object 
	public void uploadImage(){
		
		if(image_num == 2){
			//Add images
        	CreateCategory createcat = new CategoryEditor(); 
        	createcat.addImage(Finder_Upload_Activity.cat, Finder_Upload_Activity.location, 
        					   Finder_Upload_Activity.time, image_1, image_2);
		}
		else{
			Toast.makeText(getActivity(), "Please upload 2 images!", Toast.LENGTH_LONG).show();
		}
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
