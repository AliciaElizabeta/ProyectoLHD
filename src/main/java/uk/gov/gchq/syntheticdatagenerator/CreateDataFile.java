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

package uk.gov.gchq.syntheticdatagenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.gchq.syntheticdatagenerator.serialise.AvroSerialiser;
import uk.gov.gchq.syntheticdatagenerator.types.Employee;
import uk.gov.gchq.syntheticdatagenerator.types.Teacher;
import uk.gov.gchq.syntheticdatagenerator.types.Manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import com.google.common.io.Files;

/**
 * @brief This class creates a data file
 * @details the output can be selected among and "avro" or "csv" file
 * @details to create the output file 6 different arguments need to be inserted
 */
public final class CreateDataFile implements Callable<Boolean> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateDataFile.class);
    // When a large number of employees are requested, print the progress as feedback that the process has not frozen
    private static final long PRINT_EVERY = 100_000L;

    private final long numberOfPeople;
    private final SecureRandom random;
    private final File outputFile;
    private final String ocupation;
    private boolean isCSVFile = false;

    /**
     * @brief This method creates a data file
     * @param numberOfPeople number of people that want to be created
     * @param seed seed to create the data file
     * @param outputFile output file
     * @param ocupation type of person that is going to be created: the selection will be among employees or teachers
     */
    public CreateDataFile(final long numberOfPeople, final int seed, final File outputFile, final String ocupation ) {
        this.numberOfPeople = numberOfPeople;
        this.random = new SecureRandom(longToBytes(seed));
        this.outputFile = outputFile;
        this.ocupation = ocupation.toUpperCase();
        if(getExtensionByGuava(outputFile).equals("csv")){isCSVFile = true;}
        else{isCSVFile = false;}
    }

    /**
     * @brief This method creates the output file and random data
     * @return if the operation was successfully completed or not
     */
    public Boolean call() {
        if (!outputFile.getParentFile().exists()) {
            boolean mkdirSuccess = outputFile.getParentFile().mkdirs();
            if (!mkdirSuccess) {
                LOGGER.warn("Failed to create parent directory {}", outputFile.getParent());
            }
        }
        try (OutputStream out = new FileOutputStream(outputFile)) {
            if(ocupation.equals("E")){
                AvroSerialiser<Employee> employeeAvroSerialiser = new AvroSerialiser<>(Employee.class);

                // Need at least one Employee
                Employee firstEmployee = Employee.generate(random);
                Manager[] managers = firstEmployee.getManager();
                managers[0].setUid("Bob");
                firstEmployee.setManager(managers);

                // Create more employees if needed
                Stream<Employee> employeeStream = Stream.of(firstEmployee);
                if (numberOfPeople > 1) {
                    if(isCSVFile == false){
                        employeeStream = Stream.concat(employeeStream, generateStreamOfEmployees());
                    }
                    else{
                        Stream aux = Stream.of(";");
                        employeeStream = Stream.concat(employeeStream, aux);
                        employeeStream = Stream.concat(employeeStream, generateStreamOfEmployees());
                    }
                }
                // Serialise stream to output
                employeeAvroSerialiser.serialise(employeeStream, out);
                return true;

            }
            else if(ocupation.equals("T")){
                AvroSerialiser<Teacher> teacherAvroSerialiser = new AvroSerialiser<>(Teacher.class);

                // Need at least one Employee
                Teacher firstTeacher = Teacher.generate(random);
                Manager[] managers = firstTeacher.getManager();
                managers[0].setUid("Peter");
                firstTeacher.setManager(managers);

                // Create more teachers if needed
                Stream<Teacher> teacherStream = Stream.of(firstTeacher);
                if (numberOfPeople > 1) {
                    if(isCSVFile == false){
                        teacherStream = Stream.concat(teacherStream, generateStreamOfTeacher());
                    }
                    else{
                        Stream aux = Stream.of(";");
                        teacherStream = Stream.concat(teacherStream, aux);
                        teacherStream = Stream.concat(teacherStream, generateStreamOfTeacher());
                    }
                }

                // Serialise stream to output
                teacherAvroSerialiser.serialise(teacherStream, out);
                return true;
            }
            
            
        } catch (IOException ex) {
            LOGGER.error("IOException when serialising Employee or Teacher to Avro", ex);
            return false;
        }
        return false;
    }

    /**
     * @brief This method creates a stream of employees
     * @return the list of generated employee data
     */
    private Stream<Employee> generateStreamOfEmployees() {
        LOGGER.info("Generating {} employees", numberOfPeople);
        final AtomicLong counter = new AtomicLong(0);
        Stream<Employee> employeeStream = Stream.generate(() -> {
            if (counter.incrementAndGet() % PRINT_EVERY == 0) {
                LOGGER.info("Processing {} of {}", counter.get(), numberOfPeople);
            }
            return Employee.generate(random);
        });
        // Excluding the one employee we had to generate above
        return employeeStream.limit(numberOfPeople - 1);
    }

    /**
     * @brief This method creates a stream of teachers
     * @return the list of generated teacher data
     */
    private Stream<Teacher> generateStreamOfTeacher() {
        LOGGER.info("Generating {} teachers", numberOfPeople);
        final AtomicLong counter = new AtomicLong(0);
        Stream<Teacher> teacherStream = Stream.generate(() -> {
            if (counter.incrementAndGet() % PRINT_EVERY == 0) {
                LOGGER.info("Processing {} of {}", counter.get(), numberOfPeople);
            }
            return Teacher.generate(random);
        });
        // Excluding the one employee we had to generate above
        return teacherStream.limit(numberOfPeople - 1);
    }


    /**
     * @brief This method converts a long value to a byte value
     * @param x long value
     * @return long value in bytes
     */
    private byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return buffer.array();
    }

    /**
     * @brief This method obtains the file extension
     * @param filename file name
     * @return file extension
     */
    public String getExtensionByGuava(File filename) {
        String f = filename.getName();
        return Files.getFileExtension(f);
    }
}
