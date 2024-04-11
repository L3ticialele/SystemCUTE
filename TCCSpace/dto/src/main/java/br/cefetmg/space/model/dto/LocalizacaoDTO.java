package br.cefetmg.space.model.dto;

public class LocalizacaoDTO {

    private String altitude;
    private String longitude;

    public LocalizacaoDTO(String altitude, String longitude) {
        this.altitude = altitude;
        this.longitude = longitude;
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
