package com.example.ifuture.client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.IOException;
import java.util.List;

public class ReaderHelper implements Runnable {
    private static final String PATH_GET = "http://localhost:8080/api/account/get/%d";

    private final List<Integer> idList;
    private final CloseableHttpClient client;
    private int flag;

    public ReaderHelper(List<Integer> idList, CloseableHttpClient client) {
        this.idList = idList;
        this.client = client;
        this.flag = 0;
    }

    @Override
    public void run() {

        while (true) {
            try (CloseableHttpResponse response = client.execute(new HttpGet(String.format(PATH_GET, idList.get(flag))))) {
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
