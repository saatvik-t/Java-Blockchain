package CustomBlockchain.src.customBlockchain;

import java.util.*;

public class Block {

    public String hash;
    public String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    public long timeStamp;
    public int nonce;

    public Block(String previousHash ) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        merkleRoot
        );
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDificultyString(difficulty);
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined ! : " + hash);
    }

    public void addTransaction(Transaction transaction) {
        if(transaction == null)
            return;
        if(!"0".equals(previousHash)) {
            if((!transaction.processTransaction())) {
                System.out.println("Transaction Processing Failed !");
                return;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully Added to Block !");
    }
}
