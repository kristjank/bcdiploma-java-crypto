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
        byte[] name = this.transaction.asset.issuerRegistration.name.getBytes();
        byte[] legalReference = this.transaction.asset.issuerRegistration.legalReference.getBytes();
        byte[] intentDeclaration =
                this.transaction.asset.issuerRegistration.intentDeclaration.getBytes();
        byte[] host = this.transaction.asset.issuerRegistration.host.getBytes();
        byte[] KYBHash = this.transaction.asset.issuerRegistration.KYBHash.getBytes();

        this.buffer.put((byte) address.length);
        this.buffer.put(address);
        this.buffer.put((byte) name.length);
        this.buffer.put(name);
        this.buffer.put((byte) legalReference.length);
        this.buffer.put(legalReference);
        this.buffer.put((byte) intentDeclaration.length);
        this.buffer.put(intentDeclaration);
        this.buffer.put((byte) host.length);
        this.buffer.put(host);
        this.buffer.put((byte) KYBHash.length);
        this.buffer.put(KYBHash);
    }
}
