
package br.cefetmg.space.controller;

import br.cefetmg.space.dao.DadosDAO;
import br.cefetmg.space.entidades.Dados;
import br.cefetmg.space.idao.IDadosDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;

public class ManterDados implements IManterDados{
     private final IDadosDAO dadosDAO;
     
     public ManterDados(){
         dadosDAO = new DadosDAO();
     }
    
    @Override
    public void cadastrar(Dados dados) throws PersistenciaException {
        dadosDAO.inserir(dados);
    }
    
    @Override
    public boolean alterar(Dados dados) throws PersistenciaException {  
        boolean result = dadosDAO.atualizar(dados);
        return result;
    }

    @Override
    public boolean excluir(int idDados) throws PersistenciaException {
       boolean result = dadosDAO.delete(idDados);
       return result;
    }

    @Override
    public List<Dados> pesquisarTodos() throws PersistenciaException {
       List<Dados> result = dadosDAO.listarTodos();
       return result;
    }
}
