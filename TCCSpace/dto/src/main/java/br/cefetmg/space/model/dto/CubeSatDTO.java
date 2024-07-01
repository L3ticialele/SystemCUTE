package br.cefetmg.space.model.dto;

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
import javax.persistence.Table;

@Entity
@Table(name = "cubesat")
@SuppressWarnings("ValidAttributes")
public class CubeSatDTO implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column (name = "acesso")
    private String acesso;
    @Column(name = "data")
    private String data;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "nome")
    private String nome;
    @OneToMany(fetch = FetchType.EAGER, cascade =
            CascadeType.PERSIST, mappedBy = "cubesat")
    private List<DadosDTO> dado;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario", nullable = true)
    private UsuarioDTO usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEquipe", nullable = true)
    private EquipeDTO equipe;
    @Column(name = "status")
    private String status;

    public CubeSatDTO(){
        dado = new ArrayList<>();
        data = null;
        descricao = null;
        nome = null;
        equipe = null;
        status = "Inativo";
        acesso = "Público";
    }
    
    public String getAcesso(){
        return acesso;
    }
    
    public void setAcesso(String acesso){
        this.acesso = acesso;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EquipeDTO getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeDTO equipe) {
        this.equipe = equipe;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setPessoa(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setDados(DadosDTO dado){
        this.dado.add(dado);
    }
    
    public void setTodosDados(List<DadosDTO> dado){
        if(dado.isEmpty())
            this.dado = dado;
        else{
            for(int i=0; i<dado.size(); i++)
                setDados(dado.get(i));
        }
    }
    
    public List<DadosDTO> getDados(){
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