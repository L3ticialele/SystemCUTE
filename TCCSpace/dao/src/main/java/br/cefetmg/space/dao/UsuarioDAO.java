package br.cefetmg.space.dao;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.IUsuarioDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/*Esta classe inclui as funções que podem ser realizadas com o objeto Usuário*/
public class UsuarioDAO implements IUsuarioDAO {
    
    //Inserir um novo usuario no sistema
    @Override
    public Usuario inserir(Usuario usuario) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
            System.out.println("Usuário cadastrado!");
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
            return usuario;
        }
    }
    
    //Lista todos os usúarios do sistema
    @Override
    public List<Usuario> listarTodos() throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<Usuario> criteria
                = entityManager.getCriteriaBuilder().createQuery(Usuario.class);
        criteria.select(criteria.from(Usuario.class));
        List<Usuario> usuarios = entityManager.createQuery(criteria).getResultList();

        if (!usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                System.out.print(
                        "Id usuario: " + usuario.getId()
                        + " Nome: " + usuario.getNome()
                        + " CubeSats feitos/em andamento: " + usuario.quantCubeSat()
                        + " Telefone: " + usuario.getTelefone()
                );
            }
        } else {
            System.out.println(" Não há usuários cadastrados no sistema.");
        }
        entityManager.close();
        return usuarios;
    }
    
    //Deleta um usuario, conforme o id
    @Override
    public void delete(int idUsuario) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Usuario usuario = entityManager.find(Usuario.class, idUsuario);

            if (usuario != null) {
                entityManager.remove(usuario);
                entityManager.getTransaction().commit();
            } else {
                System.out.println("Não foi possível encontrar o usuário com o id: " + idUsuario);
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
    
    //Procura um usuário, conforme o nome
    @Override
    public Usuario procurarPorUserName(String user) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Usuario AS u WHERE u.username =:user ");
            query.setParameter("user", user);
            List<Usuario> usuarioPersistido = query.getResultList();
            if (!usuarioPersistido.isEmpty()) {
                return usuarioPersistido.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
    
    //Procura um usuario conforme o email
    @Override
    public Usuario procurarPorEmail(String email) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM Usuario AS u WHERE u.email =:email ");
            query.setParameter("email", email);
            List<Usuario> usuarioPersistido = query.getResultList();
            if (!usuarioPersistido.isEmpty()) {
                return usuarioPersistido.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

   //Validação usada no login
    @Override
    public boolean validarlogin(Usuario usuario) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT u.email, u.senha FROM Usuario AS u WHERE u.email = :email AND u.senha = :senha");
            query.setParameter("email", usuario.getEmail());
            query.setParameter("senha", usuario.getSenha());
            List<Usuario> usuarioPersistido = query.getResultList();
            if (!usuarioPersistido.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    //Atualiza os dados de um usuário, se alterados
    @Override
    public boolean atualizar(int idUsuario, Usuario usuario) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Usuario usuarioPersistido = entityManager.find(Usuario.class, idUsuario);

            if (usuarioPersistido != null) {
                usuarioPersistido.setEmail(usuario.getEmail());
                usuarioPersistido.setNome(usuario.getNome());
                usuarioPersistido.setSenha(usuario.getSenha());
                usuarioPersistido.setTelefone(usuario.getTelefone());
                usuarioPersistido.setCubeSat(usuario.getCubeSat());
                usuarioPersistido.setImagem(usuario.getImagem());
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o usuário com o id: " + idUsuario);
                return false;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
    
    //Procura por um usuário pelo ID
    @Override
    public Usuario procurarPorId(int id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //Validação usada no login, verifica se a senha e email inseridos pela pessoa se encontram no banco de dados
    @Override
    public Usuario validarlogin(String email, String senha) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT u.email, u.senha FROM Usuario AS u WHERE u.email = :email AND u.senha = :senha");
            query.setParameter("email", email);
            query.setParameter("senha", senha);
            List<Usuario> usuarioPersistido = query.getResultList();
            if (!usuarioPersistido.isEmpty()) {
                return usuarioPersistido.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

}
