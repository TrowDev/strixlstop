package br.com.strixcloud.lstop.provider.request.impl;

import br.com.strixcloud.lstop.entities.util.DateDuration;
import br.com.strixcloud.lstop.entities.util.HttpResponse;
import br.com.strixcloud.lstop.provider.request.IRequestProvider;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.var;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestProvider implements IRequestProvider {

    String authToken = null;

    public void auth(String keyApi) {
        authToken = keyApi;
    }

    public HttpResponse get(String url) throws IOException {
        /*
            Instances
         */

        var msCalc = new DateDuration();
        var parser = new JsonParser();
        var httpUrl = (url.contains("http://") || url.contains("https://")) ? url : "http://" + url;
        var urlObj = new URL(httpUrl);

        /*
            Perform HttpConnection
         */

        HttpURLConnection conn = defaultConnection((HttpURLConnection) urlObj.openConnection());
        if (authToken != null) {
            conn.setRequestProperty("Authorization", authToken);
        }

        conn.setRequestMethod("GET");

        /*
            Read output
         */

        int statusCode = conn.getResponseCode();

        var br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        var sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        conn.disconnect();
        br.close();

        /*
            Return output
         */

        var ms = msCalc.calculate();
        return new HttpResponse(statusCode,
                (JsonObject) parser.parse(!sb.toString().equals("") ? sb.toString() : "{}"), ms
        );
    }

    private HttpURLConnection defaultConnection(HttpURLConnection conn) {
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setAllowUserInteraction(false);
        return conn;
    }

}
