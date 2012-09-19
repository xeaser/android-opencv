package com.example.androidopencv;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Test02 extends Activity implements OnClickListener {
	private static final String TAG = "Test2";
	ImageView display;
	int id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
							WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.imageview);
		
		display = (ImageView) findViewById(R.id.ivBkgrn);
		
		ImageView im01 = (ImageView) findViewById(R.id.ivDisplay01);
		ImageView im02 = (ImageView) findViewById(R.id.ivDisplay02);
		ImageView im03 = (ImageView) findViewById(R.id.ivDisplay03);
		ImageView im04 = (ImageView) findViewById(R.id.ivDisplay04);
		ImageView im05 = (ImageView) findViewById(R.id.ivDisplay05);
		ImageView im06 = (ImageView) findViewById(R.id.ivDisplay06);
		
		im01.setOnClickListener(this);
		im02.setOnClickListener(this);
		im03.setOnClickListener(this);
		im04.setOnClickListener(this);
		im05.setOnClickListener(this);
		im06.setOnClickListener(this);
		
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				// TODO Auto-generated method stub
				return filename.endsWith(".jpg")
					|| filename.endsWith(".png")
					|| filename.endsWith(".bmp")
					|| filename.endsWith(".gif");
			}
		};
		HorizontalScrollView scr = (HorizontalScrollView)  findViewById(R.id.scroll);
		//LinearLayout llayout = scr.get
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		String[] listFileName = dir.list(filter);
		
		for (int i=0; i< listFileName.length; i++) {
			//ImageView iv = new ImageView(this);
			//iv.setImageBitmap(BitmapFactory.decodeFile(listFileName[i]));
			//scr.addView(iv);
			Log.d(TAG, dir.getAbsolutePath() + "/" + listFileName[i]);
		}
		
		id = R.drawable.airplane;
		
		Button b = (Button) findViewById(R.id.btSetBkgrn);
		b.setOnClickListener(this);
		
		Button bt = (Button) findViewById(R.id.btDoProcess);
		bt.setOnClickListener(this);
		//scr.addV
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.ivDisplay01:
			display.setImageResource(R.drawable.baboon);
			id = R.drawable.baboon;
			break;
		case R.id.ivDisplay02:
			display.setImageResource(R.drawable.fruits);
			//display.setImag
			id = R.drawable.fruits;
			break;
		case R.id.ivDisplay03:
			display.setImageResource(R.drawable.lena);
			id = R.drawable.lena;
			break;
		case R.id.ivDisplay04:
			display.setImageResource(R.drawable.puzzle);
			id = R.drawable.puzzle;
			break;
		case R.id.ivDisplay05:
			display.setImageResource(R.drawable.stuff);
			id = R.drawable.stuff;
			break;
		case R.id.ivDisplay06:
			display.setImageResource(R.drawable.box);
			id = R.drawable.box;
			break;
			
		case R.id.btSetBkgrn:
			//Bitmap bm = BitmapFactory.decodeStream(getResources().openRawResource(id));
			try {
				getApplicationContext().setWallpaper(getResources().openRawResource(id));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			//Bitmap bm = BitmapFactory.decode(pathName);
			//display.setImageBitmap()
			break;
		}
	}

}
