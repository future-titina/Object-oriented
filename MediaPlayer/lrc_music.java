package javafx;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lrc_music {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String path1 = "http://39.106.192.149/musicplayer/�ܱʳ� - ˭�����ҵ�����.lrc"; // ����ļ�·��
//        int index = path1.indexOf("musicplayer/")+12;
//        String result = path1.substring(0,index);
//        String temp = path1.substring(index);
//        String path =  result + URLEncoder.encode(temp, "utf-8").replace("+", "%20");
//        System.out.println(path1.charAt(index));
        lrc_music lrc = new lrc_music();
        List<Map<Long, String>> list = lrc.parse(path1);
        lrc.printLrc(list);
    }
    
    public static List<Map<Long, String>> parse(String path) {
        // �洢���и����Ϣ������ 	
        List<Map<Long, String>> list = new ArrayList<Map<Long, String>>();
        try {
            //String encoding = "utf-8"; // �ַ����룬�������ļ����벻�������������
            String encoding = "GBK";
//            File file = new File(path);
            
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
 //           BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
            InputStreamReader read = new InputStreamReader(connection.getInputStream(), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            
//            if (file.isFile() && file.exists()) { // �ж��ļ��Ƿ����
            if (true) {
//                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
//                BufferedReader bufferedReader = new BufferedReader(read);
                String regex = "\\[(\\d{1,2}):(\\d{1,2}).(\\d{1,2})\\]"; // ������ʽ
                Pattern pattern = Pattern.compile(regex); // ���� Pattern ����
                String lineStr = null; // ÿ�ζ�ȡһ���ַ���
                while ((lineStr = bufferedReader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(lineStr);
                    while (matcher.find()) {
                        // ���ڴ洢��ǰʱ���������Ϣ������
                        Map<Long, String> map = new HashMap<Long, String>();
                        // System.out.println(m.group(0)); // ����[02:34.94]
                        // [02:34.94] ----��Ӧ---> [����:��.����]
                        String min = matcher.group(1); // ����
                        String sec = matcher.group(2); // ��
                        String mill = matcher.group(3); // ���룬ע��������ʵ��Ҫ����10
                        long time = getLongTime(min, sec, mill + "0");
                        // ��ȡ��ǰʱ��ĸ����Ϣ
                        String text = lineStr.substring(matcher.end());
                        map.put(time, text); // ��ӵ�������
                        list.add(map);
                    }
                }
                read.close();
 //               in.close();
                return list;
            } 
//            else {
//                System.out.println("�Ҳ���ָ�����ļ�:" + path);
//            }
        } catch (Exception e) {
            System.out.println("��ȡ�ļ�����!");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �����ַ�����ʽ�����ķ��ӡ����ӡ�����ת����һ���Ժ���Ϊ��λ��long����
     * 
     * @param min
     *            ����
     * @param sec
     *            ����
     * @param mill
     *            ����
     * @return
     */
    private static long getLongTime(String min, String sec, String mill) {
        // ת������
        int m = Integer.parseInt(min);
        int s = Integer.parseInt(sec);
        int ms = Integer.parseInt(mill);

        if (s >= 60) {
            System.out.println("����: ������һ��ʱ�䲻��ȷ���� --> [" + min + ":" + sec + "."
                    + mill.substring(0, 2) + "]");
        }
        // ��ϳ�һ�������ͱ�ʾ���Ժ���Ϊ��λ��ʱ��
        long time = m * 60 * 1000 + s * 1000 + ms;
        return time;
    }

    /**
     * ��ӡ�����Ϣ
     */
    private void printLrc(List<Map<Long, String>> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("û���κθ����Ϣ��");
        } else {
            for (Map<Long, String> map : list) {
                for (Entry<Long, String> entry : map.entrySet()) {
                    System.out.println("ʱ��:" + entry.getKey() + "  \t���:"
                            + entry.getValue());
                }
            }
        }
    }
}
