
package com.mycompany.view;
import br.cefetmg.space.model.dto.PessoaDTO;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        PessoaDTO pessoa = new PessoaDTO();
        String nome, cpf, telefone, user, senha1, senha2, email;
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("-----CADASTRANDO USUÁRIO-----");
            System.out.println("Digite seu nome: ");
            nome = input.nextLine();
            System.out.println("Digite seu cpf: ");
            cpf = input.nextLine();
            System.out.println("Digite seu telefone: ");
            telefone = input.nextLine();
            System.out.println("Digite seu email: ");
            email = input.nextLine();
            System.out.println("Digite seu nome de usuário: ");
            user = input.nextLine();
            System.out.println("Digite sua senha: ");
            senha1 = input.nextLine();
            do{
                System.out.println("Confirme sua senha: ");
                senha2 = input.nextLine();
                if(senha1.equals(senha2))
                    System.out.println("Senha incorreta!");
            }while(!senha1.equals(senha2));
            
            pessoa.setCpf(cpf);
            pessoa.setEmail(email);
            pessoa.setNome(nome);
            pessoa.setSenha(senha1);
            pessoa.setTelefone(telefone);
            pessoa.setUserName(user);
            
        }
    }
}
