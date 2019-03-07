import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
public class Main {

    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String key = "Bar12345Bar12389"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV
        String pText = "{\"param1\":\"value1\",\"param2\":\"value2\",\"param3\":\"value3\"}";
        
        String encryptedText = "CQVpSA7vs9ZfVL2KyL94mvCL5xu\nLmu5Gt46NqlIjXy7UKfv\\/eyC0\\/Yc98xiQW1+ZEBteN0A+EeEER9h\n"; //Won't work -> just an example string

       
        /*System.out.println(decrypt(key, initVector,
                encrypt(key, initVector, "Hello World")));*/

        System.out.println(decrypt(key, initVector, encryptedText));
        System.out.println(encrypt(key, initVector, pText));
    }
}
