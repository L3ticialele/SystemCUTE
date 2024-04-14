package br.cefetmg.space.model.idao;

import br.cefetmg.space.model.dto.TransporteDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;


public interface ITransporteDAO {
    void inserir(TransporteDTO transporte) throws PersistenciaException;

    boolean atualizar(TransporteDTO transporte) throws PersistenciaException;

    boolean delete(int Ip) throws PersistenciaException;

    List<TransporteDTO> listarTodos() throws PersistenciaException;
}

