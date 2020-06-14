
package javafx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.util.List;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.lang.model.type.NullType;
import javax.xml.stream.events.StartDocument;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.scene.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;


public class Test extends Application{

	static ArrayList<Media> list = new ArrayList<Media>();
	static ArrayList<Rectangle> list_rec;
	static ArrayList<Label> list_rec2= new ArrayList<Label>();
	static ArrayList<Label> list_lrc2= new ArrayList<Label>();
	static ArrayList<lrc_statement> list_ls= new ArrayList<lrc_statement>();
	static Slider s1;
	static Slider s2;
	static Label lb1,lb2,lb3,lb4,lb4_1,lb4_2,lb4_3,lb4_4;
	static int index = 0;
	static double pause_time = 0;
	static MediaPlayer mp,mp2;
	static MediaView mv;
	static String mark = null;
	static boolean mouse = false,mark_mv = false,playing = false;
	static int mark_mp12=1;
	static ImageView iv,iv_bg;
	static AnchorPane root;
	static HBox hBox4;
	static VBox vBox2;
	static boolean IsReady = false,IsVisb = false;
	static Pane lrc_pane = new Pane();
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		String url1 = this.getClass().getClassLoader().getResource("res/Taylor Swift - Wildest Dreams (Radio Edit).mp3").toExternalForm();

		ArrayList<String> list_music_name = (ArrayList<String>) GetFilename.GetNames("*");//"*"网络获取地址
		
		for(String name : list_music_name) {
			String http = "*"+name.replace(" ", "%20")+".mp3";
			
			list.add(new Media(http));
		}
		
		HBox hBox = new HBox(30);
		HBox hBox2 = new HBox(30);
		HBox hBox3 = new HBox(10);
		hBox4 = new HBox(4);
		
		VBox vBox = new VBox(10);
		vBox.setMaxHeight(600);
		vBox.setMaxWidth(400);
		vBox.setMinWidth(400);
		vBox.setStyle("-fx-border-size:1px;-fx-border-color:#000;-fx-background-color:#000");
		vBox.setVisible(true);
		
		vBox2 = new VBox(10);
		vBox.setMaxHeight(40);
		vBox.setMaxWidth(400);
		
		Button bu1 = new Button("",new ImageView("res/播放.png"));
		Button bu2 = new Button("",new ImageView("res/停止.png"));
		Button bu3 = new Button("",new ImageView("res/暂停.png"));
		Button bu4 = new Button("",new ImageView("res/上一首.png"));
		Button bu5 = new Button("",new ImageView("res/下一首.png"));
		Button bu6 = new Button("",new ImageView("res/快退.png"));
		Button bu7 = new Button("",new ImageView("res/快进.png"));
		Button bu8 = new Button("",new ImageView("res/最小化.png"));
		Button bu9 = new Button("",new ImageView("res/最大化.png"));
		Button bu10 = new Button("",new ImageView("res/关  闭.png"));
		Button bu11 = new Button("",new ImageView("res/最小化 (1).png"));
		Button bu12 = new Button("",new ImageView("res/列表.png"));
		Button bu13 = new Button("",new ImageView("res/MV.png"));
		
		bu1.setBackground(null);
		bu2.setBackground(null);
		bu3.setBackground(null);
		bu4.setBackground(null);
		bu5.setBackground(null);
		bu6.setBackground(null);
		bu7.setBackground(null);
		bu8.setBackground(null);
		bu9.setBackground(null);
		bu10.setBackground(null);
		bu11.setBackground(null);
		bu12.setBackground(null);
		bu13.setBackground(null);
		
		s1 = new Slider(0,1,0.2);
		s1.setPrefWidth(100);
		s2 = new Slider(0,1,0);
		s2.setPrefWidth(1200);
		
		lb1 = new Label();
		lb2 = new Label();
		lb3 = new Label();
		lb4 = new Label();
		lb4_1 = new Label();
		lb4_2 = new Label();
		lb4_3 = new Label();
		lb4_4 = new Label();
		
        lb3.setTextFill(Color.web("#0076a3"));
        lb3.setFont(new Font("Arial", 30));
        lb3.setWrapText(true);
        lb3.setMaxWidth(800);
        lb4.setFont(new Font("Cambria", 32));
        lb4.setWrapText(true);
        lb4.setMaxWidth(800);
        lb4.setTextFill(Color.web("#d81e06"));
        lb4_1.setFont(new Font("Cambria", 20));
        lb4_1.setWrapText(true);
        lb4_1.setMaxWidth(800);
        lb4_2.setFont(new Font("Cambria", 20));
        lb4_2.setWrapText(true);
        lb4_2.setMaxWidth(800);
        lb4_3.setFont(new Font("Cambria", 20));
        lb4_3.setWrapText(true);
        lb4_3.setMaxWidth(800);
        lb4_4.setFont(new Font("Cambria", 20));
        lb4_4.setWrapText(true);
        lb4_4.setMaxWidth(800);
        vBox2.getChildren().addAll(lb4_1,lb4,lb4_2,lb4_3,lb4_4);
		
		hBox.getChildren().addAll(bu6,bu4,bu2,bu1,bu5,bu7,bu12,bu13,s1);
		hBox.setAlignment(Pos.CENTER);
		hBox2.getChildren().addAll(lb2,s2,lb1);
		hBox2.setAlignment(Pos.CENTER);
		hBox3.getChildren().addAll(bu11,bu8,bu9,bu10);
		hBox3.setAlignment(Pos.CENTER);
		hBox4.setAlignment(Pos.CENTER);
		hBox4.setScaleZ(1);
		//hBox4.setMaxHeight(500.0);
		
		iv = new ImageView();
		iv_bg = new ImageView("res/bg.jpg");
		
		iv.setClip(new Circle(250,250,250));
		iv_bg.setLayoutX(0);
		iv_bg.setStyle("-fx-opacity:0.3");
		
		//搜索栏
		HBox hBox5 = new HBox();
		TextField tf = new TextField();
		tf.setMaxWidth(500);
		tf.setMinWidth(500);
		tf.setMaxHeight(40);
		tf.setStyle("fx-border-size:1px;-fx-border-color:#d81e06;-fx-background-color:transparent");
		Button bu14 = new Button("",new ImageView("res/搜索.png"));
		bu14.setBackground(null);
		hBox5.getChildren().addAll(tf,bu14);
		
		root = new AnchorPane();
		root.getChildren().addAll(hBox4,iv_bg,hBox,hBox2,hBox3,iv,lb3,vBox,hBox5,vBox2);
		AnchorPane.setLeftAnchor(hBox, 350.0);
		AnchorPane.setTopAnchor(hBox, 850.0);
		AnchorPane.setLeftAnchor(hBox2, 150.0);
		AnchorPane.setTopAnchor(hBox2, 820.0);
		AnchorPane.setLeftAnchor(hBox3, 1300.0);
		AnchorPane.setTopAnchor(hBox3, 10.0);
		AnchorPane.setLeftAnchor(hBox4, 0.0);
		AnchorPane.setTopAnchor(hBox4, 600.0);
		AnchorPane.setLeftAnchor(hBox5, 300.0);
		AnchorPane.setTopAnchor(hBox5, 10.0);
		AnchorPane.setLeftAnchor(vBox, -600.0);
		AnchorPane.setTopAnchor(vBox, 50.0);
		AnchorPane.setLeftAnchor(vBox2, 700.0);
		AnchorPane.setTopAnchor(vBox2, 300.0);
		AnchorPane.setLeftAnchor(iv, 100.0);
		AnchorPane.setTopAnchor(iv, 100.0);
		AnchorPane.setLeftAnchor(lb3, 700.0);
		AnchorPane.setTopAnchor(lb3, 200.0);
		AnchorPane.setLeftAnchor(lrc_pane, 700.0);
		AnchorPane.setTopAnchor(lrc_pane, 200.0);
//		AnchorPane.setLeftAnchor(mv, 0.0);
//		AnchorPane.setTopAnchor(mv, 0.0);
		
		
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		primaryStage.setScene(scene);
		primaryStage.setWidth(1500);
		primaryStage.setHeight(900);
		primaryStage.centerOnScreen();
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setTitle("MediaPlayer");
		primaryStage.show();
		
		s2.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouse = true;
			}
		});
		
		s2.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mp.seek(Duration.seconds(s2.getValue()));
				mouse = false;
			}
		});
		
		//播放
		bu1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(!playing) {
					if(mark_mp12==1){
						mark = "play";
						index_music(list);
						if(list.get(index).getMetadata().get("image") != null)
							iv.setImage((Image)list.get(index).getMetadata().get("image"));
						else
							iv.setImage((Image)list.get(index).getMetadata().get("res/bg.jpg"));
					}else if(mark_mp12==2) {
						mp2.play();
					}
					ImageView imageView = new ImageView(new Image("res/暂停.png"));
					bu1.setGraphic(imageView);
					playing = true;
				}else {
					
					if(mark_mp12==1) {
						mp.pause();
						pause_time = mp.getCurrentTime().toSeconds();
					}
					else if(mark_mp12==2){
						mp2.pause();
						pause_time = mp2.getCurrentTime().toSeconds();
					}
					ImageView imageView = new ImageView(new Image("res/播放.png"));
					bu1.setGraphic(imageView);
					playing = false;
				}
			}
		});
		
		//停止
		bu2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(mark_mp12==1){
					mp.stop();
				}else if(mark_mp12==2) {
					mp2.stop();
				}
			}
		});
		
		//暂停
		bu3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if(mark_mp12==1) {
					mp.pause();
					pause_time = mp.getCurrentTime().toSeconds();
				}
				else if(mark_mp12==2){
					mp2.pause();
					pause_time = mp2.getCurrentTime().toSeconds();
				}
			}
		});
		
		//上一首
		bu4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				mark = "previous";
				index_music(list);
				
				iv.setImage((Image)list.get(index).getMetadata().get("image"));
			}
		});
		
		//下一首
		bu5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				
				mark = "next";
				index_music(list);
				
				iv.setImage((Image)list.get(index).getMetadata().get("image"));
			}
		});
		
		//快退
		bu6.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mp.pause();
				mp.seek(Duration.seconds(mp.getCurrentTime().toSeconds()-5));
				mp.play();
			}
		});
		
		//快进
		bu7.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				mp.pause();
				mp.seek(Duration.seconds(mp.getCurrentTime().toSeconds()+5));
				mp.play();
			}
		});
		
		//原始状态
		bu11.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.setMaximized(false);
				AnchorPane.setLeftAnchor(hBox, 350.0);
				AnchorPane.setTopAnchor(hBox, 850.0);
				AnchorPane.setLeftAnchor(hBox2, 150.0);
				AnchorPane.setTopAnchor(hBox2, 820.0);
				AnchorPane.setLeftAnchor(hBox3, 1300.0);
				AnchorPane.setTopAnchor(hBox3, 10.0);
				AnchorPane.setLeftAnchor(hBox4, 0.0);
				AnchorPane.setTopAnchor(hBox4, 600.0);
				AnchorPane.setLeftAnchor(vBox, -600.0);
				AnchorPane.setTopAnchor(vBox, 50.0);
				AnchorPane.setLeftAnchor(vBox2, 700.0);
				AnchorPane.setTopAnchor(vBox2, 300.0);
				AnchorPane.setLeftAnchor(iv, 100.0);
				AnchorPane.setTopAnchor(iv, 100.0);
				AnchorPane.setLeftAnchor(lb3, 700.0);
				AnchorPane.setTopAnchor(lb3, 200.0);
				AnchorPane.setLeftAnchor(lrc_pane, 700.0);
				AnchorPane.setTopAnchor(lrc_pane, 300.0);
			}
		});
		
		//最小化
		bu8.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.setIconified(true);
			}
		});
		
		//最大化
		bu9.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.setMaximized(true);

				AnchorPane.setLeftAnchor(hBox, 500.0);
				AnchorPane.setTopAnchor(hBox, 1000.0);
				AnchorPane.setLeftAnchor(hBox2, 300.0);
				AnchorPane.setTopAnchor(hBox2, 900.0);
				AnchorPane.setLeftAnchor(hBox3, 1700.0);
				AnchorPane.setTopAnchor(hBox3, 10.0);
				AnchorPane.setLeftAnchor(iv, 200.0);
				AnchorPane.setTopAnchor(iv, 100.0);
				AnchorPane.setLeftAnchor(lb3, 800.0);
				AnchorPane.setTopAnchor(lb3, 150.0);
				AnchorPane.setLeftAnchor(lrc_pane, 800.0);
				AnchorPane.setTopAnchor(lrc_pane, 250.0);
				AnchorPane.setLeftAnchor(vBox2, 800.0);
				AnchorPane.setTopAnchor(vBox2, 250.0);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				iv_bg.setFitWidth(screenSize.getWidth());
				iv_bg.setFitHeight(screenSize.getHeight());
			}
		});
		
		//关闭
		bu10.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				primaryStage.close();
			}
		});
		
		//列表
		bu12.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				vBox.getChildren().clear();
				list_rec2.clear();
				for(int i=0; i<list.size(); i++) {
					Label lb;
					String text = list.get(i).getMetadata().get("title")+"--"+list.get(i).getMetadata().get("artist");
					if(index == i) {
						lb = new Label("正在播放："+text);
						lb.setTextFill(Color.web("#ff7675"));
						lb.setFont(new Font("Cambria", 20));
						lb.setMaxWidth(400);
						lb.setWrapText(true);
						vBox.getChildren().addAll(lb);
					}
					else {
						lb = new Label(text);
						lb.setTextFill(Color.WHITE);
						lb.setFont(new Font("Cambria", 20));
						lb.setMaxWidth(400);
						lb.setWrapText(true);
						vBox.getChildren().addAll(lb);
					}
//					list_rec2.add(lb);
					
				}
				vBox.setOpacity(0.7);
				TranslateTransition tt = new TranslateTransition();
				tt.setDuration(Duration.seconds(1));
				tt.setNode(vBox);
				if(!IsVisb) {
//					for(Label r : list_rec2) {
						tt.setFromX(0);
						tt.setToX(600);
						tt.play();
//					}
					IsVisb = true;
				}else {
//					for(Label r : list_rec2) {
						tt.setFromX(600);
						tt.setToX(-600);
						tt.play();
//					}
					IsVisb = false;
				}
			}
		});
		
		//MV
		bu13.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
//				if(mp.isAutoPlay())
//					mp.stop();
//				mv.setTranslateZ(-999);
				if(!mark_mv) {
					mark_mp12=2;
					index_music_mv();
					mark_mv = true;
				}else {
					mp2.stop();
					mv.setVisible(false);
					mark_mv = false;
					mark_mp12=1;
				}
			}
		});
		
		//搜索
		bu14.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					vBox.getChildren().clear();
					list_rec2.clear();
					
				    String[] args = new String[] { 
				    		"D:\\PyCharm\\python_workspace\\Mark\\venv\\Scripts\\python.exe",
				    		"D:/PyCharm/python_workspace/Mark/音乐播放器/QQ音乐.py", tf.getText().toString()};
				    Process proc = Runtime.getRuntime().exec(args);// 执行py文件
				 
				    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				    String line = null;
				    while ((line = in.readLine()) != null) {
				    	Label lb;
				    	lb = new Label(line);
						lb.setTextFill(Color.WHITE);
				        //System.out.println(line);
				        lb.setFont(new Font("Cambria", 20));
						lb.setMaxWidth(400);
						lb.setWrapText(true);
						list_rec2.add(lb);
						vBox.getChildren().addAll(lb);
				    }
				    in.close();
				    proc.waitFor();
				    
					vBox.setOpacity(0.7);
					TranslateTransition tt = new TranslateTransition();
					tt.setDuration(Duration.seconds(1));
					tt.setNode(vBox);
					if(!IsVisb) {
							tt.setFromX(0);
							tt.setToX(900);
							tt.play();
						IsVisb = true;
					}else {
							tt.setFromX(900);
							tt.setToX(-600);
							tt.play();
						IsVisb = false;
					}
				    
				} catch (IOException e) {
				    e.printStackTrace();
				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
			}
		});
		
		
	}
	
	public static void index_music_mv() {
		String str;
		try {
			String str_name = URLEncoder.encode(list.get(index).getMetadata().get("artist").toString(),"utf-8");
			String str_title = URLEncoder.encode(list.get(index).getMetadata().get("title").toString(),"utf-8");
			str_name = str_name.replace("+", "%20");
			str_title = str_title.replace("+", "%20");
			
			mp2 = new MediaPlayer(new Media("*"+str_name+"%20-%20"+str_title+".mp4"));
			mv = new MediaView(mp2);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mv.setVisible(true);
		mv.setX(200);
		mv.setY(100);
		mv.setFitHeight(650);
		root.getChildren().add(mv);
		
		mp2.setOnReady(new Runnable() {
			public void run() {
				if(mark_mp12==2)
					mp2.play();
			}
		});
	}
	
	
	public static void index_music(ArrayList<Media> list) {
		
		if(mp != null) {
			if(mark.equals("next"))
				index = (list.indexOf(mp.getMedia())+1)%list.size();
			else if(mark.equals("previous"))
				index = (list.indexOf(mp.getMedia())-1+list.size())%list.size();
			mp.dispose();
		}
		
		mp = new MediaPlayer(list.get(index));
		
		//监听
		//歌词提示点
		mp.setOnMarker(new EventHandler<MediaMarkerEvent>() {
			public void handle(MediaMarkerEvent event) {
				//System.out.println(event.getMarker().getValue()+event.getMarker().getKey());
				for(int t=0;t<list_ls.size();t++) {
					if(list_ls.get(t).getTime()==event.getMarker().getValue().toMillis()) {			 
//						System.out.println(list_ls.get(t).getTime()+"---"+(int)event.getMarker().getValue().toMillis());				
						if(t!=0)
							lb4_1.setText(list_ls.get(t-1).getLyric());
						
						lb4.setText(list_ls.get(t).getLyric());
						
						if(t<list_ls.size()-3) {
							lb4_2.setText(list_ls.get(t+1).getLyric());
							lb4_3.setText(list_ls.get(t+2).getLyric());
							lb4_4.setText(list_ls.get(t+3).getLyric());
						}else {
							lb4_2.setText("");
							lb4_3.setText("");
							lb4_4.setText("");
						}
					}
				}
			}
		});
		
		//音频频谱
		mp.setAudioSpectrumInterval(0.1);
		mp.setAudioSpectrumListener(new AudioSpectrumListener() {
			public void spectrumDataUpdate(double timestamp, double duration, float[] magnitudes, float[] phases) {
				float t= 0;
				list_rec = new ArrayList<Rectangle>();
				list_rec.clear();
				hBox4.getChildren().clear();
				Rectangle rectangle = null;
				for(int i=0; i<128; i++) {
					t = Math.abs(magnitudes[i]);
					float t2 = 100 - (100/(60/t));
					rectangle = new Rectangle(0,0,15,t2*5);
			        rectangle.setFill(Color.PINK);
			        rectangle.setStroke(Color.PINK);
			        list_rec.add(rectangle);
				}
		        hBox4.getChildren().addAll(list_rec);
			}
			
		});
		
		mp.setOnStalled(new Runnable() {
			public void run() {
				System.out.println("缓冲中");
			}
		});
		
		mp.setOnRepeat(new Runnable() {
			public void run() {
				System.out.println("重复播放中");
			}
		});
		
		mp.setOnPaused(new Runnable() {
			public void run() {
				System.out.println("暂停");
			}
		});
		
		mp.setOnStopped(new Runnable() {
			public void run() {
				System.out.println("停止重置");
			}
		});
		
		mp.setOnPlaying(new Runnable() {
			public void run() {
				System.out.println("正在播放"+mp.getCurrentTime().toSeconds());
			}
		});
		
		mp.setOnReady(new Runnable() {
			public void run() {
				
				mp.volumeProperty().bind(s1.valueProperty());
				if(pause_time != 0)
					mp.seek(Duration.seconds(pause_time));
				pause_time = 0;
				
				s2.setValue(0);
				s2.setMin(0);
				s2.setMax(mp.getTotalDuration().toSeconds());
				mp.currentTimeProperty().addListener(new ChangeListener<Duration>() {

					@Override
					public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,Duration newValue) {
						if(mouse == false)
							s2.setValue(newValue.toSeconds());
						
							int m = (int)newValue.toSeconds();
							lb2.setText(""+m/60+":"+m%60);
							
							//lrc_lunbo.tt.playFrom(newValue);
							
					}
				});
				
				//获取歌曲信息
				ObservableMap<String, Object> map = list.get(index).getMetadata();
				for(String key : map.keySet()) {
					System.out.println(key + "--" + map.get(key));
				}
				
				//显示总时间
				int t = (int)mp.getTotalDuration().toSeconds();
				lb1.setText(""+t/60+":"+t%60);
				lb3.setText(list.get(index).getMetadata().get("title")+"--"+list.get(index).getMetadata().get("artist"));
				
				//歌词
				List<Map<Long, String>> list_lrc;
				try {
					
					String str_name = URLEncoder.encode(list.get(index).getMetadata().get("artist").toString(),"utf-8");
					String str_title = URLEncoder.encode(list.get(index).getMetadata().get("title").toString(),"utf-8");
					str_name = str_name.replace("+", "%20");
					str_title = str_title.replace("+", "%20");
					
					list_lrc = lrc_music.parse(("*"+str_name+"%20-%20"+str_title+".lrc"));
					//vBox2.getChildren().clear();
					list_ls.clear();
					for (Map<Long, String> map2 : list_lrc) {
				           for (Entry<Long, String> entry : map2.entrySet()) {
				        	   if(entry.getKey() != 0 && !entry.getValue().equals("")) {
					               //list.get(index).getMarkers().put(entry.getValue(),Duration.millis(entry.getKey()));
					               //list_lrc2.add(new Label(entry.getValue()));
					               //Label lb_lrc = new Label(entry.getValue());
					               //lb_lrc.setTextFill(Color.web("#0076a3"));
//					               lb_lrc.setFont(new Font("Cambria", 25));
//					               lb_lrc.setMaxWidth(400);
//					               lb_lrc.setWrapText(true);
					               
					               lrc_statement ls = new lrc_statement();
					               ls.setLyric(entry.getValue());
					               ls.setTime((long)entry.getKey()+500);
					               list_ls.add(ls);
					               
				        		   //vBox2.getChildren().addAll(lb_lrc);
				        	   }
				            }
				     }
				}catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				lb4.setText("");
				lb4_1.setText("");
				lb4_2.setText("");
				lb4_3.setText("");
				lb4_4.setText("");
				
				for(int t1=0;t1<list_ls.size();t1++) {
					list.get(index).getMarkers().put(list_ls.get(t1).getLyric()+t1,Duration.millis(list_ls.get(t1).getTime()));
				}
					
				
//				lrc_pane.getChildren().clear();
//				lrc_pane = lrc_lunbo.getView(400, 300, mp.getTotalDuration().toSeconds(),vBox2);
//				root.getChildren().add(lrc_pane);
				
				//System.out.println("vBox2"+vBox2.getChildren());
				
				//专辑图片动画效果
				double play_time=mp.getTotalDuration().toSeconds();//播放持续时间
		        double fromAngle=0.0;//开始角度
		        double toAngle=360.0;//结束角度
		        RotateTransition rotateTransition = new  RotateTransition(Duration.seconds(play_time), iv);
		        rotateTransition.setFromAngle(fromAngle);//设置旋转角度
		        rotateTransition.setToAngle(toAngle);
		        rotateTransition.setRate(10.0);
		        rotateTransition.setCycleCount(Animation.INDEFINITE);//播放n次
		        rotateTransition.setAutoReverse(false); // 每次旋转后是否改变旋转方向
		        rotateTransition.play();
		        iv_bg.setImage((Image)list.get(index).getMetadata().get("image"));
		        iv_bg.setFitHeight(900);
		        iv_bg.setFitWidth(1500);
		        
		        IsReady = true;
		        if(IsReady&&mark_mp12==1)
		        	mp.play();
		        IsReady = false;
			}
		});
	}
}
