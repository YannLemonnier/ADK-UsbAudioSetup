package com.elskayann.usbaudiosetup;

import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	protected static final String TAG = null;
//	private static final String ACTION_USB_PERMISSION =
//		    "com.elskayann.usbaudiosetup.USB_PERMISSION";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		UsbManager mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);		
//		PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
		
//		IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
//		registerReceiver(mUsbReceiver, filter);
//		
//		Spinner spinner = (Spinner) findViewById(R.id.usb_list_spinner);
//		spinner.setOnItemSelectedListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

    /** Called when the user clicks the Connect button */
    public void ConnectUsb(View view) {
    	
    	
    }
	
    /** Called when the user clicks the Discover button */
    public void DiscoverUsb(View view) {
    	// Initiate variables
    	String UsbList = "Detailed Device List:\n";
    	ArrayAdapter<String> connectedDevicesAdapter;
    	connectedDevicesAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1) ;
    	String VendorName = "Dummy";
    	String ProductName = "Dummy";
    	// Do something in response to button
    	
        // Retrieve the text view
        TextView textView = (TextView) findViewById(R.id.usb_list_view);

        
        // Retrieve the UsbManager service with its message
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);

        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while(deviceIterator.hasNext()){
            UsbDevice device = deviceIterator.next();
            
            UsbList=UsbList.concat("Device Name:\n"+device.getDeviceName()+"\n") ;
            UsbList=UsbList.concat("VendorId:"+Integer.toHexString(device.getVendorId())+"\t"+"ProductId:"+Integer.toHexString(device.getProductId())+"\n") ;

            UsbList=UsbList.concat("VendorId:"+device.getVendorId()+"\t"+"ProductId:"+device.getProductId()+"\n") ;


            UsbList=UsbList.concat("Device Class:"+Integer.toHexString(device.getDeviceClass())+"\t"+"subClass:"+device.getDeviceSubclass()+"\n") ;
            UsbList=UsbList.concat("DeviceId:"+device.getDeviceId()+"\t"+"InterfaceCount:"+device.getInterfaceCount()+"\n") ;
            
            int Vid = device.getVendorId();
            
            
            switch (Vid) {
            case 2372:
            	VendorName = "KORG, Inc.";
            	ProductName = "nanoKONTROL studio controller";
            	break;	
            case 2235:
            	VendorName = "Texas Instruments Japan";
            	ProductName = "PCM2900 Audio Codec";
            	break;
            default:
				// do nothing.
				break;	
            }
            
            UsbList=UsbList.concat(VendorName+"\n"+ProductName+"\n") ;
            
            connectedDevicesAdapter.add(VendorName+"\t"+ProductName);
            UsbList=UsbList.concat("\n");
        }
        textView.setText(UsbList);
        
		Spinner UsbListSpinner = (Spinner) findViewById(R.id.usb_list_spinner); 
		
		
		
		UsbListSpinner.setAdapter(connectedDevicesAdapter);
        
    }

//    public class SpinnerActivity extends Activity implements OnItemSelectedListener {
//        
//        
//        public void onItemSelected(AdapterView<?> parent, View view, 
//                int pos, long id) {
//            // An item was selected. You can retrieve the selected item using
//            // parent.getItemAtPosition(pos)
//        }
//
//        public void onNothingSelected(AdapterView<?> parent) {
//            // Another interface callback
//        }
//    }
	
//    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {

//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (ACTION_USB_PERMISSION.equals(action)) {
//                synchronized (this) {
//                    UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
//
//                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
//                        if(device != null){
//                          //call method to set up device communication
//                       }
//                    } 
//                    else {
//                        Log.d(TAG, "permission denied for device " + device);
//                    }
//                }
//            }
//        }
//    };
}

