package com.debugteam.auction_test.services;

import com.debugteam.auction_test.exceptions.AccountNotExistsException;
import com.debugteam.auction_test.exceptions.LotExistsException;
import com.debugteam.auction_test.exceptions.LotNotExistsException;
import com.debugteam.auction_test.models.LotDto;
import com.debugteam.auction_test.models.LotRequest;

import java.util.List;

public interface LotService {

    List<LotDto> getSearchLots(String name) throws LotNotExistsException;

    LotDto addLot(LotRequest lotRequest, String userId) throws LotExistsException, AccountNotExistsException;

    void deleteLot(String lotId) throws LotNotExistsException;
}
