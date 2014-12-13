package com.example.myview;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{
	private Camera camera;
	private SurfaceHolder holder;
	
	public MySurfaceView(Context context) {
		super(context);
	}
	
	public MySurfaceView(Context context,Camera camera) {
		super(context);
		this.camera = camera;
		holder = getHolder();
		holder.addCallback(this);		
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		if (holder.getSurface() == null){
	          return;
	        }
	        try {
	            camera.stopPreview();
	        } catch (Exception e){

	        }
	        try {
	            camera.setPreviewDisplay(holder);
	            camera.startPreview();
	        } catch (Exception e){
	            Log.d("TAG", "Error starting camera preview: " + e.getMessage());
	        }
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		try {
            camera.setDisplayOrientation(90);			
			camera.setPreviewDisplay(holder);
			camera.startPreview();
        } catch (IOException e) {
            Log.d("TAG", "Error setting camera preview: " + e.getMessage());
        }
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//释放相机实例，防止下次进入后发生异常
		camera.release();
	}
}
