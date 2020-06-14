package javafx;


public class lrc_statement {
 
    private long time = 0;//时间, 单位为10ms
    private String lyric = "";//歌词
 
    public long getTime() {
	return time;
    }
 
    public void setTime(long time) {
	this.time = time;
    }
 
    public void setTime(String time) {
	String str[] = time.split(":|\\.");
	this.time = Integer.parseInt(str[0]) * 6000 + Integer.parseInt(str[1]) * 100 + 
		Integer.parseInt(str[2]);
    }
   
 
    public String getLyric() {
	return lyric;
    }
 
    public void setLyric(String lyric) {
	this.lyric = lyric;
    }
 
    public void printLyric() {
	System.out.println(time + ": " + lyric);
    }
}
