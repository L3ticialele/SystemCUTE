
package br.cefetmg.space.model.dto;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class EmailValidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static boolean isValidEmail(String email) {
        // Verifica o formato do e-mail
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        
        if (!matcher.matches()) {
            return false;
        }

        // Extrai o domínio do e-mail
        String domain = email.substring(email.indexOf('@') + 1);
        
        // Valida o domínio
        try {
            InetAddress inetAddress = InetAddress.getByName(domain);
            return inetAddress.isReachable(5000); // Verifica se o servidor do domínio está acessível
        } catch (UnknownHostException e) {
            return false; // Domínio não encontrado
        } catch (IOException e) {
            return false; // Outros erros
        }
    }

    public static void main(String[] args) {
        String[] emails = {"test@example.com", "invalid-email", "user@example", "user@example.com"};

        for (String email : emails) {
            System.out.println(email + ": " + isValidEmail(email));
        }
    }
}


