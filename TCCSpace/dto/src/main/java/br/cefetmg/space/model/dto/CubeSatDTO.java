package br.cefetmg.space.model.dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cubesat")
public class CubeSatDTO implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String dataFabricacao;
    private double tamanho;
    private String nome;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.ALL, mappedBy = "cubeSat")
    private List<DadosDTO> dados;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idusuario", nullable = true)
    private UsuarioDTO usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idequipe", nullable = true)
    private EquipeDTO equipe;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.ALL, mappedBy = "cubeSat")
    private List<LocalizacaoDTO> localizacoes;
    private String status;

    public CubeSatDTO(){
        dataFabricacao = null;
        tamanho = -1;
        nome = null;
        dados = null;
        equipe = null;
        localizacoes = null;
        status = null;
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

    public List<LocalizacaoDTO> getLocalizacao() {
        return localizacoes;
    }
    
    public void setLocalizacao(LocalizacaoDTO localizacao) {
        localizacoes.add(localizacao);
    }

    public void setLocalizacao(List<LocalizacaoDTO> localizacao) {
        if(localizacao.isEmpty())
             localizacoes = localizacao;
        else{
            for(int i=0; i<localizacao.size(); i++)
               setLocalizacao(localizacao.get(i));
        }
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
        dados.add(dado);
    }
    
    public void setTodosDados(List<DadosDTO> dado){
        if(dados.isEmpty())
            dados = dado;
        else{
            for(int i=0; i<dado.size(); i++)
                setDados(dado.get(i));
        }
    }
    
    public List<DadosDTO> getDados(){
        return dados;
    }

    public String getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(String dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }
    
    public double getTamanho(){
        return tamanho;
    }
    
    public void setTamanho(double tamanho){
        this.tamanho = tamanho;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}