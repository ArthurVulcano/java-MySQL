package SistemaUsuario;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
public class HashSenha {
    public static String gerarHash(String senha) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest(
                senha.getBytes(StandardCharsets.UTF_8)
        );
        StringBuilder resultado = new StringBuilder();
        for (byte b : hash){
            resultado.append(String.format("%02x", b));

        }
        return resultado.toString();
    }


}
