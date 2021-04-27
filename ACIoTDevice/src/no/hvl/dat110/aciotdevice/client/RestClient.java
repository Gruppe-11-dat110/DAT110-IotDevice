package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;


import java.io.IOException;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}


	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {
		Gson gson = new Gson();
		OkHttpClient client = new OkHttpClient();
		AccessMessage accessMessage = new AccessMessage(message);

		String json = gson.toJson(accessMessage);

		RequestBody body = RequestBody.create(
				MediaType.parse("application/json"), json);

		Request request = new Request.Builder()
				.url("http://localhost:8080" + logpath)
				.post(body)
				.build();

		try {
			client.newCall(request).execute();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String codepath = "/accessdevice/code";

	public AccessCode doGetAccessCode() {

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url("http://localhost:8080" + codepath)
				.get()
				.build();

		Gson gson = new GsonBuilder().create();

		AccessCode code = null;


		try (Response response = client.newCall(request).execute()) {

		    String repbody = response.body().string(); // Får ta i string som json her.

			code = gson.fromJson(repbody, AccessCode.class);

			// TODO: implement a HTTP GET on the service to get current access code


		} catch (IOException e) {
			e.printStackTrace();
		}

		return code;
	}
}
