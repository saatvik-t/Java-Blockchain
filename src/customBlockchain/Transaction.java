package customBlockchain;

import java.util.*;
import java.security.*;

public class Transaction {
    public String transactionId;
    public PublicKey sender;
    public PublicKey recipient;
    public float value;
    public byte[] signature;
    public ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
    public ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();

    private static int sequence = 0;

    public Transaction(PublicKey from, PublicKey to, float value, ArrayList<TransactionInput> inputs) {
        this.sender = from;
        this.recipient = to;
        this.value = value;
        this.inputs = inputs;
    }

    public boolean processTransaction() {

        if(! verifySignature()) {
            System.out.println("Transaction Signature failed to verify");
            return false;
        }

        for(TransactionInput i : inputs) {
            i.UTXO = CustomBlockchain.UTXOs.get(i.transactionOutputId);
        }

        if(getInputsValue() < CustomBlockchain.minimumTransaction) {
            System.out.println("Transaction Input too small: " + getInputsValue());
            System.out.println("Please enter the amount greater than " + CustomBlockchain.minimumTransaction);
            return false;
        }

        float leftOver = getInputsValue() - value;
        transactionId = calulateHash();
        outputs.add(new TransactionOutput( this.recipient, value,transactionId));
        outputs.add(new TransactionOutput( this.sender, leftOver,transactionId));

        for(TransactionOutput o : outputs) {
            CustomBlockchain.UTXOs.put(o.id , o);
        }

        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue;
            CustomBlockchain.UTXOs.remove(i.UTXO.id);
        }

        return true;
    }

    public float getInputsValue() {
        float total = 0;
        for(TransactionInput i : inputs) {
            if(i.UTXO == null) continue;
            total += i.UTXO.value;
        }
        return total;
    }

    public void generateSignature(PrivateKey privateKey) {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value)	;
        signature = StringUtil.applyECDSASig(privateKey,data);
    }

    public boolean verifySignature() {
        String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(recipient) + Float.toString(value)	;
        return StringUtil.verifyECDSASig(sender, data, signature);
    }

    public float getOutputsValue() {
        float total = 0;
        for(TransactionOutput o : outputs) {
            total += o.value;
        }
        return total;
    }

    private String calulateHash() {
        sequence++;
        return StringUtil.applySha256(
                StringUtil.getStringFromKey(sender) +
                        StringUtil.getStringFromKey(recipient) +
                        Float.toString(value) + sequence
        );
    }
}
