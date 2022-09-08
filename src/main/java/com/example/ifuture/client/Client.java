package com.example.ifuture.client;

import lombok.Data;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Data
public class Client {

    private final int rCount;
    private final int wCount;
    private final List<Integer> idList;
    private final CloseableHttpClient client = HttpClients.createDefault();

    public Client(@Value("${client.rCount}") int rCount, @Value("${client.wCount}") int wCount, @Value("${client.idListStart}") int idListStart, @Value("${client.idListEnd}") int idListEnd) {
        this.rCount = rCount;
        this.wCount = wCount;
        idList = new ArrayList<>();
        for (int i = idListStart; i < idListEnd; i++) {
            idList.add(i);
        }
    }

    public void start() {
        ExecutorService executorService = Executors.newFixedThreadPool(rCount + wCount);
        for (int i = 0; i < rCount; i++) {
            executorService.submit(new ReaderHelper(idList, client));
        }
        for (int j = 0; j < wCount; j++) {
            executorService.submit(new WriterHelper(idList, client));
        }
    }
}
