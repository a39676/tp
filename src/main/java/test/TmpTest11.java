package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class TmpTest11 {

	public static void main(String[] args) {
		String urlString = "https://api.telegram.org/bot1392767975:AAHNC-3e9nJV2zkBNbUlTi2mAqHK4u4Al58/sendMessage?chat_id=605288296&text=something"; 
		try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());
          
          ///////////////////////////////////////////////////////////////////////////////
          ///////////////////////////////////////////////////////////////////////////////
          //Additoinal Code is HERE
          ///////////////////////////////////////////////////////////////////////////////
            //getting text, we can set it to any TextView
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String inputLine = "";
                StringBuilder sb = new StringBuilder();
                while ((inputLine = br.readLine()) != null) {
                    sb.append(inputLine);
                }
                //You can set this String to any TextView
                String response = sb.toString();
                
                System.out.println(response);
          ///////////////////////////////////////////////////////////////////////////////
          ///////////////////////////////////////////////////////////////////////////////
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
}