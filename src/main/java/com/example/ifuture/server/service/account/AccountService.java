package com.example.ifuture.server.service.account;

import com.example.ifuture.server.utils.response.ServiceResponse;

public interface AccountService {
    /**
     * Retrieves current balance or zero if addAmount() method was not called before for specified id
     *
     * @param id balance identifier
     */
    ServiceResponse<Long> getAmount(Integer id);

    /**
     * Increases balance or set if addAmount() method was called first time
     *
     * @param id balance identifier
     * @param value positive or negative value, which must be added to current balance
     */
    ServiceResponse<Long> addAmount(Integer id, Long value);
}
