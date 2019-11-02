package com.freenow.pojo.address;

import com.freenow.pojo.geo.Geo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    @Override
    public String toString() {
        return "ClassPojo [street = " + street + ", suite = " + suite + ", city = " + city + ", zipcode = " + zipcode + ", geo = " + geo + "]";
    }
}
