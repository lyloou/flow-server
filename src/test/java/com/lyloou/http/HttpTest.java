package com.lyloou.http;

import com.lyloou.common.http.Client;
import org.junit.Test;

public class HttpTest {
    @Test
    public void test1() {
        Client client = new Client();
        GitRepos gitRepos = client.get("https://api.github.com/repos/lyloou/lou", GitRepos.class);
        System.out.println(gitRepos.getFull_name());
    }
}
