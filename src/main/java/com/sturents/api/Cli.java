package com.sturents.api;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Command Line Interface for interacting with StuRents
 * <p>
 * arguments should be as follows:
 * <p>
 * <ul>
 * <li>landlord_id</li>
 * <li>api_key</li>
 * <li>path to data.json file</li>
 * </ul>
 */
public class Cli {

    private static final String ERROR_HELP = "arguments should be as follows:\n* landlord_id\n* api_key\n* path to data.json file";

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
	if (args.length != 3) {
	    throw new IllegalArgumentException("not all arguments specified).\n" + ERROR_HELP);
	}
	String response = new SendHouses(args).run();

	System.out.println(response);
    }
}