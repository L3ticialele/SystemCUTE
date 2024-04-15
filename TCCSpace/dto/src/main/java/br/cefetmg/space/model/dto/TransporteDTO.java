package br.cefetmg.space.model.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transporte")
public class TransporteDTO implements Serializable {

    @Id
    private int id;
    private int IP;
    private String entrada;
    private String saida;
    @OneToOne(mappedBy = "transporte")
    private CubeSatDTO cubeSat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public CubeSatDTO getCubeSat() {
        return cubeSat;
    }

    public void setCubeSat(CubeSatDTO cubeSat) {
        this.cubeSat = cubeSat;
    }
    
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
