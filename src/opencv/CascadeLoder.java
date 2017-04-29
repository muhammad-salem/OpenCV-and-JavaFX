package opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;
import org.opencv.objdetect.CascadeClassifier;

public class CascadeLoder {

    public CascadeClassifier defalut;

    /**
     *
     */
    public enum Platform {

        linux,
        windows;

        @Override
        public String toString() {
            if (this == linux) {
                return "linux";
            }
            if (this == windows) {
                return "Windows 8.1";
            }
            return System.getProperty("os.name");
        }

    }

    public enum BinLib {

        linux,
        window;

        @Override
        public String toString() {
            String pathWindows = "C:/opencv/build/java/x64/libopencv_java247.dll";
            String pathLinux = "/usr/local/share/OpenCV/java/libopencv_java247.so";
            switch (this) {
                case linux:
                	return pathLinux;

                default:
                	return pathWindows;
            }
        }

        public static String platform() {

            String pathWindows = "C:/opencv/build/java/x64/opencv_java247.dll";
            String pathLinux = "/usr/local/share/OpenCV/java/libopencv_java247.so";
            if (System.getProperty("os.name").equals( "linux")) {
            	return pathLinux;
            } else {
            	return pathWindows;
            }
            
        }

        public static void loadLibrary() {
            try {
                System.load(platform());
            } catch (Exception e) {
                System.err.println("error loading Native Library. \n trying defalut lib.");
            }
//            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        }

    }

    public enum HaarCascede {

        eye,
        eye_tree_eyeglasses,
        lefteye_2splits,
        righteye_2splits,
        smile,
        profileface,
        frontalface_alt,
        frontalface_alt2,
        frontalface_default,
        frontalface_alt_tree,
        fullbody,
        lowerbody,
        upperbody,
        mcs_upperbody,
        mcs_eyepair_big,
        mcs_eyepair_small,
        mcs_leftear,
        mcs_rightear,
        mcs_lefteye,
        mcs_righteye,
        mcs_nose,
        mcs_mouth;

        
        public String getPath() {

            String pathWindows = "C:/opencv/sources/data/haarcascades/haarcascade_";
//            String pathWindows = "C:\\opencv\\sources\\data\\haarcascades\\haarcascade_";
            String pathLinux = "/usr/local/share/OpenCV/haarcascades/haarcascade_";

            String path ;

            if (System.getProperty("os.name").equals( Platform.windows.toString())) {
                path = pathWindows;
            } else {
                path = pathLinux;
            }

            switch (this) {
                case eye:
                    path += "eye";
                    break;
                case eye_tree_eyeglasses:
                    path += "eye_tree_eyeglasses";
                    break;
                case lefteye_2splits:
                    path += "lefteye_2splits";
                    break;
                case righteye_2splits:
                    path += "righteye_2splits";
                    break;
                case smile:
                    path += "smile";
                    break;
                case profileface:
                    path += "profileface";
                    break;
                case frontalface_alt:
                    path += "frontalface_alt";
                    break;
                case frontalface_alt2:
                    path += "frontalface_alt2";
                    break;
                case frontalface_default:
                    path += "frontalface_default";
                    break;
                case frontalface_alt_tree:
                    path += "frontalface_alt_tree";
                    break;
                case fullbody:
                    path += "fullbody";
                    break;
                case lowerbody:
                    path += "lowerbody";
                    break;
                case upperbody:
                    path += "upperbody";
                    break;
                case mcs_upperbody:
                    path += "mcs_upperbody";
                    break;
                case mcs_eyepair_big:
                    path += "mcs_eyepair_big";
                    break;
                case mcs_eyepair_small:
                    path += "mcs_eyepair_small";
                    break;
                case mcs_leftear:
                    path += "mcs_leftear";
                    break;
                case mcs_rightear:
                    path += "mcs_rightear";
                    break;
                case mcs_lefteye:
                    path += "mcs_lefteye";
                    break;
                case mcs_righteye:
                    path += "mcs_righteye";
                    break;
                case mcs_nose:
                    path += "mcs_nose";
                    break;
                case mcs_mouth:
                    path += "mcs_mouth";
                    break;

            }

            path += ".xml";
            return path;
        }
        
        @Override
        public String toString() {
            String name ="";

            switch (this) {
                case eye:
                    name += "Eye";
                    break;
                case eye_tree_eyeglasses:
                    name += "Eye Glasses Tree";
                    break;
                case lefteye_2splits:
                    name += "Left Eye 2 Splits";
                    break;
                case righteye_2splits:
                    name += "Right Eye 2 Splits";
                    break;
                case smile:
                    name += "Smile";
                    break;
                case profileface:
                    name += "Profile Face";
                    break;
                case frontalface_alt:
                    name += "Frontal Face Alt";
                    break;
                case frontalface_alt2:
                    name += "Frontal Face Alt 2";
                    break;
                case frontalface_default:
                    name += "Frontal Face Default";
                    break;
                case frontalface_alt_tree:
                    name += "Frontal Face Alt Tree";
                    break;
                case fullbody:
                    name += "Full Body";
                    break;
                case lowerbody:
                    name += "Lower Body";
                    break;
                case upperbody:
                    name += "Upper Body";
                    break;
                case mcs_upperbody:
                    name += "Upper Body";
                    break;
                case mcs_eyepair_big:
                    name += "Eye Pair Big";
                    break;
                case mcs_eyepair_small:
                    name += "Eye Pair Small";
                    break;
                case mcs_leftear:
                    name += "Left Ear";
                    break;
                case mcs_rightear:
                    name += "Right Ear";
                    break;
                case mcs_lefteye:
                    name += "Left Eye";
                    break;
                case mcs_righteye:
                    name += "Right Eye";
                    break;
                case mcs_nose:
                    name += "Nose";
                    break;
                case mcs_mouth:
                    name += "Mouth";
                    break;

            }
            return name;
        }

    }

    
    public CascadeLoder( ) {
    	
    }
    public CascadeLoder(HaarCascede cascade) {
//        loadNATIVE_LIBRARY();
        loadClassifier(cascade);
    }
    
    

    public  void loadNATIVE_LIBRARY() {
        BinLib.loadLibrary();
    }

    void loadClassifier(HaarCascede cascade) {
        defalut = new CascadeClassifier(cascade.getPath());
    }
    
    public MatOfRect detectMultiScale(Mat image) {
        MatOfRect rects = new MatOfRect();
        defalut.detectMultiScale(image, rects);
//        System.out.println(String.format("\nDetected %s ", rects.toArray().length));
        return rects;

    }
    
    public Mat drawRect(Mat image, MatOfRect matOfRect) {

        for (Rect rect : matOfRect.toArray()) {
            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
        return image;
    }

    public Mat drawEllipse(Mat image, MatOfRect matOfRect) {

        Point center;
        for (Rect rect : matOfRect.toArray()) {
            center = new Point(rect.x + rect.width * .5, rect.y + rect.height * .5);
            Core.ellipse(image, center, new Size(rect.width * .5, rect.height * .5),
                    0, 0, 360, new Scalar(255, 0, 255), 2, 8, 0);
        }
        
        
        return image;
    }

    public static void main(String[] a) {
    	System.out.println(Core.NATIVE_LIBRARY_NAME);
//    	System.out.println(System.getenv().toString());
    	
//    	loadLibrary();
    	
        test();
    }

    public static void test() {
        System.out.println(System.getProperty("os.name"));
        System.out.println(System.getProperty("os.version"));
        System.out.println(System.getProperty("os.arch"));

        System.err.println(HaarCascede.eye_tree_eyeglasses);
        System.err.println(BinLib.window);
        System.err.println(BinLib.platform());
        
        
        System.out.println(System.getProperty("java.library.path"));
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        System.load(BinLib.platform());
        
        VideoCapture cam = new VideoCapture(0);
        Mat mat = new Mat();
        for (int i = 0; i < 30; i++) {
        	cam.read(mat);
        	System.out.println(',');
		}
        
        CascadeLoder loder = new CascadeLoder(HaarCascede.frontalface_alt);
        
        mat = loder.drawRect(mat, loder.detectMultiScale(mat));
        
        System.out.println("Saved in ->> "+System.getProperty("user.home") +"/Pictures/name.jpg");
        Highgui.imwrite(System.getProperty("user.home") +"/Pictures/name.jpg", mat);
        cam.release();
    }
    
    public static void loadLibrary() {
    	String oldPath = System.getProperty("java.library.path");
    	oldPath += ":";
		if(System.getProperty("os.name").equalsIgnoreCase("Linux")){
			oldPath += "/usr/local/share/OpenCV/java:/usr/local/lib";
		}else {
			oldPath += "C:/opencv/build/java/x64/";
		}
		System.setProperty("java.library.path", oldPath);
	}

}




//public static CascadeClassifier eye;
//public static CascadeClassifier eye_tree_eyeglasses;
//public static CascadeClassifier lefteye_2splits;
//public static CascadeClassifier righteye_2splits;
//
//public static CascadeClassifier smile;
//public static CascadeClassifier profileface;
//
//public static CascadeClassifier frontalface_alt;
//public static CascadeClassifier frontalface_alt2;
//public static CascadeClassifier frontalface_default;
//public static CascadeClassifier frontalface_alt_tree;
//
//public static CascadeClassifier fullbody;
//public static CascadeClassifier lowerbody;
//public static CascadeClassifier upperbody;
//public static CascadeClassifier mcs_upperbody;
//
//public static CascadeClassifier mcs_eyepair_big;
//public static CascadeClassifier mcs_eyepair_small;
//
//public static CascadeClassifier mcs_leftear;
//public static CascadeClassifier mcs_rightear;
//
//public static CascadeClassifier mcs_lefteye;
//public static CascadeClassifier mcs_righteye;
//
//public static CascadeClassifier mcs_nose;
//public static CascadeClassifier mcs_mouth;
