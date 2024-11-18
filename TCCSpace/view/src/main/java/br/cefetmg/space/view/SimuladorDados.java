package br.cefetmg.space.view;

import br.cefetmg.space.model.dao.CubeSatDAO;
import br.cefetmg.space.model.dao.DadosDAO;
import br.cefetmg.space.model.dto.DadosDTO;
import br.cefetmg.space.model.idao.ICubeSatDAO;
import br.cefetmg.space.model.idao.IDadosDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SimuladorDados {

    public void gerarDadosParaCubeSat(int idCubeSat) {

        try {
            // Gera dados simulados
            DadosDTO dados = new DadosDTO();
            ICubeSatDAO cubesatDAO = new CubeSatDAO();
            dados.setCubeSat(cubesatDAO.procurarPorId(1));
            dados.setAcelerometroX((float) (Math.random() * 10));  // Convertendo para float
            dados.setAcelerometroY((float) (Math.random() * 10));  // Convertendo para float
            dados.setAcelerometroZ((float) (Math.random() * 10));  // Convertendo para float
            dados.setAnguloX((float) (Math.random() * 360));  // Convertendo para float
            dados.setAnguloY((float) (Math.random() * 360));  // Convertendo para float
            dados.setAnguloZ((float) (Math.random() * 360));  // Convertendo para float
            dados.setAltitude((float) (Math.random() * 10000));  // Convertendo para float
            dados.setBateria((float) (Math.random() * 100));  // Convertendo para float
            dados.setCorrenteBateria((float) (Math.random() * 5));  // Convertendo para float
            dados.setCorrentePlacaSolar((float) (Math.random() * 10));  // Convertendo para float
            /*
            dados.setGas1((float) (Math.random() * 100));  // Convertendo para float
            dados.setGas2((float) (Math.random() * 100));  // Convertendo para float
            */
            dados.setLuz1((float) (Math.random() * 1000));  // Convertendo para float
            dados.setLuz2((float) (Math.random() * 1000));  // Convertendo para float
            dados.setPontoOrvalho((float) (Math.random() * 50));  // Convertendo para float
            dados.setPressao((float) (Math.random() * 1000));  // Convertendo para float
            dados.setSensorUV((float) (Math.random() * 10));  // Convertendo para float
            dados.setTemperaturaExterna((float) (Math.random() * 50));  // Convertendo para float
            dados.setTemperaturaInterna((float) (Math.random() * 50));  // Convertendo para float
            dados.setTensaoBateria((float) (Math.random() * 14));  // Convertendo para float
            dados.setTensaoPlacaSolar((float) (Math.random() * 30));  // Convertendo para float
            dados.setUmidade((float) (Math.random() * 100));  // Convertendo para float
            /*
            dados.setVelocidade((float) (Math.random() * 1000));  // Convertendo para float
            dados.setVelocidadeAngularX((float) (Math.random() * 10));  // Convertendo para float
            dados.setVelocidadeAngularY((float) (Math.random() * 10));  // Convertendo para float
            dados.setVelocidadeAngularZ((float) (Math.random() * 10));  // Convertendo para float
            */

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            dados.setDataObtencao(now.format(formatter));

            // Adiciona os dados no banco de dados
            IDadosDAO dadosDAO = new DadosDAO();
            dadosDAO.inserir(dados);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimuladorDados simulador = new SimuladorDados();
        int idCubeSat = 1;  // Aqui você define o ID do primeiro CubeSat

        // Loop para simular inserção contínua de dados a cada segundo
        while (true) {
            simulador.gerarDadosParaCubeSat(idCubeSat);
            try {
                Thread.sleep(1000);  // Aguarda 1 segundo entre cada inserção
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

