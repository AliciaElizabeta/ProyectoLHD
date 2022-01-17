/*
 * Copyright 2018-2021 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.gchq.syntheticdatagenerator.types;

import com.github.javafaker.Faker;

import java.util.Random;
import java.util.StringJoiner;
import java.io.Serializable;

/**
 * @class Address
 * @brief This class creates random values for an address
 */
public class Address implements Serializable {

    private String streetAddressNumber;
    private String streetName;
    private String city;
    private String state;
    private String zipCode;

    /**
     * @brief This method generates addresses
     * @param faker fake data values
     * @param random random value
     * @return generated addresses
     */
    public static Address generate(final Faker faker, final Random random) {
        Address address = new Address();
        com.github.javafaker.Address fakeAddress = faker.address();
        address.setStreetAddressNumber(fakeAddress.streetAddressNumber());
        address.setStreetName(fakeAddress.streetName());
        address.setCity(fakeAddress.city());
        address.setState(fakeAddress.state());
        address.setZipCode(fakeAddress.zipCode());
        return address;
    }

    /**
     * @brief This method returns the street address number
     * @return street address number
     */
    public String getStreetAddressNumber() {
        return streetAddressNumber;
    }

    /**
     * @brief This method assigns a street number address
     * @param streetAddressNumber street number address
     */
    public void setStreetAddressNumber(final String streetAddressNumber) {
        this.streetAddressNumber = streetAddressNumber;
    }


    /**
     * @brief This method returns the street name
     * @return street name
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @brief This method assigns a street name
     * @param streetName street name
     */
    public void setStreetName(final String streetName) {
        this.streetName = streetName;
    }


    /**
     * @brief This method returns the city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @brief This method assigns a city
     * @param city city
     */
    public void setCity(final String city) {
        this.city = city;
    }


    /**
     * @brief This method returns the state
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @brief This method assigns a state
     * @param state state
     */
    public void setState(final String state) {
        this.state = state;
    }


    /**
     * @brief This method returns the zip code of a city
     * @return zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @brief This method assigns the zip code of a country
     * @param zipCode zip code
     */
    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("streetAddressNumber='" + streetAddressNumber + "'")
                .add("streetName='" + streetName + "'")
                .add("city='" + city + "'")
                .add("state='" + state + "'")
                .add("zipCode='" + zipCode + "'")
                .toString();
    }
}
