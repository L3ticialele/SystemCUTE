
package br.cefetmg.space.dao;

import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Dados;
import br.cefetmg.space.idao.IDadosDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class DadosDAO implements IDadosDAO{
    @Override
    public void gerarDadosParaCubeSat(int idCubeSat) {
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            // Gera dados simulados
            Dados dados = new Dados();
            dados.setId(idCubeSat);
            dados.setAcelerometroX((float) (Math.random() * 10));  // Convertendo para float
            dados.setAcelerometroY((float) (Math.random() * 10));  // Convertendo para float
            dados.setAcelerometroZ((float) (Math.random() * 10));  // Convertendo para float
            dados.setAnguloX((float) (Math.random() * 360));  // Convertendo para float
            dados.setAnguloY((float) (Math.random() * 360));  // Convertendo para float
            dados.setAnguloZ((float) (Math.random() * 360));  // Convertendo para float
            dados.setAltitude((float) (Math.random() * 10000));  // Convertendo para float
            dados.setBateria((float) (Math.random() * 100));  // Convertendo para float
            dados.setCorrenteBateria((float) (Math.random() * 5));  // Convertendo para float
            dados.setCorrentePlacaSolar((float) (Math.random() * 10));  // Convertendo para float
            /*
            dados.setGas1((float) (Math.random() * 100));  // Convertendo para float
            dados.setGas2((float) (Math.random() * 100));  // Convertendo para float
            */
            dados.setLuz1((float) (Math.random() * 1000));  // Convertendo para float
            dados.setLuz2((float) (Math.random() * 1000));  // Convertendo para float
            dados.setPontoOrvalho((float) (Math.random() * 50));  // Convertendo para float
            dados.setPressao((float) (Math.random() * 1000));  // Convertendo para float
            dados.setSensorUV((float) (Math.random() * 10));  // Convertendo para float
            dados.setTemperaturaExterna((float) (Math.random() * 50));  // Convertendo para float
            dados.setTemperaturaInterna((float) (Math.random() * 50));  // Convertendo para float
            dados.setTensaoBateria((float) (Math.random() * 14));  // Convertendo para float
            dados.setTensaoPlacaSolar((float) (Math.random() * 30));  // Convertendo para float
            dados.setUmidade((float) (Math.random() * 100));  // Convertendo para float
            /*
            dados.setVelocidade((float) (Math.random() * 1000));  // Convertendo para float
            dados.setVelocidadeAngularX((float) (Math.random() * 10));  // Convertendo para float
            dados.setVelocidadeAngularY((float) (Math.random() * 10));  // Convertendo para float
            dados.setVelocidadeAngularZ((float) (Math.random() * 10));  // Convertendo para float
            */

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dados.setDataObtencao(now.format(formatter));

            // Persiste os dados no banco de dados
            entityManager.persist(dados);
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @Override
    public Dados buscarDadoMaisRecente() {
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // Consulta para obter o dado mais recente da tabela 'dados' ordenado pelo ID decrescente
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("SELECT d FROM Dados d ORDER BY d.id DESC", Dados.class);
             List<Dados> dadosPersistidos = query.getResultList();
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
    @Override
    public boolean inserir(Dados dados) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(dados);
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
    public List<Dados> procurarPorCubeSat(CubeSat cubesat) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
             entityManager.getTransaction().begin();
             Query query = entityManager.createQuery("FROM Dados AS d WHERE d.cubesat =:cubesat ");
             query.setParameter("cubesat", cubesat);
             List<Dados> dadosPersistidos = query.getResultList();
            if(!dadosPersistidos.isEmpty()){
                return dadosPersistidos;
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
    public List<Dados> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<Dados> criteria = 
        entityManager.getCriteriaBuilder().createQuery(Dados.class);
        criteria.select(criteria.from(Dados.class));
        List<Dados> dados = entityManager.createQuery(criteria).getResultList();
        
        if(!dados.isEmpty()){
            for(Dados dado : dados){
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
                        /*
                        + " Gás 1: " + dado.getGas1()
                        + " Gás 2: " + dado.getGas2() 
                        */
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
                        /*
                        + " Velocidade: " + dado.getVelocidade()
                        + " Velocidade Angular X: " + dado.getVelocidadeAngularX()
                        + " Velocidade Angular Y: " + dado.getVelocidadeAngularY()
                        + " Velocidade Angular Z: " + dado.getVelocidadeAngularZ()
                        */
                        + " Cubesat: " + dado.getCubeSat().getNome() 
                        + " Data: " + dado.getDataObtencao()
                        + " Hora: " + dado.getHora()
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
            Dados dado = entityManager.find(Dados.class, idDado);
            
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
    public boolean atualizar(Dados dados) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            Dados dadosPersistidos = entityManager.find(Dados.class, dados.getId());
            
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
                /*
                dadosPersistidos.setGas1(dados.getGas1());
                dadosPersistidos.setGas2(dados.getGas2());
                */
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
                /*
                dadosPersistidos.setVelocidade(dados.getVelocidade());
                dadosPersistidos.setVelocidadeAngularX(dados.getVelocidadeAngularX());
                dadosPersistidos.setVelocidadeAngularY(dados.getVelocidadeAngularY());
                dadosPersistidos.setVelocidadeAngularZ(dados.getVelocidadeAngularZ());
                */
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
    public Dados procurarPorId(int id) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
             entityManager.getTransaction().begin();
             Query query = entityManager.createQuery("FROM Dados AS d WHERE d.id =:id ");
             query.setParameter("id", id);
             List<Dados> dadosPersistidos = query.getResultList();
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
