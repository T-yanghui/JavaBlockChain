import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    public String data;
    public long timeStamp;
    public int nonce = 0;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String calculateHash = CryptoUtil.applySha256(
            this.previousHash +
                Long.toString(timeStamp) +
                Integer.toString(nonce) +
                data
        );
        return calculateHash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!this.hash.startsWith(target)) {
            nonce++;
            this.hash = calculateHash();
        }
        System.out.println("Block mined!" + this.hash);
    }
}
