
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
        /*
        if((dados.getId() == -1))
            throw new NegocioException("Obrigatório informar o id dos dados.");
        if((dados.getCubSat() == null))
            throw new NegocioException("Obrigatório informar o CubeSat.");
        if((dados.getMassasAr() == null) || (dados.getMassasAr().isEmpty()))
            throw new NegocioException("Obrigatório informar a massa de ar.");
        if((dados.getRadiacao() == null) || (dados.getRadiacao().isEmpty()))
            throw new NegocioException("Obrigatório informar a radiacao.");
        if((dados.getVelocidadeVento() == -1))
            throw new NegocioException("Obrigatório informar a velocidade do vento.");
        if((dados.getTemperatura() == 430))
            throw new NegocioException("Obrigatório informar a temperatura.");
        if((dados.getPressao() == -1))
            throw new NegocioException("Obrigatório informar a pressão.");
        if((dados.getUmidade() == null) || (dados.getUmidade().isEmpty()))
            throw new NegocioException("Obrigatório informar a umidade."); 
    */
        dadosDAO.inserir(dados);
    }
    
    @Override
    public boolean alterar(DadosDTO dados) throws PersistenciaException {
       /*
        if((dados.getId() == -1))
            throw new NegocioException("Obrigatório informar o id dos dados.");
        if((dados.getCubSat() == null))
            throw new NegocioException("Obrigatório informar o CubeSat.");
        if((dados.getMassasAr() == null) || (dados.getMassasAr().isEmpty()))
            throw new NegocioException("Obrigatório informar a massa de ar.");
        if((dados.getRadiacao() == null) || (dados.getRadiacao().isEmpty()))
            throw new NegocioException("Obrigatório informar a radiacao.");
        if((dados.getVelocidadeVento() == -1))
            throw new NegocioException("Obrigatório informar a velocidade do vento.");
        if((dados.getTemperatura() == 430))
            throw new NegocioException("Obrigatório informar a temperatura.");
        if((dados.getPressao() == -1))
            throw new NegocioException("Obrigatório informar a pressão.");
        if((dados.getUmidade() == null) || (dados.getUmidade().isEmpty()))
            throw new NegocioException("Obrigatório informar a umidade.");
     */    

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
