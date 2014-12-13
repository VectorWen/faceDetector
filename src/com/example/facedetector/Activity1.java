package com.example.facedetector;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.myview.MySurfaceView;

public class Activity1 extends Activity implements OnClickListener{
	private FrameLayout surfaceView0;
	private Button button0;
	private Camera camera;
	private MySurfaceView preview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity1);
		surfaceView0 = (FrameLayout) findViewById(R.id.surface_0);
		initSurfaceView(surfaceView0);
		button0 = (Button) findViewById(R.id.shoot_pic_0);	
		camera = getCameraInstance();
		preview = new MySurfaceView(this,camera);
		surfaceView0.addView(preview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity1, menu);
		return true;
	}
		
	private void initSurfaceView(FrameLayout surfaceView){
		setSurfaceSize(surfaceView);
	}
	
	//设置surfaceView的宽高一样大
	@SuppressLint("NewApi")
	private void setSurfaceSize(FrameLayout surfaceView){
		WindowManager wm = this.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		LayoutParams param =  surfaceView.getLayoutParams();
		param.width = width;
		param.height = width;
		surfaceView.setLayoutParams(param);
	}
	
	//检查相机设备是否存在
	private boolean checkCameraHardWare(Context context){
	        PackageManager packageManager = context.getPackageManager();
	        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
	            return true;
	        }
	        return false;
	    }

	private void showCameraView(){
//		camera.setPreviewDisplay(surfaceView0);
	}
	 
	@Override
	public void onClick(View v) {
		if(button0.getId() == v.getId()){
			
		}
		
	}
	
	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	    	// Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}
}
