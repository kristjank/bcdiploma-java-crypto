package org.arkecosystem.crypto.transactions.deserializers;

import java.nio.ByteBuffer;
import org.arkecosystem.crypto.transactions.Transaction;

public class IssuerRegistration extends AbstractDeserializer {
    public IssuerRegistration(String serialized, ByteBuffer buffer, Transaction transaction) {
        super(serialized, buffer, transaction);
    }

    @Override
    public void deserialize(int assetOffset) {
        this.buffer.position(assetOffset / 2);

        int addressLength = this.buffer.get();
        byte[] address = new byte[addressLength];
        this.transaction.asset.issuerRegistration.address = new String(address);

        int nameLength = this.buffer.get();
        byte[] name = new byte[nameLength];
        this.transaction.asset.issuerRegistration.name = new String(name);

        int legalReferenceLength = this.buffer.get();
        byte[] legalReference = new byte[legalReferenceLength];
        this.transaction.asset.issuerRegistration.legalReference = new String(legalReference);

        int intentDeclarationLength = this.buffer.get();
        byte[] intentDeclaration = new byte[intentDeclarationLength];
        this.transaction.asset.issuerRegistration.intentDeclaration = new String(intentDeclaration);

        int hostLength = this.buffer.get();
        byte[] host = new byte[hostLength];
        this.transaction.asset.issuerRegistration.host = new String(host);

        int KYBHashLength = this.buffer.get();
        byte[] KYBHash = new byte[KYBHashLength];
        this.transaction.asset.issuerRegistration.KYBHash = new String(KYBHash);
    }
}
