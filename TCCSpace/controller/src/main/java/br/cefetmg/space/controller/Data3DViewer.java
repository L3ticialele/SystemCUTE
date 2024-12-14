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

        return fileChooser;
    }

    public void preencherDados(ArrayList<Dados> listaDados) {
        Sheet sheet = workbook.getSheet("PlanilhaCubeSat");
        int rowIndex = 0;

        Row headerRow = sheet.createRow(rowIndex++);
        String[] headers = {
            "Gravação", "Data", "Altitude", "Bateria", "Corrente Bateria", "Corrente Placa Solar",
            "Ângulo X", "Ângulo Y", "Ângulo Z", "Horário", "Luz 1", "Luz 2",
            "Ponto de Orvalho", "Pressão", "Sensor UV", "Temperatura Externa",
            "Temperatura Interna", "Tensão Bateria", "Tensão Placa Solar", "Umidade"
        };

        for (int col = 0; col < headers.length; col++) {
            headerRow.createCell(col).setCellValue(headers[col]);
        }

        for (Dados dados : listaDados) {
            Row dataRow = sheet.createRow(rowIndex++);

            int col = 0;
            dataRow.createCell(col++).setCellValue(dados.getId());
            dataRow.createCell(col++).setCellValue(dados.getDataObtencao());
            dataRow.createCell(col++).setCellValue(dados.getAltitude());
            dataRow.createCell(col++).setCellValue(dados.getBateria());
            dataRow.createCell(col++).setCellValue(dados.getCorrenteBateria());
            dataRow.createCell(col++).setCellValue(dados.getCorrentePlacaSolar());
            dataRow.createCell(col++).setCellValue(dados.getAnguloX());
            dataRow.createCell(col++).setCellValue(dados.getAnguloY());
            dataRow.createCell(col++).setCellValue(dados.getAnguloZ());
            dataRow.createCell(col++).setCellValue(dados.getHora());
            dataRow.createCell(col++).setCellValue(dados.getLuz1());
            dataRow.createCell(col++).setCellValue(dados.getLuz2());
            dataRow.createCell(col++).setCellValue(dados.getPontoOrvalho());
            dataRow.createCell(col++).setCellValue(dados.getPressao());
            dataRow.createCell(col++).setCellValue(dados.getSensorUV());
            dataRow.createCell(col++).setCellValue(dados.getTemperaturaExterna());
            dataRow.createCell(col++).setCellValue(dados.getTemperaturaInterna());
            dataRow.createCell(col++).setCellValue(dados.getTensaoBateria());
            dataRow.createCell(col++).setCellValue(dados.getTensaoPlacaSolar());
            dataRow.createCell(col++).setCellValue(dados.getUmidade());
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
