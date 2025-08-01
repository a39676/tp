// Java program to play an Audio
// file using Clip Object
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.decoder.JavaLayerException;

public class SimpleAudioPlayer2 {

	public static void main(String[] args) throws FileNotFoundException, JavaLayerException {

		String filePathStr = "C:\\Users\\daven\\mu/Titanic_MyHeartWillGoOn_CelineDion.mp3";
		Scanner sc = new Scanner(System.in);
		
		@SuppressWarnings({ "resource", "unused" })
		FileInputStream is = new FileInputStream(new File(filePathStr).getPath());
		Media hit = new Media(new File(filePathStr).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		

		while (true) {
//			System.out.println("1. pause");
			System.out.println("2. resume");
//			System.out.println("3. restart");
			System.out.println("4. stop");
			int c = sc.nextInt();
			System.out.println("Input: " + c);
			if (c == 2) {
				System.out.println("Play");
				mediaPlayer.play();
				System.out.println(";");
			} else if (c == 4) {
				System.out.println("Stop");
				mediaPlayer.stop();
				System.out.println(";");
				break;
			}
		}
		sc.close();
	}

}