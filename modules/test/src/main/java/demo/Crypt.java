package demo;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Crypt {
    public static void main(String[] args) throws IOException {
        boolean mode = "e".equalsIgnoreCase(args[0]);

        Path src = Paths.get(args[1]);
        Path tgt = Paths.get(args[2]);

        StringEncryptor stringEncryptor = stringEncryptor();

        long tLine;
        try (BufferedReader reader = Files.newBufferedReader(src)) {
            tLine = reader.lines().count();
            System.out.printf("%13d\n", tLine);
        }

        try (BufferedReader reader = Files.newBufferedReader(src);
             BufferedWriter writer = Files.newBufferedWriter(tgt)) {

            int cLine = 1;
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ss = line.split(",");

                ss[1] = mode ? stringEncryptor.encrypt(ss[1]) : stringEncryptor.decrypt(ss[1]);
                ss[2] = mode ? stringEncryptor.encrypt(ss[1]) : stringEncryptor.decrypt(ss[2]);

                String newLine = String.join(",", ss);

                writer.write(newLine);
                writer.newLine();

                if (cLine % 50 == 0 || cLine == tLine) {
                    System.out.printf("%5d / %5d\n", cLine, tLine);
                }

                cLine++;
            }
        }
    }

    private static final int DEFAULT_ITERATION_COUNT = 1024;
    private static final String SECRET_KEY = "ghatyvldahdk!@#!ghatyvldahdk!@#!";
    private static final String ALGORITHM = "PBEWITHHMACSHA512ANDAES_256";

    private static StringEncryptor stringEncryptor() {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(SECRET_KEY);
        config.setAlgorithm(ALGORITHM);
        config.setKeyObtentionIterations(DEFAULT_ITERATION_COUNT);
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");

        PooledPBEStringEncryptor stringEncryptor = new PooledPBEStringEncryptor();
        stringEncryptor.setConfig(config);

        return stringEncryptor;
    }
}
