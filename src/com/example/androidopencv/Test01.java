package com.example.androidopencv;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Test01 extends Activity implements OnClickListener{

	TextView tv;
	EditText ed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.radiogroup_and_edittext);
		
		tv = (TextView) findViewById(R.id.tvOutput);
		ed = (EditText) findViewById(R.id.etInput);
		ed.setText(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath());
		tv.setText(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath());
		Button b = (Button) findViewById(R.id.button);
		b.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		tv.setText(ed.getText());		
	}

}
