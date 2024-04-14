
package br.cefetmg.space.model.dto;

import java.util.ArrayList;

public class EquipeDTO{

    public CubeSatDTO getCubeSat() {
        return cubeSat;
    }
    private ArrayList<UsuarioDTO> integrantes;
    private String nome;
    private int id;
    private CubeSatDTO cubeSat;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<UsuarioDTO> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<UsuarioDTO> integrantes) {
        this.integrantes = integrantes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCubeSat(CubeSatDTO cubeSat) {
        this.cubeSat = cubeSat;
    }
}
