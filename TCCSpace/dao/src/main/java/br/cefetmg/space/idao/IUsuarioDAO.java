package br.cefetmg.space.idao;
import br.cefetmg.space.entidades.Usuario;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;

/*interface com as funções da classe UsuarioDAO*/
public interface IUsuarioDAO {
    Usuario inserir(Usuario usuario) throws PersistenciaException;

    boolean atualizar(int idUsuario, Usuario usuario) throws PersistenciaException;

    void delete(int idUsuario) throws PersistenciaException;

    List<Usuario> listarTodos() throws PersistenciaException;
    
    Usuario procurarPorUserName(String user) throws PersistenciaException;
    
    Usuario procurarPorEmail(String email) throws PersistenciaException;
    
    Usuario procurarPorId(int id) throws PersistenciaException;
    
    Usuario validarlogin(String email, String senha) throws PersistenciaException;
    
    boolean validarlogin(Usuario usuario) throws PersistenciaException;
}
