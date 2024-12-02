
package br.cefetmg.space.controller;

import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;

public interface IManterCubeSat {
    public void cadastrar(CubeSat cube) throws PersistenciaException;
    public boolean alterar(CubeSat cube) throws PersistenciaException;
    public boolean excluir(int cubeId) throws PersistenciaException;
    public List<CubeSat> pesquisarTodos() throws PersistenciaException;
}
