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
  
    public static List<Map<Long, String>> parse(String path) {
        List<Map<Long, String>> list = new ArrayList<Map<Long, String>>();
        try {
            String encoding = "GBK";
            
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStreamReader read = new InputStreamReader(connection.getInputStream(), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            if (true) {
                String regex = "\\[(\\d{1,2}):(\\d{1,2}).(\\d{1,2})\\]"; // 正则表达式
                Pattern pattern = Pattern.compile(regex); // 创建 Pattern 对象
                String lineStr = null; // 每次读取一行字符串
                while ((lineStr = bufferedReader.readLine()) != null) {
                    Matcher matcher = pattern.matcher(lineStr);
                    while (matcher.find()) {
                        Map<Long, String> map = new HashMap<Long, String>();
                        String min = matcher.group(1); // 分钟
                        String sec = matcher.group(2); // 秒
                        String mill = matcher.group(3); // 毫秒，注意这里其实还要乘以10
                        long time = getLongTime(min, sec, mill + "0");
                        // 获取当前时间的歌词信息
                        String text = lineStr.substring(matcher.end());
                        map.put(time, text); // 添加到容器中
                        list.add(map);
                    }
                }
                read.close
                return list;
            }
        } catch (Exception e) {
            System.out.println("读取文件出错!");
            e.printStackTrace();
        }
        return null;
    }

    //将以字符串形式给定的分钟、秒钟、毫秒转换成一个以毫秒为单位的long型数
    
    private static long getLongTime(String min, String sec, String mill) {
        // 转成整型
        int m = Integer.parseInt(min);
        int s = Integer.parseInt(sec);
        int ms = Integer.parseInt(mill);

        if (s >= 60) {
            System.out.println("警告: 出现了一个时间不正确的项 --> [" + min + ":" + sec + "."
                    + mill.substring(0, 2) + "]");
        }
        // 组合成一个长整型表示的以毫秒为单位的时间
        long time = m * 60 * 1000 + s * 1000 + ms;
        return time;
    }

    //打印歌词信息
    private void printLrc(List<Map<Long, String>> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("没有任何歌词信息！");
        } else {
            for (Map<Long, String> map : list) {
                for (Entry<Long, String> entry : map.entrySet()) {
                    System.out.println("时间:" + entry.getKey() + "  \t歌词:"
                            + entry.getValue());
                }
            }
        }
    }
}
