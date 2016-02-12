package com.sturents.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    private final static String STURENTS_URL = "https://sturents.com/api/houses?";
    private int landlord_id;
    private String api_key;
    private String json;

    /**
     * Constructor
     * 
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public SendHouses(Integer landlord_id, String api_key) throws UnsupportedEncodingException, IOException {
		this.landlord_id = landlord_id;

		this.api_key = api_key;
    }

    /**
     * do the thing
     * 
     * @return response from http request to stu rents
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String run(String json) throws IOException, NoSuchAlgorithmException {
		String auth_string = json + this.api_key;
		String auth = DigestUtils.md5Hex(auth_string);
		System.out.println("Auth is " + auth);

		String response = post(auth, json);

		return response;
    }

    private String post(String auth, String json) throws IOException {
		HttpClient client = new DefaultHttpClient();

		HttpPost post = new HttpPost(buildHttpPostString(this.landlord_id, auth));

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
    private String buildHttpPostString(int landlord_id, String auth) {
		StringBuilder sb = new StringBuilder();
		
		sb.append(STURENTS_URL);
		sb.append("?landlord=");
		sb.append(landlord_id);
		sb.append("&auth=");
		sb.append(auth);

		return sb.toString();
    }
}