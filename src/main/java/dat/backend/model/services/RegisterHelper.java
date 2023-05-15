package dat.backend.model.services;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.protobuf.Message;

public class RegisterHelper {

            public static String hashPassword(String password) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    md.update(password.getBytes());
                    byte[] byteData = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < byteData.length; i++) {
                        sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    return sb.toString();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            // To use this class, simply call the
            //hashPassword method with the password string as the input parameter:

            /*      String password = "myPassword123";
                    String hashedPassword = PasswordHasher.hashPassword(password);
                    System.out.println(hashedPassword);         */

        }



