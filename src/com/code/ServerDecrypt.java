package com.code;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class ServerDecrypt {
    public static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ2UPMkCfAaqgeHm610CTrcxKt8z\n" +
            "fqLpSc4odyuKc3S/xJtSz0JNrT3rsb45pwimp6Yrbio/rWW5aRFD0eJCIzW2nJwcaJDTLXQT32X8\n" +
            "K32M+rEgNsIilDoyVrKFXqLElIj6bbX6h3i87tbFqUoizcc33bTz1Aq+0iFjYYiwPlMpAgMBAAEC\n" +
            "gYEAlFpvL79LDOuSBB/nDlWRzh5YAnL0kmOXvKOULkXWlN/GNYUs43tF0roHSNP2Ucq0o1jYCqaQ\n" +
            "lOiN5lc3ThB+HBzyhol6Vf0Zssa/RS59m9GDCrrtWrdVz/lWT1u9KrKIYUWzdISKQPUYvNH05Y5I\n" +
            "wGuGITtjtSQgbRG79VZTUxECQQD4NlSyqMrvDpdtgH5DmzzZ7sBOTcl8AFpTpmgaID82mgkOcmYp\n" +
            "QYIohjTdP7hEjYoRg7RHW46kK1YNllrGSQATAkEAooXuNQ1DlAcHqn6niQUMgdwK2gI9p+L1mR+d\n" +
            "37ceKolYlGH31vzRBlHSLPtSW2UjW5lU3HJxBLRdfa/ujksfUwJBAPDhQSc1OePvxDMnnOGdWNui\n" +
            "oQwc32kwqng9qQ+Ztd8Wc62KheyJv3f2bn085mXY+mHu79jEWNSnndaH0MZXd/MCQA/cek77KGk9\n" +
            "9mWRTtB0ia/yxaxZI6NK5yU2jLXUrUEBfn+cwA9fRRcUzSpOrM72o+jAoiORl64Z3DX0AZgphLkC\n" +
            "QDwMWQgQN4d2GzVLx6wStDZPGiyc16k8QNbtqhPIwyjHrRM+FfGAPlbOdWvo6lx5QQkZNIz2YK8q\n" +
            "mriC7b+Qw5E=";

    public static void decrypt(String encryptedTextString,String encryptedSecretKeyString){

        try {

            // 1. Get private key
            PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(Base64.decode(privateKey, Base64.DEFAULT));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(privateSpec);

            // 2. Decrypt encrypted secret key using private key
            Cipher cipher1 = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
            cipher1.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] secretKeyBytes = cipher1.doFinal(Base64.decode(encryptedSecretKeyString, Base64.DEFAULT));
            SecretKey secretKey = new SecretKeySpec(secretKeyBytes, 0, secretKeyBytes.length, "AES");

            // 3. Decrypt encrypted text using secret key
            byte[] raw = secretKey.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(new byte[16]));
            byte[] original = cipher.doFinal(Base64.decode(encryptedTextString, Base64.DEFAULT));
            String text = new String(original, Charset.forName("UTF-8"));

            // 4. Print the original text sent by client
            System.out.println("text\n" + text + "\n\n");

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
