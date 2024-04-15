package br.cefetmg.space.model.dto;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class LocalizacaoDTO implements Serializable {

    private String altitude;
    private String longitude;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCubeSat", nullable = false)
    private CubeSatDTO cubeSat;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public CubeSatDTO getCubeSat() {
        return cubeSat;
    }

    public void setCubeSat(CubeSatDTO cubeSat) {
        this.cubeSat = cubeSat;
    }

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
