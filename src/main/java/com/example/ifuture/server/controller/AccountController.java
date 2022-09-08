package com.example.ifuture.server.controller;

import com.example.ifuture.server.service.account.AccountService;
import com.example.ifuture.server.utils.response.ServiceResponse;
import com.example.ifuture.server.service.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/add/{id}/{amount}")
    public ServiceResponse<Long> addAmount(@PathVariable("id") final Integer id,
                                               @PathVariable("amount") final Long amount) {
        StatisticService.incrementWriterCount();
        return this.accountService.addAmount(id, amount);
    }

    @GetMapping(value = "/get/{id}")
    public ServiceResponse<Long> getAmount(@PathVariable Integer id) {
        StatisticService.incrementReaderCount();
        return this.accountService.getAmount(id);
    }

    @GetMapping("/start")
    public void startStatistic() {
        StatisticService.start();
    }

    @GetMapping("/reset")
    public void resetStatistic() {
        StatisticService.reset();
    }

}
