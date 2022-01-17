package uk.gov.gchq.syntheticdatagenerator.types;

import java.util.Random;

/**
 * @enum Subject
 * @brief This enum class lists a number of courses available at ULL
 */

public enum Subject {
    LABORATORIO_DE_DESARROLLO_Y_HERRAMIENTAS,
    INGENIERIA_LOGISTICA,
    VISION_POR_COMPUTADOR,
    ROBOTICA_COMPUTACIONAL,
    INTERFACES_INTELIGENTES,
    SISTEMAS_INTELIGENTES,
    COMPLEJIDAD_COMPUTACIONAL,
    SISTEMAS_EMPOTRADOS,
    ARQUITECTURAS_AVANZADAS_Y_DE_PROPOSITO_ESPECIFICO,
    SEGURIDAD_DE_SISTEMAS_INFORMATICOS,
    NORMATIVA_Y_REGULACION,
    SISTEMAS_DE_INFORMACION_CONTABLE,
    GESTION_DE_LA_INNOVACION,
    DESARROLLO_Y_MANTENIMENTO_DE_SISTEMAS_DE_INFORMACION,
    TECNOLOGIAS_DE_LA_INFORMACION_PARA_LAS_ORGANIZACIONES,
    SISTEMAS_Y_TECNOLOGIAS_WEB,
    GESTION_DEL_CONOCIMIENTO_EN_LAS_ORGANIZACIONES;

    /**
     * @brief This method generates subjects
     * @param random random value
     * @return subjects
     */
    public static Subject generate(final Random random) {
        return Subject.values()[random.nextInt(Subject.values().length)];
    }
}
