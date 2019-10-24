package org.arkecosystem.crypto;

import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.client.Connection;
import org.arkecosystem.crypto.configuration.Network;
import org.arkecosystem.crypto.identities.Address;
import org.arkecosystem.crypto.networks.Testnet;
import org.arkecosystem.crypto.transactions.Transaction;
import org.arkecosystem.crypto.transactions.builder.Transfer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Example {
    public static Transaction CreateDemoTransaction(int amount, String recipientAddress, String passphrase1) {
        Transaction actual = new Transfer()
            .recipient(recipientAddress)
            .nonce(3)
            .amount(amount)
            .sign(passphrase1)
            .transaction;

        return actual;
    }

    public static void main(String[] args) throws IOException {
        Network.set(new Testnet());
        HashMap<String, Object> map = new HashMap<>();
         map.put("host", "http://127.0.0.1:4003/api/"); // network settings are autoc-configured from the node
//        map.put("host", "https://dexplorer.ark.io:8443/api/");
        map.put("content-type","application/json");

        Connection connection2 = new Connection(map);

        // testing blocks endpoint // find block with height 545774
        // LinkedTreeMap<String, Object> actual = connection2.api().peers.all();
        // System.out.println(actual);

        // adding transaction to payload, payload is an array of transactions
        ArrayList<HashMap> payload = new ArrayList<>();
        Transaction transfer1 = CreateDemoTransaction(100, Address.fromPassphrase("clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire"), "clay harbor enemy utility margin pretty hub comic piece aerobic umbrella acquire");
        payload.add(transfer1.toHashMap());
        System.out.println(transfer1.toJson());




        // posting transactions to the connected node as specified in the connection above
        LinkedTreeMap<String, Object> postResponse = connection2.api().transactions.create(payload);

        payload.clear();
        System.out.println(postResponse);


    }
}
