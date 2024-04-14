
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dao.TransporteDAO;
import br.cefetmg.space.model.dto.TransporteDTO;
import br.cefetmg.space.model.idao.ITransporteDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public class ManterTransporte implements IManterTransporte{
    private final ITransporteDAO transporteDAO;
     
     public ManterTransporte(){
         transporteDAO = new TransporteDAO();
     }
    
    @Override
    public void cadastrar(TransporteDTO transporte) throws PersistenciaException {
        transporteDAO.inserir(transporte);
    }
    
    @Override
    public boolean alterar(TransporteDTO transporte) throws PersistenciaException {
        boolean result = transporteDAO.atualizar(transporte);
        return result;
    }

    @Override
    public boolean excluir(int Ip) throws PersistenciaException {
       boolean result = transporteDAO.delete(Ip);
       return result;
    }

    @Override
    public List<TransporteDTO> pesquisarTodos() throws PersistenciaException {
       List<TransporteDTO> result = transporteDAO.listarTodos();
       return result;
    }
}
