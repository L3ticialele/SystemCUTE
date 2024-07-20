
package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.CubeSatDTO;
import br.cefetmg.space.model.idao.ICubeSatDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;


public class CubeSatDAO implements ICubeSatDAO{
    
    @Override
    public boolean inserir(CubeSatDTO cube) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(cube);
            entityManager.getTransaction().commit();
            System.out.println("CubeSat cadastrado!");
            return true;
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public List<CubeSatDTO> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<CubeSatDTO> criteria = 
        entityManager.getCriteriaBuilder().createQuery(CubeSatDTO.class);
        criteria.select(criteria.from(CubeSatDTO.class));
        List<CubeSatDTO> cubes = entityManager.createQuery(criteria).getResultList();
        
        if(!cubes.isEmpty()){
        for(CubeSatDTO cube : cubes){
                System.out.print(
                          "Id: " + cube.getId() 
                        + " Nome: " + cube.getNome()
                        + " Cadastro: " + cube.getDataCadastro() 
                        + " Descrição: " + cube.getDescricao() 
                        + " Status: " + cube.getStatus()
                );
                if(cube.getEquipe() == null)
                    System.out.println(" Criador: " + cube.getUsuario().getNome());
                else
                    System.out.println(" Equipe: " + cube.getEquipe());
                }
        }
        entityManager.close();
        return cubes;
    }
       
    @Override
    public boolean delete(int idCube) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            CubeSatDTO cube = entityManager.find(CubeSatDTO.class, idCube);
            
            if(cube != null){
                entityManager.remove(cube);
                entityManager.getTransaction().commit();
                return true;
            }else{
                System.out.println("Não foi possível encontrar o CubeSat com o id: " + idCube);
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
    public boolean atualizar(CubeSatDTO cube) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            CubeSatDTO cubePersistido = entityManager.find(CubeSatDTO.class, cube.getId());
            
            if(cubePersistido != null){
                cubePersistido.setId(cube.getId());
                cubePersistido.setNome(cube.getNome());
                cubePersistido.setDataCadastro(cube.getDataCadastro());
                cubePersistido.setDescricao(cube.getDescricao());
                cubePersistido.setTodosDados(cube.getDados());
                cubePersistido.setPessoa(cube.getUsuario());
                cubePersistido.setEquipe(cube.getEquipe());
                cubePersistido.setStatus(cube.getStatus());
                entityManager.getTransaction().commit();
                return true;
            }else{
                System.out.println("Não foi possível encontrar o CubeSat com o id: " + cube.getId());
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
    public CubeSatDTO procurarPorId(int id) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
             entityManager.getTransaction().begin();
             Query query = entityManager.createQuery("FROM CubeSatDTO AS u WHERE u.id =:id ");
             query.setParameter("id", id);
             List<CubeSatDTO> cubesatPersistido = query.getResultList();
            if(!cubesatPersistido.isEmpty()){
                return cubesatPersistido.get(0);
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
    public CubeSatDTO procurarPorNome(String nomeC) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
             entityManager.getTransaction().begin();
             Query query = entityManager.createQuery("FROM CubeSatDTO AS c WHERE c.nome =:nomeC");
             query.setParameter("nomeC", nomeC);
             List<CubeSatDTO> cubesatPersistido = query.getResultList();
            if(!cubesatPersistido.isEmpty()){
                return cubesatPersistido.get(0);
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
