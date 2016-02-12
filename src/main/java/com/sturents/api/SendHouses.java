package com.sturents.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class SendHouses {

    private final static String STU_RENTS_URL = "https://sturents.com/api/houses?";
    private int landlord_id;
    private String api_key;
    private String json;

    /**
     * Constructor
     * 
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public SendHouses(String[] args) throws UnsupportedEncodingException, IOException {
	landlord_id = Integer.parseInt(args[0]);
	System.out.println("landlord_id" + landlord_id);

	api_key = args[1];
	System.out.println("api_key: " + api_key);

	json = new String(Files.readAllBytes(Paths.get(args[2])), "UTF-8");
	System.out.println("data.json read.");
    }

    /**
     * do the thing
     * 
     * @return response from http request to stu rents
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String run() throws IOException, NoSuchAlgorithmException {
	String auth_string = json + api_key;
	String auth = DigestUtils.md5Hex(auth_string);
	System.out.println("Auth is " + auth);

	String response = post(auth);

	return response;
    }

    private String post(String auth) throws IOException {
	HttpClient client = new DefaultHttpClient();

	HttpPost post = new HttpPost(buildHttpPostString(auth));

	HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));

	post.setEntity(entity);

	HttpResponse response = client.execute(post);
	String result = EntityUtils.toString(response.getEntity());

	return result;
    }

    /**
     * should use StringBuilder not String concatenation
     * 
     * @param auth
     * @return
     */
    private String buildHttpPostString(String auth) {
	StringBuilder sb = new StringBuilder();
	sb.append(STU_RENTS_URL);
	sb.append("?landlord=");
	sb.append(landlord_id);
	sb.append("&auth=");
	sb.append(auth);
	return sb.toString();
    }
}