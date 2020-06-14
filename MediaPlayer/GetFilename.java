package javafx;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
 
public class GetFilename {
 
    public static void main(String[] arges) {
    	String path = "http://39.106.192.149/musicplayer/names.txt"; // 歌曲名文件路径
 
    	List<String> list = GetNames(path);
    	for(String string : list)
    		System.out.println(string);
    }
    
    public static List<String> GetNames(String path) {
        // 存储所有歌词信息的容器 	
        List<String> list = new ArrayList<String>();
        try {
            //String encoding = "utf-8"; // 字符编码，若与歌词文件编码不符将会出现乱码
            String encoding = "GBK";
            
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStreamReader read = new InputStreamReader(connection.getInputStream(), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            
            String lineStr = null; // 每次读取一行字符串
            while ((lineStr = bufferedReader.readLine()) != null) {
               list.add(lineStr);
            }
            
            read.close();
            
            return list;
        } catch (Exception e) {
            System.out.println("读取文件出错!");
            e.printStackTrace();
        }
        return null;
    }
}