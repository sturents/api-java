package com.sturents.api;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Command Line Interface for interacting with StuRents
 * <p>
 * arguments should be as follows:
 * <p>
 * <ul>
 * <li>stuRentsUrl</li>
 * <li>landlord_id</li>
 * <li>api_key</li>
 * <li>path to data.json file</li>
 * </ul>
 */
public class Cli {

    private static String README = "arguments should be as follows:\n* stuRentsUrl\n* landlord_id\n* api_key\n* path to data.json file";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
	if (args.length != 4) {
	    throw new IllegalArgumentException("not all arguments specified).\n" + README);
	}
	String response = new SendHouses(args).run();

	System.out.println(response);
    }
}