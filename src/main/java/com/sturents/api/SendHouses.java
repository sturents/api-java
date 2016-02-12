package com.sturents.api;

import java.net.URLConnection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class SendHouses {
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		String json = readFile("data.json");

		String landlord_id = args[0];
		String api_key = args[1];

		System.out.println(landlord_id);

		String auth_string = json + api_key;
		String auth = md5(auth_string);
		System.out.println("Auth is " + auth);

        String response = post(json, landlord_id, auth);
        
        System.out.println(response);
    }

    public static String post(String json, String landlord_id, String auth) throws IOException {
    	HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost/test.sturents.com/api/houses?landlord="+landlord_id+"&auth="+auth);
        
        HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
        
        post.setEntity(entity);

        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());

        return result;
    }

	static String readFile(String path) throws IOException  {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		
		return new String(encoded, "UTF-8");
	}

	static String md5(String plaintext) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		
		m.reset();
		m.update(plaintext.getBytes());
		byte[] digest = m.digest();
		
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		
		// Now we need to zero pad it if you actually want the full 32 chars.
		while(hashtext.length() < 32 ){
			hashtext = "0"+hashtext;
		}

		return hashtext;
	}
}