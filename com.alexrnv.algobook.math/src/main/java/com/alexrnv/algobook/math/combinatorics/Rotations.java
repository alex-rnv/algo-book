package com.alexrnv.algobook.math.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rotations {

    public static List<byte[]> generateAllRotations(byte[] array) {
        List<byte[]> result = new ArrayList<>(array.length);
        result.add(array);

        byte[] aux = new byte[array.length];

        for (int i=1; i<array.length; i++) {
            for (int j=0; j<array.length; j++) {
                int k = (i + j) % array.length;
                aux[k] = array[j];
            }
            result.add(Arrays.copyOf(aux, aux.length));
        }
        return result;
    }
}
