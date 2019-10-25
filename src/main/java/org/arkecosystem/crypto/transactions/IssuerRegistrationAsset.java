package org.arkecosystem.crypto.transactions;

public class IssuerRegistrationAsset {
    public String address;
    public String name;
    public String legalReference;
    public String intentDeclaration;
    public String host;
    public String KYBHash;

    public IssuerRegistrationAsset() {}

    public IssuerRegistrationAsset(
            String address,
            String name,
            String legalReference,
            String intentDeclaration,
            String host,
            String KYBHash) {
        this.address = address;
        this.name = name;
        this.legalReference = legalReference;
        this.intentDeclaration = intentDeclaration;
        this.host = host;
        this.KYBHash = KYBHash;
    }
}
