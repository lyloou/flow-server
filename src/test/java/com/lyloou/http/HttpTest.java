package com.lyloou.http;

import com.lyloou.common.http.HttpClient;

public class HttpTest {
    public void test1() {
        HttpClient httpClient = new HttpClient();
        GitRepos gitRepos = httpClient.get("https://api.github.com/repos/lyloou/lou", GitRepos.class);
        System.out.println(gitRepos.getFull_name());
    }
}
