package br.cefetmg.space.controller;

import br.cefetmg.space.entidades.Dados;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data3DViewer {

    Workbook workbook;

    public FileChooser abrirExploradorArquivos() throws IOException {
        workbook = new XSSFWorkbook();
        Sheet sheet = (Sheet) workbook.createSheet("PlanilhaCubeSat");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Planilha");
        fileChooser.setInitialFileName("PlanilhaCubesat.xlsx");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos Excel", "*.xlsx"));
        
        String[] headers = {
            "Gravação", "Data", "Altitude", "Bateria", "Corrente Bateria", "Corrente Placa Solar",
            "Eixo X", "Eixo Y", "Eixo Z", "Horário", "Luz 1", "Luz 2",
            "Ponto de Orvalho", "Pressão", "Sensor UV", "Temperatura Externa",
            "Temperatura Interna", "Tensão Bateria", "Tensão Placa Solar", "Umidade"
        };

        for (int i = 0; i < headers.length; i++) {
            Row row = sheet.createRow(i); // Cada header em uma nova linha
            Cell cell = row.createCell(0); // Primeira coluna
            cell.setCellValue(headers[i]);
        }

        return fileChooser;
    }

    public void preencherDados(ArrayList<Dados> listaDados) {
        Sheet sheet = workbook.getSheet("PlanilhaCubeSat");
        int rowIndex = 0;

        for (Dados dados : listaDados) {
            // Cria uma nova linha e insere os dados verticalmente
            sheet.createRow(rowIndex).createCell(0).setCellValue("Gravação: " + dados.getId());
            sheet.createRow(rowIndex + 1).createCell(0).setCellValue("Data de Obtenção:");
            sheet.getRow(rowIndex + 1).createCell(1).setCellValue(dados.getDataObtencao());

            sheet.createRow(rowIndex + 2).createCell(0).setCellValue("Altitude:");
            sheet.getRow(rowIndex + 2).createCell(1).setCellValue(dados.getAltitude());

            sheet.createRow(rowIndex + 3).createCell(0).setCellValue("Bateria:");
            sheet.getRow(rowIndex + 3).createCell(1).setCellValue(dados.getBateria());

            sheet.createRow(rowIndex + 4).createCell(0).setCellValue("Corrente Bateria:");
            sheet.getRow(rowIndex + 4).createCell(1).setCellValue(dados.getCorrenteBateria());

            sheet.createRow(rowIndex + 5).createCell(0).setCellValue("Corrente Placa Solar:");
            sheet.getRow(rowIndex + 5).createCell(1).setCellValue(dados.getCorrentePlacaSolar());

            sheet.createRow(rowIndex + 6).createCell(0).setCellValue("Ângulo X:");
            sheet.getRow(rowIndex + 6).createCell(1).setCellValue(dados.getAnguloX());

            sheet.createRow(rowIndex + 7).createCell(0).setCellValue("Ângulo Y:");
            sheet.getRow(rowIndex + 7).createCell(1).setCellValue(dados.getAnguloY());

            sheet.createRow(rowIndex + 8).createCell(0).setCellValue("Ângulo Z:");
            sheet.getRow(rowIndex + 8).createCell(1).setCellValue(dados.getAnguloZ());

            sheet.createRow(rowIndex + 9).createCell(0).setCellValue("Horário:");
            sheet.getRow(rowIndex + 9).createCell(1).setCellValue(dados.getHora());

            sheet.createRow(rowIndex + 10).createCell(0).setCellValue("Luz 1:");
            sheet.getRow(rowIndex + 10).createCell(1).setCellValue(dados.getLuz1());

            sheet.createRow(rowIndex + 11).createCell(0).setCellValue("Luz 2:");
            sheet.getRow(rowIndex + 11).createCell(1).setCellValue(dados.getLuz2());

            sheet.createRow(rowIndex + 12).createCell(0).setCellValue("Ponto de Orvalho:");
            sheet.getRow(rowIndex + 12).createCell(1).setCellValue(dados.getPontoOrvalho());

            sheet.createRow(rowIndex + 13).createCell(0).setCellValue("Pressão:");
            sheet.getRow(rowIndex + 13).createCell(1).setCellValue(dados.getPressao());

            sheet.createRow(rowIndex + 14).createCell(0).setCellValue("Sensor UV:");
            sheet.getRow(rowIndex + 14).createCell(1).setCellValue(dados.getSensorUV());

            sheet.createRow(rowIndex + 15).createCell(0).setCellValue("Temperatura Externa:");
            sheet.getRow(rowIndex + 15).createCell(1).setCellValue(dados.getTemperaturaExterna());

            sheet.createRow(rowIndex + 16).createCell(0).setCellValue("Temperatura Interna:");
            sheet.getRow(rowIndex + 16).createCell(1).setCellValue(dados.getTemperaturaInterna());

            sheet.createRow(rowIndex + 17).createCell(0).setCellValue("Tensão Bateria:");
            sheet.getRow(rowIndex + 17).createCell(1).setCellValue(dados.getTensaoBateria());

            sheet.createRow(rowIndex + 18).createCell(0).setCellValue("Tensão Placa Solar:");
            sheet.getRow(rowIndex + 18).createCell(1).setCellValue(dados.getTensaoPlacaSolar());

            sheet.createRow(rowIndex + 19).createCell(0).setCellValue("Umidade:");
            sheet.getRow(rowIndex + 19).createCell(1).setCellValue(dados.getUmidade());

        }
    }

    public void baixarPlanilha(File file) throws IOException {
        if (file != null) {
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                System.out.println("Planilha salva em: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erro ao salvar a planilha.");
            } finally {
                workbook.close();
            }
        } else {
            System.out.println("Operação de salvamento cancelada.");
        }
    }

}
