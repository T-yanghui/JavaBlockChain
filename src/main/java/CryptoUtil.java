import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class CryptoUtil {
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer(hash.length * 2);
            String tempHex;
            for (byte element : hash) {
                tempHex = Integer.toHexString(0xff & element);
                if (tempHex.length() == 1) hexString.append('0');
                hexString.append(tempHex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
