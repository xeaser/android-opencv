package com.example.androidopencv;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.opencv.photo.Photo;

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
import android.widget.Toast;

public class MainAct extends Activity implements OnClickListener{
	private static final String TAG = "MAIN";
	String[] listFileName = null;
	int iSelImg = 0;
	Mat matCurrent = null, matSaved;
	//MainAct mView;

    private BaseLoaderCallback  mOpenCVCallBack = new BaseLoaderCallback(this) {
    	@Override
    	public void onManagerConnected(int status) {
    		switch (status) {
				case LoaderCallbackInterface.SUCCESS:
				{
					Log.i(TAG, "OpenCV loaded successfully");
					setContentView(R.layout.mainlayout);
					// Check native OpenCV camera
					//if( !mView.openCamera() ) {
					//	AlertDialog ad = new AlertDialog.Builder(mAppContext).create();
					//	ad.setCancelable(false); // This blocks the 'BACK' button
					//	ad.setMessage("Fatal error: can't open camera!");
					//	ad.setButton("OK", new DialogInterface.OnClickListener() {
					//	    public void onClick(DialogInterface dialog, int which) {
					//		dialog.dismiss();
					//		finish();
					//	    }
					//	});
					//	ad.show();
					//}
					
					Init();
				} break;
				default:
				{
					super.onManagerConnected(status);
				} break;
			}
    	}
	}; 
	
	private void Init()
	{
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
		//dir.mkdir();
		
		listFileName = dir.list(filter);
		//Toast.makeText(this, dir.getAbsolutePath(), 3000);
		Log.d(TAG, dir.getAbsolutePath());
		
		
		Log.d(TAG, "Start add view" + String.valueOf(listFileName.length));
//		ImageView iv0 = new ImageView(this);
//	    iv0.setScaleType(ImageView.ScaleType.FIT_CENTER);
//	    iv0.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
//	                LayoutParams.WRAP_CONTENT));
//		iv0.setImageResource(R.drawable.stuff);
//		llayout.addView(iv0);
		
		for (int i=0; i< listFileName.length; i++) {
			Log.d(TAG, listFileName[i]);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.mainlayout);
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
        //logoTimer.start();
        
//        Button b = (Button) findViewById(R.id.bt1);
//        b.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View arg0) {
//				// TODO Auto-generated method stub
//				startActivity(new Intent("android.intent.action.TESTIMAGE"));
//			}
//		});
        //setContentView(R.layout.activity_main);
        Log.i(TAG, "Trying to load OpenCV library");
        if (!OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_2, this, mOpenCVCallBack))
        {
        	Log.e(TAG, "Cannot connect to OpenCV Manager");
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
		if (matCurrent == null)
			return false;
		
		//Mat dst = null;
		Bitmap bmp = null;
		ImageView iv;
		matSaved = matCurrent.clone();
		
		switch (item.getItemId()) {
		case R.id.itemAdvance:
			Log.d(TAG, "Advance");
			return true;
		case R.id.itemFilter:
			Log.d(TAG, "Filter");
			return true;
		case R.id.itemBasic:
			Log.d(TAG, "Basic");
			return true;
		case R.id.itemFlipRotate:
			Log.d(TAG, "Flip rotate");
			return true;
			
		case R.id.itemFaceRec:
			MatOfRect facemat = new MatOfRect();
			FaceDetect(matCurrent, facemat, "/sdcard/haar_frontalface.xml", true);
			break;
		case R.id.itemFilterGrey:
			Log.d(TAG, "Filter grey");
			Log.d(TAG, listFileName[0]);
			//Bitmap b = BitmapFactory.decodeFile(listFileName[0]);
			//mat = new Mat(b.getWidth(), b.getHeight(), CvType.CV_8UC1);
			//Utils.bitmapToMat(b, mat);
			Log.d(TAG, "Imread");
			//dst = new Mat(matCurrent.size(), matCurrent.type());
			Imgproc.cvtColor(matCurrent, matCurrent, Imgproc.COLOR_RGB2GRAY);
			
			break;
		case R.id.itemFilterGreen:
			Log.d(TAG, "Filter green");
			
			return true;
		case R.id.itemFilterBlue:
			Log.d(TAG, "Filter blue");
			return true;
		case R.id.itemFilterRed:
			Log.d(TAG, "Filter red");
			return true;
			
		case R.id.itemBrightContrast:
			Log.d(TAG, "Brightness & Contrast");
			startActivity(new Intent("android.intent.action.BRIGHTNESSCONTRAST"));
			break;
		case R.id.itemCrop:
			Log.d(TAG, "Crop");
//			Intent intent = new Intent();
//			intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//			startActivityForResult(intent, 1);
//			startActivityForResult(Intent.createChooser(intent,"Wybierz plik"), SELECT_FILE);
			//startActivity(new Intent("android.intent.action.SHOWLOGO"));
			break;
		case R.id.itemResize:
			Log.d(TAG, "Resize");
			//startActivity(new Intent("android.intent.action.SHOWLOGO"));
			break;
		case R.id.itemBlur:
			Log.d(TAG, "Blur");
			Imgproc.medianBlur(matCurrent, matCurrent, 11);
			break;
		case R.id.itemSharpen:
			Log.d(TAG, "Sharpen");
			//int array[][] = { {0, -1, 0} ,
			//				{-1, 5, -1} , {0, -1, 0}};
			Mat kern = new Mat(3, 3, CvType.CV_8UC1);
			Log.d(TAG, "Depth=" +String.valueOf(kern.depth()) + ", chanels=" + String.valueOf(kern.channels()));
			kern.get(0, 0)[0] = 0;
			kern.get(0, 1)[0] =-1;
			kern.get(0, 2)[0] = 0;
			kern.get(1, 0)[0] =-1;
			kern.get(1, 1)[0] = 5;
			kern.get(1, 2)[0] =-1;
			kern.get(2, 0)[0] = 0;
			kern.get(2, 1)[0] =-1;
			kern.get(2, 2)[0] = 0;
			Imgproc.filter2D(matCurrent, matCurrent, matCurrent.depth(), kern);					
						
			break;
			
		case R.id.itemEqualHist:
			Log.d(TAG, "Eualize histogram");
			Mat hsv = matCurrent.clone();
			List<Mat> listmat = new ArrayList<Mat>(3);
			Imgproc.cvtColor(matCurrent, hsv, Imgproc.COLOR_RGB2HSV);
			Core.split(hsv, listmat);
			Imgproc.equalizeHist(listmat.get(2), listmat.get(2));
			Core.merge(listmat, hsv);
			Imgproc.cvtColor(hsv, matCurrent, Imgproc.COLOR_HSV2RGB);
			
			break;
			
		/////////
		//	Flip
		case R.id.itemFlipHorizontal:
			Log.d(TAG, "Flip horizontal");
			//startActivity(new Intent("android.intent.action.TESTIMAGE"));
			//dst = new Mat(matCurrent.size(), matCurrent.type());
			Core.flip(matCurrent, matCurrent, 1);
			break;
		case R.id.itemFlipVertical:
			Log.d(TAG, "Flip vertical");
			//dst = new Mat(matCurrent.size(), matCurrent.type());
			Core.flip(matCurrent, matCurrent, 0);
			break;
		case R.id.itemRotate90:
			Log.d(TAG, "Rotate 90° clockwise");
			//dst = new Mat(matCurrent.size(), matCurrent.type());
			Core.flip(matCurrent.t(), matCurrent, 0);
		
			break;
		case R.id.itemRotate180:
			Log.d(TAG, "Rotate 180° clockwise");
			//dst = new Mat(matCurrent.size(), matCurrent.type());
			Core.flip(matCurrent, matCurrent, 0);
			Core.flip(matCurrent, matCurrent, 1);
			break;
		case R.id.itemRotate270:
			Log.d(TAG, "Rotate 270° clockwise");
			//dst = new Mat(matCurrent.size(), matCurrent.type());
			Core.flip(matCurrent.t(), matCurrent, 1);
			break;
			
		case R.id.itemInpaint:
			Log.d(TAG, "Inpaint");
			Mat mask = Mat.zeros(matCurrent.size(), CvType.CV_8U);
			Photo.inpaint(matCurrent, mask, matCurrent, 1, Photo.INPAINT_TELEA);
			break;
		default:
			return false;
		}
		
		bmp = Bitmap.createBitmap(matCurrent.cols(), matCurrent.rows(), Config.ARGB_8888);
		Utils.matToBitmap(matCurrent, bmp);
		
		iv = (ImageView) findViewById(R.id.ivDisplay);
		iv.setImageBitmap(bmp);
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		ImageView iv = (ImageView) v;
		ImageView ivDisplay = (ImageView) findViewById(R.id.ivDisplay);
		iSelImg = (Integer) iv.getTag();
		Log.d(TAG, String.valueOf(iSelImg));
		Bitmap bmp;
		bmp = BitmapFactory.decodeFile(listFileName[iSelImg]);

		if (matCurrent == null)
			matCurrent = new Mat();

		Log.d(TAG, "Bitmap to Mat");
		Utils.bitmapToMat(bmp, matCurrent);
		//matCurrent = Highgui.imread(listFileName[iSelImg]);
		Log.d(TAG, "Mat to Bitmap");
		//Utils.matToBitmap(matCurrent, bmp, true);
		ivDisplay.setImageBitmap(bmp);
	}
	
	void FaceDetect(Mat img, MatOfRect face, String facexml, Boolean blDrawFace)
	{
		Mat gray, smallImg;
		gray = new Mat(img.rows(), img.cols(), CvType.CV_8UC1);
		smallImg = new Mat(img.rows(), img.cols(), CvType.CV_8UC1);
		CascadeClassifier cascade = new CascadeClassifier();//, nestedCascade;
		cascade.load(facexml);
	    Imgproc.cvtColor(img, gray, Imgproc.COLOR_BGR2GRAY);
	    Imgproc.resize( gray, smallImg, smallImg.size(), 0, 0, Imgproc.INTER_LINEAR);
	    Imgproc.equalizeHist(smallImg, smallImg);
		cascade.detectMultiScale(smallImg, face, 1.1, 3,
				Objdetect.CASCADE_SCALE_IMAGE,	// detect multi scale
				new Size(30, 30),	//	min face size
				new Size(600, 600)	//	max face size
		);
		
		if (blDrawFace)
			for (Rect r : face.toArray())
				Core.rectangle(img,	r.tl(), r.br(), new Scalar(0, 255, 0, 255), 2);
	}
}
