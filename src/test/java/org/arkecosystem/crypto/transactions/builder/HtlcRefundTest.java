package org.arkecosystem.crypto.transactions.builder;

import org.arkecosystem.crypto.transactions.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HtlcRefundTest {
    @Test
    void build() {
        Transaction actual = new HtlcRefund()
            .htlcRefundAsset("943c220691e711c39c79d437ce185748a0018940e1a4144293af9d05627d2eb4")
            .nonce(3)
            .sign("this is a top secret passphrase")
            .transaction;

        assertTrue(actual.verify());
    }

    @Test
    void buildSecondSignature() {
        Transaction actual = new HtlcRefund()
            .htlcRefundAsset("943c220691e711c39c79d437ce185748a0018940e1a4144293af9d05627d2eb4")
            .nonce(3)
            .sign("this is a top secret passphrase")
            .secondSign("this is a top secret second passphrase")
            .transaction;

        assertTrue(actual.verify());
        assertTrue(actual.secondVerify("03699e966b2525f9088a6941d8d94f7869964a000efe65783d78ac82e1199fe609"));
    }

}
