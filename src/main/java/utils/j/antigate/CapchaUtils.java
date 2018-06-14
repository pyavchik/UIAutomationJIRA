package utils.j.antigate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapchaUtils {
	public static byte[] readBytes(InputStream inputStream) throws IOException {
		  ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
		  int bufferSize = 1024;
		  byte[] buffer = new byte[bufferSize];
		  int len = 0;
		  while ((len = inputStream.read(buffer)) != -1) {
		    byteBuffer.write(buffer, 0, len);
		  }
		  return byteBuffer.toByteArray();
		}
	public static String getBalance(String apikey) throws IOException{
		URL url = new URL("http://antigate.com/res.php?key="+apikey+"&action=getbalance"); 
		URLConnection con = url.openConnection(); 
		Pattern p1 = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
		Matcher m1 = p1.matcher(con.getContentType());
		String charset = m1.matches() ? m1.group(1) : "ISO-8859-1";
		Reader r1 = new InputStreamReader(con.getInputStream(), charset);
		StringBuilder buf1 = new StringBuilder();
		while (true) {
		int ch1 = r1.read();
		if (ch1 < 0)
		break;
		buf1.append((char) ch1);
		}
		String line = buf1.toString();
		return line;
	}
}
