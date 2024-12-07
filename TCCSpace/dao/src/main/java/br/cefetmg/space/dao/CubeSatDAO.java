package br.cefetmg.space.dao;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.idao.ICubeSatDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/*Esta classe inclui as funções que podem ser realizadas com o objeto CubeSat*/
public class CubeSatDAO implements ICubeSatDAO {
    
    //Inserir um novo cubesat a lista de cubesats de um usuário
    @Override
    public boolean inserir(CubeSat cube) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cube);
            entityManager.getTransaction().commit();
            System.out.println("CubeSat cadastrado!");
            return true;
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
    
    //Lista todos os cubesats de um usúario
    @Override
    public List<CubeSat> listarTodos() throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<CubeSat> criteria
                = entityManager.getCriteriaBuilder().createQuery(CubeSat.class);
        criteria.select(criteria.from(CubeSat.class));
        List<CubeSat> cubes = entityManager.createQuery(criteria).getResultList();

        if (!cubes.isEmpty()) {
            for (CubeSat cube : cubes) {
                System.out.print(
                        "Id: " + cube.getId()
                        + " Nome: " + cube.getNome()
                        + " Cadastro: " + cube.getDataCadastro()
                        + " Descrição: " + cube.getDescricao()
                );
            }
        }
        entityManager.close();
        return cubes;
    }

    //Deleta um cubesat, de um usuário, conforme o id
    @Override
    public boolean delete(int idCube) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            CubeSat cube = entityManager.find(CubeSat.class, idCube);

            if (cube != null) {
                entityManager.remove(cube);
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o CubeSat com o id: " + idCube);
                return false;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
    
    //Atualiza o alguma configuração do cubesat, se alterado
    @Override
    public boolean atualizar(CubeSat cube) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            CubeSat cubePersistido = entityManager.find(CubeSat.class, cube.getId());

            if (cubePersistido != null) {
                cubePersistido.setId(cube.getId());
                cubePersistido.setNome(cube.getNome());
                cubePersistido.setDataCadastro(cube.getDataCadastro());
                cubePersistido.setDescricao(cube.getDescricao());
                cubePersistido.setTodosDados(cube.getDados());
                cubePersistido.setPessoa(cube.getUsuario());
                entityManager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Não foi possível encontrar o CubeSat com o id: " + cube.getId());
                return false;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
    
    //Procura um cubeSat, de um usuário, pelo ID
    @Override
    public CubeSat procurarPorId(int id) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM CubeSat AS u WHERE u.id =:id ");
            query.setParameter("id", id);
            List<CubeSat> cubesatPersistido = query.getResultList();
            if (!cubesatPersistido.isEmpty()) {
                return cubesatPersistido.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }
    
    //Procura um cubeSat, de um usuário, pelo nome
    @Override
    public CubeSat procurarPorNome(String nomeC) throws PersistenciaException {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Query query = entityManager.createQuery("FROM CubeSat AS c WHERE c.nome =:nomeC");
            query.setParameter("nomeC", nomeC);
            List<CubeSat> cubesatPersistido = query.getResultList();
            if (!cubesatPersistido.isEmpty()) {
                return cubesatPersistido.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            throw ex;
        } finally {
            entityManager.close();
        }
    }

}
