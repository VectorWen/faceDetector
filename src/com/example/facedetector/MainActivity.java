package com.example.facedetector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	private Button button0;
	private Button button1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button0 = (Button) findViewById(R.id.fd_0);
		button0.setOnClickListener(this);
		button1 = (Button) findViewById(R.id.fd_1);
		button1.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	//在本activity中实现onclick
	@Override
	public void onClick(View v) {
		if(button0.getId() == v.getId()){
			Intent _intent = new Intent(this,Activity0.class);
			startActivity(_intent);
		}else if(button1.getId() == v.getId()){
			Intent _intent = new Intent(this,Activity1.class);
			startActivity(_intent);
		}
		
	}

}
