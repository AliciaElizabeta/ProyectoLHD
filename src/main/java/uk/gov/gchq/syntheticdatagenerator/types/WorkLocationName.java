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
 * @enum Work Location Name
 * @brief This enum class lists possible work locations
 */
public enum WorkLocationName {
    MADRID,
    BARCELONA,
    SEVILLA,
    VALENCIA,
    ALICANTE,
    MURCIA,
    BILBAO,
    ZARAGOZA,
    MALAGA,
    CORDOBA,
    VALLADOLID,
    VIGO,
    GIJON,
    GRANADA,
    CADIZ,
    VITORIA,
    JEREZ_DE_LA_FRONTERA,
    PAMPLONA,
    SAN_SEBASTIAN,
    PALMA_DE_MALLORCA,
    ALMERIA,
    GETAFE,
    BURGOS,
    ALBACETE,
    SANTANDER,
    SALAMANCA,
    MARBELLA,
    HUELVA,
    TARRAGONA,
    TOLEDO,
    LAS_PALMAS_DE_GRAN_CANARIA,
    SAN_SEBASTIAN_DE_LA_GOMERA,
    PUERTO_DEL_ROSARIO,
    ARRECIFE,
    ADEJE,
    PUERTO_DE_LA_CRUZ,
    SANTA_CRUZ_DE_TENERIFE,
    SAN_CRISTOBAL_DE_LA_LAGUNA;


    /**
     * @brief This method generates a Work Location
     * @param random random value
     * @return work location name
     */
    public static WorkLocationName generate(final Random random) {
        return WorkLocationName.values()[random.nextInt(WorkLocationName.values().length)];
    }
}


