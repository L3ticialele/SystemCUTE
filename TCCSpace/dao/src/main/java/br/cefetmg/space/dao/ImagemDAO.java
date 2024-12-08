
package br.cefetmg.space.dao;

import br.cefetmg.space.entidades.Imagem;
import br.cefetmg.space.idao.IImagemDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class ImagemDAO implements IImagemDAO{
    @Override
    public Imagem procurarPorId(int id) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
             entityManager.getTransaction().begin();
             Query query = entityManager.createQuery("FROM Imagem AS i WHERE i.id =:id ");
             query.setParameter("id", id);
             List<Imagem> imagemPersistida = query.getResultList();
            if(!imagemPersistida.isEmpty()){
                return imagemPersistida.get(0);
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
    public boolean inserir(Imagem imagem) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(imagem);
            entityManager.getTransaction().commit();
            return true;
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public List<Imagem> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<Imagem> criteria = 
        entityManager.getCriteriaBuilder().createQuery(Imagem.class);
        criteria.select(criteria.from(Imagem.class));
        List<Imagem> imagens = entityManager.createQuery(criteria).getResultList();
        
        if(!imagens.isEmpty()){
            for(Imagem imagem : imagens){
                System.out.println("Id Imagem: " + imagem.getId()
                        + " Nome Imagem: " + imagem.getNome()
                );
            }
        }
        entityManager.close();
        return imagens;
    }
       
    @Override
    public boolean delete(int idImagem) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            Imagem imagem = entityManager.find(Imagem.class, idImagem);
            
            if(imagem != null){
                entityManager.remove(imagem);
                entityManager.getTransaction().commit();
                return true;
            }else{
                System.out.println("Não foi possível encontrar a imagem com o id: " + idImagem);
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
    public boolean atualizar(Imagem imagem) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            Imagem imagemPersistida = entityManager.find(Imagem.class, imagem.getId());
            
            if(imagemPersistida != null){
                imagemPersistida.setDados(imagem.getDados());
                imagemPersistida.setId(imagem.getId());
                imagemPersistida.setNome(imagem.getNome());
                entityManager.getTransaction().commit();
                return true;
            }else{
                System.out.println("Não foi possível encontrar a imagem com o id: " + imagem.getId());
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
