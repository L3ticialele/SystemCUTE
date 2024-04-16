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
@Table(name = "localizacao")
public class LocalizacaoDTO implements Serializable {

    private String altitude;
    private String longitude;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cubesat", nullable = false)
    private CubeSatDTO cubeSat;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public void setCubeSat(CubeSatDTO cube){
        cubeSat = cube;
    }
    
    public CubeSatDTO getCubeSat(){
        return cubeSat;
    }
    
    public int getIdCubeSat(){
        return cubeSat.getId();
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
