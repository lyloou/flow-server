package com.lyloou.flow;

import com.lyloou.common.http.HttpClient;
import com.lyloou.common.status.Result;
import com.lyloou.flow.model.flow.FlowReq;
import org.junit.Test;

/**
 * @author lyloou
 * @date 2020/01/02
 */
public class UnitTest {
    private static final String BASE_URL = "http://localhost:8888/api/v1/flow%s";

    private String url(String str) {
        return String.format(BASE_URL, str);
    }

    @Test
    public void testList() {
        HttpClient httpClient = new HttpClient();
        Result result = httpClient.get(url("/list?limit=5&offset=2"), Result.class);

        System.out.println(result);
    }

    @Test
    public void testSync() {
        HttpClient httpClient = new HttpClient();
        Result result = httpClient.postJson(url("/sync")
                , FlowReq.builder()
                        .day("20200112")
                        .item("whowhowh23o")
                        .build()
                , Result.class);

        System.out.println(result);
    }
}
