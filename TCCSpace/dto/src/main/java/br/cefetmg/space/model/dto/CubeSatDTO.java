package br.cefetmg.space.model.dto;

import java.util.ArrayList;

public class CubeSatDTO {

    private int id;
    private String dataFabricacao;
    private double tamanho;
    private String nome;
    private String competicao;
    final private ArrayList<DadosDTO> dados;
   
    public CubeSatDTO(){
        dados = null;
        tamanho = 0;
        id = -1;
    }
    
    public void setDados(DadosDTO dado){
        dados.add(dado);
    }
    
    public ArrayList<DadosDTO> getDados(){
        return dados;
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
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setCompeticao(String competicao){
        this.competicao = competicao;
    }
    
    public String getCompeticao(){
        return competicao;
    }
}
