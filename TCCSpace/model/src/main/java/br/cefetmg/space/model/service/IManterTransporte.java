
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dto.TransporteDTO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public interface IManterTransporte {
    public void cadastrar(TransporteDTO transporte) throws PersistenciaException;
    public boolean alterar(TransporteDTO transporte) throws PersistenciaException;
    public boolean excluir(int Ip) throws PersistenciaException;
    public List<TransporteDTO> pesquisarTodos() throws PersistenciaException;
}
