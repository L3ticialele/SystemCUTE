
package br.cefetmg.space.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Usuario implements Serializable{
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "senha")
    private String senha;
    @Column(name = "email")
    private String email;
    @Column(name = "administrador")
    private boolean administrador;
    @Column(name = "nome")
    private String nome;
    @Column(name = "telefone")
    private String telefone;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.PERSIST, mappedBy = "usuario")
    private List<CubeSat> cubeSat;
    
    public Usuario(){
        senha = null;
        email = null;
        telefone = null;
        administrador = false;
        cubeSat = new ArrayList<>();
    }
    
    public CubeSat getCubeSat(int posicao){
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<CubeSat> getCubeSat() {
        return cubeSat;
    }

    public void setCubeSat(CubeSat cubeSat) {
        this.cubeSat.add(cubeSat);
    }
    
    public void setCubeSat(List<CubeSat> cubeSat) {
        if(this.cubeSat.isEmpty())
            this.cubeSat = cubeSat;
        else
            for(int i=0; i<cubeSat.size(); i++)
                setCubeSat(cubeSat.get(i));
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
}