package org.arkecosystem.crypto.enums;

public enum  BcDiplomaTransactionType {
    ISSUER_REGISTRATION(0);

    private final int value;

    BcDiplomaTransactionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
