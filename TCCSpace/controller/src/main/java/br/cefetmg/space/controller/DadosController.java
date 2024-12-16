package br.cefetmg.space.controller;

import br.cefetmg.space.dao.DadosDAO;
import br.cefetmg.space.entidades.CubeSat;
import br.cefetmg.space.entidades.Dados;
import br.cefetmg.space.idao.IDadosDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.ArrayList;
import java.util.List;

public class DadosController {

    IDadosDAO dadosDAO = new DadosDAO();
    Dados dados;

    public boolean cadastrarDados(float acelerometroX, float acelerometroY, float acelerometroZ, float anguloX, float anguloY, float anguloZ, float altitude, float bateria, float correnteBateria, float correntePlacaSolar, float luz1, float luz2, float pontoOrvalho, float pressao, float sensorUV, float temperaturaExterna, float temperaturaInterna, float tensaoBateria, float tensaoPlacaSolar, float umidade, CubeSat cubesat, String dataObtencao, String hora, int posicao) throws PersistenciaException {
        dados = new Dados(acelerometroX, acelerometroY, acelerometroZ, anguloX, anguloY, anguloZ, altitude, bateria, correnteBateria, correntePlacaSolar, luz1, luz2, pontoOrvalho, pressao, sensorUV, temperaturaExterna, temperaturaInterna, tensaoBateria, tensaoPlacaSolar, umidade, cubesat, dataObtencao, hora, posicao);
        cubesat.setDados(dados);
        return dadosDAO.inserir(dados);
    }

    public List<Dados> listarDados() throws PersistenciaException {
        return dadosDAO.listarTodos();
    }

    public Dados recuperarDadosPorId(int ultimaGravacao) throws PersistenciaException {
        return dadosDAO.procurarPorId(ultimaGravacao);
    }

    public List<Dados> listarDadosPorCubeSat(CubeSat cubesat) throws PersistenciaException {
        return dadosDAO.procurarPorCubeSat(cubesat);
    }

    public ArrayList<Dados> atualizaDados(int ultimaGravacao, CubeSat cubesat) throws PersistenciaException {
        ArrayList<Dados> listaDados = new ArrayList<>();
        List<Dados> dados = listarDadosPorCubeSat(cubesat);
        if (dados != null) {
            for (int i = ultimaGravacao; i < dados.size(); i++) {
                listaDados.add(dados.get(i));
            }
        }else{
            System.out.println("Não há dados.");
        }
        return listaDados;
    }
}
