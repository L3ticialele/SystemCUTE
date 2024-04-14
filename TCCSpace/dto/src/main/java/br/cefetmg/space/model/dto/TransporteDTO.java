package br.cefetmg.space.model.dto;

public class TransporteDTO {

    private int IP;
    private String entrada;
    private String saida;

    
    public void setEntrada(String entrada){
        this.entrada = entrada;
    }
    
    public String getEntrada(){
        return entrada;
    }
    
    public void setSaida(String saida){
        this.saida = saida;
    }
    
    public String getSaida(){
        return saida;
    }

    public int getIP() {
        return IP;
    }

    public void setIP(int IP) {
        this.IP = IP;
    }

}
