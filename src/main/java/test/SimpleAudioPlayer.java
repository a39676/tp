// Java program to play an Audio
// file using Clip Object
package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SimpleAudioPlayer {

	public static void main(String[] args) throws FileNotFoundException, JavaLayerException {

		FileInputStream stream = new FileInputStream(
				new File("C:\\Users\\daven\\mu/Titanic_MyHeartWillGoOn_CelineDion.mp3").getPath());
		Header header = new Bitstream(stream).readFrame();
		Player player = null;
		Scanner sc = new Scanner(System.in);
		if (header.toString().contains("Layer III")) {
			player = new Player(stream);
			// play mp3
		} else {
			// play wav file
		}

		while (true) {
//			System.out.println("1. pause");
			System.out.println("2. resume");
//			System.out.println("3. restart");
			System.out.println("4. stop");
			int c = sc.nextInt();
			System.out.println("Input: " + c);
			if (c == 2) {
				System.out.println("Play");
				player.play();
				System.out.println(";");
			} else if (c == 4) {
				System.out.println("Stop");
				player.close();
				System.out.println(";");
				break;
			}
		}
		sc.close();
	}

}