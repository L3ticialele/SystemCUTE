package br.cefetmg.space.view;
import br.cefetmg.space.model.dao.CubeSatDAO;
import br.cefetmg.space.model.dao.DadosDAO;
import br.cefetmg.space.model.dto.CubeSatDTO;
import br.cefetmg.space.model.dto.DadosDTO;
import br.cefetmg.space.model.dao.UsuarioDAO;
import br.cefetmg.space.model.dto.UsuarioDTO;
import br.cefetmg.space.model.idao.ICubeSatDAO;
import br.cefetmg.space.model.idao.IDadosDAO;
import br.cefetmg.space.model.idao.IUsuarioDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;

public class Main 
{
    public static void main(String[] args) throws PersistenciaException {
         
        IDadosDAO enviar = new DadosDAO();
        //Trecho de código necessário para a primeira parte (criar usuário, cubesat e os primeiros dados do Banco)
        DadosDTO objetoDados = new DadosDTO();
        
        /*
        //Trecho de código necessário para a segunda parte (apenas envio de dados) 
        DadosDTO objetoDados = enviar.procurarPorId(6);
        */
        
        // Define os valores para os atributos da classe DadosDTO
        objetoDados.setId(2);
        objetoDados.setAcelerometroX(1.5f);
        objetoDados.setAcelerometroY(2.0f);
        objetoDados.setAcelerometroZ(0.8f);
        objetoDados.setAnguloX(30.0f);
        objetoDados.setAnguloY(45.0f);
        objetoDados.setAnguloZ(60.0f);
        objetoDados.setAltitude(100.0f);
        objetoDados.setBateria(95.0f);
        objetoDados.setCorrenteBateria(5.0f);
        objetoDados.setCorrentePlacaSolar(3.0f);
        objetoDados.setGas1(0.2f);
        objetoDados.setGas2(0.5f);
        objetoDados.setLuz1(1500.0f);
        objetoDados.setLuz2(1800.0f);
        objetoDados.setPontoOrvalho(15.0f);
        objetoDados.setPressao(1013.25f);
        objetoDados.setSensorUV(8.5f);
        objetoDados.setTemperaturaExterna(25.0f);
        objetoDados.setTemperaturaInterna(28.0f);
        objetoDados.setTensaoBateria(12.0f);
        objetoDados.setTensaoPlacaSolar(24.0f);
        objetoDados.setUmidade(70.0f);
        objetoDados.setVelocidade(50.0f);
        objetoDados.setVelocidadeAngularX(10.0f);
        objetoDados.setVelocidadeAngularY(15.0f);
        objetoDados.setVelocidadeAngularZ(20.0f);
        objetoDados.setDataObtencao("2024-06-21");
       
        /*
        
        //Criação de um usuário e de um CubeSat para o primeiro armazenamento dos dados
        CubeSatDTO cubeSat = new CubeSatDTO();
        UsuarioDTO usuario = new UsuarioDTO();
        IUsuarioDAO user = new UsuarioDAO();
       
        cubeSat.setDataFabricacao("21/06/2006");
        cubeSat.setNome("cube");
        cubeSat.setDados(objetoDados);
        cubeSat.setTamanho(10);
        
        objetoDados.setCubeSat(cubeSat);
        
        usuario.setAdministrador(false);
        usuario.setCubeSat(cubeSat);
        usuario.setEmail("123@gmail.com");
        usuario.setId(2);
        usuario.setNome("L3ticialele");
        usuario.setSenha("123");
        usuario.setTelefone("123456789");
        usuario.setUserName("usu");
        
        cubeSat.setId(3);
        
        cubeSat.setPessoa(usuario);
        cubeSat.setDados(objetoDados);
        
        user.inserir(usuario);
        */
        
        /*
        //Apenas o envio de novos dados relacionados ao CubeSat criado anteriormente
        //Para que o código funcione, é necessário que os dados acima estejam comentados
        enviar.inserir(objetoDados);
        */
    }
}
