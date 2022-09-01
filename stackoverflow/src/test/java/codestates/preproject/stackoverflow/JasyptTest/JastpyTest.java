package codestates.preproject.stackoverflow.JasyptTest;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JastpyTest {

    @Test
    void jasypt() {

        String username = "jungdo8016@gmail.com";
        String password = "ijfjzvldnhndzzvf";


        System.out.println(jasyptEncoding(username));
        System.out.println(jasyptEncoding(password));
    }

    public String jasyptEncoding(String value) {

        String key = "my_jasypt_key";
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWithMD5AndDES");
        pbeEnc.setPassword(key);


        pbeEnc.setStringOutputType("base64"); //인코딩 방식

        return pbeEnc.encrypt(value);
    }
}
