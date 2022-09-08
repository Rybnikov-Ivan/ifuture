package com.example.ifuture.server.service.statistic;

import com.example.ifuture.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class StatisticService {

    private static final Logger LOGGER = Logger.getLogger(StatisticService.class.getName());

    private static Client client;

    private static int writerCount = 0;
    private static int readerCount = 0;

    private static int totalNumberAddAmount = 0;
    private static int totalNumberGetAmount = 0;

    private static int reportGetAmountPerSecond = 0;
    private static int reportAddAmountPerSecond = 0;

    @Autowired
    public StatisticService(Client client) {
        StatisticService.client = client;
    }

    @Scheduled(fixedDelay = 1000)
    public static void start() {
        reportAddAmountPerSecond = writerCount;
        reportGetAmountPerSecond = writerCount;
        writerCount = 0;
        readerCount = 0;
        client.start();
    }

    public static void reset() {
        totalNumberGetAmount = 0;
        totalNumberAddAmount = 0;
    }

    public static void incrementWriterCount() {
        StatisticService.writerCount += 1;
        totalNumberAddAmount += 1;
    }

    public static void incrementReaderCount() {
        StatisticService.readerCount += 1;
        totalNumberGetAmount += 1;
    }

    @Scheduled(fixedDelay = 10000)
    public static void report() {
        LOGGER.info(
                "\n" + "---------------------------------------------------------" + "\n" +
                "total number of method calls 'add amount': " + totalNumberAddAmount + "\n" +
                "number of method calls 'add amount' per second: " + reportAddAmountPerSecond + "\n" +
                "total number of method calls 'get amount': " + totalNumberGetAmount + "\n" +
                "number of method calls 'get amount' per second: " + reportGetAmountPerSecond +
                "---------------------------------------------------------" + "\n"
                );
    }
}
