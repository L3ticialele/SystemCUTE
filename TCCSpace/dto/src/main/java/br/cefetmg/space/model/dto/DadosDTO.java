package br.cefetmg.space.model.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DadosDTO implements Serializable {

    private String umidade;
    private double pressao;
    private double temperatura;
    private double velocidadeVento;
    private String radiacao;
    private String massasAr;
    private CubeSatDTO cube;
    private LocalizacaoDTO localizacao;
    private int id;
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public CubeSatDTO getCube() {
        return cube;
    }

    public void setCube(CubeSatDTO cube) {
        this.cube = cube;
    }

    public LocalizacaoDTO getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoDTO localizacao) {
        this.localizacao = localizacao;
    }
    
    public DadosDTO(){
        pressao = -1;
        temperatura = 430;
        velocidadeVento = -1;
        id = -1;
    }
    
    public void setCubSat(CubeSatDTO cube){
        this.cube = cube;
    }
    
    public CubeSatDTO getCubSat(){
        return cube;
    }
    
    public int getIdCubSat(){
        return cube.getId();
    }
    
    public String getUmidade() {
        return umidade;
    }

    public void setUmidade(String umidade) {
        this.umidade = umidade;
    }

    public double getPressao() {
        return pressao;
    }

    public void setPressao(double pressao) {
        this.pressao = pressao;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public String getRadiacao() {
        return radiacao;
    }

    public void setRadiacao(String radiacao) {
        this.radiacao = radiacao;
    }

    public String getMassasAr() {
        return massasAr;
    }

    public void setMassasAr(String massasAr) {
        this.massasAr = massasAr;
    }

}
