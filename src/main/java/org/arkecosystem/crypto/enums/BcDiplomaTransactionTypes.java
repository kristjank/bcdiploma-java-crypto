package org.arkecosystem.crypto.enums;

public enum BcDiplomaTransactionTypes {
    ISSUER_REGISTRATION(0);

    private final int value;

    BcDiplomaTransactionTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
