package Utils;

import java.security.MessageDigest;

public class HashUtil {

    public static String gerarMD5(String input) {
        try {
            // Cria a instância do MessageDigest para MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Atualiza o digest com os bytes da string
            byte[] hashBytes = md.digest(input.getBytes());

            // Converte os bytes para hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar hash MD5", e);
        }
    }
}