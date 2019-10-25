package org.arkecosystem.crypto.transactions.builder;

import org.arkecosystem.crypto.transactions.IssuerRegistrationAsset;

public class IssuerRegistration extends AbstractTransaction<IssuerRegistration> {

    public IssuerRegistration() {
        super();
        this.typeGroup(1002);
        this.fee(1000000);
    }

    public IssuerRegistration issuerRegistration(IssuerRegistrationAsset issuerRegistrationAsset) {
        this.transaction.asset.issuerRegistration = issuerRegistrationAsset;
        return this;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public IssuerRegistration instance() {
        return this;
    }
}
