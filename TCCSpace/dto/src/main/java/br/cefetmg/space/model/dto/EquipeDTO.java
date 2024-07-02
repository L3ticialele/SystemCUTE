
package br.cefetmg.space.model.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "equipes")
public class EquipeDTO implements Serializable{

    @ManyToMany(mappedBy="equipes")
    private List<UsuarioDTO> integrantes;
    @Column(name = "nome")
    private String nome;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.ALL, mappedBy = "equipe")
    private List<CubeSatDTO> cubesat;
    @ManyToMany(mappedBy="equipesAdministradas")
    private List<AdministradorDTO> administrador;
    @Column(name = "senha")
    private String senha;
    @Column(name = "username")
    private String username;
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "email")
    private String email;
    
    public EquipeDTO(){
        nome = null;
        integrantes = null;
        cubesat = null;
        administrador = null;
        senha = null;
        username = null;
        email = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }
    
    public int quantCubeSat(){
        return cubesat.size();
    }
    
    public int quantIntegrantes(){
        return integrantes.size();
    }
    
    public int quantAdministradores(){
        return administrador.size();
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public List<AdministradorDTO> getAdministrador() {
        return administrador;
    }

    public void setAdministrador(List<AdministradorDTO> administrador) {
        if(this.administrador.isEmpty())
            this.administrador = administrador;
        else
            for(int i=0; i<administrador.size(); i++)
                setAdministrador(administrador.get(i));
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

    public void setUserName(String username) {
        this.username = username;
    }
    
    public AdministradorDTO getAdministrador(int posicao){
        return administrador.get(posicao);
    }
    
    public List<AdministradorDTO> getAdministradores(){
        return administrador;
    }
    
    public void setAdministrador(AdministradorDTO adm){
        if(adm.isAdministrador())
          administrador.add(adm);
        else
           System.out.println("Este usuário não é um administrador.");
    }
    
    public CubeSatDTO getCubeSat(int posicao){
        return cubesat.get(posicao);
    }
    
    public List<CubeSatDTO> getCubeSat() {
        return cubesat;
    }
    
    public UsuarioDTO getIntegrante(int posicao){
        return integrantes.get(posicao);
    }

    public List<UsuarioDTO> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrante(UsuarioDTO integrante) {
        integrantes.add(integrante);
    }
    
    public void setIntegrante(List<UsuarioDTO> integrante) {
        if(integrantes.isEmpty())
            integrantes = integrante;
        else
            for(int i=0; i<integrante.size(); i++)
                setIntegrante(integrante.get(i));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCubeSat(CubeSatDTO cubeSat) {
        this.cubesat.add(cubeSat);
    }
    
    public void setCubeSat(List<CubeSatDTO> cubeSat) {
        if(this.cubesat.isEmpty())
            this.cubesat = cubeSat;
        else
            for(int i=0; i<cubeSat.size(); i++)
                setCubeSat(cubeSat.get(i));
    }
}