
package br.cefetmg.space.model.dto;

import java.util.ArrayList;
import javax.persistence.Entity;

@Entity
public class EquipeDTO extends UsuarioDTO{

    private ArrayList<PessoaDTO> integrantes;
    private String nome;
    private ArrayList<CubeSatDTO> cubeSat;
    private ArrayList<PessoaDTO> administrador;
    
    public int quantAdministradores(){
        return administrador.size();
    }
    
    public PessoaDTO getAdministrador(int posicao){
        return administrador.get(posicao);
    }
    
    public ArrayList<PessoaDTO> getAdministradores(){
        return administrador;
    }
    
    public void setAdministrador(PessoaDTO adm){
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
