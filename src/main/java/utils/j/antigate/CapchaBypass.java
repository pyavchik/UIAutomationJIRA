package utils.j.antigate;



import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;


public class CapchaBypass {
	static String api;
	static String answer;
public static String CapchaAnswer(InputStream is, String apikey, String words, String regsense, String russian) throws IOException, InterruptedException{
	api = apikey;
	String base64 = Base64Encoder.encode(is);
	
	String data = URLEncoder.encode("method", "UTF-8") + "=" + URLEncoder.encode("base64", "UTF-8"); 
	data += "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode(apikey, "UTF-8") ;
	data += "&" + URLEncoder.encode("body", "UTF-8") + "=" + URLEncoder.encode(base64, "UTF-8") ;
	if(words != null){
		if(words.equals("ONE")){
		data += "&" + URLEncoder.encode("phrase", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8") ;
		}
		if(words.equals("TWO")){
			data += "&" + URLEncoder.encode("phrase", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") ;
			}
	}
	if(regsense != null){
		if(regsense.equals("NO")){
		data += "&" + URLEncoder.encode("regsense", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8") ;
		}
		if(regsense.equals("YES")){
			data += "&" + URLEncoder.encode("regsense", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") ;
			}
	}
	if(russian != null){
		if(russian.equals("NO")){
		data += "&" + URLEncoder.encode("is_russian", "UTF-8") + "=" + URLEncoder.encode("0", "UTF-8") ;
		}
		if(russian.equals("YES")){
			data += "&" + URLEncoder.encode("is_russian", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") ;
			}
	}

	answer = CapchaPush.pushToServer(data);
	return answer;
}
}
