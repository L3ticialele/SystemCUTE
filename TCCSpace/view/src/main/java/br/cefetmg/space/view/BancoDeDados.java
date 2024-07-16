package br.cefetmg.space.view;

import br.cefetmg.space.model.dao.DadosDAO;
import br.cefetmg.space.model.dto.DadosDTO;

public class BancoDeDados {

    public static void main(String[] args) {
        DadosDAO dadosDAO = new DadosDAO();

        try {
            while (true) {
                DadosDTO dadoMaisRecente = dadosDAO.buscarDadoMaisRecente();
                if (dadoMaisRecente != null) {
                    System.out.println("Dado mais recente:");
                    System.out.println("ID: " + dadoMaisRecente.getId());
                    System.out.println("Cubesat: " + dadoMaisRecente.getCubeSat());
                    System.out.println("AcelerometroX: " + dadoMaisRecente.getAcelerometroX());
                    System.out.println("AcelerometroY: " + dadoMaisRecente.getAcelerometroY());
                    System.out.println("AcelerometroZ: " + dadoMaisRecente.getAcelerometroZ());
                    System.out.println("AnguloX: " + dadoMaisRecente.getAnguloX());
                    System.out.println("AnguloY: " + dadoMaisRecente.getAnguloY());
                    System.out.println("AnguloZ: " + dadoMaisRecente.getAnguloZ());
                    System.out.println("Altitude: " + dadoMaisRecente.getAltitude());
                    System.out.println("Bateria: " + dadoMaisRecente.getBateria());
                    System.out.println("Corrente da Bateria: " + dadoMaisRecente.getCorrenteBateria());
                    System.out.println("Corrente da Placa Solar: " + dadoMaisRecente.getCorrentePlacaSolar());
                    /*
                    System.out.println("Gas1: " + dadoMaisRecente.getGas1());
                    System.out.println("Gas2: " + dadoMaisRecente.getGas2());
                    */
                    System.out.println("Luz1: " + dadoMaisRecente.getLuz1());
                    System.out.println("Luz2: " + dadoMaisRecente.getLuz2());
                    System.out.println("Ponto de Orvalho: " + dadoMaisRecente.getPontoOrvalho());
                    System.out.println("Pressao: " + dadoMaisRecente.getPressao());
                    System.out.println("Sensor UV: " + dadoMaisRecente.getSensorUV());
                    System.out.println("Temperatura Externa: " + dadoMaisRecente.getTemperaturaExterna());
                    System.out.println("Temperatura Interna: " + dadoMaisRecente.getTemperaturaInterna());
                    System.out.println("Tensao da Bateria: " + dadoMaisRecente.getTensaoBateria());
                    System.out.println("Tensao da Placa Solar: " + dadoMaisRecente.getTensaoPlacaSolar());
                    System.out.println("Umidade: " + dadoMaisRecente.getUmidade());
                    /*
                    System.out.println("Velocidade: " + dadoMaisRecente.getVelocidade());
                    System.out.println("Velocidade Angular X: " + dadoMaisRecente.getVelocidadeAngularX());
                    System.out.println("Velocidade Angular Y: " + dadoMaisRecente.getVelocidadeAngularY());
                    System.out.println("Velocidade Angular Z: " + dadoMaisRecente.getVelocidadeAngularZ());
                    */
                    System.out.println("Data de Obtencão: " + dadoMaisRecente.getDataObtencao());
                    System.out.println(); // Adiciona uma linha em branco após cada objeto Dados
                }

                Thread.sleep(1000); // Espera 1 segundo antes de buscar novamente
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
