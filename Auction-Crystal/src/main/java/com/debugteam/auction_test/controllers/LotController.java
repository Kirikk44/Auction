package com.debugteam.auction_test.controllers;


import com.debugteam.auction_test.exceptions.*;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;
import com.debugteam.auction_test.security.models.OurAuthToken;
import com.debugteam.auction_test.services.LotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lots")
public class LotController {

    private final LotService lotServices;

    public LotController(LotService lotServices) {
        this.lotServices = lotServices;
    }


    @GetMapping("")
    public List<LotDto> getSearchLots(@RequestParam("name") String name) throws LotNotExistsException {

        return lotServices.getSearchLots(name);
    }

    @GetMapping("/all")
    public List<LotDto> geTLots() {

        return lotServices.getLots();
    }


    @PostMapping
    public LotDto addLot(@RequestBody LotRequest lotRequest, OurAuthToken authToken) throws LotExistsException,
            AccountNotExistsException, ProductAlreadyInLotException {
        return lotServices.addLot(lotRequest, authToken.getPrincipal().getId());
    }

    @DeleteMapping("/{id}")
    public void deleteLot(@PathVariable String id, OurAuthToken authToken) throws LotNotExistsException,
            UserAccessViolationException {
        lotServices.deleteLot(id, authToken.getPrincipal().getId());
    }

}
