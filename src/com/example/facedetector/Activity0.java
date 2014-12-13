package com.example.facedetector;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class Activity0 extends Activity {
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private File file;
	private ImageView imageview0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity0);
		imageview0 = (ImageView) findViewById(R.id.imageview0);
		
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		
		String sdCardExist = Environment.getExternalStorageState();
		String sdCardPath = Environment.getExternalStorageDirectory().getPath();
		
		file = new File(sdCardPath +"/faceDetector");
		if(!file.exists()){
			boolean suc = file.mkdirs();
			Log.d("----------0", file.getAbsolutePath());
		}		
		Log.d("----------1", file.getAbsolutePath());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {
	        	Bundle bundle = data.getExtras();
	        	Bitmap image = (Bitmap) bundle.get("data");	  
	        	
	        	//获取系统时间搓
	        	Long tsLong = System.currentTimeMillis()/1000;
	        	String ts = tsLong.toString();

	        	String filename = ts + ".jpg";
	        	String filepath = file.getAbsoluteFile().toString();
	        	Log.d("----------2", filepath);	        	
	        	File imgfile = new File(filepath,filename);
	        	Log.d("----------3", imgfile.getAbsolutePath().toString());
	        	FileOutputStream fileOutputStream = null;
	        	try {
					fileOutputStream = new FileOutputStream(imgfile);
					if(image != null){
						if(image.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)){
							fileOutputStream.flush();
							fileOutputStream.close();
						}						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
	        	imageview0.setImageBitmap(image);
	        } else if (resultCode == RESULT_CANCELED) {
	        } else {}
	    }

	    if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
	        if (resultCode == RESULT_OK) {	            
	        	Toast.makeText(this, "Video saved to:\n" +
	                     data.getData(), Toast.LENGTH_LONG).show();
	            Bundle bundle = data.getExtras();
	        } else if (resultCode == RESULT_CANCELED) {
	        } else {
	        }
	    }
	}
}
