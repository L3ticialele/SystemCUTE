
package br.cefetmg.space.model.dto;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="administradores")
public class AdministradorDTO extends UsuarioDTO{
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "administrador_equipe",
            joinColumns={@JoinColumn(name="idadministrador")},
            inverseJoinColumns={@JoinColumn(name="idequipe")})
    private List<EquipeDTO> equipesAdministradas;
    
    public AdministradorDTO(){
        equipesAdministradas = null;
    }

    public List<EquipeDTO> getEquipesA() {
        return equipesAdministradas;
    }
    
    public int quantEquipesAdministradas(){
        return equipesAdministradas.size();
    }

    public EquipeDTO getEquipeAdministradas(int posicao){
        return equipesAdministradas.get(posicao);
    }
    
    public void setEquipesAdministradas(List<EquipeDTO> equipes) {
        equipesAdministradas = equipes;
    }
    
    public void setEquipeAdministrada(EquipeDTO equipe){
        equipesAdministradas.add(equipe);
    }
}
