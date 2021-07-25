
package Algoritmos_de_cifrado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Algoritmos_de_cifrado {
    
    private static String txt;
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    
   public static void main(String[] args) {
  
        try {
            //gestion de texto original
            System.out.println("Texto original: ");
            txt=bf.readLine();
            byte textoPlano[] = txt.getBytes();
            
            System.out.println("--------------------------------");
            //AES
            KeyGenerator kgAES =KeyGenerator.getInstance("AES");
            kgAES.init(128);
            SecretKey claveAES = kgAES.generateKey();
            Cipher cAES = Cipher.getInstance("AES");
            cAES.init(Cipher.ENCRYPT_MODE, claveAES);
            byte textoCifradoAES[] = cAES.doFinal(textoPlano);
            System.out.println("Cifrado AES: ");
            System.out.println(new String(textoCifradoAES));
            
            
            System.out.println("--------------------------------");
            //3DES
            KeyGenerator kg3DES =KeyGenerator.getInstance("TripleDES");
            kg3DES.init(168);
            SecretKey clave3DES = kg3DES.generateKey();
            Cipher c3DES = Cipher.getInstance("TripleDES");
            c3DES.init(Cipher.ENCRYPT_MODE, clave3DES);
            byte textoCifrado3DES[] = c3DES.doFinal(textoPlano);
            System.out.println("Cifrado TripleDes: ");
            System.out.println(new String(textoCifrado3DES));
            
            
            System.out.println("--------------------------------");
            //RSA
            KeyPairGenerator kgRSA =KeyPairGenerator.getInstance("RSA");
            kgRSA.initialize(2048);
            KeyPair claveRSA = kgRSA.genKeyPair();
            
            PublicKey clavePublica = claveRSA.getPublic();
            Cipher cRSA = Cipher.getInstance("RSA");
            cRSA.init(Cipher.ENCRYPT_MODE, clavePublica);
            byte[] textoCifradoRSA = cRSA.doFinal(textoPlano);
            System.out.println("Cifrado RSA: ");
            System.out.println(new String(textoCifradoRSA));
            
            
            
            System.out.println("--------------------------------");
            //Resumen
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(textoPlano);
            byte resumen[] = md.digest();
            System.out.println("Calcular Resumen: ");
            System.out.println(new String(resumen));
            
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
	}
        
    }
}
