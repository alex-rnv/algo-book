package com.alexrnv.algobook.cs.sparsetables;

public class RMQTable {

    public static void main(String[] args) {
        RMQTable t = new RMQTable(new int[]{2,1,3,2,2,4,1});
        System.out.println(t.min(2,4));
        System.out.println(t.min(5,5));
        System.out.println(t.min(0,6));
        System.out.println(t.min(1,5));
        System.out.println(t.min(2,5));
    }

    private final int[][] table;
    private final int[] log;

    protected RMQTable(int[] array) {
        int N = array.length;
        int[] log = buildLogTable(N);

        int K = log[N] + 1;
        int[][] table = new int[K][];
        int n = N;
        table[0] = new int[n];
        System.arraycopy(array, 0, table[0], 0, n);

        for (int j=1; j<K; j++) {
            int pow2j = 1 << (j-1);
            n -= pow2j;
            table[j] = new int[n];
            for (int i=0; i + (1<<j) <= N; i++) {
                table[j][i] = Math.min(table[j-1][i], table[j-1][i + pow2j]);
            }
        }
        this.table = table;
        this.log = log;
    }

    private static int[] buildLogTable(int N) {
        int[] log = new int[N+1];
        log[1] = 0;
        for (int i=2; i<=N; i++) {
            log[i] = log[i/2] + 1;
        }
        return log;
    }

    public int min(int L, int R) {
        int j = log[R - L + 1];
        return Math.min(table[j][L], table[j][R - (1<<j) + 1]);
    }
}
