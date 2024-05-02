
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dao.DadosDAO;
import br.cefetmg.space.model.dto.DadosDTO;
import br.cefetmg.space.model.idao.IDadosDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public class ManterDados implements IManterDados{
     private final IDadosDAO dadosDAO;
     
     public ManterDados(){
         dadosDAO = new DadosDAO();
     }
    
    @Override
    public void cadastrar(DadosDTO dados) throws PersistenciaException {
        dadosDAO.inserir(dados);
    }
    
    @Override
    public boolean alterar(DadosDTO dados) throws PersistenciaException {  
        boolean result = dadosDAO.atualizar(dados);
        return result;
    }

    @Override
    public boolean excluir(int idDados) throws PersistenciaException {
       boolean result = dadosDAO.delete(idDados);
       return result;
    }

    @Override
    public List<DadosDTO> pesquisarTodos() throws PersistenciaException {
       List<DadosDTO> result = dadosDAO.listarTodos();
       return result;
    }
}
