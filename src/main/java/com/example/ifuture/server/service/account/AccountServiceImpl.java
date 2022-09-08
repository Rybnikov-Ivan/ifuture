package com.example.ifuture.server.service.account;

import com.example.ifuture.server.model.Account;
import com.example.ifuture.server.repository.AccountRepository;
import com.example.ifuture.server.service.account.AccountService;
import com.example.ifuture.server.utils.response.MessageServiceResponse;
import com.example.ifuture.server.utils.response.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AccountServiceImpl implements AccountService {

    private final Map<Integer, Account> hash = new ConcurrentHashMap<>();
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public ServiceResponse<Long> getAmount(Integer id) {
        ServiceResponse<Long> serviceResponse = new ServiceResponse<>();
        if (hash.containsKey(id)) {
            serviceResponse.setResponseObject(hash.get(id).getValue());
            serviceResponse.setHttpStatus(HttpStatus.OK);
            serviceResponse.setResponseMessage(MessageServiceResponse.OK);
        } else {
            serviceResponse.setInternalServerError();
        }
        return serviceResponse;
    }

    @Override
    public synchronized ServiceResponse<Long> addAmount(Integer id, Long value) {
        ServiceResponse<Long> serviceResponse = new ServiceResponse<Long>();
        Account account;
        if (hash.containsKey(id)) {
            account = hash.get(id);
            account.setValue(account.getValue() + value);
        } else {
            account = new Account(id, value);
            hash.put(id, account);
        }
        accountRepository.save(account);
        serviceResponse.setSuccessResponse();
        return serviceResponse;
    }
}
