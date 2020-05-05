package com.example.avro2json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.Iterator;

import org.apache.avro.Schema;
import org.apache.avro.util.RandomData;
import org.json.JSONObject;


public class Avro2Json {

	public static void main(String[] args) {

		InputStream inputStream = Avro2Json.class.getResourceAsStream("/user.avro");

		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}

			Schema schema = new Schema.Parser().parse(stringBuilder.toString());

			Iterator<Object> it = new RandomData(schema, 1).iterator();

			System.out.println("Sample JSON: \n" + toPrettyJSON(it.next().toString()));

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}

	}

	public static String toPrettyJSON(String jsonString) {

		JSONObject jsonObj = new JSONObject(jsonString);

		return jsonObj.toString(2);

	}

}
