
package br.cefetmg.space.view;
import br.cefetmg.space.model.dao.UsuarioDAO;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.IUsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws PersistenciaException { 
        UsuarioDTO usuario = new UsuarioDTO();
        String nome, cpf, telefone, user, senha1, senha2, email;
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        char resposta; int id;
        
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("O que deseja fazer com o usuário? ");
            System.out.println("(1)Adicionar (2)Excluir (3)Listar (4)Atualizar");
            resposta = input.next().charAt(0);
            input.nextLine();
            switch(resposta){
                case '1' -> {
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
                        if(!senha1.equals(senha2))
                            System.out.println("Senha incorreta!");
                    }while(!senha1.equals(senha2));
                    usuario.setEmail(email);
                    usuario.setNome(nome);
                    usuario.setSenha(senha1);
                    usuario.setUserName(user);
                    usuario.setCpf(cpf);
                    usuario.setTelefone(telefone);
                    usuarioDAO.inserir(usuario);
                }
                case '2' -> {
                    System.out.println("-----EXCLUINDO USUÁRIO-----");
                    System.out.println("Digite o id do usuário que deseja excluir:");
                    id = input.nextInt();
                    input.nextLine();
                    usuarioDAO.delete(id);
                }
                case '3' -> {
                    System.out.println("-----LISTAR USUÁRIOS-----");
                    usuarioDAO.listarTodos();
                }
                case '4' -> {
                    System.out.println("-----ATUALIZAR USUÁRIO-----");
                    System.out.println("Digite o novo nome: ");
                    nome = input.nextLine();
                    System.out.println("Digite o novo cpf: ");
                    cpf = input.nextLine();
                    System.out.println("Digite o novo telefone: ");
                    telefone = input.nextLine();
                    System.out.println("Digite o novo email: ");
                    email = input.nextLine();
                    System.out.println("Digite o novo nome de usuário: ");
                    user = input.nextLine();
                    System.out.println("Digite a  senha: ");
                    senha1 = input.nextLine();
                    do{
                        System.out.println("Confirme sua senha: ");
                        senha2 = input.nextLine();
                        if(!senha1.equals(senha2))
                            System.out.println("Senha incorreta!");
                    }while(!senha1.equals(senha2));
                    System.out.println("Digite o id do usuário que deseja atualizar:");
                    id = input.nextInt();
                    input.nextLine();
                    usuario.setEmail(email);
                    usuario.setNome(nome);
                    usuario.setSenha(senha1);
                    usuario.setUserName(user);
                    usuario.setCpf(cpf);
                    usuario.setTelefone(telefone);
                    usuarioDAO.atualizar(id, usuario);
                }
                    
            }
            
            System.out.println("FIM");
        }
    }
}
