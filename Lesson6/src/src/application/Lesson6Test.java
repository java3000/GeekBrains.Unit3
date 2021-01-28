package src.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static src.application.Lesson6.hasOneOrFour;
import static src.application.Lesson6.trimFroMLastFour;

class Lesson6Test {

    @ParameterizedTest
    @NullAndEmptySource
    void trimFroMLastFourException(int[] values) {
        assertThrows(RuntimeException.class, () -> trimFroMLastFour(values));
    }

    @Test
    void trimFroMLastFourNoFour() {
        assertThrows(RuntimeException.class, () -> trimFroMLastFour(new int[]{1, 3}));
    }

    @Test
    //грязно проверяем по значениям.
    void trimFroMLastFourGood() {
        int[] array = new int[] {1, 2, 4, 4, 2, 3, 4, 1, 7 };
        int[] result = new int[] {1, 7 };
        Assertions.assertEquals(result[0], trimFroMLastFour(array)[0]);
    }

    @Test
    void hasOneOrFourGood() {
        int[] arr = new int[] { 1, 1, 1, 4, 4, 1, 4, 4 };
        Assertions.assertTrue(hasOneOrFour(arr));
    }

    @Test
    void hasOneOrFourBad() {
        int[] arr = new int[] { 1, 1, 1, 1, 1, 1 };
        Assertions.assertFalse(hasOneOrFour(arr));
    }
}