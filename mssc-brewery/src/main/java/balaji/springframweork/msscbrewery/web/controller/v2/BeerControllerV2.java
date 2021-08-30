package balaji.springframweork.msscbrewery.web.controller.v2;

import balaji.springframweork.msscbrewery.web.model.v2.BeerDTOV2;
import balaji.springframweork.msscbrewery.web.service.v2.BeerServiceV2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/beer")
@Validated
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerServiceV2) {
        this.beerServiceV2 = beerServiceV2;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTOV2> getBeer(@Valid @NotNull @PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBeer(@Valid @RequestBody BeerDTOV2 beerDTO) {
        BeerDTOV2 savedDTO = beerServiceV2.save(beerDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("location", "/api/v1/beer/" + savedDTO.getId().toString());

        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@Valid @NotNull @PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDTOV2 beerDTO) {
        beerServiceV2.updateBeer(beerId, beerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@Valid @NotNull @PathVariable("beerId") UUID beerId) {
        beerServiceV2.deleteBeerById(beerId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations().stream().map(constraintViolation
                -> constraintViolation.getPropertyPath() + ":" + constraintViolation.getMessage()).collect(Collectors.toList());

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
