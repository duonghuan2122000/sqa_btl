package com.sora.n4bank.constants;

public enum RegisterLoanPaperTypeEnum {
    DEFAULT(0),
    RED_NOTE(1);

    private int value;

    private RegisterLoanPaperTypeEnum(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
