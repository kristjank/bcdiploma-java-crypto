package org.arkecosystem.crypto.transactions.builder;

import org.arkecosystem.crypto.enums.BcDiplomaTransactionType;
import org.arkecosystem.crypto.enums.TransactionTypeGroup;
import org.arkecosystem.crypto.transactions.IssuerRegistrationAsset;

public class IssuerRegistration extends AbstractTransaction<IssuerRegistration> {

    public IssuerRegistration() {
        super();
        this.typeGroup(TransactionTypeGroup.BC_DIPLOMA_TYPE_GROUP.getValue());
        this.fee(1000000);
    }

    public IssuerRegistration issuerRegistration(IssuerRegistrationAsset issuerRegistrationAsset) {
        this.transaction.asset.issuerRegistration = issuerRegistrationAsset;
        return this;
    }

    @Override
    public int getType() {
        return BcDiplomaTransactionType.ISSUER_REGISTRATION.getValue();
    }

    @Override
    public IssuerRegistration instance() {
        return this;
    }
}
