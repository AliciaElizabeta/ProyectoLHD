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
 * @brief This class generates random values for the entity Teacher
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
        teacher.setAddress(Address.generate(faker, random));
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

    public static String generateUID(final Random random) {
        return String.valueOf(random.nextInt(Integer.MAX_VALUE));
    }

    public String getUid() {
        return uid;
    }

    public void setUid(final String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PhoneNumber[] getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(final PhoneNumber[] contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public EmergencyContact[] getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(final EmergencyContact[] emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(final Nationality nationality) {
        this.nationality = nationality;
    }

    public Manager[] getManager() {
        return manager;
    }


    public Subject getSubject() { return subject; }

    public void setSubject(final Subject subject) { this.subject = subject; }

    public Department getDepartment() { return department;}

    public void setDepartment(final Department department) {this.department = department;}

    public void setManager(final Manager[] manager) {
        this.manager = manager;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(final String hireDate) {
        this.hireDate = hireDate;
    }

    public int getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(final int salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public int getSalaryBonus() {
        return salaryBonus;
    }

    public void setSalaryBonus(final int salaryBonus) {
        this.salaryBonus = salaryBonus;
    }

    public WorkLocation getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(final WorkLocation workLocation) {
        this.workLocation = workLocation;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

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
