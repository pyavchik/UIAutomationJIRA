package utils.j.antigate;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CapchaPush {
	static String capchaId;
	static String capcha;
	public static void responseCapcha() throws UnsupportedEncodingException, IOException, InterruptedException{
		URL url1 = new URL("http://antigate.com/res.php?key="+CapchaBypass.api+"&action=get&id="+capchaId); 
		URLConnection con1 = url1.openConnection(); 
		Pattern p1 = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
		Matcher m1 = p1.matcher(con1.getContentType());
		String charset1 = m1.matches() ? m1.group(1) : "ISO-8859-1";
		Reader r1 = new InputStreamReader(con1.getInputStream(), charset1);
		StringBuilder buf2 = new StringBuilder();
		while (true) {
		int ch2 = r1.read();
		if (ch2 < 0)
		break;
		buf2.append((char) ch2);
		}
		String str1 = buf2.toString();
		if(str1.equals("CAPCHA_NOT_READY")){
			Thread.sleep(5000);
			responseCapcha();
		}else{
		if(str1.equals("ERROR_KEY_DOES_NOT_EXIST") || str1.equals("ERROR_WRONG_ID_FORMAT")){
			capcha = str1;
		}else{
			String[] response = str1.split("\\|");
			String respcode = response[0];
			capcha = response[1];
             
		}
		}
	}
public static String pushToServer(String data) throws UnsupportedEncodingException, IOException, InterruptedException{
	URL url = new URL("http://antigate.com/in.php"); 
	URLConnection con = url.openConnection(); 
	con.setDoOutput(true);
	OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
	wr.write(data);
	wr.flush();
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
	if(line.equals("ERROR_NO_SLOT_AVAILABLE") || line.equals("ERROR_WRONG_USER_KEY") || line.equals("ERROR_KEY_DOES_NOT_EXIST") || line.equals("ERROR_ZERO_BALANCE") || line.equals("ERROR_ZERO_CAPTCHA_FILESIZE") || line.equals("ERROR_TOO_BIG_CAPTCHA_FILESIZE") || line.equals("ERROR_WRONG_FILE_EXTENSION") || line.equals("ERROR_IMAGE_TYPE_NOT_SUPPORTED") || line.equals("ERROR_IP_NOT_ALLOWED")){
		return line;
	}
	else{
		String[] response = line.split("\\|");
		capchaId = response[1];
			Thread.sleep(10000);
			responseCapcha();
	return capcha;
	}
}
}
