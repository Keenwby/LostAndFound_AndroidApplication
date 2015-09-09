package ui.finder.upload;

import com.example.lostandfound.R;
import com.example.lostandfound.R.id;
import com.example.lostandfound.R.layout;

import Adapter.Category.CategoryEditor;
import Adapter.Category.CreateCategory;
import Entities.Category;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;  
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;


public class Finder_Upload_1Fragment extends Fragment {

	//UI Properties
    private Button founderLocationButton;
	private Button next;
	private EditText locationEditText;
	private DatePicker datePicker;
	private TimePicker timePicker;
	private Finder_Upload_2Fragment up_2;
    private TextView LocationText;
	//Date properties
	private int year;
	private int month;
	private int day;
	private int hour;
	private int  min;
	//
	private Category cat;
	private String loc;
	private String dt;

    private AppLocationService appLocationService;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Finder_Upload_1_Fragment--onCreate"); 
	}
	
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState){
		System.out.println("Finder_Upload_1_Fragment--onCreateView"); 
		
		View view = inflater.inflate(R.layout.fragment_finder__upload_1, container, false);
        appLocationService = new AppLocationService(getActivity());
		getReferences(view);
		getCat();

        founderLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location location = appLocationService
                        .getLocation(LocationManager.GPS_PROVIDER);

                //you can hard-code the lat & long if you have issues with getting it
                //remove the below if-condition and use the following couple of lines
                //double latitude = 37.422005;
                //double longitude = -122.084095

                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    System.out.println("address:" + getCompleteAddressString(latitude,longitude));
                    locationEditText.setText(getCompleteAddressString(latitude,longitude));
                } else {
                    showSettingsAlert();
                }
            }
        });

		next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

           if(locationEditText.getText().toString().length()!= 0){
               //Get date properties from UI tools
               getLocation();
               getDateFromPicker();
               getTimeFromPicker();
               getDateandTime();

               //Create an item with location & time
               CreateCategory createcat = new CategoryEditor();
               createcat.createItem(cat, loc, dt);
               //Upload to the pk-shared category object
               uploaditem();

               //Transaction to upload 2
               FragmentManager fm = getFragmentManager();
               FragmentTransaction tr = fm.beginTransaction();
               up_2 = new Finder_Upload_2Fragment();
               tr.replace(R.id.upload_content, up_2);
               tr.commit();
           } else
                Toast.makeText(getActivity().getBaseContext(),"Location cannot be empty. " +
                            "Please enter a location or click to share your location!",Toast.LENGTH_LONG).show();
            }
        });
		
		return view;
	}

	@Override 
	public void onPause() { 
	// TODO Auto-generated method stub 
	super.onPause(); 
	System.out.println("Finder_Upload_1_Fragment--onPause"); 
	} 
	
	@Override 
	public void onResume() { 
	// TODO Auto-generated method stub 
	super.onResume(); 
	System.out.println("Finder_Upload_1_Fragment--onResume"); 
	} 
	
	@Override 
	public void onStop() { 
	// TODO Auto-generated method stub 
	super.onStop(); 
	System.out.println("Finder_Upload_1_Fragment--onStop");
	}
	
	public void getReferences(View view){
		next = (Button) view.findViewById(R.id.founder_Upload_1Next_5Button);
		locationEditText = (EditText) view.findViewById(R.id.founder_Upload_1LocationEditText);
		datePicker = (DatePicker) view.findViewById(R.id.founder_Upload_1datePicker);
		timePicker = (TimePicker) view.findViewById(R.id.founder_Upload_1timePicker);
        founderLocationButton = (Button) view.findViewById(id.founder_Upload_1LocationButton);
        LocationText = (TextView) view.findViewById(R.id.founder_Upload_1Location_1TextView);
	}
	//The followed four methods are used for get dt and loc
	public void getLocation(){
		if(locationEditText.getText().toString().length()!= 0)
            this.loc = locationEditText.getText().toString();
        else
            Toast.makeText(getActivity().getBaseContext(),"Location cannot be empty. " +
                    "Please enter a location or click to share your location!",Toast.LENGTH_LONG).show();
	}
	
	public void getDateFromPicker() {
		this.year = datePicker.getYear();
		this.month = datePicker.getMonth();
		this.day = datePicker.getDayOfMonth();
		}
	
	public void getTimeFromPicker() {
		TimeListener times = new TimeListener();
		timePicker.setOnTimeChangedListener(times);
		this.hour = timePicker.getCurrentHour();
		this.min = timePicker.getCurrentMinute();
	}    
	class TimeListener implements OnTimeChangedListener{  
	    @Override  
	    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {  
        // TODO Auto-generated method stub  
	       System.out.println("h:"+ hourOfDay +" m:"+minute);  
	      }     
	  }
	
    public void getDateandTime(){
		
		StringBuilder str = new StringBuilder();
		   
			str.append(++month + "/");
			str.append(day + "/");
			str.append(year + " ");
			str.append(hour + ": ");
			str.append(min);
		
		this.dt = str.toString();
	}

  //Get the Category object created from last activity
  	public void getCat(){
  		Intent getCat = getActivity().getIntent();
  		this.cat = (Category) getCat.getSerializableExtra("Cat");
  	}
  //Upload the item to package-shared properties
    public void uploaditem(){
    	Finder_Upload_Activity.cat = this.cat;
    	Finder_Upload_Activity.location = this.loc;
    	Finder_Upload_Activity.time = this.dt;
    }

    public void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                getActivity());
        alertDialog.setTitle("SETTINGS");
        alertDialog.setMessage("Enable Location Provider! Go to settings menu?");
        alertDialog.setPositiveButton("Settings",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        getActivity().startActivity(intent);
                    }
                });
        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }


    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();

            } else {

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return strAdd;
    }

}
