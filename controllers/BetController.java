package com.example.auction.controllers;

import com.example.auction.controllers.exceptions.BetNotExistException;
import com.example.auction.controllers.models.BetRequest;
import com.example.auction.controllers.models.BetResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bets")
public class BetController {
    private HashMap<Integer, BetRequest> savedBets = new HashMap<>();

    @GetMapping("/{id}")
    BetResponse getBet(@PathVariable("id") Integer betId) throws BetNotExistException
    {
        if (!savedBets.containsKey(betId))
        {
            throw new BetNotExistException();
        }
        BetResponse foundBet = convertToResponse(savedBets.get(betId));
        return foundBet;
    }

    @GetMapping("")
    ArrayList<BetResponse> getBets()
    {
        ArrayList<BetResponse> result = new ArrayList<>();
        for (Map.Entry<Integer, BetRequest> entry : savedBets.entrySet()) {
            BetResponse betResp = convertToResponse(entry.getValue());
            result.add(betResp);
        }
        return result;
    }

    @PostMapping("")
    Integer addBet(BetRequest newBet)
    {
        Integer id = savedBets.size();
        savedBets.put(id, newBet);
        return id;
    }

    @DeleteMapping("/{id}")
    Integer deleteBet(@PathVariable("id") Integer betId) throws BetNotExistException //but its strange. How did yoi get id of not existing bet.
    {
        if(!savedBets.containsKey(betId))
        {
            throw new BetNotExistException();
        }
        savedBets.remove(betId);
        return betId;
    }

    ///////////////////////////////////////////////////////////////////////////
    //                      private
    ///////////////////////////////////////////////////////////////////////////
    private BetResponse convertToResponse(BetRequest betReq)
    {
        BetResponse betResp = new BetResponse();
        betResp.setBetSize(betReq.getBetSize());
        betResp.setLotId(betReq.getLotId());
        betResp.setUserNickname(betReq.getUserNickname());
        return betResp;
    }

}