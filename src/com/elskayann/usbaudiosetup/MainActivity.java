package com.elskayann.usbaudiosetup;

import java.util.HashMap;
import java.util.Iterator;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
	private static final String ACTION_USB_PERMISSION =
		    "com.android.example.USB_PERMISSION";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Setup Filter for USB Permission
		IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
		registerReceiver(new mUsbReceiver(), filter);
		
		// Setup dynamic spinner for list of USB device to connect to
		Spinner spinner = (Spinner) findViewById(R.id.usb_list_spinner);
		spinner.setOnItemSelectedListener( new SpinnerActivity());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

    /** Called when the user clicks on the Connect button */
    public void ConnectUsb(View view) {
    	Spinner UsbListSpinner = (Spinner) findViewById(R.id.usb_list_spinner);
    	String VendorName = "Dummy";
    	String ProductName = "Dummy";
    	TextView textView = (TextView) findViewById(R.id.usb_list_view);
    	String UsbList = (String) textView.getText();
        // Retrieve the UsbManager service with its message
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        PendingIntent mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);

        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while(deviceIterator.hasNext()){
            UsbDevice device = deviceIterator.next();
            String SelectedText = String.valueOf(UsbListSpinner.getSelectedItem());
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
            
            String IteratorText = VendorName+"\t"+ProductName;
//            UsbList=UsbList.concat("\n Selected:" + SelectedText);
//            UsbList=UsbList.concat("\n Device:" + IteratorText);
            if(SelectedText.equalsIgnoreCase(IteratorText)){
            	manager.requestPermission(device, mPermissionIntent);
//    	Toast.makeText(view.getContext(), 
//    			"OnItemSelectedListener : " + String.valueOf(device.getDeviceName()),
//    			Toast.LENGTH_SHORT).show();
            }
        }
        textView.setText(UsbList);
    }
	
    /** Called when the user clicks on the Discover button */
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
            
            test();
            
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

public void test ()
{
	
}
	

}

