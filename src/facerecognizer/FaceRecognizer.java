package facerecognizer;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

abstract class FaceRecognizer extends Algorithm{

	protected FaceRecognizer(long addr) {
		super(addr);
		// TODO Auto-generated constructor stub
	}
    // Trains a FaceRecognizer.
    abstract void train(Mat src, Mat labels);

    // Gets a prediction from a FaceRecognizer.
    abstract int predict(Mat src);

    // Predicts the label and confidence for a given sample.
    abstract void predict(Mat src, int label, double dist);

    // Serializes this object to a given filename.
    abstract void save(String filename);

    // Deserializes this object from a given filename.
    abstract void load(String filename);

    // Serializes this object to a given cv::FileStorage.
    //abstract void save(FileStorage fs);

    // Deserializes this object from a given cv::FileStorage.
    //abstract void load(FileStorage fs);

}
