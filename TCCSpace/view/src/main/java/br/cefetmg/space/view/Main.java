
package br.cefetmg.space.view;
import br.cefetmg.space.model.dao.UsuarioDAO;
import br.cefetmg.space.model.dto.EmailValidator;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.dto.ValidaTelefone;
import br.cefetmg.space.model.dto.ValidarSenha;
import br.cefetmg.space.model.idao.IUsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws PersistenciaException { 
        UsuarioDTO usuario = new UsuarioDTO();
        ValidarSenha validaS = new ValidarSenha();
        String nome, telefone, user, senha1, senha2, email;
        ValidaTelefone validaT = new ValidaTelefone();
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        int resposta; int id;
        
        try (Scanner input = new Scanner(System.in)) {
            do{
                    System.out.println("Que operação deseja realizar? ");
                    System.out.println("(1)Adicionar um novo usuário");
                    System.out.println("(2)Excluir usuário");
                    System.out.println("(3)Listar usuários");
                    System.out.println("(4)Atualizar usuário");
                    System.out.println("(5)Sair");
                    resposta = input.nextInt();
                    input.nextLine();
                switch(resposta){
                    case 1 -> {
                        System.out.println("-----CADASTRANDO ADM-----");
                        System.out.println("Digite seu nome completo: ");
                        nome = input.nextLine();
                        do{
                            System.out.println("Digite seu telefone: ");
                            telefone = input.nextLine();
                            if(!validaT.validarTelefone(telefone))
                                System.out.println("Telefone inválido!");
                        }while(!validaT.validarTelefone(telefone));
                        do{
                            System.out.println("Digite seu e-mail: ");
                            email = input.nextLine();
                            if(!EmailValidator.isValidEmail(email))
                                System.out.println("E-mail inválido!");
                            if(usuarioDAO.procurarPorEmail(email) != null)
                                System.out.println("Já existe um usuário cadastrado com esse e-mail.");
                        }while(!EmailValidator.isValidEmail(email) && usuarioDAO.procurarPorEmail(email) != null);
                        do{
                            System.out.println("Digite um nome de usuário: ");
                            user = input.nextLine();
                            if(usuarioDAO.procurarPorUserName(user) != null)
                                System.out.println("Já existe um usuário com esse nome.");
                        }while(usuarioDAO.procurarPorUserName(user) != null);
                        do{
                            System.out.println("Digite sua senha: ");
                            senha1 = input.nextLine();
                            if(!validaS.senhaForte(senha1))
                               System.out.println("Senha fraca.");
                        }while(!validaS.senhaForte(senha1));
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
                        usuario.setTelefone(telefone);
                        usuarioDAO.inserir(usuario);
                    }
                    case 2 -> {
                        System.out.println("-----EXCLUINDO USUÁRIO-----");
                        System.out.println("Digite o id do usuário que deseja excluir:");
                        id = input.nextInt();
                        input.nextLine();
                        usuarioDAO.delete(id);
                    }
                    case 3 -> {
                        System.out.println("-----LISTANDO USUÁRIOS-----");
                        usuarioDAO.listarTodos();
                    }
                    case 4 -> {
                        System.out.println("-----ATUALIZAR USUÁRIO-----");
                        System.out.println("Digite o e-mail do usuário que deseja atualizar:");
                        email = input.nextLine();
                        if(usuarioDAO.procurarPorEmail(email) == null)
                            System.out.println("Este usuário não existe.");
                        else{
                            System.out.println("Digite o novo nome: ");
                            nome = input.nextLine();
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
                            usuario.setEmail(email);
                            usuario.setNome(nome);
                            usuario.setSenha(senha1);
                            usuario.setUserName(user);
                            usuario.setTelefone(telefone);
                            id = usuarioDAO.procurarPorEmail(email).getId();
                            usuarioDAO.atualizar(id, usuario);
                        }
                    }
                 }
            }while(resposta != 5);
            
            System.out.println("FIM");
        }
    }
}
