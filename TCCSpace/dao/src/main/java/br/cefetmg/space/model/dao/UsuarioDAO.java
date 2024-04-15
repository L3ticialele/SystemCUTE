
package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.IUsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        
        for(UsuarioDTO usuario : usuarios){
            System.out.print("Id equipe: " + usuario.getId() + " Nome: " + usuario.getNome() + " CubeSats feitos/em andamento: " + usuario.quantCubeSat() + " Telefone: " + usuario.getTelefone() + " Participa de quantas equipes: " + usuario.quantEquipes());
            if(usuario.isAdministrador())
                System.out.println("Administrador: sim");
            else
                System.out.println("Administrador: não");
        }
        entityManager.close();
        return usuarios;
    }
       
    @Override
    public boolean delete(int idUsuario) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            UsuarioDTO usuario = entityManager.find(UsuarioDTO.class, idUsuario);
            
            if(usuario != null){
                entityManager.remove(usuario);
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
    public boolean atualizar(UsuarioDTO usuario) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            UsuarioDTO usuarioPersistido = entityManager.find(UsuarioDTO.class, usuario.getId());
            
            if(usuarioPersistido != null){
                usuarioPersistido.setEmail(usuario.getEmail());
                usuarioPersistido.setId(usuario.getId());
                usuarioPersistido.setUserName(usuario.getUserName());
                usuarioPersistido.setNome(usuario.getNome());
                usuarioPersistido.setSenha(usuario.getSenha());
                usuarioPersistido.setAdministrador(usuario.isAdministrador());
                usuarioPersistido.setCpf(usuario.getCpf());
                usuarioPersistido.setTelefone(usuario.getTelefone());
                usuarioPersistido.setEquipes(usuario.getEquipes());
                usuarioPersistido.setCubeSat(usuario.getCubeSat());
                return true;
            }else{
                System.out.println("Não foi possível encontrar o usuário com o id: " + usuario.getId());
                return false;
            }
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
}
