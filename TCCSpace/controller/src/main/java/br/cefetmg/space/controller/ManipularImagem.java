package br.cefetmg.space.controller;

import br.cefetmg.space.dao.ImagemDAO;
import br.cefetmg.space.entidades.Imagem;
import br.cefetmg.space.idao.IImagemDAO;
import br.cefetmg.space.idao.exception.PersistenciaException;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ManipularImagem {
    
    private final IImagemDAO imagemDAO= new ImagemDAO();

    public Imagem selecionarImagem() throws PersistenciaException {
        // Abrir explorador de arquivos
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione uma imagem");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Filtro para arquivos de imagem
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));

        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            System.out.println("Arquivo selecionado: " + arquivoSelecionado.getAbsolutePath());

            // Lê o arquivo como bytes
            try {
                byte[] dadosImagem = Files.readAllBytes(arquivoSelecionado.toPath());

                // Criar entidade Imagem
                Imagem imagem = new Imagem();
                imagem.setNome(arquivoSelecionado.getName());
                imagem.setDados(dadosImagem);

                return imagem;

            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Nenhum arquivo selecionado.");
        }
        return null;
    }

    public void salvarImagem(Imagem imagem) throws PersistenciaException {
        // Persistir no banco de dados
        imagemDAO.inserir(imagem);
    }

    public Imagem recuperarImagem(int idImagem) throws PersistenciaException {
        // Recupera o objeto Imagem do banco pelo ID
        Imagem imagem = imagemDAO.procurarPorId(idImagem);
        return imagem;
    }
    
    public boolean excluirImagem(Imagem imagem) throws PersistenciaException{
        return imagemDAO.delete(imagem.getId());
    }
    
    public boolean atualizarImagem(Imagem imagem) throws PersistenciaException{
        return imagemDAO.atualizar(imagem);
    }
}
