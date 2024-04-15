
package br.cefetmg.space.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "equipes")
public class EquipeDTO implements Serializable{

    @ManyToMany(mappedBy="equipes")
    private ArrayList<PessoaDTO> integrantes;
    private String nome;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.PERSIST, mappedBy = "equipes")
    private ArrayList<CubeSatDTO> cubeSat;
    @ManyToMany(mappedBy="equipes")
    private ArrayList<AdministradorDTO> administrador;
    private String senha;
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public ArrayList<AdministradorDTO> getAdministrador() {
        return administrador;
    }

    public void setAdministrador(ArrayList<AdministradorDTO> administrador) {
        this.administrador = administrador;
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
    
    public int quantAdministradores(){
        return administrador.size();
    }
    
    public AdministradorDTO getAdministrador(int posicao){
        return administrador.get(posicao);
    }
    
    public ArrayList<AdministradorDTO> getAdministradores(){
        return administrador;
    }
    
    public void setAdministrador(AdministradorDTO adm){
        if(adm.isAdministrador())
          administrador.add(adm);
        else
           System.out.println("Este usuário não é um administrador.");
    }
    
    public int quantIntegrantes(){
        return integrantes.size();
    }
    
    public int quantCubeSat(){
        return cubeSat.size();
    }
    
    public CubeSatDTO getCubeSat(int posicao){
        return cubeSat.get(posicao);
    }
    
    public ArrayList<CubeSatDTO> getCubeSat() {
        return cubeSat;
    }
    
    public PessoaDTO getIntegrante(int posicao){
        return integrantes.get(posicao);
    }

    public ArrayList<PessoaDTO> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrante(PessoaDTO integrante) {
        integrantes.add(integrante);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCubeSat(CubeSatDTO cubeSat) {
        this.cubeSat.add(cubeSat);
    }
}
