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
 * @class Work Location
 * @brief This class creates random values for a work location
 */
public class WorkLocation implements Serializable{
    private WorkLocationName workLocationName;
    private Address address;

    /**
     * @brief This method generates work locations
     * @param faker fake data value
     * @param random random value
     * @return generated work location
     */
    public static WorkLocation generate(final Faker faker, final Random random) {
        WorkLocation workLocation = new WorkLocation();
        workLocation.setAddress(Address.generate(faker, random));
        workLocation.setWorkLocationName(WorkLocationName.generate(random));
        return workLocation;
    }

    /**
     * @brief This method returns the work location of a person
     * @return work location
     */
    public WorkLocationName getWorkLocationName() {
        return workLocationName;
    }

    /**
     * @brief This method assigns a work location to a person
     * @param workLocationName work Location
     */
    public void setWorkLocationName(final WorkLocationName workLocationName) {
        this.workLocationName = workLocationName;
    }

    /**
     * @brief This method returns the address of a person
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @brief This method assigns an address to a person
     * @param address address
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", WorkLocation.class.getSimpleName() + "[", "]")
                .add("workLocationName=" + workLocationName)
                .add("address=" + address)
                .toString();
    }
}


