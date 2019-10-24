package org.arkecosystem.crypto.transactions.serializers;

import org.arkecosystem.crypto.transactions.Transaction;

import java.nio.ByteBuffer;

public class IssuerRegistration extends AbstractSerializer{
    public IssuerRegistration(ByteBuffer buffer, Transaction transaction) {
        super(buffer, transaction);
    }

    @Override
    public void serialize() {

    }
}
