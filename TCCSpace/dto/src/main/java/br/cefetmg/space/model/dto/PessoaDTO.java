
package br.cefetmg.space.model.dto;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas")
public class PessoaDTO extends UsuarioDTO{
    private String cpf;
    private boolean administrador;
    private String nome;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Pessoa_Equipe",
            joinColumns = {@JoinColumn(name="idPessoa")},
            inverseJoinColumns={@JoinColumn(name="idEquipe")})
    private ArrayList<EquipeDTO> equipes;
    private String telefone;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.PERSIST, mappedBy = "pessoas")
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
