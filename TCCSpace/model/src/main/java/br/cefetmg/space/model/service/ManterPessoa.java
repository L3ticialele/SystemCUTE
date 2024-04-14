
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dao.PessoaDAO;
import br.cefetmg.space.model.dto.PessoaDTO;
import br.cefetmg.space.model.idao.IPessoaDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public class ManterPessoa implements IManterPessoa{
     private final IPessoaDAO pessoaDAO;
     
     public ManterPessoa(){
         pessoaDAO = new PessoaDAO();
     }
    
    @Override
    public void cadastrar(PessoaDTO pessoa) throws PersistenciaException {
        pessoaDAO.inserir(pessoa);
    }
    
    @Override
    public boolean alterar(PessoaDTO pessoa) throws PersistenciaException{
        boolean result = pessoaDAO.atualizar(pessoa);
        return result;
    }

    @Override
    public boolean excluir(int idPessoa) throws PersistenciaException{
       boolean result = pessoaDAO.delete(idPessoa);
       return result;
    }

    @Override
    public List<PessoaDTO> pesquisarTodos() throws PersistenciaException {
       List<PessoaDTO> result = pessoaDAO.listarTodos();
       return result;
    }
}
