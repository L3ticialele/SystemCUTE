
package br.cefetmg.space.controller;

import br.cefetmg.space.entidades.Dados;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;


public interface IManterDados {
    public void cadastrar(Dados dados) throws PersistenciaException;
    public boolean alterar(Dados dados) throws PersistenciaException;
    public boolean excluir(int idDados) throws PersistenciaException;
    public List<Dados> pesquisarTodos() throws PersistenciaException;
}
