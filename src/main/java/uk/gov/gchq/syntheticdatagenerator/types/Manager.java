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

import java.util.Arrays;
import java.util.Random;
import java.util.StringJoiner;
import java.io.Serializable;


import static java.util.Objects.requireNonNull;

/**
 * @class Manager
 * @brief This class creates random values for a manager
 * @details When creating a Manager entity it is possible to create up to 3 levels of managers
 */
public class Manager implements Serializable {
    private String uid;
    private Manager[] managers;
    private String managerType;

    /**
     * @brief This method allows to create a number of different levels of managers
     * @param random random value
     * @param chain level depth of managers
     * @return different managers levels
     */
    public static Manager[] generateMany(final Random random, final int chain) {
        return new Manager[]{
                generateRecursive(random, chain, "Human Resources Manager"),
                generateRecursive(random, chain, "Department Manager"),
                generateRecursive(random, chain, "Career Manager")
        };
    }

    /**
     * @brief This method evaluates the different levels of managers defined
     * @param random random value
     * @param chain level depth of managers
     * @param managerType type of manager
     * @return generated managers
     */
    public static Manager generateRecursive(final Random random, final int chain, final String managerType) {
        Manager manager = Manager.generate(random, managerType);
        if (chain <= 1) {
            manager.setManager(null);
        } else {
            manager.setManager(Manager.generateMany(random, chain - 1));
        }
        return manager;
    }

    /**
     * @brief This method generates a new manager
     * @param random random value
     * @param managerType type of manager
     * @return generated manager
     */
    public static Manager generate(final Random random, final String managerType) {
        Manager manager = new Manager();
        manager.setUid(Employee.generateUID(random));
        manager.setManagerType(managerType);

        return manager;
    }

    /**
     * @brief This method returns the identifier
     * @return identifier
     */
    public String getUid() {
        return uid;
    }

    /**
     * @brief This method assigns an identifiers
     * @param uid identifier
     */
    public void setUid(final String uid) {
        requireNonNull(uid);
        this.uid = uid;
    }

    /**
     * @brief This method returns the manager type
     * @return manager type
     */
    public String getManagerType() {
        return managerType;
    }

    /**
     * @brief This method assigns a manager type
     * @param managerType manager type
     */
    public void setManagerType(final String managerType) {
        requireNonNull(managerType);
        this.managerType = managerType;
    }

    /**
     * @brief This method returns the list of managers
     * @return list of managers
     */
    public Manager[] getManager() {
        if (null == managers) {
            return new Manager[0];
        } else {
            return managers.clone();
        }
    }

    /**
     * @brief This method assigns a list of managers
     * @param managers list of managers
     */
    public void setManager(final Manager[] managers) {
        if (null == managers) {
            this.managers = null;
        } else {
            this.managers = managers.clone();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Manager.class.getSimpleName() + "[", "]")
                .add("uid=" + uid)
                .add("manager=" + Arrays.toString(managers))
                .add("managerType='" + managerType + "'")
                .toString();
    }
}
