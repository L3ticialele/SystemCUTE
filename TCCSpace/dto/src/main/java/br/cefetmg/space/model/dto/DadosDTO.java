package br.cefetmg.space.model.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dados")
public class DadosDTO implements Serializable {

    private String umidade;
    private double pressao;
    private double temperatura;
    private double velocidadeVento;
    private String radiacao;
    private String massasAr;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCubeSat", nullable = false)
    private CubeSatDTO cubeSat;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dataObtencao;

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
    
    public DadosDTO(){
        pressao = -1;
        temperatura = 430;
        velocidadeVento = -1;
        id = -1;
    }
    
    public void setCubeSat(CubeSatDTO cube){
        cubeSat = cube;
    }
    
    public CubeSatDTO getCubeSat(){
        return cubeSat;
    }
    
    public int getIdCubeSat(){
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
