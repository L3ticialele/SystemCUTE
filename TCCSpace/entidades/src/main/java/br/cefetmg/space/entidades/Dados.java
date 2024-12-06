package br.cefetmg.space.entidades;

import java.io.Serializable;
import javax.persistence.Column;
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
public class Dados implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "acelerometroX")
    private float acelerometroX;
    @Column(name = "acelerometroY")
    private float acelerometroY;
    @Column(name = "acelerometroZ")
    private float acelerometroZ;
    @Column(name = "anguloX")
    private float anguloX;
    @Column(name = "anguloY")
    private float anguloY;
    @Column(name = "anguloZ")
    private float anguloZ;
    @Column(name = "altitude")
    private float altitude;
    @Column(name = "bateria")
    private float bateria;
    @Column(name = "correnteBateria")
    private float correnteBateria;
    @Column(name = "correntePlacaSolar")
    private float correntePlacaSolar;
    /*
    @Column(name = "gas1")
    private float gas1;
    @Column(name = "gas2")
    private float gas2;
    */
    @Column(name = "luz1")
    private float luz1;
    @Column(name = "luz2")
    private float luz2;
    @Column(name = "pontoOrvalho")
    private float pontoOrvalho;
    @Column(name = "pressao")
    private float pressao;
    @Column(name = "sensorUV")
    private float sensorUV;
    @Column(name = "temperaturaExterna")
    private float temperaturaExterna;
    @Column(name = "temperaturaInterna")
    private float temperaturaInterna;
    @Column(name = "tensaoBateria")
    private float tensaoBateria;
    @Column(name = "tensaoPlacaSolar")
    private float tensaoPlacaSolar;
    @Column(name = "umidade")
    private float umidade;
    /*
    @Column(name = "velocidade")
    private float velocidade;
    @Column(name = "velocidadeAngularX")
    private float velocidadeAngularX;
    @Column(name = "velocidadeAngularY")
    private float velocidadeAngularY;
    @Column(name = "velocidadeAngularZ")
    private float velocidadeAngularZ;
    */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cubesat", nullable = false)
    private CubeSat cubesat;
    @Column(name = "dataObtencao")
    private String dataObtencao;

    public Dados(int id, float acelerometroX, float acelerometroY, float acelerometroZ, float anguloX, float anguloY, float anguloZ, float altitude, float bateria, float correnteBateria, float correntePlacaSolar, float luz1, float luz2, float pontoOrvalho, float pressao, float sensorUV, float temperaturaExterna, float temperaturaInterna, float tensaoBateria, float tensaoPlacaSolar, float umidade, CubeSat cubesat, String dataObtencao) {
        this.id = id;
        this.acelerometroX = acelerometroX;
        this.acelerometroY = acelerometroY;
        this.acelerometroZ = acelerometroZ;
        this.anguloX = anguloX;
        this.anguloY = anguloY;
        this.anguloZ = anguloZ;
        this.altitude = altitude;
        this.bateria = bateria;
        this.correnteBateria = correnteBateria;
        this.correntePlacaSolar = correntePlacaSolar;
        this.luz1 = luz1;
        this.luz2 = luz2;
        this.pontoOrvalho = pontoOrvalho;
        this.pressao = pressao;
        this.sensorUV = sensorUV;
        this.temperaturaExterna = temperaturaExterna;
        this.temperaturaInterna = temperaturaInterna;
        this.tensaoBateria = tensaoBateria;
        this.tensaoPlacaSolar = tensaoPlacaSolar;
        this.umidade = umidade;
        this.cubesat = cubesat;
        this.dataObtencao = dataObtencao;
    }
    
    
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

    /*
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

    */

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

    /*
    public float getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(float velocidade) {
        this.velocidade = velocidade;
    }
    */

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
    
    /*

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

   */

    public float getSensorUV() {
        return sensorUV;
    }

    public void setSensorUV(float sensorUV) {
        this.sensorUV = sensorUV;
    }
    
    public Dados(){
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
    
    public void setCubeSat(CubeSat cube){
        cubesat = cube;
    }
    
    public CubeSat getCubeSat(){
        return cubesat;
    }
    
}