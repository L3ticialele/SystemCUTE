package br.cefetmg.space.model.dto;

public class DadosDTO {

    private String umidade;
    private double pressao, temperatura;
    private double velocidadeVento;
    private String radiacao;
    private String massasAr;

    public DadosDTO(String umidade, double pressao, double temperatura, double velocidadeVento, String radiacao, String massasAr) {
        this.umidade = umidade;
        this.pressao = pressao;
        this.temperatura = temperatura;
        this.velocidadeVento = velocidadeVento;
        this.radiacao = radiacao;
        this.massasAr = massasAr;
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
