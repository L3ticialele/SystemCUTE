
package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.IUsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class UsuarioDAO implements IUsuarioDAO{
    @Override
    public void inserir(UsuarioDTO usuario) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
            System.out.println("Usuário cadastrado!");
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public List<UsuarioDTO> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<UsuarioDTO> criteria = 
        entityManager.getCriteriaBuilder().createQuery(UsuarioDTO.class);
        criteria.select(criteria.from(UsuarioDTO.class));
        List<UsuarioDTO> usuarios = entityManager.createQuery(criteria).getResultList();
        
        if(!usuarios.isEmpty()){
            for(UsuarioDTO usuario : usuarios){
                System.out.print(
                          "Id usuario: " + usuario.getId() 
                        + " Nome: " + usuario.getNome() 
                        + " CubeSats feitos/em andamento: " + usuario.quantCubeSat() 
                        + " Telefone: " + usuario.getTelefone() 
                        + " Participa de quantas equipes: " + usuario.quantEquipes()
                );
                if(usuario.isAdministrador())
                    System.out.println(" Administrador: sim");
                else
                    System.out.println(" Administrador: não");
            }
        }else{
            System.out.println(" Não há usuários cadastrados no sistema.");
        }
        entityManager.close();
        return usuarios;
    }
       
    @Override
    public void delete(int idUsuario) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            UsuarioDTO usuario = entityManager.find(UsuarioDTO.class, idUsuario);
            
            if(usuario != null){
                entityManager.remove(usuario);
                entityManager.getTransaction().commit();
            }else{
                System.out.println("Não foi possível encontrar o usuário com o id: " + idUsuario);
            }
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public UsuarioDTO procurarPorUserName(String user) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
             entityManager.getTransaction().begin();
             Query query = entityManager.createQuery("FROM UsuarioDTO AS u WHERE u.username =:user ");
             query.setParameter("user", user);
             List<UsuarioDTO> usuarioPersistido = query.getResultList();
            if(!usuarioPersistido.isEmpty()){
                return usuarioPersistido.get(0);
            }else{
                return null;
            }
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public UsuarioDTO procurarPorEmail(String email) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
             entityManager.getTransaction().begin();
             Query query = entityManager.createQuery("FROM UsuarioDTO AS u WHERE u.email =:email ");
             query.setParameter("email", email);
             List<UsuarioDTO> usuarioPersistido = query.getResultList();
            if(!usuarioPersistido.isEmpty()){
                return usuarioPersistido.get(0);
            }else{
                return null;
            }
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
     @Override
    public boolean validarlogin(UsuarioDTO usuario) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
             entityManager.getTransaction().begin();
             Query query = entityManager.createQuery("SELECT u.email, u.senha FROM UsuarioDTO AS u WHERE u.email = :email AND u.senha = :senha");
             query.setParameter("email", usuario.getEmail());
             query.setParameter("senha", usuario.getSenha());
             List<UsuarioDTO> usuarioPersistido = query.getResultList();
            if(!usuarioPersistido.isEmpty()){
                return true;
            }else{
                return false;
            }
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public boolean atualizar(int idUsuario, UsuarioDTO usuario) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            UsuarioDTO usuarioPersistido = entityManager.find(UsuarioDTO.class, idUsuario);
            
            if(usuarioPersistido != null){
                usuarioPersistido.setEmail(usuario.getEmail());
                usuarioPersistido.setNome(usuario.getNome());
                usuarioPersistido.setSenha(usuario.getSenha());
                usuarioPersistido.setAdministrador(usuario.isAdministrador());
                usuarioPersistido.setTelefone(usuario.getTelefone());
                usuarioPersistido.setEquipes(usuario.getEquipes());
                usuarioPersistido.setCubeSat(usuario.getCubeSat());
                entityManager.getTransaction().commit();
                return true;
            }else{
                System.out.println("Não foi possível encontrar o usuário com o id: " + idUsuario);
                return false;
            }
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }

    @Override
    public UsuarioDTO procurarPorId(int id) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public UsuarioDTO validarlogin(String email, String senha) throws PersistenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
