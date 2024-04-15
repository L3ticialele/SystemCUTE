
package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.PessoaDTO;
import br.cefetmg.space.model.idao.IPessoaDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class PessoaDAO implements IPessoaDAO{
    @Override
    public void inserir(PessoaDTO pessoa) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(pessoa);
            entityManager.getTransaction().commit();
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public List<PessoaDTO> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<PessoaDTO> criteria = 
        entityManager.getCriteriaBuilder().createQuery(PessoaDTO.class);
        criteria.select(criteria.from(PessoaDTO.class));
        List<PessoaDTO> pessoas = entityManager.createQuery(criteria).getResultList();
        
        for(PessoaDTO pessoa : pessoas){
            System.out.print("Id equipe: " + pessoa.getId() + " Nome: " + pessoa.getNome() + " CubeSats feitos/em andamento: " + pessoa.quantCubeSat() + " Telefone: " + pessoa.getTelefone() + " Participa de quantas equipes: " + pessoa.quantEquipes());
            if(pessoa.isAdministrador())
                System.out.println("Administrador: sim");
            else
                System.out.println("Administrador: não");
        }
        entityManager.close();
        return pessoas;
    }
       
    @Override
    public boolean delete(int idPessoa) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            PessoaDTO pessoa = entityManager.find(PessoaDTO.class, idPessoa);
            
            if(pessoa != null){
                entityManager.remove(pessoa);
                return true;
            }else{
                System.out.println("Não foi possível encontrar o usuário com o id: " + idPessoa);
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
    public boolean atualizar(PessoaDTO pessoa) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            PessoaDTO pessoaPersistida = entityManager.find(PessoaDTO.class, pessoa.getId());
            
            if(pessoaPersistida != null){
                pessoaPersistida.setEmail(pessoa.getEmail());
                pessoaPersistida.setId(pessoa.getId());
                pessoaPersistida.setNome(pessoa.getNome());
                pessoaPersistida.setSenha(pessoa.getSenha());
                pessoaPersistida.setUserName(pessoa.getUserName());
                pessoaPersistida.setAdministrador(pessoa.isAdministrador());
                pessoaPersistida.setCpf(pessoa.getCpf());
                pessoaPersistida.setTelefone(pessoa.getTelefone());
                pessoaPersistida.setEquipes(pessoa.getEquipes());
                pessoaPersistida.setCubeSat(pessoa.getCubeSat());
                return true;
            }else{
                System.out.println("Não foi possível encontrar o usuário com o id: " + pessoa.getId());
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
