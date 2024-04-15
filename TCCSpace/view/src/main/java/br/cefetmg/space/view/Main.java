
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
            
            System.out.println("FIM");
        }
    }
}
