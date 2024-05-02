
package br.cefetmg.space.view;
import br.cefetmg.space.model.dto.ValidaCPF;
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
        String nome, telefone, cpf, user, senha1, senha2, email;
        ValidaCPF valida = new ValidaCPF();
        ValidaTelefone validaT = new ValidaTelefone();
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        char resposta; int id;
        
        try (Scanner input = new Scanner(System.in)) {
            do{
                do{
                    System.out.println("Que operação deseja realizar? ");
                    System.out.println("(1)Adicionar um novo usuário");
                    System.out.println("(2)Excluir usuário");
                    System.out.println("(3)Listar usuários");
                    System.out.println("(4)Atualizar usuário");
                    System.out.println("(5)Sair");
                    resposta = input.next().charAt(0);
                    input.nextLine();
                    if(resposta < 1 && resposta > 4)
                        System.out.println("Selecione uma opção válida.");
                }while(resposta < 1 && resposta > 4);
                switch(resposta){
                    case '1' -> {
                        System.out.println("-----CADASTRANDO USUÁRIO-----");
                        System.out.println("Digite seu nome completo: ");
                        nome = input.nextLine();
                        do{
                            System.out.println("Digite seu cpf: ");
                            cpf = input.nextLine();
                            if(!valida.isCPF(cpf))
                                System.out.println("CPF inválido!");
                            else if(usuarioDAO.procurarPorCPF(cpf) != null)
                                System.out.println("Já existe um usuário cadastrado com esse cpf.(ID do usuário: " + usuarioDAO.procurarPorCPF(cpf).getId() + ")");
                        }while(!valida.isCPF(cpf) || usuarioDAO.procurarPorCPF(cpf) != null);
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
                        }while(!EmailValidator.isValidEmail(email));
                        do{
                            System.out.println("Digite um nome de usuário: ");
                            user = input.nextLine();
                            if(usuarioDAO.procurarPorUserName(user) != null)
                                System.out.println("Já existe um usuário com esse nome.");
                        }while(usuarioDAO.procurarPorCPF(cpf) != null);
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
                        System.out.println("-----LISTANDO USUÁRIOS-----");
                        usuarioDAO.listarTodos();
                    }
                    case '4' -> {
                        System.out.println("-----ATUALIZAR USUÁRIO-----");
                        System.out.println("Digite o cpf do usuário que deseja atualizar:");
                        cpf = input.nextLine();
                        if(usuarioDAO.procurarPorCPF(cpf) == null)
                            System.out.println("Este usuário não existe.");
                        else{
                            System.out.println("Digite a senha: ");
                            senha1 = input.nextLine();
                            if(usuarioDAO.procurarPorCPF(cpf).getSenha().equals(senha1)){
                                System.out.println("O que deseja alterar?");
                                System.out.println("(1)Nome");
                                System.out.println("(1)CPF");
                                System.out.println("(1)Telefone");
                                System.out.println("(1)E-mail");
                                System.out.println("(1)Nome de Usuário");
                                System.out.println("(1)Senha");
                            }
                            
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
                            usuario.setEmail(email);
                            usuario.setNome(nome);
                            usuario.setSenha(senha1);
                            usuario.setUserName(user);
                            usuario.setCpf(cpf);
                            usuario.setTelefone(telefone);
                            id = usuarioDAO.procurarPorCPF(cpf).getId();
                            usuarioDAO.atualizar(id, usuario);
                        }
                    }

                 }
            }while(resposta != 5);
            
            System.out.println("FIM");
        }
    }
}
