
package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.AdministradorDTO;
import br.cefetmg.space.model.dto.EquipeDTO;
import br.cefetmg.space.model.idao.IAdministradorDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class AdministradorDAO implements IAdministradorDAO {
    @Override
    public void inserir(AdministradorDTO adm) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(adm);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    
    @Override
    public List<AdministradorDTO> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<AdministradorDTO> criteria = 
        entityManager.getCriteriaBuilder().createQuery(AdministradorDTO.class);
        criteria.select(criteria.from(AdministradorDTO.class));
        List<AdministradorDTO> adms = entityManager.createQuery(criteria).getResultList();
        List<EquipeDTO> equipes;
        
        if(!adms.isEmpty()){
            for(AdministradorDTO adm : adms){
                System.out.print(
                        "Id equipe: " + adm.getId() 
                        + " Nome: " + adm.getNome() 
                        + " CubeSats feitos/em andamento: " + adm.quantCubeSat() 
                        + " Telefone: " + adm.getTelefone() 
                        + " Participa de quantas equipes: " + adm.quantEquipes() 
                        + " Administra " + adm.quantEquipesAdministradas() + " equipes"
                );
                equipes = adm.getEquipesA();
                System.out.println("Equipes administradas por:");
                for(EquipeDTO equipe : equipes){
                    System.out.println(equipe.getNome());
                }
            }
        }
        entityManager.close();
        return adms;
    }
       
    @Override
    public boolean delete(int idAdm) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            AdministradorDTO adm = entityManager.find(AdministradorDTO.class, idAdm);
            
            if(adm != null){
                entityManager.remove(adm);
                entityManager.getTransaction().commit();
                return true;
            }else{
                System.out.println("Não foi possível encontrar o usuário com o id: " + idAdm);
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
    public boolean atualizar(AdministradorDTO adm) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            AdministradorDTO admPersistido = entityManager.find(AdministradorDTO.class, adm.getId());
            
            if(admPersistido != null){
                admPersistido.setEmail(adm.getEmail());
                admPersistido.setUserName(adm.getUserName());
                admPersistido.setNome(adm.getNome());
                admPersistido.setSenha(adm.getSenha());
                admPersistido.setTelefone(adm.getTelefone());
                admPersistido.setEquipes(adm.getEquipes());
                admPersistido.setCubeSat(adm.getCubeSat());
                admPersistido.setAdministrador(true);
                entityManager.getTransaction().commit();
                return true;
            }else{
                System.out.println("Não foi possível encontrar o usuário com o id: " + adm.getId());
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
