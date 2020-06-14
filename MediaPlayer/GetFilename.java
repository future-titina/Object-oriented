package javafx;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
 
public class GetFilename {
 
    public static void main(String[] arges) {
    	String path = "http://39.106.192.149/musicplayer/names.txt"; // �������ļ�·��
 
    	List<String> list = GetNames(path);
    	for(String string : list)
    		System.out.println(string);
    }
    
    public static List<String> GetNames(String path) {
        // �洢���и����Ϣ������ 	
        List<String> list = new ArrayList<String>();
        try {
            //String encoding = "utf-8"; // �ַ����룬�������ļ����벻�������������
            String encoding = "GBK";
            
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStreamReader read = new InputStreamReader(connection.getInputStream(), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            
            String lineStr = null; // ÿ�ζ�ȡһ���ַ���
            while ((lineStr = bufferedReader.readLine()) != null) {
               list.add(lineStr);
            }
            
            read.close();
            
            return list;
        } catch (Exception e) {
            System.out.println("��ȡ�ļ�����!");
            e.printStackTrace();
        }
        return null;
    }
}