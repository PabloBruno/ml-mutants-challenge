package com.mlmutants.mlmutants.enums;

public enum DnaTypeEnum {
    MUTANT('M'),
    HUMAN('H');

    private final char value;

    DnaTypeEnum(char value)
    {
        this.value=value;
    }

    public char getValue(){
        return this.value;
    }
}
