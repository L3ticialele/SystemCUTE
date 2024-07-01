
package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.DadosDTO;
import br.cefetmg.space.model.idao.IDadosDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
        
        if(!dados.isEmpty()){
            for(DadosDTO dado : dados){
                System.out.println("Id dados: " + dado.getId() 
                        + " Acelerômetro X: " + dado.getAcelerometroX() 
                        + " Acelerômetro Y: " + dado.getAcelerometroY()
                        + " Acelerômetro Z: " + dado.getAcelerometroZ()
                        + " Ângulo X: " + dado.getAnguloX()
                        + " Ângulo Y: " + dado.getAnguloY()
                        + " Ângulo Z: " + dado.getAnguloZ()
                        + " Altitude: " + dado.getAltitude()
                        + " Bateria: " + dado.getBateria() + "%"
                        + " Corrente Bateria: " + dado.getCorrenteBateria()
                        + " Corrente da Placa Solar: " + dado.getCorrentePlacaSolar()
                        + " Gás 1: " + dado.getGas1()
                        + " Gás 2: " + dado.getGas2() 
                        + " Luz 1: " + dado.getLuz1()
                        + " Luz 2: " + dado.getLuz2()
                        + " Ponto do Orvalho: " + dado.getPontoOrvalho()
                        + " Pressão: " + dado.getPressao()
                        + " Sensor UV: " + dado.getSensorUV()
                        + " Temperatura Interna: " + dado.getTemperaturaInterna()
                        + " Temperatura Externa: " + dado.getTemperaturaExterna()
                        + " Tensão da Bateria: " + dado.getTensaoBateria()
                        + " Tensão da Placa Solar: " + dado.getTensaoPlacaSolar()
                        + " Umidade: " + dado.getUmidade()
                        + " Velocidade: " + dado.getVelocidade()
                        + " Velocidade Angular X: " + dado.getVelocidadeAngularX()
                        + " Velocidade Angular Y: " + dado.getVelocidadeAngularY()
                        + " Velocidade Angular Z: " + dado.getVelocidadeAngularZ()
                        + " Cubesat: " + dado.getCubeSat().getNome() 
                        + " Data: " + dado.getDataObtencao()
                );
            }
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
                entityManager.getTransaction().commit();
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
                dadosPersistidos.setAcelerometroX(dados.getAcelerometroX());
                dadosPersistidos.setAcelerometroY(dados.getAcelerometroY());
                dadosPersistidos.setAcelerometroZ(dados.getAcelerometroZ());
                dadosPersistidos.setAnguloX(dados.getAnguloX());
                dadosPersistidos.setAnguloY(dados.getAnguloY());
                dadosPersistidos.setAnguloZ(dados.getAnguloZ());
                dadosPersistidos.setAltitude(dados.getAltitude());
                dadosPersistidos.setBateria(dados.getBateria());
                dadosPersistidos.setCorrenteBateria(dados.getCorrenteBateria());
                dadosPersistidos.setGas1(dados.getGas1());
                dadosPersistidos.setGas2(dados.getGas2());
                dadosPersistidos.setLuz1(dados.getLuz1());
                dadosPersistidos.setLuz2(dados.getLuz2());
                dadosPersistidos.setPontoOrvalho(dados.getPontoOrvalho());
                dadosPersistidos.setPressao(dados.getPressao());
                dadosPersistidos.setSensorUV(dados.getSensorUV());
                dadosPersistidos.setTemperaturaExterna(dados.getTemperaturaExterna());
                dadosPersistidos.setTemperaturaInterna(dados.getTemperaturaInterna());
                dadosPersistidos.setTensaoBateria(dados.getTensaoBateria());
                dadosPersistidos.setTensaoPlacaSolar(dados.getTensaoPlacaSolar());
                dadosPersistidos.setUmidade(dados.getUmidade());
                dadosPersistidos.setVelocidade(dados.getVelocidade());
                dadosPersistidos.setVelocidadeAngularX(dados.getVelocidadeAngularX());
                dadosPersistidos.setVelocidadeAngularY(dados.getVelocidadeAngularY());
                dadosPersistidos.setVelocidadeAngularZ(dados.getVelocidadeAngularZ());
                entityManager.getTransaction().commit();
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
    
    @Override
    public DadosDTO procurarPorId(int id) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
             entityManager.getTransaction().begin();
             Query query = entityManager.createQuery("FROM DadosDTO AS d WHERE d.id =:id ");
             query.setParameter("id", id);
             List<DadosDTO> dadosPersistidos = query.getResultList();
            if(!dadosPersistidos.isEmpty()){
                return dadosPersistidos.get(0);
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
}
