package dat.backend.model.services;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.protobuf.Message;

public class RegisterHelper {

    /**
     * Genererer en hash-værdi for den givne adgangskode ved hjælp af SHA-256 værdien
     * Metoden anvender MessageDigest-klassen til at udføre hashing-operationen.
     *
     * @param password Adgangskoden, der skal hashes.
     * @return En streng, der repræsenterer den hashede værdi af adgangskoden.
     */
            //Hash konverter strings og andet om til bytes
            public static String hashPassword(String password) {


                //Pakker ind i en try-catch og forsøger at:
                try {

                    //laver md-objekt med SHA-256 algoritme som instans
                    MessageDigest md = MessageDigest.getInstance("SHA-256");

                    // Konverterer stringens til bytes og giver til algoritmen
                    md.update(password.getBytes());

                    //Beregner hash-værdien af bytes ud fra SHA-256 algoritmen
                    byte[] byteData = md.digest();

                    //Opretter en Stringbuilder (en klasse til at hjælpe med at lave strings med)
                    StringBuilder sb = new StringBuilder();

                    //Tager et tal mellem 0-256 og laver om til hexdecimal-tal - returnerer som String
                    for (int i = 0; i < byteData.length; i++) {
                        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    return sb.toString();

                    //Exception hvis det fejler
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    return null;
                }
            }


            /*      String password = "myPassword123";
                    String hashedPassword = PasswordHasher.hashPassword(password);
                    System.out.println(hashedPassword);         */

        }



