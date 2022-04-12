package com.debugteam.auction_test.controllers;


import com.debugteam.auction_test.exceptions.LotExistsException;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;
import com.debugteam.auction_test.security.models.OurAuthToken;
import com.debugteam.auction_test.services.LotService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/lots")
public class LotController {

    private final LotService lotServices;

    public LotController(LotService lotServices) {
        this.lotServices = lotServices;
    }


    @GetMapping("")
    public List<LotDto> getSearchLots(@RequestParam("name") String name) {
        return new ArrayList<>();
    }


    @PostMapping
    public LotDto addLot(@RequestBody LotRequest lotRequest, OurAuthToken ourAuthToken) throws LotExistsException {
        return lotServices.addLot(lotRequest, ourAuthToken.getPrincipal().getId());
    }

//    @PatchMapping
//    public int addBetOnLot(BetRequest new_bet) { // в контроллере ставок
//        return 0;
//    }

    @DeleteMapping("/{id}")
    public void deleteLot(@PathVariable int id) {
    }

}
