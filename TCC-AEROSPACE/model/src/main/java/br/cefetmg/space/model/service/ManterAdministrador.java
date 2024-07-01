
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dao.AdministradorDAO;
import br.cefetmg.space.model.dto.AdministradorDTO;
import br.cefetmg.space.model.idao.IAdministradorDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public class ManterAdministrador implements IManterAdministrador{
     private final IAdministradorDAO admDAO;
     
     public ManterAdministrador(){
         admDAO = new AdministradorDAO();
     }
    
    @Override
    public void cadastrar(AdministradorDTO adm) throws PersistenciaException {
        admDAO.inserir(adm);
    }
    
    @Override
    public boolean alterar(AdministradorDTO adm) throws PersistenciaException{
        boolean result = admDAO.atualizar(adm);
        return result;
    }

    @Override
    public boolean excluir(int idAdm) throws PersistenciaException{
       boolean result = admDAO.delete(idAdm);
       return result;
    }

    @Override
    public List<AdministradorDTO> pesquisarTodos() throws PersistenciaException {
       List<AdministradorDTO> result = admDAO.listarTodos();
       return result;
    }
}
