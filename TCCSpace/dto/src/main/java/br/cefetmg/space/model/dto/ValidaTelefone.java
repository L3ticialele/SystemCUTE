
package br.cefetmg.space.model.dto;

public class ValidaTelefone {
   public boolean validarTelefone(String telefone) {
         //Baseado no original para javascript:
         //https://gist.github.com/jonathangoncalves/7bdec924e9bd2bdf353d6b7520820b62
         
    //retira todos os caracteres não-numéricos (incluindo espaço,tab, etc)
    telefone = telefone.replaceAll("\\D","");
    
    //verifica se tem a qtde de numeros correta
    if (!(telefone.length() >= 10 && telefone.length() <= 11)) return false;
 
    //Se tiver 11 caracteres, verificar se começa com 9 o celular
    if (telefone.length() == 11 && Integer.parseInt(telefone.substring(2, 3)) != 9) return false;
 
    //verifica se o numero foi digitado com todos os dígitos iguais
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(telefone.charAt(0)+"{"+telefone.length()+"}");
    java.util.regex.Matcher m = p.matcher(telefone);
    if(m.find()) return false;
    
    //DDDs validos
    Integer[] codigosDDD = {
        11, 12, 13, 14, 15, 16, 17, 18, 19,
        21, 22, 24, 27, 28, 31, 32, 33, 34,
        35, 37, 38, 41, 42, 43, 44, 45, 46,
        47, 48, 49, 51, 53, 54, 55, 61, 62,
        64, 63, 65, 66, 67, 68, 69, 71, 73,
        74, 75, 77, 79, 81, 82, 83, 84, 85,
        86, 87, 88, 89, 91, 92, 93, 94, 95,
        96, 97, 98, 99};
    //verifica se o DDD é valido (sim, da pra verificar rsrsrs)
    if ( java.util.Arrays.asList(codigosDDD).indexOf(Integer.valueOf(telefone.substring(0, 2))) == -1) return false;
    
    //Se o número só tiver dez digitos não é um celular e por isso o número logo após o DDD deve ser 2, 3, 4, 5 ou 7 
    Integer[] prefixos = {2, 3, 4, 5, 7};
        //se passar por todas as validações acima, então está tudo certo
    
    return !(telefone.length() == 10 && java.util.Arrays.asList(prefixos).indexOf(Integer.valueOf(telefone.substring(2, 3))) == -1);
}
}
