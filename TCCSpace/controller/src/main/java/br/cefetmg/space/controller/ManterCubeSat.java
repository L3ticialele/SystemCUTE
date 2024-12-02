
package br.cefetmg.space.controller;

import br.cefetmg.space.dao.CubeSatDAO;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.idao.ICubeSatDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;

public class ManterCubeSat implements IManterCubeSat {
     private final ICubeSatDAO cubeSatDAO;
     
     public ManterCubeSat(){
         cubeSatDAO = new CubeSatDAO();
     }
    
    @Override
    public void cadastrar(CubeSat cube) throws PersistenciaException {
        cubeSatDAO.inserir(cube);
    }
    
    @Override
    public boolean alterar(CubeSat cube) throws PersistenciaException{
        boolean result = cubeSatDAO.atualizar(cube);
        return result;
    }

    @Override
    public boolean excluir(int cubeId) throws PersistenciaException{
       boolean result = cubeSatDAO.delete(cubeId);
       return result;
    }

    @Override
    public List<CubeSat> pesquisarTodos() throws PersistenciaException {
       List<CubeSat> result = cubeSatDAO.listarTodos();
       return result;
    }
}
