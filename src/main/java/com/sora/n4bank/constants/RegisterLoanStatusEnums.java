package com.sora.n4bank.constants;

public enum RegisterLoanStatusEnums {
    ALL(0),
    CREATE(1),
    WAITINGAPPROVE(2),
    APPROVE(3),
    DISBURSEMENT(4),
    RETURNMONEY(5);

    private int value;

    private RegisterLoanStatusEnums(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
