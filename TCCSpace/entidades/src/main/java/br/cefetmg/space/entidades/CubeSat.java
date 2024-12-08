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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*Está classe define os atributos dos Cubesats, contendo também os gets, os sets e 2 construtores*/
@Entity
@Table(name = "cubesat")
@SuppressWarnings("ValidAttributes")
public class CubeSat implements Serializable {
    //atributos
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "data")
    private String data;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "nome")
    private String nome;
    @OneToMany(fetch = FetchType.EAGER, cascade =
            CascadeType.ALL, mappedBy = "cubesat")
    private List<Dados> dado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario", nullable = true)
    private Usuario usuario;
    @OneToOne(cascade = CascadeType.ALL) // Cascade para persistir a imagem automaticamente
    @JoinColumn(name = "imagem_id") // Nome da chave estrangeira
    private Imagem imagem; // A associação com a imagem

    public CubeSat(){
        dado = new ArrayList<>();
        data = null;
        descricao = null;
        nome = null;
    }
  
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Dados> getDado() {
        return dado;
    }

    public void setDado(List<Dados> dado) {
        this.dado = dado;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    //gets e sets
    public void setPessoa(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setDados(Dados dado){
        this.dado.add(dado);
    }
    
    public void setTodosDados(List<Dados> dado){
        if(dado.isEmpty())
            this.dado = dado;
        else{
            for(int i=0; i<dado.size(); i++)
                setDados(dado.get(i));
        }
    }
    
    public List<Dados> getDados(){
        return dado;
    }

    public String getDataCadastro() {
        return data;
    }

    public void setDataCadastro(String dataCadastro) {
        this.data = dataCadastro;
    }
    
    public String getDescricao(){
        return descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}