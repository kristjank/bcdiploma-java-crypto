package org.arkecosystem.crypto.transactions.deserializers;

import java.nio.ByteBuffer;
import org.arkecosystem.crypto.transactions.Transaction;

public class DelegateRegistration extends AbstractDeserializer {
    public DelegateRegistration(String serialized, ByteBuffer buffer, Transaction transaction) {
        super(serialized, buffer, transaction);
    }

    @Override
    public void deserialize(int assetOffset) {
        this.buffer.position(assetOffset / 2);

        int usernameLength = this.buffer.get() & 0xff;

        byte[] username = new byte[usernameLength];
        this.buffer.get(username);

        transaction.asset.delegate.username = new String(username);

        this.transaction.parseSignatures(this.serialized, assetOffset + (usernameLength + 1) * 2);
    }
}
