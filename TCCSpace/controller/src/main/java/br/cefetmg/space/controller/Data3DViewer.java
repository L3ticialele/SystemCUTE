package br.cefetmg.space.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data3DViewer {

    Workbook workbook;

    public FileChooser abrirExploradorArquivos() throws IOException {
        workbook = new XSSFWorkbook();
        Sheet sheet = (Sheet) workbook.createSheet("PlanilhaVazia");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Planilha");
        fileChooser.setInitialFileName("PlanilhaCubesat.xlsx");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Arquivos Excel", "*.xlsx"));

        return fileChooser;
    }
    
    public void baixarPlanilha(File file) throws IOException{
         if (file != null) {
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                System.out.println("Planilha vazia salva em: " + file.getAbsolutePath());
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
