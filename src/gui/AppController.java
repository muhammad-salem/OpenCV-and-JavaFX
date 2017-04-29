package gui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import opencv.CascadeLoder;
import opencv.CascadeLoder.HaarCascede;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.highgui.Highgui;
import org.opencv.highgui.VideoCapture;

public class AppController implements Initializable {
	
//****************************************************************//	
	@FXML private ImageView imageView;
	@FXML private ImageView imgCam;
    @FXML private ImageView imgVideo;
    @FXML private ImageView imgPicture;
    @FXML private ImageView imgSave;
//****************************************************************//    
    @FXML private CheckBox faceBox;
    @FXML private CheckBox eyeBox;
    @FXML private CheckBox mouthBox;
    @FXML private CheckBox noseBox;
    
    @FXML private CheckBox leftEarBox;
    @FXML private CheckBox rightEarBox;
    
    @FXML private CheckBox upperBodyBox;
    
//****************************************************************//	
	VideoCapture capture ;
	VideoCapture video;
	Mat image;
	Mat frame;
	MatOfByte matOfByte;
	ByteArrayInputStream stream ;
//****************************************************************//
	CascadeLoder DRAW;
	CascadeLoder face;
	CascadeLoder eye;
	CascadeLoder mouth;
	CascadeLoder nose;
	CascadeLoder leftEar;
	CascadeLoder rightEar;
	CascadeLoder upperBody;
//****************************************************************//
	
	@FXML private void relesae(){
		if(capture.isOpened()){
			capture.release();
		}
		if(video.isOpened()){
			video.release();
		}
	}
	
//	Mat pmg = new Mat();
	List<File> list ;
	String path = System.getProperty("user.home") + "/Pictures/";
	@FXML private void fromPictures(){
		relesae();
		fileChooser = new FileChooser();
		if(file != null){
			fileChooser.setInitialDirectory(file.getParentFile());
		}
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image file", "*.jpg", "*.png", "*.gif"));
		list = fileChooser.showOpenMultipleDialog(null);
		if(list == null) return;
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if(list.size() > 0){
					num = 0;
					getDectPicts();
				}
			}
		});
		
	}
	
	
	private void getDectPicts() {
		if ( num >=  list.size() ) {
			setImageView(image);
			return;
		}
		file = list.get(num++);
		image = Highgui.imread(file.getAbsolutePath());
		detectMultiScale(image);
		Highgui.imwrite(System.getProperty("user.home") 
				+"/Pictures/opencv/"+ file.getName() +".jpg", image);
		getDectPicts();
	}
	
	File file;
	FileChooser fileChooser ;
	@FXML private void readMedia(){
		relesae();
		fileChooser = new FileChooser();
		if(file != null){
			fileChooser.setInitialDirectory(file.getParentFile());
		}
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Video file", "*.avi", "*.mp4", "*.flv", "*.mkv", "*.3gp"));
		file = fileChooser.showOpenDialog(new Stage());
		
		if(file == null) return;
		
		video = new VideoCapture(file.getAbsolutePath());
		System.out.println("Start Streaming video file.");
		video.read(frame);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if(video.isOpened()){
					num = 0;
					getVideoFrame();
				}
			}
		});
	}
	
	int num = 0;
	private void getVideoFrame() {
		video.read(frame);
//		if(num == 1) 
			detectMultiScale(frame);
//		if( ++num == 3) num = 0;
		setImageView(frame);
		System.out.println("found frame");
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if(video.isOpened()){
					getVideoFrame();
				}
			}
		});
	}
	
	@FXML private void click(){
		relesae();
		System.out.println("click");
		capture = new VideoCapture(0);
//		capture.set(Highgui.CV_CAP_PROP_FRAME_WIDTH, 320);
//		capture.set(Highgui.CV_CAP_PROP_FRAME_HEIGHT, 240);
		System.out.println("Start Capture webCam.");
		capture.read(image);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (capture.isOpened()) {
					read();
				}
			}
		});
	}
	
	private void setImageView( Mat picture) {
		matOfByte = new MatOfByte();
	    Highgui.imencode(".jpg", picture, matOfByte);
	    stream = new ByteArrayInputStream(matOfByte.toArray());
	    imageView.setImage(new Image((InputStream)stream));
	}
	
	
	private void read() {
		capture.read(image);
//		if(num == 1) 
			detectMultiScale(image);
//		if( ++num == 3) num = 0;
	    setImageView(image);
	    Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (capture.isOpened()) {
					read();
				}
			}
		});
	}
	

	private void detectMultiScale(Mat picture) {
		
		if(faceBox.isSelected())		DRAW.drawRect	(picture, face	.detectMultiScale(picture));
		if(eyeBox.isSelected())			DRAW.drawEllipse(picture, eye	.detectMultiScale(picture));
		if(mouthBox.isSelected())		DRAW.drawEllipse(picture, mouth	.detectMultiScale(picture));
		if(noseBox.isSelected())		DRAW.drawEllipse(picture, nose	.detectMultiScale(picture));
		if(leftEarBox.isSelected())		DRAW.drawRect	(picture, leftEar.detectMultiScale(picture));
		if(rightEarBox.isSelected())	DRAW.drawRect	(picture, rightEar.detectMultiScale(picture));
		if(upperBodyBox.isSelected())	DRAW.drawRect	(picture, upperBody.detectMultiScale(picture));
		
	}
	

	
	private void loadImage() {
//		*** initialize image view coolection ***	
//		imageView.setImage(new Image("img/Utility.png"));
		imgCam.setImage(new Image("img/photos.png"));
		imgVideo.setImage(new Image("img/movie.png"));
		imgPicture.setImage(new Image("img/photofilters.png"));
		imgSave.setImage(new Image("img/human-folder-downloads.png"));
	}
	
	private void initCkeckBox() {
		faceBox		.setText(HaarCascede.frontalface_alt_tree.toString());
		eyeBox		.setText(HaarCascede.eye_tree_eyeglasses.toString());
		mouthBox	.setText(HaarCascede.mcs_mouth.toString());
		noseBox		.setText(HaarCascede.mcs_nose.toString());
		leftEarBox	.setText(HaarCascede.mcs_leftear.toString());
		rightEarBox	.setText(HaarCascede.mcs_rightear.toString());
		upperBodyBox.setText(HaarCascede.mcs_upperbody.toString());
		
	}
	
	private void initCascade() {
		DRAW		= new CascadeLoder();
		face		= new CascadeLoder(HaarCascede.frontalface_default);
		eye			= new CascadeLoder(HaarCascede.eye_tree_eyeglasses);
		mouth		= new CascadeLoder(HaarCascede.mcs_mouth);
		nose		= new CascadeLoder(HaarCascede.mcs_nose);
		leftEar		= new CascadeLoder(HaarCascede.mcs_leftear);
		rightEar	= new CascadeLoder(HaarCascede.mcs_rightear);
		upperBody	= new CascadeLoder(HaarCascede.mcs_upperbody);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(">> Start");
		
		
		loadImage();
		initCkeckBox();
		initCascade();
		
        capture = video =  new VideoCapture();
		image = frame = new Mat();

		App.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				relesae();
			}
		});
		
//		System.out.println(System.getProperties().toString());
		
	}

}
