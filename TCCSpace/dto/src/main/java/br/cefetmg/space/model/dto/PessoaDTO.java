
package br.cefetmg.space.model.dto;

import java.util.ArrayList;
import javax.persistence.Entity;

@Entity
public class PessoaDTO extends UsuarioDTO{
    private String cpf;
    private boolean administrador;
    private String nome;
    private ArrayList<EquipeDTO> equipes;
    private String telefone;
    private ArrayList<CubeSatDTO> cubeSat;
    
    public int quantEquipes(){
        return equipes.size();
    }
    
    public CubeSatDTO getCubeSat(int posicao){
        return cubeSat.get(posicao);
    }
    
    public int quantCubeSat(){
        return cubeSat.size();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
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
    
    public EquipeDTO getEquipe(int posicao){
        return equipes.get(posicao);
    }

    public void setEquipe(EquipeDTO equipes) {
        this.equipes.add(equipes);
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

    public void setCubeSat(CubeSatDTO cubeSat) {
        this.cubeSat.add(cubeSat);
    }
    
}
