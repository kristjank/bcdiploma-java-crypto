package org.arkecosystem.crypto.transactions;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.arkecosystem.crypto.configuration.Network;
import org.arkecosystem.crypto.encoding.Hex;
import org.arkecosystem.crypto.enums.CoreTransactionTypes;
import org.arkecosystem.crypto.enums.TransactionTypeGroup;
import org.arkecosystem.crypto.transactions.serializers.*;

public class Serializer {

    private ByteBuffer buffer;
    private Transaction transaction;

    public byte[] serialize(
            Transaction transaction, boolean skipSignature, boolean skipSecondSignature) {
        this.transaction = transaction;

        this.buffer = ByteBuffer.allocate(512);
        this.buffer.order(ByteOrder.LITTLE_ENDIAN);

        serializeHeader();
        serializeVendorField();

        serializeTypeSpecific();

        serializeSignatures(skipSignature, skipSecondSignature);

        byte[] result = new byte[this.buffer.position()];
        this.buffer.rewind();
        this.buffer.get(result);

        return result;
    }

    public byte[] serialize(Transaction transaction) {
        return this.serialize(transaction, false, false);
    }

    private void serializeHeader() {
        this.buffer.put((byte) 0xff);

        if (this.transaction.version > 0) {
            this.buffer.put((byte) this.transaction.version);
        } else {
            this.buffer.put((byte) 0x01);
        }

        if (this.transaction.network > 0) {
            this.buffer.put((byte) this.transaction.network);
        } else {
            this.buffer.put((byte) Network.get().version());
        }

        if (transaction.version == 1) {
            this.buffer.put((byte) this.transaction.type);
            this.buffer.putInt(this.transaction.timestamp);
        } else {
            this.buffer.putInt(this.transaction.typeGroup);
            this.buffer.putShort((short) this.transaction.type);
            this.buffer.putLong(this.transaction.nonce);
        }

        this.buffer.put(Hex.decode(this.transaction.senderPublicKey));
        this.buffer.putLong(this.transaction.fee);
    }

    private void serializeVendorField() {
        if (this.transaction.vendorField != null) {
            int vendorFieldLength = this.transaction.vendorField.length();

            this.buffer.put((byte) vendorFieldLength);
            this.buffer.put(this.transaction.vendorField.getBytes());
        } else if (this.transaction.vendorFieldHex != null) {
            int vendorFieldHexLength = this.transaction.vendorFieldHex.length();

            this.buffer.put((byte) (vendorFieldHexLength / 2));
            this.buffer.put(Hex.decode(this.transaction.vendorFieldHex));
        } else {
            this.buffer.put((byte) 0x00);
        }
    }

    private void serializeTypeSpecific() {
        if (this.transaction.typeGroup == TransactionTypeGroup.BC_DIPLOMA.getValue()) {
            new IssuerRegistration(this.buffer, this.transaction).serialize();
        } else {
            CoreTransactionTypes transactionType = CoreTransactionTypes.values()[transaction.type];
            switch (transactionType) {
                case TRANSFER:
                    new Transfer(this.buffer, this.transaction).serialize();
                    break;
                case SECOND_SIGNATURE_REGISTRATION:
                    new SecondSignatureRegistration(this.buffer, this.transaction).serialize();
                    break;
                case DELEGATE_REGISTRATION:
                    new DelegateRegistration(this.buffer, this.transaction).serialize();
                    break;
                case VOTE:
                    new Vote(this.buffer, this.transaction).serialize();
                    break;
                case MULTI_SIGNATURE_REGISTRATION:
                    new MultiSignatureRegistration(this.buffer, this.transaction).serialize();
                    break;
                case IPFS:
                    new Ipfs(this.buffer, this.transaction).serialize();
                    break;
                case MULTI_PAYMENT:
                    new MultiPayment(this.buffer, this.transaction).serialize();
                    break;
                case DELEGATE_RESIGNATION:
                    new DelegateResignation(this.buffer, this.transaction).serialize();
                    break;
                case HTLC_LOCK:
                    new HtlcLock(this.buffer, this.transaction).serialize();
                    break;
                case HTLC_CLAIM:
                    new HtlcClaim(this.buffer, this.transaction).serialize();
                    break;
                case HTLC_REFUND:
                    new HtlcRefund(this.buffer, this.transaction).serialize();
                    break;
                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    private void serializeSignatures(boolean skipSignature, boolean skipSecondSignature) {
        if (!skipSignature) {
            buffer.put(Hex.decode(this.transaction.signature));
        }

        if (!skipSecondSignature && this.transaction.secondSignature != null) {
            buffer.put(Hex.decode(this.transaction.secondSignature));
        }

        if (this.transaction.signatures != null) {
            this.buffer.put((byte) 0xff);
            buffer.put(Hex.decode(String.join("", this.transaction.signatures)));
        }
    }
}
