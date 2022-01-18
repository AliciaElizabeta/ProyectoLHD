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
import uk.gov.gchq.syntheticdatagenerator.utils.DateHelper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;

/**
 * @class Teacher
 * @brief This class generates random values for the entity teacher
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MIN_MANGERS_TREE_HEIGHT = 2;
    private static final int EXTRA_MANAGERS_TREE_HEIGHT_RANGE = 3;
    private static final int MIN_SALARY = 20_000;
    private static final int EXTRA_SALARY_RANGE = 100_000;
    private static final int SALARY_BONUS_RANGE = 10_000;

    private String uid;
    private String name;
    private String dateOfBirth;
    private PhoneNumber[] contactNumbers;
    private EmergencyContact[] emergencyContacts;
    private Address address;
    private Nationality nationality;
    private Subject subject;
    private Department department;
    private Manager[] manager;
    private String hireDate;
    private int salaryAmount;
    private int salaryBonus;
    private WorkLocation workLocation;
    private Sex sex;

    /**
     * @brief This method generates random values for a teacher
     * @param random random value
     * @return generated teachers
     */
    public static Teacher generate(final Random random) {
        Teacher teacher = new Teacher();
        Faker faker = ThreadLocalFaker.getFaker(random);
        teacher.setUid(generateUID(random));
        Name teacherName = faker.name();
        teacher.setName(teacherName.firstName() + " " + teacherName.lastName()); // we are storing name as a string not a Name
        teacher.setDateOfBirth(DateHelper.generateDateOfBirth(random));
        teacher.setContactNumbers(PhoneNumber.generateMany(random));
        teacher.setEmergencyContacts(EmergencyContact.generateMany(faker, random));
        teacher.setAddress(Address.generate(faker));
        teacher.setNationality(Nationality.generate(random));
        teacher.setSubject(Subject.generate(random));
        teacher.setDepartment(Department.generate(random));
        teacher.setManager(Manager.generateMany(random, MIN_MANGERS_TREE_HEIGHT + random.nextInt(EXTRA_MANAGERS_TREE_HEIGHT_RANGE)));
        teacher.setHireDate(DateHelper.generateHireDate(teacher.dateOfBirth, random));
        teacher.setSalaryAmount(MIN_SALARY + random.nextInt(EXTRA_SALARY_RANGE));
        teacher.setSalaryBonus(random.nextInt(SALARY_BONUS_RANGE));
        teacher.setWorkLocation(WorkLocation.generate(faker, random));
        teacher.setSex(Sex.generate(random));

        return teacher;
    }

    /**
     * @brief This method generates an identifier
     * @param random random value
     * @return generated identifier
     */
    public static String generateUID(final Random random) {
        return String.valueOf(random.nextInt(Integer.MAX_VALUE));
    }

    /**
     * @brief This method returns the identifier of a teacher
     * @return identifier
     */
    public String getUid() {
        return uid;
    }

    /**
     * @brief This method assigns the identifier of a teacher
     * @param uid identifier
     */
    public void setUid(final String uid) {
        this.uid = uid;
    }

    /**
     * @brief This method returns the name of a teacher
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @brief This method assigns a name to a teacher
     * @param name name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @brief This method returns the date of birth of a teacher
     * @return date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @brief This method assigns a date of birth to a teacher
     * @param dateOfBirth date of birth
     */
    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @brief This method returns list of contact numbers of a teacher
     * @return list of contact numbers
     */
    public PhoneNumber[] getContactNumbers() {
        return contactNumbers;
    }

    /**
     * @brief This method assigns a list of contact number to a teacher
     * @param contactNumbers list of contact numbers
     */
    public void setContactNumbers(final PhoneNumber[] contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    /**
     * @brief This method returns the list of emergency contacts of a teacher
     * @return list of emergency contacts
     */
    public EmergencyContact[] getEmergencyContacts() {
        return emergencyContacts;
    }

    /**
     * @brief This method assigns a list of emergency contacts to a teacher
     * @param emergencyContacts list of emergency contacts
     */
    public void setEmergencyContacts(final EmergencyContact[] emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    /**
     * @brief This method returns the address of a teacher
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @brief This method assigns an address to a teacher
     * @param address address
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * @brief This method returns the list of managers of a teacher
     * @return list of managers
     */
    public Manager[] getManager() {
        return manager;
    }

    /**
     * @brief This method assigns a list of managers to a teacher
     * @param manager list of managers
     */
    public void setManager(final Manager[] manager) {
        this.manager = manager;
    }

    /**
     * @brief This method returns the nationality of a teacher
     * @return nationality
     */
    public Nationality getNationality() {
        return nationality;
    }

    /**
     * @brief This method assigns a nationality to a teacher
     * @param nationality nationality
     */
    public void setNationality(final Nationality nationality) {
        this.nationality = nationality;
    }

    /**
     * @brief This method returns the subject taught by a teacher
     * @return subject
     */
    public Subject getSubject() { return subject; }

    /**
     * @brief This method assigns a taught subject to a teacher
     * @param subject subject
     */
    public void setSubject(final Subject subject) { this.subject = subject; }


    /**
     * @brief This method returns the department of a teacher
     * @return department
     */
    public Department getDepartment() { return department;}

    /**
     * @brief This method assigns a department to a teacher
     * @param department department
     */
    public void setDepartment(final Department department) {this.department = department;}


    /**
     * @brief This method returns the hire date of a teacher
     * @return hire date
     */
    public String getHireDate() {
        return hireDate;
    }

    /**
     * @brief This method assigns a hire date to a teacher
     * @param hireDate hire date
     */
    public void setHireDate(final String hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * @brief This method returns the salary amount of a teacher
     * @return salary amount
     */
    public int getSalaryAmount() {
        return salaryAmount;
    }

    /**
     * @brief This method assigns a salary amount to a teacher
     * @param salaryAmount salary amount
     */
    public void setSalaryAmount(final int salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    /**
     * @brief This method returns the salary bonus of a teacher
     * @return salary bonus
     */
    public int getSalaryBonus() {
        return salaryBonus;
    }

    /**
     * @brief This method assigns a salary bonus to a teacher
     * @param salaryBonus salary bonus
     */
    public void setSalaryBonus(final int salaryBonus) {
        this.salaryBonus = salaryBonus;
    }

    /**
     * @brief This method returns the work location of a teacher
     * @return work location
     */
    public WorkLocation getWorkLocation() {
        return workLocation;
    }

    /**
     * @brief This method assigns a work location to a teacher
     * @param workLocation work location
     */
    public void setWorkLocation(final WorkLocation workLocation) {
        this.workLocation = workLocation;
    }

    /**
     * @brief This method returns the sex of a teacher
     * @return sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * @brief This method assigns a sex to a teacher
     * @param sex sex
     */
    public void setSex(final Sex sex) {
        this.sex = sex;
    }


    /**
     * @brief Change attributes to a string format
     * @return attributes in a string format
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", Teacher.class.getSimpleName() + "[", "]")
                .add("uid=" + uid)
                .add("name='" + name + "'")
                .add("dateOfBirth='" + dateOfBirth + "'")
                .add("contactNumbers=" + Arrays.toString(contactNumbers))
                .add("emergencyContacts=" + Arrays.toString(emergencyContacts))
                .add("address=" + address)
                .add("nationality=" + nationality)
                .add("subject=" + subject)
                .add("department=" + department)
                .add("manager=" + Arrays.toString(manager))
                .add("hireDate='" + hireDate + "'")
                .add("salaryAmount=" + salaryAmount)
                .add("salaryBonus=" + salaryBonus)
                .add("workLocation=" + workLocation)
                .add("sex=" + sex)
                .toString();
    }
}
