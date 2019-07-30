package com.lyloou.common.http;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.lyloou.common.exception.CommonException;
import com.lyloou.common.exception.HttpRespEmptyException;
import okhttp3.*;
import okio.BufferedSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class HttpClient {

    private final int maxRequest;

    private final int maxRequestsPerHost;

    private final int connectTimeout;

    private final int readTimeout;

    private final OkHttpClient client;


    public HttpClient() {
        this(64, 16, 10, 30);
    }

    /**
     * @param maxRequest
     * @param maxRequestsPerHost
     * @param connectTimeout
     * @param readTimeout
     */
    public HttpClient(int maxRequest, int maxRequestsPerHost, int connectTimeout, int readTimeout) {
        this.maxRequest = maxRequest;
        this.maxRequestsPerHost = maxRequestsPerHost;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(this.maxRequest);
        dispatcher.setMaxRequestsPerHost(this.maxRequestsPerHost);
        ConnectionPool connectionPool = new ConnectionPool(32,
                5, TimeUnit.MINUTES);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.dispatcher(dispatcher);
        builder.connectionPool(connectionPool);
        builder.connectTimeout(this.connectTimeout, TimeUnit.SECONDS);
        builder.readTimeout(this.readTimeout, TimeUnit.SECONDS);
        builder.writeTimeout(0, TimeUnit.SECONDS);
        this.client = builder.build();
    }


    public <T> T get(String url, Class<T> clazz) {
        return this.get(url, null, clazz);
    }

    public <T> T get(String url, Map<String, String> querystring, Class<T> clazz) {
        return this.get(url, querystring, null, clazz);
    }

    public <T> T get(String url, Map<String, String> querystring, Map<String, String> header, Class<T> clazz) {
        Objects.requireNonNull(url, "request url can not be null");
        String queryStr = "";
        if (null != querystring) {
            queryStr = querystring.entrySet().stream()
                    .map(e -> Joiner.on("=").join(e.getKey(), e.getValue()))
                    .collect(Collectors.joining("&"));
        }
        String joinStr = url.contains("?") ? "&" : "?";
        return this.send(Joiner.on(joinStr).join(url, queryStr),
                "GET", header, null, null, clazz);
    }

    public <T> T post(String url, Map<String, String> header, String contentType, String body, Class<T> clazz) {
        return this.send(url, "POST", header, contentType, body, clazz);
    }

    public <T> T send(String url, String method, Map<String, String> header,
                      String contentType, String body, Class<T> clazz) {
        Objects.requireNonNull(url, "request url can not be null");
        Objects.requireNonNull(method, "request method can not be null");
        Request.Builder build = new Request.Builder();
        RequestBody reqBody = requestBody(contentType, body);
        build.method(method.toUpperCase(), reqBody).url(url);
        Optional.ofNullable(header).ifPresent(h -> build.headers(Headers.of(h)));
        Request req = build.build();
        return respJsonBody(req, clazz);
    }

    private <T> T respJsonBody(Request req, Class<T> clazz) {
        Response resp;
        try {
            resp = this.client.newCall(req).execute();
        } catch (IOException e) {
            throw new CommonException("http client request has error ", e);
        }
        Optional<ResponseBody> opBody = Optional.ofNullable(resp.body());
        if (opBody.isPresent()) {
            try (InputStream in = opBody.get().byteStream();
                 InputStreamReader reader = new InputStreamReader(in);
                 BufferedReader bufferedReader = new BufferedReader(reader);) {
                String strBody = bufferedReader.lines().collect(Collectors.joining());
                return new Gson().fromJson(strBody, clazz);
            } catch (IOException e) {
                throw new CommonException("http client request has error ", e);
            }
        } else {
            throw new HttpRespEmptyException("response is empty");
        }
    }


    private RequestBody requestBody(String contentType, String body) {
        if (Strings.isNullOrEmpty(contentType) || Strings.isNullOrEmpty(body)) {
            return null;
        }
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return MediaType.parse(contentType);
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(body.getBytes(StandardCharsets.UTF_8));
            }
        };
    }

}
