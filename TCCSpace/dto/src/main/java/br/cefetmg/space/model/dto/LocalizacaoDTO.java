package br.cefetmg.space.model.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class LocalizacaoDTO implements Serializable {

    private String altitude;
    private String longitude;
    private int id;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
