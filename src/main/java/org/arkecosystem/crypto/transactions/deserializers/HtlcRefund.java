package org.arkecosystem.crypto.transactions.deserializers;

import java.nio.ByteBuffer;
import org.arkecosystem.crypto.encoding.Hex;
import org.arkecosystem.crypto.transactions.Transaction;

public class HtlcRefund extends AbstractDeserializer {
    public HtlcRefund(String serialized, ByteBuffer buffer, Transaction transaction) {
        super(serialized, buffer, transaction);
    }

    @Override
    public void deserialize(int assetOffset) {
        this.buffer.position(assetOffset / 2);

        byte[] buf = new byte[32];
        this.buffer.get(buf);
        this.transaction.asset.htlcRefundAsset.lockTransactionId = Hex.encode(buf);

        this.transaction.parseSignatures(this.serialized, assetOffset + 64);
    }
}
