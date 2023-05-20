import com.google.gson.GsonBuilder;
import java.util.ArrayList;

public class NoobChain {

    public static ArrayList<Block> blockChain = new ArrayList<>();
    public static int DIFFICULTY = 5;
    public static String hashTarget;

    static {
        hashTarget = new String(new char[DIFFICULTY]).replace('\0', '0');
    }

    public static void main(String[] args) {
        blockChain.add(new Block("Hi im the first block", "0"));
        System.out.println("mining block 1");
        blockChain.get(0).mineBlock(DIFFICULTY);

        blockChain.add(
            new Block("Yo im the second block", blockChain.get(blockChain.size() - 1).hash));
        System.out.println("mining block 2");
        blockChain.get(1).mineBlock(DIFFICULTY);

        blockChain.add(
            new Block("Hey im the third block", blockChain.get(blockChain.size() - 1).hash));
        System.out.println("mining block 3");
        blockChain.get(2).mineBlock(DIFFICULTY);

        System.out.println("The blockChain is Valid: " + isChainValid());
        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);
        System.out.println(blockChainJson);
    }

    public static Boolean isChainValid() {
        Block previousBlock;
        Block currentBlock;
        for (int i = 1; i < blockChain.size(); i++) {
            previousBlock = blockChain.get(i - 1);
            currentBlock = blockChain.get(i);
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("previous hash wrong!");
                return false;
            }
            if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
                System.out.println("current hash wrong!");
                return false;
            }
            if (!currentBlock.hash.startsWith(hashTarget)) {
                System.out.println("Hash dont match request!");
                return false;
            }
        }
        return true;
    }
}
