
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dao.CubeSatDAO;
import br.cefetmg.space.model.dto.CubeSatDTO;
import br.cefetmg.space.model.idao.ICubeSatDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public class ManterCubeSat implements IManterCubeSat {
     private final ICubeSatDAO cubeSatDAO;
     
     public ManterCubeSat(){
         cubeSatDAO = new CubeSatDAO();
     }
    
    @Override
    public void cadastrar(CubeSatDTO cube) throws PersistenciaException {
       /*
        if((cube.getNome() == null) || (cube.getNome().isEmpty()))
            throw new NegocioException("Obrigatório informar o nome do CubeSat.");
        if((cube.getDataFabricacao() == null) || (cube.getDataFabricacao().isEmpty()))
            throw new NegocioException("Obrigatório informar o ano de fabricação do CubeSat.");
        if(cube.getTamanho() == 0)
            throw new NegocioException("Obrigatório informar o tamanho do CubeSat.");
        if((cube.getCompeticao() == null) || (cube.getCompeticao().isEmpty()))
            throw new NegocioException("Obrigatório informar a competição que o CubeSat participou ou irá participar.");
       */
        cubeSatDAO.inserir(cube);
    }
    
    @Override
    public boolean alterar(CubeSatDTO cube) throws PersistenciaException{
        /*
        if((cube.getNome() == null) || (cube.getNome().isEmpty()))
            throw new NegocioException("Obrigatório informar o nome do CubeSat.");
        if((cube.getDataFabricacao() == null) || (cube.getDataFabricacao().isEmpty()))
            throw new NegocioException("Obrigatório informar o ano de fabricação do CubeSat.");
        if(cube.getTamanho() == 0)
            throw new NegocioException("Obrigatório informar o tamanho do CubeSat.");
        if((cube.getCompeticao() == null) || (cube.getCompeticao().isEmpty()))
            throw new NegocioException("Obrigatório informar a competição que o CubeSat participou ou irá participar.");
    */
        boolean result = cubeSatDAO.atualizar(cube);
        return result;
    }

    @Override
    public boolean excluir(int cubeId) throws PersistenciaException{
       boolean result = cubeSatDAO.delete(cubeId);
       return result;
    }

    @Override
    public List<CubeSatDTO> pesquisarTodos() throws PersistenciaException {
       List<CubeSatDTO> result = cubeSatDAO.listarTodos();
       return result;
    }
}
