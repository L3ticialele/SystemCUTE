package br.cefetmg.space.idao;

import br.cefetmg.space.entidades.Imagem;
import br.cefetmg.space.idao.exception.PersistenciaException;
import java.util.List;

public interface IImagemDAO {
    boolean inserir(Imagem imagem) throws PersistenciaException;

    boolean atualizar(Imagem imagem) throws PersistenciaException;

    boolean delete(int idImagem) throws PersistenciaException;

    List<Imagem> listarTodos() throws PersistenciaException;
    
    Imagem procurarPorId(int id) throws PersistenciaException;
}
