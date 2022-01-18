<!--
Copyright 2018-2021 Crown Copyright

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->
 
# Synthetic Data Generator

This synthetic data generator fakes classic human resources data about employees and teachers.
The data structure contains the types of complex data structures that can make 
computation expensive or difficult to truly test your platform.

This repository provides the code to generate as many Employee records as you want, split over as many Avro files as you desire. You can also optionally define the number of parallel threads used to generate your data.

An `Employee` and a `Teacher` objects were created.

The `Employee`contains the following fields:
```
class Employee {
    UserId uid;
    String name;
    String dateOfBirth;
    PhoneNumber[] contactNumbers;
    EmergencyContact[] emergencyContacts;
    Address address;
    BankDetails bankDetails;
    String taxCode;
    Nationality nationality;
    Manager[] manager;
    String hireDate;
    Grade grade;
    Department department;
    int salaryAmount;
    int salaryBonus;
    WorkLocation workLocation;
    Sex sex;
}
```

The `Teacher` object contains the following fields:
```
class Teacher{
    UserId uid;
    String name;
    String dateOfBirth;
    PhoneNumber[] contactNumbers;
    EmergencyContact[] emergencyContacts;
    Address address;
    Nationality nationality;
    Subject subject;
    Department department;
    Manager[] manager;
    String hireDate;
    int salaryAmount;
    int salaryBonus;
    WorkLocation workLocation;
    Sex sex;
}
```
The manager field is an array of manager, which could potentially be nested several layers deep, in the generated example manager is nested 3-5 layers deep.

## Get Started
To use the generator you will need to have installed (git, maven and JDK 11).  
To get started first clone this repo locally.

```bash
git clone https://github.com/alu0101221960/ProyectoLHD
```

Then cd into the synthetic-data-generator directory and build the codebase

```bash
mvn clean install
```

then to start the generator:

```bash
.createHRData.sh PATH EMPLOYEES FILES [THREADS]
```
where:
- PATH is the relative path to generate the files
- EMPLOYEES is the number of employee records to create
- FILES is the number of files to spread them over
- THREADS (optional) specifies the number of threads to use.

For example to generate 1,000,000 employee records, spread over 15 files, running the program with 4 threads, and writing the output files to /data/employee:

```bash
.createHRData.sh data/employee 1000000 15 4
```

## Print generated data
It is possible to print the generated data in a *csv* format


[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/summary/new_code?id=alu0101221960_ProyectoLHD)

[![CircleCI](https://circleci.com/gh/proyectolhd/proyectolhd.svg?style=svg?circle-token=4492874ff7474bfd6b66ec0b236e6fccfc996b25)](https://app.circleci.com/pipelines/github/alu0101221960/ProyectoLHD)
