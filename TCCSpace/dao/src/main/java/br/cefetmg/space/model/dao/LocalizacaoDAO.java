package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.LocalizacaoDTO;
import br.cefetmg.space.model.idao.ILocalizacaoDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class LocalizacaoDAO implements ILocalizacaoDAO{
    
    @Override
    public void inserir(LocalizacaoDTO localizacao) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(localizacao);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public List<LocalizacaoDTO> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<LocalizacaoDTO> criteria = 
        entityManager.getCriteriaBuilder().createQuery(LocalizacaoDTO.class);
        criteria.select(criteria.from(LocalizacaoDTO.class));
        List<LocalizacaoDTO> localizacoes = entityManager.createQuery(criteria).getResultList();
        
        for(LocalizacaoDTO localizacao : localizacoes){
            System.out.println("Id: " + localizacao.getId() + " Altitude: " + localizacao.getAltitude()+ " Longitude: " + localizacao.getLongitude() + " Id do CubSat: " + localizacao.getIdCubeSat());
        }
        
        entityManager.close();
        return localizacoes;
    }
       
    @Override
    public boolean delete(int idLocalizacao) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            LocalizacaoDTO localizacao = entityManager.find(LocalizacaoDTO.class, idLocalizacao);
            
            if(localizacao != null){
                entityManager.remove(localizacao);
                return true;
            }else{
                System.out.println("Não foi possível encontrar a localização com o id: " + idLocalizacao);
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
    public boolean atualizar(LocalizacaoDTO localizacao) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            LocalizacaoDTO localizacaoPersistida = entityManager.find(LocalizacaoDTO.class, localizacao.getId());
            
            if(localizacaoPersistida != null){
                localizacaoPersistida.setId(localizacao.getId());
                localizacaoPersistida.setAltitude(localizacao.getAltitude());
                localizacaoPersistida.setLongitude(localizacao.getLongitude());
                localizacaoPersistida.setCubeSat(localizacao.getCubeSat());
                return true;
            }else{
                System.out.println("Não foi possível encontrar a localização com o id: " + localizacao.getId());
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
