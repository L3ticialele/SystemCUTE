
package br.cefetmg.space.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class UsuarioDTO implements Serializable{
    private String senha;
    private String username;
    private String email;
    @Id 
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String cpf;
    private boolean administrador;
    private String nome;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Usuario_Equipe",
            joinColumns = {@JoinColumn(name="idUsuario")},
            inverseJoinColumns={@JoinColumn(name="idEquipe")})
    private ArrayList<EquipeDTO> equipes;
    private String telefone;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.PERSIST, mappedBy = "usuarios")
    private ArrayList<CubeSatDTO> cubeSat;
    
    public UsuarioDTO(){
        administrador = false;
        equipes = new ArrayList<>();
        cubeSat = new ArrayList<>();
    }
    
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
    
    public void setEquipes(ArrayList<EquipeDTO> equipes) {
        if(this.equipes.isEmpty())
            this.equipes = equipes;
        else 
            for(int i=0; i<equipes.size(); i++)
                setEquipe(equipes.get(i));
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
    
    public void setCubeSat(ArrayList<CubeSatDTO> cubeSat) {
        if(cubeSat.isEmpty())
            this.cubeSat = cubeSat;
        else
            for(int i=0; i<cubeSat.size(); i++)
                setCubeSat(cubeSat.get(i));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String usuario) {
        username = usuario;
    }
}
