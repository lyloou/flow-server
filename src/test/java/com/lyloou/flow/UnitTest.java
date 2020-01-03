package com.lyloou.flow;

import com.lyloou.common.http.HttpClient;
import com.lyloou.common.status.Result;
import com.lyloou.flow.model.flow.FlowReq;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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

    public static <T> List<List<T>> partition(List<T> list, int partitionSize) {
        List<List<T>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += partitionSize) {
            partitions.add(list.subList(i, Math.min(i + partitionSize, list.size())));
        }
        return partitions;
    }

    @Test
    public void testPartitionSize() {
        List<String> yourlist = new ArrayList<String>() {{
            add("0");
            add("2");
            add("3");
            add("4");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
            add("0");
        }};
        System.out.println(partition(yourlist, 5));
    }
}
