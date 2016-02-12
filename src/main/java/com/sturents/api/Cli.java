package com.sturents.api;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

		int landlord_id = Integer.parseInt(args[0]);
		System.out.println("landlord_id: " + landlord_id);

		String api_key = args[1];
		System.out.println("api_key: " + api_key);

		String json = new String(Files.readAllBytes(Paths.get(args[2])), "UTF-8");

		String response = new SendHouses(landlord_id, api_key).run(json);

		System.out.println(response);
    }
}