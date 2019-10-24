package org.arkecosystem.crypto.transactions.deserializers;

import org.arkecosystem.crypto.transactions.Transaction;

import java.nio.ByteBuffer;

public class IssuerRegistration extends AbstractDeserializer{
    public IssuerRegistration(String serialized, ByteBuffer buffer, Transaction transaction) {
        super(serialized, buffer, transaction);
    }

    @Override
    public void deserialize(int assetOffset) {

    }
}
