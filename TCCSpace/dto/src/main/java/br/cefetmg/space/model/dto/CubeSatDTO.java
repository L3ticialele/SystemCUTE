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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class CubeSatDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String dataFabricacao;
    private double tamanho;
    private String nome;
    private String competicao;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.PERSIST, mappedBy = "cubesat")
    final private ArrayList<DadosDTO> dados;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario", nullable = true)
    private PessoaDTO pessoa;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEquipe", nullable = true)
    private EquipeDTO equipe;
    @OneToMany(fetch = FetchType.EAGER, cascade = 
            CascadeType.PERSIST, mappedBy = "cubesat")
    private ArrayList<LocalizacaoDTO> localizacao;
    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    @JoinColumn(name="idTransporte", nullable = true)
    private TransporteDTO transporte;

    public TransporteDTO getTransporte() {
        return transporte;
    }

    public void setTransporte(TransporteDTO transporte) {
        this.transporte = transporte;
    }

    public EquipeDTO getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeDTO equipe) {
        this.equipe = equipe;
    }

    public ArrayList<LocalizacaoDTO> getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(ArrayList<LocalizacaoDTO> localizacao) {
        this.localizacao = localizacao;
    }

    public PessoaDTO getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaDTO pessoa) {
        this.pessoa = pessoa;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   
    public CubeSatDTO(){
        dados = null;
        tamanho = 0;
        id = -1;
    }
    
    public void setDados(DadosDTO dado){
        dados.add(dado);
    }
    
    public ArrayList<DadosDTO> getDados(){
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
    
    public void setCompeticao(String competicao){
        this.competicao = competicao;
    }
    
    public String getCompeticao(){
        return competicao;
    }
}
