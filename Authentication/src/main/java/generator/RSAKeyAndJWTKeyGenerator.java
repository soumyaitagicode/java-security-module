package generator;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

public class RSAKeyAndJWTKeyGenerator {

    public static void main(String[] args) throws Exception {
        // Generate an RSA key pair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // You can adjust the key size as needed
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // Get the private and public keys
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Convert and print keys in PEM format
        String privateKeyPEM = convertToPEM(privateKey.getEncoded(), "PRIVATE KEY");
        String publicKeyPEM = convertToPEM(publicKey.getEncoded(), "PUBLIC KEY");

        System.out.println("Private Key (PEM Format):");
        System.out.println(privateKeyPEM);

        System.out.println("\nPublic Key (PEM Format):");
        System.out.println(publicKeyPEM);

        // Generate a random JWT key
        String jwtKey = UUID.randomUUID().toString().replace("-", "");
        System.out.println("\nJWT Key: " + jwtKey);
    }

    private static String convertToPEM(byte[] keyBytes, String type) {
        String base64 = Base64.getEncoder().encodeToString(keyBytes);

        StringBuilder pem = new StringBuilder();
        pem.append("-----BEGIN ").append(type).append("-----\n");
        pem.append(base64).append("\n");
        pem.append("-----END ").append(type).append("-----\n");

        return pem.toString();
    }
}