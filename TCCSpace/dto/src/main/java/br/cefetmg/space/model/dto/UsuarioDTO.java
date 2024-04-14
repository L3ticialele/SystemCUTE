
package br.cefetmg.space.model.dto;

import java.util.ArrayList;

public class UsuarioDTO {
    private String senha;
    private String usuario;
    private int id;
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<EquipeDTO> getEquipes() {
        return equipes;
    }

    public void setEquipes(ArrayList<EquipeDTO> equipes) {
        this.equipes = equipes;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ArrayList<CubeSatDTO> getCubeSat() {
        return cubeSat;
    }

    public void setCubeSat(ArrayList<CubeSatDTO> cubeSat) {
        this.cubeSat = cubeSat;
    }
    private String nome;
    private ArrayList<EquipeDTO> equipes;
    private String telefone;
    private ArrayList<CubeSatDTO> cubeSat;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
