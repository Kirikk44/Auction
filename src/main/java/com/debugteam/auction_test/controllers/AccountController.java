package com.debugteam.auction_test.controllers;

import com.debugteam.auction_test.exceptions.AccountExistsException;
import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.models.AccountDto;
import com.debugteam.auction_test.models.AccountRequest;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.security.models.OurAuthToken;
import com.debugteam.auction_test.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts") // accounts (Был account)
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public AccountDto getUser(OurAuthToken authToken) throws AccountNotExistsException { // добавить аргументы
        //Был обычный string, мы поставили @PathVariable("id") и запрос был  @GetMapping("")
        //return new AccountDto();
        return accountService.getUser(authToken.getPrincipal().getId());
    }

    @PatchMapping("")
    public void changeUser(@RequestBody AccountRequest accountRequest) throws AccountNotExistsException
    {
        accountService.changeUser(accountRequest);
    }

    @DeleteMapping("")
    public void deleteUser(OurAuthToken ourAuthToken) throws AccountNotExistsException
    {
        accountService.deleteUser(ourAuthToken.getPrincipal().getId());
    }

    @GetMapping("/lots")
    public List<LotDto> getUserLots(OurAuthToken authToken) throws AccountNotExistsException { // добавить аргументы #TODO: Спросить, что передавать в аргументы.
        return accountService.getUserLots(authToken.getPrincipal().getId());
    }

    @PostMapping("/money")
    public void addMoney(@RequestBody AccountRequest accountRequest, OurAuthToken ourAuthToken) throws AccountNotExistsException// добавить аргументы // token
    {
        accountRequest.setId(ourAuthToken.getPrincipal().getId());
        accountService.addMoney(accountRequest);
    }
}