package br.cefetmg.space.view;

import java.security.SecureRandom;

public class GeradorSenha {

    private static final String NUMEROS = "0123456789";
    private static final String LETRAS_MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String SIMBOLOS = "!@#$%^&*()-_+=<>?/";

    private static final String TODOS_CARACTERES = NUMEROS + LETRAS_MAIUSCULAS + LETRAS_MINUSCULAS + SIMBOLOS;

    private static final SecureRandom RANDOM = new SecureRandom();

    public static String gerarSenha(int tamanho) {
        if (tamanho < 6) {
            throw new IllegalArgumentException("O tamanho mínimo da senha é 6 caracteres.");
        }

        StringBuilder senha = new StringBuilder(tamanho);

        senha.append(LETRAS_MAIUSCULAS.charAt(RANDOM.nextInt(LETRAS_MAIUSCULAS.length())));
        senha.append(LETRAS_MINUSCULAS.charAt(RANDOM.nextInt(LETRAS_MINUSCULAS.length())));
        senha.append(NUMEROS.charAt(RANDOM.nextInt(NUMEROS.length())));
        senha.append(SIMBOLOS.charAt(RANDOM.nextInt(SIMBOLOS.length())));

        for (int i = 4; i < tamanho; i++) {
            senha.append(TODOS_CARACTERES.charAt(RANDOM.nextInt(TODOS_CARACTERES.length())));
        }

        return embaralhar(senha.toString());
    }

    private static String embaralhar(String senha) {
        char[] caracteres = senha.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            int j = RANDOM.nextInt(caracteres.length);
            char temp = caracteres[i];
            caracteres[i] = caracteres[j];
            caracteres[j] = temp;
        }
        return new String(caracteres);
    }
}