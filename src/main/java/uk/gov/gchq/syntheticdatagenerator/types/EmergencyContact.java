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
import com.github.javafaker.Name;

import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;
import java.io.Serializable;

/**
 * @class Emergency Contact
 * @brief This class creates random values for an emergercy contact
 */
public class EmergencyContact implements Serializable{
    private static final int MAX_EXTRA_CONTACTS = 4;
    private String contactName;
    private Relation relation;
    private PhoneNumber[] contactNumbers;

    /**
     * @brief This method generates emergency contacts
     * @param faker fake data values
     * @param random random value
     * @return generated emergency contact
     */
    public static EmergencyContact generate(final Faker faker, final Random random) {
        EmergencyContact contact = new EmergencyContact();
        Name tempName = faker.name();
        contact.setContactName(tempName.firstName() + " " + tempName.lastName());
        contact.setRelation(Relation.generate(random));
        contact.setContactNumbers(PhoneNumber.generateMany(random));
        return contact;
    }

    /**
     * @brief This method generates a list of emergency contacts
     * @param faker fake data values
     * @param random random value
     * @return generated list of emergency contacts
     */
    public static EmergencyContact[] generateMany(final Faker faker, final Random random) {
        int numberOfExtraContacts = random.nextInt(MAX_EXTRA_CONTACTS);
        EmergencyContact[] emergencyContacts = new EmergencyContact[numberOfExtraContacts + 1];
        emergencyContacts[0] = EmergencyContact.generate(faker, random);
        for (int i = 1; i <= numberOfExtraContacts; i++) {
            emergencyContacts[i] = EmergencyContact.generate(faker, random);
        }
        return emergencyContacts;
    }

    /**
     * @brief This method returns the contact name
     * @return contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @brief This method assigns the contact name
     * @param contactName contact name
     */
    public void setContactName(final String contactName) {
        this.contactName = contactName;
    }


    /**
     * @brief This method returns the relation
     * @return relation
     */
    public Relation getRelation() {
        return relation;
    }


    /**
     * @brief This method assigns the relation
     * @param relation relation
     */
    public void setRelation(final Relation relation) {
        this.relation = relation;
    }

    /**
     * @brief This method returns the list of contact numbers
     * @return list of contact numbers
     */
    public PhoneNumber[] getContactNumbers() {
        return contactNumbers;
    }

    /**
     * @brief This method assigns the list of contact numbers
     * @param contactNumbers list of contact numbers
     */
    public void setContactNumbers(final PhoneNumber[] contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    /**
     * @brief Change attributes to a string format
     * @return attributes in a string format
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", EmergencyContact.class.getSimpleName() + "[", "]")
                .add("contactName='" + contactName + "'")
                .add("relation=" + relation)
                .add("contactNumbers=" + Arrays.toString(contactNumbers))
                .toString();
    }
}
