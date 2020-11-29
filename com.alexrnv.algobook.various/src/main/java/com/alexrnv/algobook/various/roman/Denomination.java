package com.alexrnv.algobook.various.roman;

public enum Denomination {
    I('I', 1),
    V('V', 5),
    X('X', 10),
    L('L', 50),
    C('C', 100),
    D('D', 500),
    M('M', 1000);

    public final char value;
    public final int number;

    Denomination(char value, int number) {
        this.value = value;
        this.number = number;
    }

    public static Denomination fromRoman(char c) {
        for (Denomination rd : values()) {
            if (c == rd.value) {
                return rd;
            }
        }
        return null;
    }
}
