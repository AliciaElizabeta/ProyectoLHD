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
 * @class Employee
 * @brief This class generates random values for the entity Employee
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int MIN_MANGERS_TREE_HEIGHT = 2;
    private static final int EXTRA_MANAGERS_TREE_HEIGHT_RANGE = 3;
    private static final int MIN_SALARY = 20_000;
    private static final int EXTRA_SALARY_RANGE = 100_000;
    private static final int SALARY_BONUS_RANGE = 10_000;
    private static final String TAX_CODE = "11500L";

    private String uid;
    private String name;
    private String dateOfBirth;
    private PhoneNumber[] contactNumbers;
    private EmergencyContact[] emergencyContacts;
    private Address address;
    private BankDetails bankDetails;
    private String taxCode;
    private Nationality nationality;
    private Manager[] manager;
    private String hireDate;
    private Grade grade;
    private Department department;
    private int salaryAmount;
    private int salaryBonus;
    private WorkLocation workLocation;
    private Sex sex;

    /**
     * @class Employee
     * @brief This method generates random values for an employee
     * @param random random value
     * @return generated employees
     */
    public static Employee generate(final Random random) {
        Employee employee = new Employee();
        Faker faker = ThreadLocalFaker.getFaker(random);
        employee.setUid(generateUID(random));
        Name employeeName = faker.name();
        employee.setName(employeeName.firstName() + " " + employeeName.lastName()); // we are storing name as a string not a Name
        employee.setDateOfBirth(DateHelper.generateDateOfBirth(random));
        employee.setContactNumbers(PhoneNumber.generateMany(random));
        employee.setEmergencyContacts(EmergencyContact.generateMany(faker, random));
        employee.setAddress(Address.generate(faker, random));
        employee.setBankDetails(BankDetails.generate(random));
        employee.setTaxCode(generateTaxCode());
        employee.setNationality(Nationality.generate(random));
        employee.setManager(Manager.generateMany(random, MIN_MANGERS_TREE_HEIGHT + random.nextInt(EXTRA_MANAGERS_TREE_HEIGHT_RANGE)));
        employee.setHireDate(DateHelper.generateHireDate(employee.dateOfBirth, random));
        employee.setGrade(Grade.generate(random));
        employee.setDepartment(Department.generate(random));
        employee.setSalaryAmount(MIN_SALARY + random.nextInt(EXTRA_SALARY_RANGE));
        employee.setSalaryBonus(random.nextInt(SALARY_BONUS_RANGE));
        employee.setWorkLocation(WorkLocation.generate(faker, random));
        employee.setSex(Sex.generate(random));

        return employee;
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
     * This method generates a tax code
     * @return tax code
     */
    private static String generateTaxCode() {
        return TAX_CODE;
    }

    /**
     * @brief This method returns the identifier of an employee
     * @return identifier
     */
    public String getUid() {
        return uid;
    }

    /**
     * @brief This method assigns the identifier of an employee
     * @param uid identifier
     */
    public void setUid(final String uid) {
        this.uid = uid;
    }

    /**
     * @brief This method returns the name of an employee
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @brief This method assigns a name to an employee
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @brief This method returns the date of birth of an employee
     * @return date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @brief This method assigns a date of birth to an employee
     * @param dateOfBirth date of birth
     */
    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @brief This method returns list of contact numbers of an employee
     * @return list of contact numbers
     */
    public PhoneNumber[] getContactNumbers() {
        return contactNumbers;
    }

    /**
     * @brief This method assigns a list of contact number to an employee
     * @param contactNumbers list of contact numbers
     */
    public void setContactNumbers(final PhoneNumber[] contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    /**
     * @brief This method returns the list of emergency contacts of an employee
     * @return list of emergency contacts
     */
    public EmergencyContact[] getEmergencyContacts() {
        return emergencyContacts;
    }

    /**
     * @brief This method assigns a list of emergency contacts to an employee
     * @param emergencyContacts list of emergency contacts
     */
    public void setEmergencyContacts(final EmergencyContact[] emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    /**
     * @brief This method returns the address of an employee
     * @return address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @brief This method assigns an address to an employee
     * @param address address
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    /**
     * @brief This method return the bank details of an employee
     * @return bank details
     */
    public BankDetails getBankDetails() {
        return bankDetails;
    }

    /**
     * @brief This method assigns the bank details to an employee
     * @param bankDetails bank details
     */
    public void setBankDetails(final BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    /**
     * @brief This method returns the tax code to an employee
     * @return tax code
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * @brief This method assigns the tax code to an employee
     * @param taxCode tax code
     */
    public void setTaxCode(final String taxCode) {
        this.taxCode = taxCode;
    }

    /**
     * @brief This method returns the nationality of an employee
     * @return nationality
     */
    public Nationality getNationality() {
        return nationality;
    }

    /**
     * @brief This method assigns a nationality to an employee
     * @param nationality nationality
     */
    public void setNationality(final Nationality nationality) {
        this.nationality = nationality;
    }

    /**
     * @brief This method returns the list of managers of an employee
     * @return list of managers
     */
    public Manager[] getManager() {
        return manager;
    }

    /**
     * @brief This method assigns a list of managers to an employee
     * @param manager list of managers
     */
    public void setManager(final Manager[] manager) {
        this.manager = manager;
    }

    /**
     * @brief This method returns the hire date of an employee
     * @return hire date
     */
    public String getHireDate() {
        return hireDate;
    }

    /**
     * @brief This method assigns a hire date to an employee
     * @param hireDate hire date
     */
    public void setHireDate(final String hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * @brief This method returns the grade of an employee
     * @return grade
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * @brief This method assigns a grade to an employee
     * @param grade grade
     */
    public void setGrade(final Grade grade) {
        this.grade = grade;
    }

    /**
     * @brief This method returns the department of an employee
     * @return department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @brief This method assigns a department to an employee
     * @param department department
     */
    public void setDepartment(final Department department) {
        this.department = department;
    }

    /**
     * @brief This method returns the salary amount of an employee
     * @return salary amount
     */
    public int getSalaryAmount() {
        return salaryAmount;
    }

    /**
     * @brief This method assigns a salary amount to an employee
     * @param salaryAmount salary amount
     */
    public void setSalaryAmount(final int salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    /**
     * @brief This method returns the salary bonus of an employee
     * @return salary bonus
     */
    public int getSalaryBonus() {
        return salaryBonus;
    }

    /**
     * @brief This method assigns a salary bonus to an employee
     * @param salaryBonus salary bonus
     */
    public void setSalaryBonus(final int salaryBonus) {
        this.salaryBonus = salaryBonus;
    }

    /**
     * @brief This method returns the work location of an employee
     * @return work location
     */
    public WorkLocation getWorkLocation() {
        return workLocation;
    }

    /**
     * @brief This method assigns a work location to an employee
     * @param workLocation work location
     */
    public void setWorkLocation(final WorkLocation workLocation) {
        this.workLocation = workLocation;
    }

    /**
     * @brief This method returns the sex of an employee
     * @return sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * @brief This method assigns a sex to an employee
     * @param sex sex
     */
    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("uid=" + uid)
                .add("name='" + name + "'")
                .add("dateOfBirth='" + dateOfBirth + "'")
                .add("contactNumbers=" + Arrays.toString(contactNumbers))
                .add("emergencyContacts=" + Arrays.toString(emergencyContacts))
                .add("address=" + address)
                .add("bankDetails=" + bankDetails)
                .add("taxCode='" + taxCode + "'")
                .add("nationality=" + nationality)
                .add("manager=" + Arrays.toString(manager))
                .add("hireDate='" + hireDate + "'")
                .add("grade=" + grade)
                .add("department=" + department)
                .add("salaryAmount=" + salaryAmount)
                .add("salaryBonus=" + salaryBonus)
                .add("workLocation=" + workLocation)
                .add("sex=" + sex)
                .toString();
    }
}
