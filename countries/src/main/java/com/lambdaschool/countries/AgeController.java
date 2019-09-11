package com.lambdaschool.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/age")
public class AgeController
{
    // localhost:8080/age/age/25
    @GetMapping(value = "/age/{age}",
            produces = {"application/json"})
    public ResponseEntity<?> findOlderPeople(
            @PathVariable
                    long age)
    {
        ArrayList<Country> rtnCountries = CountriesApplication.countryList.
                findCountries(c -> c.getMedianAge() >= age);

        return new ResponseEntity<>(rtnCountries, HttpStatus.OK);
    }

    // localhost:8080/age/min
    @GetMapping(value = "/min",
            produces = {"application/json"})
    public ResponseEntity<?> findMinAge()
    {
        CountriesApplication.countryList.countryList.sort((c1, c2) -> (c1.getMedianAge() - c2.getMedianAge()));

        Country rtnCountry = CountriesApplication.countryList.countryList.get(0);
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    // localhost:8080/age/max
    @GetMapping(value = "/max",
            produces = {"application/json"})
    public ResponseEntity<?> findMaxAge()
    {
        CountriesApplication.countryList.countryList.sort((c1, c2) -> (c2.getMedianAge() - c1.getMedianAge()));

        Country rtnCountry = CountriesApplication.countryList.countryList.get(0);
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

    // Stretch Goal
    // localhost:8080/age/median
    @GetMapping(value = "/median",
            produces = {"application/json"})
    public ResponseEntity<?> findMedianAge()
    {
        CountriesApplication.countryList.countryList.sort((c1, c2) -> (int) (c1.getMedianAge() - c2.getMedianAge()));

        Country rtnCountry = CountriesApplication.countryList.countryList.get((CountriesApplication.countryList.countryList.size() / 2) + 1);
        return new ResponseEntity<>(rtnCountry, HttpStatus.OK);
    }

}
