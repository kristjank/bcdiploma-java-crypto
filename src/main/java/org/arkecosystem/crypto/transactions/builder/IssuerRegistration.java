package org.arkecosystem.crypto.transactions.builder;

import org.arkecosystem.crypto.enums.CoreTransactionTypes;

public class IssuerRegistration extends AbstractTransaction<IssuerRegistration> {

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public IssuerRegistration instance() {
        return this;
    }
}
