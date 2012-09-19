package com.example.androidopencv;

import java.io.File;
import java.io.FilenameFilter;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainAct extends Activity implements OnClickListener{
	private static final String TAG = "MAIN";
	String[] listFileName = null;
	int iSelImg = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        Thread logoTimer = new Thread() {
	        public void Run() {
	        	try {
					sleep(1500);
					System.out.println("Start activity !");
					startActivity(new Intent("android.intent.action.SHOWLOGO"));
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					finish();
				}
	        }
        };

        System.out.println("Start activity !");
        logoTimer.start();
        
//        Button b = (Button) findViewById(R.id.bt1);
//        b.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				startActivity(new Intent("android.intent.action.TESTIMAGE"));
//			}
//		});
        //setContentView(R.layout.activity_main);
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
		LinearLayout llayout = (LinearLayout) findViewById(R.id.linearscroll);
		File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		listFileName = dir.list(filter);
		
		Log.d(TAG, "Start add view");
//		ImageView iv0 = new ImageView(this);
//	    iv0.setScaleType(ImageView.ScaleType.FIT_CENTER);
//	    iv0.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
//	                LayoutParams.WRAP_CONTENT));
//		iv0.setImageResource(R.drawable.stuff);
//		llayout.addView(iv0);
		
		for (int i=0; i< listFileName.length; i++) {
			ImageView iv = new ImageView(this);
		    iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
		    iv.setLayoutParams(new LayoutParams(400, LayoutParams.WRAP_CONTENT));
		    iv.setPadding(5, 5, 5, 5);
		    //iv.setBackgroundColor(Color.BLUE);
		    listFileName[i] = dir.getAbsolutePath() + "/" + listFileName[i];
		    iv.setImageBitmap(BitmapFactory.decodeFile(listFileName[i]));
		    iv.setTag(i);
		    iv.setOnClickListener(this);
			llayout.addView(iv);
			Log.d(TAG, listFileName[i]);
		}
    }

    @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()) {
		case R.id.itemAdvance:
			Log.d(TAG, "Advance");
			break;
		case R.id.itemFilter:
			Log.d(TAG, "Filter");
			break;
		case R.id.itemBasic:
			Log.d(TAG, "Basic");
			break;
		case R.id.itemFlipRotate:
			Log.d(TAG, "Flip rotate");
			break;
			
		case R.id.itemFilterGrey:
			Log.d(TAG, "Filter grey");
			Mat mat, dst;
			mat = Highgui.imread(listFileName[0]);
			Log.d(TAG, "Imread");
			dst = mat;
			Imgproc.cvtColor(mat, dst, Imgproc.COLOR_RGB2GRAY);
			Bitmap bmp = Bitmap.createBitmap(mat.cols(), mat.rows(), Config.ARGB_8888);
			Utils.matToBitmap(mat, bmp);
			
			ImageView iv = (ImageView) findViewById(R.id.ivDisplay);
			iv.setImageBitmap(bmp);
			
			
			
			break;
		case R.id.itemFilterGreen:
			Log.d(TAG, "Filter green");
			break;
		case R.id.itemFilterBlue:
			Log.d(TAG, "Filter blue");
			break;
		case R.id.itemFilterRed:
			Log.d(TAG, "Filter red");
			break;
			
		case R.id.itemBrightContrast:
			Log.d(TAG, "Brightness & Contrast");
			startActivity(new Intent("android.intent.action.SHOWLOGO"));
			break;
		case R.id.itemCrop:
			Log.d(TAG, "Crop");
			//startActivity(new Intent("android.intent.action.SHOWLOGO"));
			break;
		case R.id.itemResize:
			Log.d(TAG, "Resize");
			//startActivity(new Intent("android.intent.action.SHOWLOGO"));
			break;
			
		case R.id.itemFlipHorizontal:
			Log.d(TAG, "Flip horizontal");
			startActivity(new Intent("android.intent.action.TESTIMAGE"));
			break;
		case R.id.itemFlipVertical:
			Log.d(TAG, "Flip vertical");
			break;
		case R.id.itemRotate90:
			Log.d(TAG, "Rotate 90°");
			break;
		case R.id.itemRotate180:
			Log.d(TAG, "Rotate 180°");
			break;
		case R.id.itemRotate270:
			Log.d(TAG, "Rotate 270°");
			break;
		default:
			return false;
		}
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		ImageView iv = (ImageView) v;
		ImageView ivDisplay = (ImageView) findViewById(R.id.ivDisplay);
		iSelImg = (Integer) iv.getTag();
		Log.d(TAG, String.valueOf(iSelImg));
		ivDisplay.setImageBitmap(BitmapFactory.decodeFile(listFileName[iSelImg]));
		//iv = (ImageView) v.findViewWithTag(tag)
		//switch (v.getTag()) {
		//case 0:
		//}
	}
}
