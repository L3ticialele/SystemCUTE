package br.cefetmg.space.model.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dados")
public class DadosDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String umidade;
    private double pressao;
    private double temperatura;
    private double velocidadeVento;
    private String radiacao;
    private String massasAr;
    @ManyToOne
    private CubeSatDTO cubeSat;
    private String dataObtencao;

    public DadosDTO(){
        umidade = null;
        pressao = -1;
        temperatura = -1;
        velocidadeVento = -1;
        radiacao = null;
        massasAr = null;
        dataObtencao = null;
    }
    
    public String getDataObtencao() {
        return dataObtencao;
    }

    public void setDataObtencao(String dataObtencao) {
        this.dataObtencao = dataObtencao;
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setCubeSat(CubeSatDTO cube){
        cubeSat = cube;
    }
    
    public CubeSatDTO getCubeSat(){
        return cubeSat;
    }
    
    public long getIdCubeSat(){
        return cubeSat.getId();
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