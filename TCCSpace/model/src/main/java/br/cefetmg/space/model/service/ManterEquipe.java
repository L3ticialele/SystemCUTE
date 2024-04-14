
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dao.EquipeDAO;
import br.cefetmg.space.model.dto.EquipeDTO;
import br.cefetmg.space.model.idao.IEquipeDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public class ManterEquipe implements IManterEquipe{
     private final IEquipeDAO equipeDAO;
     
     public ManterEquipe(){
         equipeDAO = new EquipeDAO();
     }
    
    @Override
    public void cadastrar(EquipeDTO equipe) throws PersistenciaException {
        equipeDAO.inserir(equipe);
    }
    
    @Override
    public boolean alterar(EquipeDTO equipe) throws PersistenciaException{
        boolean result = equipeDAO.atualizar(equipe);
        return result;
    }

    @Override
    public boolean excluir(int idEquipe) throws PersistenciaException{
       boolean result = equipeDAO.delete(idEquipe);
       return result;
    }

    @Override
    public List<EquipeDTO> pesquisarTodos() throws PersistenciaException {
       List<EquipeDTO> result =  equipeDAO.listarTodos();
       return result;
    }
}
