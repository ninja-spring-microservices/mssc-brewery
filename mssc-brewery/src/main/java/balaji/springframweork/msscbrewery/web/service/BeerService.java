package balaji.springframweork.msscbrewery.web.service;

import balaji.springframweork.msscbrewery.web.model.BeerDTO;

import java.util.UUID;

@Deprecated
public interface BeerService {
    BeerDTO getBeerById(UUID beerId);

    BeerDTO save(BeerDTO beerDTO);

    void updateBeer(UUID beerId, BeerDTO beerDTO);

    void deleteBeerById(UUID beerId);
}
