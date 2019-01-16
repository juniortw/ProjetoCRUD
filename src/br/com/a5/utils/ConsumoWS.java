package br.com.a5.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ConsumoWS {

	public static String conexaoGet(String urlBase) throws IOException {

		URL url;
		try {
			url = new URL(urlBase);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();

			System.out.println("'GET' request URL : " + url);
			System.out.println("C�digo HTTP: " + con.getResponseCode());

			while (br.ready()) {
				sb.append(br.readLine());
			}
			br.close();

			return (sb.toString());

		} catch (MalformedURLException e) {
			return "Ocorreu um erro: " + e.getMessage();
		}

	}

	public static String conexaoPost(String urlBase, String urlParametro) {

		URL url;
		StringBuilder sb = new StringBuilder();

		try {
			url = new URL(urlBase);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.addRequestProperty("Content-Type", "application/json");

			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParametro);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			String responseMessage = con.getResponseMessage();

			System.out.println("'POST' request URL : " + url);
			System.out.println("Post par�metros : " + urlParametro);
			System.out.println("C�digo HTTP : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			while (br.ready()) {
				sb.append("{");
				sb.append("\n\"responseCode\" : " + responseCode + ",");
				sb.append("\n\"responseMessage\" : \"" + responseMessage + "\",");
				sb.append("\n\"pedidos\":" + br.readLine() + "\n}");
				sb.append("\n\"flagError\": false\n}");
			}
			br.close();

			return sb.toString();

		} catch (Exception e) {
			System.out.println();
			sb.append("{");
			sb.append("\n\"flagError\": true,");
			sb.append("\n\"messageError\": \"" + ExceptionUtil.stackTraceToString(e) + "\"\n}");

			return sb.toString();
		}

	}

}
