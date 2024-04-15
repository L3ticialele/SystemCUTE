
package br.cefetmg.space.model.dto;

import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="administrador")
public class AdministradorDTO extends UsuarioDTO{
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Administrador_Equipe",
            joinColumns={@JoinColumn(name="idAdministrador")},
            inverseJoinColumns={@JoinColumn(name="idEquipe")})
    private ArrayList<EquipeDTO> equipesAdministradas;

    public ArrayList<EquipeDTO> getEquipesA() {
        return equipesAdministradas;
    }
    
    public int quantEquipesAdministradas(){
        return equipesAdministradas.size();
    }

    public EquipeDTO getEquipeAdministradas(int posicao){
        return equipesAdministradas.get(posicao);
    }
    
    public void setEquipesAdministradas(ArrayList<EquipeDTO> equipes) {
        equipesAdministradas = equipes;
    }
    
    public void setEquipeAdministrada(EquipeDTO equipe){
        equipesAdministradas.add(equipe);
    }
}
