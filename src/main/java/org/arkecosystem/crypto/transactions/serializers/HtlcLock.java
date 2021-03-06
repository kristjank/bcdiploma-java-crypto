package org.arkecosystem.crypto.transactions.serializers;

import java.nio.ByteBuffer;
import org.arkecosystem.crypto.encoding.Base58;
import org.arkecosystem.crypto.encoding.Hex;
import org.arkecosystem.crypto.transactions.Transaction;

public class HtlcLock extends AbstractSerializer {
    public HtlcLock(ByteBuffer buffer, Transaction transaction) {
        super(buffer, transaction);
    }

    @Override
    public void serialize() {
        this.buffer.putLong(this.transaction.amount);
        this.buffer.put(Hex.decode(this.transaction.asset.htlcLockAsset.secretHash));
        this.buffer.put((byte) this.transaction.asset.htlcLockAsset.expiration.type.getValue());
        this.buffer.putInt(this.transaction.asset.htlcLockAsset.expiration.value);
        this.buffer.put(Base58.decodeChecked(this.transaction.recipientId));
    }
}
