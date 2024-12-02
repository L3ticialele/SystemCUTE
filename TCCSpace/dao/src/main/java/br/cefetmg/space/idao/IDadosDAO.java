
package br.cefetmg.space.idao;

import br.cefetmg.space.entidades.Dados;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;

public interface IDadosDAO {
    void inserir(Dados dados) throws PersistenciaException;

    boolean atualizar(Dados Dados) throws PersistenciaException;

    boolean delete(int idDado) throws PersistenciaException;

    List<Dados> listarTodos() throws PersistenciaException;
    
    Dados procurarPorId(int id) throws PersistenciaException;
    
    Dados buscarDadoMaisRecente();
    
    void gerarDadosParaCubeSat(int idCubeSat);
}

