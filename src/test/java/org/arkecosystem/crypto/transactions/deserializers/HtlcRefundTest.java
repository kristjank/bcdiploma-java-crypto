package org.arkecosystem.crypto.transactions.deserializers;

import com.google.gson.internal.LinkedTreeMap;
import org.arkecosystem.crypto.enums.TransactionTypeGroup;
import org.arkecosystem.crypto.transactions.Deserializer;
import org.arkecosystem.crypto.transactions.FixtureLoader;
import org.arkecosystem.crypto.transactions.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtlcRefundTest {

    @Test
    void passphrase() {
        LinkedTreeMap<String, Object> fixture = FixtureLoader.load("transactions/V2/htlc-refund/passphrase");
        LinkedTreeMap<String, Object> data = (LinkedTreeMap<String, Object>) fixture.get("data");

        Transaction actual = new Deserializer().deserialize(fixture.get("serialized").toString());
        assertEquals(((Double) data.get("type")).intValue(), actual.type);
        assertEquals(TransactionTypeGroup.CORE.getValue(), actual.typeGroup);
        assertEquals((Long.valueOf((String) data.get("fee"))), actual.fee);
        assertEquals((Long.valueOf((String) data.get("nonce"))), actual.nonce);
        assertEquals(data.get("senderPublicKey").toString(), actual.senderPublicKey);
        assertEquals(data.get("signature").toString(), actual.signature);

        LinkedTreeMap<String, Object> asset = (LinkedTreeMap<String, Object>) ((LinkedTreeMap<String, Object>) data.get("asset")).get("refund");
        assertEquals((asset.get("lockTransactionId")), actual.asset.htlcRefundAsset.lockTransactionId);

        assertEquals(data.get("id").toString(), actual.id);

    }

    @Test
    void secondPassphrase() {
        LinkedTreeMap<String, Object> fixture = FixtureLoader.load("transactions/V2/htlc-refund/second-passphrase");
        LinkedTreeMap<String, Object> data = (LinkedTreeMap<String, Object>) fixture.get("data");

        Transaction actual = new Deserializer().deserialize(fixture.get("serialized").toString());
        assertEquals(((Double) data.get("type")).intValue(), actual.type);
        assertEquals(TransactionTypeGroup.CORE.getValue(), actual.typeGroup);
        assertEquals((Long.valueOf((String) data.get("fee"))), actual.fee);
        assertEquals((Long.valueOf((String) data.get("nonce"))), actual.nonce);
        assertEquals(data.get("senderPublicKey").toString(), actual.senderPublicKey);
        assertEquals(data.get("signature").toString(), actual.signature);
        assertEquals(data.get("secondSignature").toString(), actual.signSignature);
        assertEquals(data.get("secondSignature").toString(), actual.secondSignature);

        LinkedTreeMap<String, Object> asset = (LinkedTreeMap<String, Object>) ((LinkedTreeMap<String, Object>) data.get("asset")).get("refund");
        assertEquals((asset.get("lockTransactionId")), actual.asset.htlcRefundAsset.lockTransactionId);

        assertEquals(data.get("id").toString(), actual.id);

    }
}
