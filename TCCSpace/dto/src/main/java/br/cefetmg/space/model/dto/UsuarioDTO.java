
package br.cefetmg.space.model.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class UsuarioDTO implements Serializable{
    private String senha;
    private String username;
    private String email;
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private boolean administrador;
    private String nome;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_equipe",
            joinColumns = {@JoinColumn(name="idusuario")},
            inverseJoinColumns={@JoinColumn(name="idequipe")})
    private List<EquipeDTO> equipes;
    private String telefone;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.ALL, mappedBy = "usuario")
    private List<CubeSatDTO> cubeSat;
    
    public UsuarioDTO(){
        senha = null;
        username = null;
        email = null;
        telefone = null;
        administrador = false;
        equipes = null;
        cubeSat = null;
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

    public List<EquipeDTO> getEquipes() {
        return equipes;
    }
    
    public EquipeDTO getEquipe(int posicao){
        return equipes.get(posicao);
    }

    public void setEquipe(EquipeDTO equipes) {
        this.equipes.add(equipes);
    }
    
    public void setEquipes(List<EquipeDTO> equipes) {
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

    public List<CubeSatDTO> getCubeSat() {
        return cubeSat;
    }

    public void setCubeSat(CubeSatDTO cubeSat) {
        this.cubeSat.add(cubeSat);
    }
    
    public void setCubeSat(List<CubeSatDTO> cubeSat) {
        if(this.cubeSat.isEmpty())
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