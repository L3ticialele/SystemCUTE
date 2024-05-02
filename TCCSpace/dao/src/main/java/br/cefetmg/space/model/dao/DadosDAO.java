
package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.DadosDTO;
import br.cefetmg.space.model.idao.IDadosDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class DadosDAO implements IDadosDAO{
    
    @Override
    public void inserir(DadosDTO dados) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(dados);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public List<DadosDTO> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<DadosDTO> criteria = 
        entityManager.getCriteriaBuilder().createQuery(DadosDTO.class);
        criteria.select(criteria.from(DadosDTO.class));
        List<DadosDTO> dados = entityManager.createQuery(criteria).getResultList();
        
        for(DadosDTO dado : dados){
            System.out.println("Id dados: " + dado.getId() + " Velocidade do vento: " + dado.getVelocidadeVento() + " Umidade: " + dado.getUmidade() + " Massa do ar: " + dado.getMassasAr() + " Radiação: " + dado.getRadiacao() + " Pressão: " + dado.getPressao() + " Temperatura: " + dado.getTemperatura() + " Id CubSat: " + dado.getCubeSat().getId() + " Data de Obtenção: " + dado.getDataObtencao());
        }
        entityManager.close();
        return dados;
    }
       
    @Override
    public boolean delete(int idDado) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            DadosDTO dado = entityManager.find(DadosDTO.class, idDado);
            
            if(dado != null){
                entityManager.remove(dado);
                return true;
            }else{
                System.out.println("Não foi possível encontrar os dados com o id: " + idDado);
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
    public boolean atualizar(DadosDTO dados) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            DadosDTO dadosPersistidos = entityManager.find(DadosDTO.class, dados.getId());
            
            if(dadosPersistidos != null){
                dadosPersistidos.setId(dados.getId());
                dadosPersistidos.setCubeSat(dados.getCubeSat());
                dadosPersistidos.setMassasAr(dados.getMassasAr());
                dadosPersistidos.setPressao(dados.getPressao());
                dadosPersistidos.setRadiacao(dados.getRadiacao());
                dadosPersistidos.setTemperatura(dados.getTemperatura());
                dadosPersistidos.setVelocidadeVento(dados.getVelocidadeVento());
                dadosPersistidos.setUmidade(dados.getUmidade());
                dadosPersistidos.setDataObtencao(dados.getDataObtencao());
                return true;
            }else{
                System.out.println("Não foi possível encontrar os dados com o id: " + dados.getId());
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
