package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.TransporteDTO;
import br.cefetmg.space.model.idao.ITransporteDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class TransporteDAO implements ITransporteDAO{
    
    @Override
    public void inserir(TransporteDTO transporte) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(transporte);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public List<TransporteDTO> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<TransporteDTO> criteria = 
        entityManager.getCriteriaBuilder().createQuery(TransporteDTO.class);
        criteria.select(criteria.from(TransporteDTO.class));
        List<TransporteDTO> transportes = entityManager.createQuery(criteria).getResultList();
        
        for(TransporteDTO transporte : transportes){
            System.out.println("IP: " + transporte.getIP() + " Saída: " + transporte.getSaida() + " Entrada: " + transporte.getEntrada());
        }
        entityManager.close();
        return transportes;
    }
       
    @Override
    public boolean delete(int Ip) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            TransporteDTO transporte = entityManager.find(TransporteDTO.class, Ip);
            
            if(transporte != null){
                entityManager.remove(transporte);
                return true;
            }else{
                System.out.println("Não foi possíbel encontrar o transporte com o IP: " + Ip);
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
    public boolean atualizar(TransporteDTO transporte) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            TransporteDTO transportePersistido = entityManager.find(TransporteDTO.class, transporte.getIP());
            
            if(transportePersistido != null){
                transportePersistido.setIP(transporte.getIP());
                transportePersistido.setEntrada(transporte.getEntrada());
                transportePersistido.setSaida(transporte.getSaida());
                return true;
            }else{
                System.out.println("Não foi possível encontrar o tranporte com o IP: " + transporte.getIP());
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
