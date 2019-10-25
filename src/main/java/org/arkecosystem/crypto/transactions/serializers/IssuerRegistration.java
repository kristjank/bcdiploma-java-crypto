package org.arkecosystem.crypto.transactions.serializers;

import java.nio.ByteBuffer;
import org.arkecosystem.crypto.transactions.Transaction;

public class IssuerRegistration extends AbstractSerializer {
    public IssuerRegistration(ByteBuffer buffer, Transaction transaction) {
        super(buffer, transaction);
    }

    @Override
    public void serialize() {
        byte[] address = this.transaction.asset.issuerRegistration.address.getBytes();
        this.buffer.put((byte) address.length);
        this.buffer.put(address);

        byte[] name = this.transaction.asset.issuerRegistration.name.getBytes();
        this.buffer.put((byte) name.length);
        this.buffer.put(name);

        byte[] legalReference = this.transaction.asset.issuerRegistration.legalReference.getBytes();
        this.buffer.put((byte) legalReference.length);
        this.buffer.put(legalReference);

        byte[] intentDeclaration =
            this.transaction.asset.issuerRegistration.intentDeclaration.getBytes();
        this.buffer.put((byte) intentDeclaration.length);
        this.buffer.put(intentDeclaration);

        byte[] host = this.transaction.asset.issuerRegistration.host.getBytes();
        this.buffer.put((byte) host.length);
        this.buffer.put(host);

        byte[] KYBHash = this.transaction.asset.issuerRegistration.KYBHash.getBytes();
        this.buffer.put((byte) KYBHash.length);
        this.buffer.put(KYBHash);
    }
}
