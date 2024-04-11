package br.cefetmg.space.model.dto;

public class CubeSatDTO {

    private int id;
    private String dataFabricacao;
    private double tamanho;

    public CubeSatDTO(int id, String dataFabricacao, double tamanho) {
        this.id = id;
        this.dataFabricacao = dataFabricacao;
        this.tamanho = tamanho;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }
    
    public double getTamanho(){
        return tamanho;
    }
    
    public void setTamanho(double tamanho){
        this.tamanho = tamanho;
    }
}
