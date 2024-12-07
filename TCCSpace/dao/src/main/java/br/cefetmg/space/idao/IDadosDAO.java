package br.cefetmg.space.idao;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Dados;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;

/*interface com as funções da classe DadosDAO*/
public interface IDadosDAO {
    boolean inserir(Dados dados) throws PersistenciaException;

    boolean atualizar(Dados Dados) throws PersistenciaException;

    boolean delete(int idDado) throws PersistenciaException;

    List<Dados> listarTodos() throws PersistenciaException;
    
    Dados procurarPorId(int id) throws PersistenciaException;
    
    Dados buscarDadoMaisRecente();
    
    List<Dados> procurarPorCubeSat(CubeSat cubesat) throws PersistenciaException;
}

