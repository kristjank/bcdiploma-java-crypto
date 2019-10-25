package org.arkecosystem.crypto;

import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.client.Connection;
import org.arkecosystem.crypto.configuration.Network;
import org.arkecosystem.crypto.identities.Address;
import org.arkecosystem.crypto.networks.Testnet;
import org.arkecosystem.crypto.transactions.IssuerRegistrationAsset;
import org.arkecosystem.crypto.transactions.Transaction;
import org.arkecosystem.crypto.transactions.builder.IssuerRegistration;
import org.arkecosystem.crypto.transactions.builder.Transfer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Example {
    public static Transaction CreateDemoTransferTransaction(int amount, String recipientAddress, String passphrase1, long nonce) {
        Transaction actual = new Transfer()
            .recipient(recipientAddress)
            .nonce(nonce)
            .amount(amount)
            .sign(passphrase1)
            .transaction;

        return actual;
    }

    public static Transaction CreateDemoIssuerRegistrationTransaction(IssuerRegistrationAsset issuerRegistrationAsset, String passphrase, long nonce){
        Transaction actual = new IssuerRegistration()
            .issuerRegistration(issuerRegistrationAsset)
            .nonce(nonce)
            .sign(passphrase)
            .transaction;

        return actual;
    }

    public static long getNonce(Connection connection, String sended) throws IOException {
        // wait to be forged
        LinkedTreeMap<String, Object> actual = connection.api().wallets.sentTransactions(sended);
        ArrayList<Object> data = (ArrayList<Object>) actual.get("data");
        return data.size() + 1;
    }

    public static void main(String[] args) throws IOException {
        Network.set(new Testnet());
        HashMap<String, Object> map = new HashMap<>();
         map.put("host", "http://127.0.0.1:4003/api/"); // network settings are autoc-configured from the node
//        map.put("host", "https://dexplorer.ark.io:8443/api/");
        map.put("content-type","application/json");

        Connection connection = new Connection(map);

        // adding transaction to payload, payload is an array of transactions
        ArrayList<HashMap> payload = new ArrayList<>();
//        Transaction transfer1 = CreateDemoTransferTransaction(100, Address.fromPassphrase("clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire"), "clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire",5);
//        payload.add(transfer1.toHashMap());
//        System.out.println(transfer1.toJson());

        System.out.println(Address.fromPassphrase("clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire"));
        getNonce(connection,"ANBkoGqWeTSiaEVgVzSKZd3jS7UWzv9PSo");
        Transaction issuer = CreateDemoIssuerRegistrationTransaction(
            new IssuerRegistrationAsset(
                "To be further explained",
                "To be further explained",
                "To be further explained",
                "To be further explained",
                "To be further explained",
                "To be further explained"),
            "clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire",
            getNonce(connection, "ANBkoGqWeTSiaEVgVzSKZd3jS7UWzv9PSo")
        );
        payload.add(issuer.toHashMap());
        System.out.println(issuer.toJson());

        // posting transactions to the connected node as specified in the connection above
        LinkedTreeMap<String, Object> postResponse = connection.api().transactions.create(payload);

        payload.clear();
        System.out.println(postResponse);
    }
}
