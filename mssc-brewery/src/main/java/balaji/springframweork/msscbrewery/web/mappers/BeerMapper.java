package balaji.springframweork.msscbrewery.web.mappers;

import balaji.springframweork.msscbrewery.domain.Beer;
import balaji.springframweork.msscbrewery.web.model.v2.BeerDTOV2;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {

    BeerDTOV2 beerToBeerDTO(Beer beer);

    Beer beerDTOToBeer(BeerDTOV2 beerDTOV2);

}
