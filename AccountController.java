package com.example.auction_test.controllers;

import com.example.auction_test.models.AccountResponse;
import com.example.auction_test.models.LotResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {


    @GetMapping("")
    public List<AccountResponse> getUser() { // добавить аргументы

        return new ArrayList<AccountResponse>();
    }

    @GetMapping("/lots")
    public List<LotResponse> getLots() { // добавить аргументы

        return new ArrayList<LotResponse>();
    }

    @PostMapping("")
    public AccountResponse addUser() { // добавить аргументы
        return new AccountResponse();
    }

    @PatchMapping("")
    public String addMoney() // добавить аргументы
    {
        return new String("1234");
    }

    @DeleteMapping("")
    public boolean deleteUser() {
        return true;
    }
}