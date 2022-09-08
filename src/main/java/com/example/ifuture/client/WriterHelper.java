package com.example.ifuture.client;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.IOException;
import java.util.List;

public class WriterHelper implements Runnable{
    private static final String PATH_ADD = "http://localhost:8080/api/account/add/%d/10";

    private final List<Integer> idList;
    private final CloseableHttpClient client;
    private int flag;

    public WriterHelper(List<Integer> idList, CloseableHttpClient client) {
        this.idList = idList;
        this.client = client;
        this.flag = 0;
    }

    @Override
    public void run() {

        while (true) {
            try (CloseableHttpResponse response = client.execute(new HttpPost(String.format(PATH_ADD, idList.get(flag))))) {
                flag += 1;
                if (flag == idList.size()) {
                    flag = 0;
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
