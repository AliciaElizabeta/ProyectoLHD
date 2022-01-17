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

import java.util.Random;

/**
 * @enum Department
 * @brief This enum class lists all the different faculties at ULL University
 */
public enum Department {
    EDUCATION,
    HEALTH_SCIENCES,
    HUMANITIES,
    BUSINESS,
    TOURISM,
    ECONOMICS,
    LAW,
    POLITICAL_SCIENCES,
    SOCIAL_SCIENCES,
    COMMUNICATION,
    SCIENCES,
    HIGHER_POLYTECHNIC_SCHOOL_OF_ENGINEERING,
    HIGHER_POLYTECHNIC_SCHOOL_OF_ENGINEERING_AND_TECHNOLOGY,
    SCHOOL_DOCTORAL_AND_GRADUATES_STUDIES;

    /**
     * @brief This method generates a department
     * @param random random value
     * @return department
     */
    public static Department generate(final Random random) {
        return Department.values()[random.nextInt(Department.values().length)];
    }
}
