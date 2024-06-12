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
    private float acelerometroX, acelerometroY, acelerometroZ;
    private float anguloX, anguloY, anguloZ;
    private float altitude;
    private float bateria;
    private float correnteBateria;
    private float correntePlacaSolar;
    private float gas1;
    private float gas2;
    private float luz1;
    private float luz2;
    private float pontoOrvalho;
    private float pressao;
    private float sensorUV;
    private float temperaturaExterna;
    private float temperaturaInterna;
    private float tensaoBateria;
    private float tensaoPlacaSolar;
    private float umidade;
    private float velocidade;
    private float velocidadeAngularX, velocidadeAngularY, velocidadeAngularZ;
    @ManyToOne
    private CubeSatDTO cubeSat;
    private String dataObtencao;


    public float getLuz1() {
        return luz1;
    }

    public void setLuz1(float luz1) {
        this.luz1 = luz1;
    }

    public float getLuz2() {
        return luz2;
    }

    public void setLuz2(float luz2) {
        this.luz2 = luz2;
    }

    public float getAnguloX() {
        return anguloX;
    }

    public void setAnguloX(float anguloX) {
        this.anguloX = anguloX;
    }

    public float getAnguloY() {
        return anguloY;
    }

    public void setAnguloY(float anguloY) {
        this.anguloY = anguloY;
    }

    public float getAnguloZ() {
        return anguloZ;
    }

    public void setAnguloZ(float anguloZ) {
        this.anguloZ = anguloZ;
    }

    public float getVelocidadeAngularX() {
        return velocidadeAngularX;
    }

    public void setVelocidadeAngularX(float velocidadeAngularX) {
        this.velocidadeAngularX = velocidadeAngularX;
    }

    public float getVelocidadeAngularY() {
        return velocidadeAngularY;
    }

    public void setVelocidadeAngularY(float velocidadeAngularY) {
        this.velocidadeAngularY = velocidadeAngularY;
    }

    public float getVelocidadeAngularZ() {
        return velocidadeAngularZ;
    }

    public void setVelocidadeAngularZ(float velocidadeAngularZ) {
        this.velocidadeAngularZ = velocidadeAngularZ;
    }

    public float getAcelerometroX() {
        return acelerometroX;
    }

    public void setAcelerometroX(float acelerometroX) {
        this.acelerometroX = acelerometroX;
    }

    public float getAcelerometroY() {
        return acelerometroY;
    }

    public void setAcelerometroY(float acelerometroY) {
        this.acelerometroY = acelerometroY;
    }

    public float getAcelerometroZ() {
        return acelerometroZ;
    }

    public void setAcelerometroZ(float acelerometroZ) {
        this.acelerometroZ = acelerometroZ;
    }

    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }

    public float getBateria() {
        return bateria;
    }

    public void setBateria(float bateria) {
        this.bateria = bateria;
    }

    public float getTensaoPlacaSolar() {
        return tensaoPlacaSolar;
    }

    public void setTensaoPlacaSolar(float tensaoPlacaSolar) {
        this.tensaoPlacaSolar = tensaoPlacaSolar;
    }

    public float getCorrentePlacaSolar() {
        return correntePlacaSolar;
    }

    public void setCorrentePlacaSolar(float correntePlacaSolar) {
        this.correntePlacaSolar = correntePlacaSolar;
    }

    public float getCorrenteBateria() {
        return correnteBateria;
    }

    public void setCorrenteBateria(float correnteBateria) {
        this.correnteBateria = correnteBateria;
    }

    public float getTensaoBateria() {
        return tensaoBateria;
    }

    public void setTensaoBateria(float tensaoBateria) {
        this.tensaoBateria = tensaoBateria;
    }

    public float getTemperaturaInterna() {
        return temperaturaInterna;
    }

    public void setTemperaturaInterna(float temperaturaInterna) {
        this.temperaturaInterna = temperaturaInterna;
    }

    public float getTemperaturaExterna() {
        return temperaturaExterna;
    }

    public void setTemperaturaExterna(float temperaturaExterna) {
        this.temperaturaExterna = temperaturaExterna;
    }

    public float getPressao() {
        return pressao;
    }

    public void setPressao(float pressao) {
        this.pressao = pressao;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
    }

    public float getUmidade() {
        return umidade;
    }

    public void setUmidade(float umidade) {
        this.umidade = umidade;
    }

    public float getPontoOrvalho() {
        return pontoOrvalho;
    }

    public void setPontoOrvalho(float pontoOrvalho) {
        this.pontoOrvalho = pontoOrvalho;
    }

    public float getGas1() {
        return gas1;
    }

    public void setGas1(float gas1) {
        this.gas1 = gas1;
    }

    public float getGas2() {
        return gas2;
    }

    public void setGas2(float gas2) {
        this.gas2 = gas2;
    }

    public float getSensorUV() {
        return sensorUV;
    }

    public void setSensorUV(float sensorUV) {
        this.sensorUV = sensorUV;
    }
    
    public DadosDTO(){
        pressao = -1;
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
    
}