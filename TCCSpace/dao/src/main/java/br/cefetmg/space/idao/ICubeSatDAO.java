package br.cefetmg.space.idao;

import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;


public interface ICubeSatDAO{
    boolean inserir(CubeSat cube) throws PersistenciaException;

    boolean atualizar(CubeSat cube) throws PersistenciaException;

    boolean delete(int cube) throws PersistenciaException;

    List<CubeSat> listarTodos() throws PersistenciaException;
    
    CubeSat procurarPorId(int id) throws PersistenciaException;
    
    CubeSat procurarPorNome(String nomeC) throws PersistenciaException;
}
