package customBlockchain;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;

public class CustomBlockchain {
    public static class block {
        public String hash;
        public String previousHash;
        private String data;

        public block (String data, String previousHash) {
            this.data = data;
            this.previousHash = previousHash;
            this.hash = calculateHash();
        }
        public String calculateHash () {
            return applySha256(previousHash + data);
        }
    }
    static String applySha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    static ArrayList<block> blockChain = new ArrayList<>();
    static void displayBlockChain(ArrayList<block> blockChain) {
        for (int i = 0; i < blockChain.size(); i++) {
            System.out.println("Block " + (i + 1));
            System.out.println("Hash          : " + blockChain.get(i).hash);
            System.out.println("Previous Hash : " + blockChain.get(i).previousHash);
            System.out.println("Data          : " + blockChain.get(i).data);
            System.out.println();
        }
    }
    static boolean isValid(ArrayList<block> blockChain) {
        for (int i = 1; i < blockChain.size(); i++) {
            if (! blockChain.get(i).previousHash.equals(blockChain.get(i - 1).hash))
                return false;
        }
        return true;
    }
    public static void main(String[] args) {
        block genesisBlock = new block("Elon Musk bought Twitter", "0");
        block secondBlock = new block("Meta has launched Threads", genesisBlock.hash);
        block thirdBlock = new block("Which of these is better ?", secondBlock.hash);
        blockChain.add(genesisBlock);
        blockChain.add(secondBlock);
        blockChain.add(thirdBlock);
        displayBlockChain(blockChain);
        if (isValid(blockChain))
            System.out.println("The Block Chain is Valid !");
        else
            System.out.println("The Block Chain is Compromised !");
    }
}
