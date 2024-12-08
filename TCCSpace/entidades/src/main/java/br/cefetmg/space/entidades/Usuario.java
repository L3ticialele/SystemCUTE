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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*Está classe define os atributos dos Usuários, contendo também os gets, os sets e o construtor*/
@Entity
@Table(name = "usuarios")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable{
    //atributos
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "senha")
    private String senha;
    @Column(name = "email")
    private String email;
    @Column(name = "nome")
    private String nome;
    @Column(name = "telefone")
    private String telefone;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.ALL, mappedBy = "usuario")
    private List<CubeSat> cubeSat;
     @OneToOne(cascade = CascadeType.ALL) // Cascade para persistir a imagem automaticamente
    @JoinColumn(name = "imagem_id") // Nome da chave estrangeira
    private Imagem imagem; // A associação com a imagem
    
    public Usuario(){
        senha = null;
        email = null;
        telefone = null;
        cubeSat = new ArrayList<>();
        imagem = null;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public CubeSat getCubeSat(int posicao){
        return cubeSat.get(posicao);
    }
    
    public int quantCubeSat(){
        return cubeSat.size();
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