package com.ismaelmohamedbouh;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    private final Solution solution = new Solution();

    @ParameterizedTest
    @MethodSource("fournirCasDeTest")
    public void testCanFinish(int nombreDeCours, int[][] prerequis, boolean attendu) {
        assertEquals(attendu, solution.canFinish(nombreDeCours, prerequis));
    }

    private static Stream<Arguments> fournirCasDeTest() {
        return Stream.of(
            Arguments.of(2, new int[][]{{1, 0}}, true), // Cas simple, un cours dépend d'un autre
            Arguments.of(2, new int[][]{{1, 0}, {0, 1}}, false), // Cycle, impossible de terminer les cours
            Arguments.of(4, new int[][]{{1, 0}, {2, 1}, {3, 2}}, true), // Chaîne linéaire de dépendances
            Arguments.of(3, new int[][]{{1, 0}, {2, 1}, {2, 0}}, true), // Plusieurs prérequis pour un cours
            Arguments.of(3, new int[][]{{1, 0}, {2, 1}, {1, 2}}, false), // Cycle indirect
            Arguments.of(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}}, true), // Un cours requis par plusieurs autres
            Arguments.of(1, new int[][]{}, true), // Un seul cours, aucun prérequis
            Arguments.of(5, new int[][]{}, true), // Plusieurs cours, aucun prérequis
            Arguments.of(3, new int[][]{{0, 1}, {0, 2}, {1, 2}}, true), // Pré-requis convergents vers un seul cours
            Arguments.of(6, new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}, {5, 4}, {3, 5}}, false) // Cycle complexe
        );
    }
}