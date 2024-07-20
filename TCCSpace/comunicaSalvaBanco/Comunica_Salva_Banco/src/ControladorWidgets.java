import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fazecast.jSerialComm.SerialPort;

public class ControladorWidgets {

   @FXML private TextArea bruto_COM;
   @FXML private Button btn_busca_com;
   @FXML private Button btn_conectar;
   @FXML private Button btn_salvaBanco;
   @FXML private CheckBox chkbox_COM_status;
   @FXML private ChoiceBox<String> seletor_com;
   @FXML private TextField tF_accX;
   @FXML private TextField tF_accY;
   @FXML private TextField tF_accZ;
   @FXML private TextField tf_Gas1;
   @FXML private TextField tf_Gas2;
   @FXML private TextField tf_Luz1;
   @FXML private TextField tf_Luz2;
   @FXML private TextField tf_Umidade;
   @FXML private TextField tf_Veloc;
   @FXML private TextField tf_altitude;
   @FXML private TextField tf_angX;
   @FXML private TextField tf_angY;
   @FXML private TextField tf_angZ;
   @FXML private TextField tf_bat;
   @FXML private TextField tf_externo;
   @FXML private TextField tf_iBat;
   @FXML private TextField tf_iSolar;
   @FXML private TextField tf_idCubesat;
   @FXML private TextField tf_interno;
   @FXML private TextField tf_orvalho;
   @FXML private TextField tf_pressao;
   @FXML private TextField tf_uv;
   @FXML private TextField tf_vBat;
   @FXML private TextField tf_vSolar;
   @FXML private TextField tf_wX;
   @FXML private TextField tf_wY;
   @FXML private TextField tf_wZ;
   
   private ThreadUartAerospace leitorUart;   //Objeto/Classe Responsavel por criar triggers para o Thread do gerenciador de janela do JAVAFX, fazendo com que a janela principal atualize, alem de ser responsavel pela verificacao da chegada de novos dados na classe ReceptorUart
   private TextField[] widgetsTFields;       //Lista de Widgets para interagir rapidamente com os widgets, sem fazer um por um
   private ArrayList<String> dadosSeparados; //Lista com numeros em String de cada variavel enviada via UART/Serial, posteriormente eh atribuido ao widgetsTFields

   public CheckBox getChkbox_COM_status() {
       return chkbox_COM_status;
   }

   @FXML
   void initialize() {
      // Asserts gerados pelo Scene Builder
      assert bruto_COM != null : "fx:id=\"bruto_COM\" was not injected: check your FXML file 'root.fxml'.";
      assert btn_busca_com != null : "fx:id=\"btn_busca_com\" was not injected: check your FXML file 'root.fxml'.";
      assert btn_conectar != null : "fx:id=\"btn_conectar\" was not injected: check your FXML file 'root.fxml'.";
      assert btn_salvaBanco != null : "fx:id=\"btn_salvaBanco\" was not injected: check your FXML file 'root.fxml'.";
      assert chkbox_COM_status != null : "fx:id=\"chkbox_COM_status\" was not injected: check your FXML file 'root.fxml'.";
      assert seletor_com != null : "fx:id=\"seletor_com\" was not injected: check your FXML file 'root.fxml'.";
      assert tF_accX != null : "fx:id=\"tF_accX\" was not injected: check your FXML file 'root.fxml'.";
      assert tF_accY != null : "fx:id=\"tF_accY\" was not injected: check your FXML file 'root.fxml'.";
      assert tF_accZ != null : "fx:id=\"tF_accZ\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_Gas1 != null : "fx:id=\"tf_Gas1\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_Gas2 != null : "fx:id=\"tf_Gas2\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_Luz1 != null : "fx:id=\"tf_Luz1\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_Luz2 != null : "fx:id=\"tf_Luz2\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_Umidade != null : "fx:id=\"tf_Umidade\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_Veloc != null : "fx:id=\"tf_Veloc\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_altitude != null : "fx:id=\"tf_altitude\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_angX != null : "fx:id=\"tf_angX\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_angY != null : "fx:id=\"tf_angY\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_angZ != null : "fx:id=\"tf_angZ\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_bat != null : "fx:id=\"tf_bat\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_externo != null : "fx:id=\"tf_externo\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_iBat != null : "fx:id=\"tf_iBat\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_iSolar != null : "fx:id=\"tf_iSolar\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_idCubesat != null : "fx:id=\"tf_idCubesat\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_interno != null : "fx:id=\"tf_interno\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_orvalho != null : "fx:id=\"tf_orvalho\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_pressao != null : "fx:id=\"tf_pressao\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_uv != null : "fx:id=\"tf_uv\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_vBat != null : "fx:id=\"tf_vBat\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_vSolar != null : "fx:id=\"tf_vSolar\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_wX != null : "fx:id=\"tf_wX\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_wY != null : "fx:id=\"tf_wY\" was not injected: check your FXML file 'root.fxml'.";
      assert tf_wZ != null : "fx:id=\"tf_wZ\" was not injected: check your FXML file 'root.fxml'.";

      // Inicializar o array de TextFields, nao vamos incluir o id_cubesat pq nao tem necessidade
      widgetsTFields = new TextField[]{
         tF_accX, tF_accY, tF_accZ,tf_angX,tf_angY, tf_angZ,tf_altitude, tf_bat,tf_iBat,
         tf_iSolar, tf_Gas1,tf_Gas2, tf_Luz1, tf_Luz2,tf_orvalho,tf_pressao,tf_uv,tf_externo,tf_interno,
         tf_vBat,tf_vSolar,tf_Umidade,tf_Veloc,tf_wX, tf_wY, tf_wZ
      };
      dadosSeparados = new ArrayList<>(); //Inicializa array
   }

   @FXML
   void conectarCOM(ActionEvent event) {
      //Tenta criar uma instacia/conexao COM com a porta escolhido pelo choicebox seletor_com, se nao for possivel,criarInstacia retorna objeto nulo
      leitorUart = ThreadUartAerospace.criarInstancia(this,seletor_com.getValue());
      if (leitorUart == null) {
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erro");
         alert.setHeaderText(null);
         alert.setContentText("Não foi possivel conectar com a porta: " + seletor_com.getValue());
         alert.showAndWait();
      }
      else{
         chkbox_COM_status.setSelected(true); // Coloca verdadeiro no checkbox "Status Conexao" da interface grafica
         leitorUart.start();  //Inicializa a leitura/thread responsavel pelo aviso de novos dados
      }
   }

   @FXML
   void popularSeletorCOM(ActionEvent event) {
      // Limpa os itens do ChoiceBox
      seletor_com.getItems().clear();

      // Obtém as portas COM disponíveis
      SerialPort[] ports = SerialPort.getCommPorts();

      // Adiciona os nomes das portas ao ChoiceBox
      for (SerialPort port : ports) {
         seletor_com.getItems().add(port.getSystemPortName());
      }

      // Seleciona o primeiro item por padrão
      if (seletor_com.getItems().size() > 0) {
         seletor_com.setValue(seletor_com.getItems().get(0));
      }
   }

   @FXML
   void salvarForcadamente(ActionEvent event) {
      // URL de conexão com o banco de dados
      String DB_URL = "jdbc:postgresql://localhost:5432/tccaerospace";
      String USER = "postgres"; // Substitua pelo seu usuário do PostgreSQL
      String PASS = "123456"; // Substitua pela sua senha do PostgreSQL

      // Conecte-se e insira os dados
      try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
         System.out.println("Conexão estabelecida com sucesso!");

         String sql =   "INSERT INTO dados " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

         try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(tf_idCubesat.getText())); //Prepara o envio do id_cubesat

            for (int i = 0,j=2; i < widgetsTFields.length; i++,j++) {
               pstmt.setDouble(j, Double.parseDouble(widgetsTFields[i].getText())); //Captura o valor de cada TextField e converte para double para futuramente enviar para o banco de dados
            }

            pstmt.executeUpdate();//Salva os dados no banco de dados
            System.out.println("Dados inseridos com sucesso!");
            }//END try (PreparedStatement pstmt = conn.prepareStatement(sql))
      }//END try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS))
      catch (SQLException e) {
         System.out.println("Erro ao conectar ou inserir dados: " + e.getMessage());
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("Erro");
         alert.setHeaderText(null);
         alert.setContentText(e.getMessage());
         alert.showAndWait();
      }//END CATCH
   }//END FUNCTION SALVAR FORCADAMENTE

   @FXML
   void volta_estado(ActionEvent event) {
      //Apenas para bloquear a interacao do usuario com o checkbox "Status Conexao"
      boolean isSelected = chkbox_COM_status.isSelected();
      chkbox_COM_status.setSelected(!isSelected);
   }

   public void atualizaListaDadosSeparados(String dados){
      //Metodo que separa cada valor/numero da ultima mensagem, jogando-os para o ArrayList<String> dadosSeparados
      dadosSeparados.clear();//Limpa todos dados separados anteriormente
      StringBuilder sb = new StringBuilder();   //Esse objeto ficara responsavel por pegar apenas um valor numa posicao especifica da string vinda da porta Serial
      //O padrao de dados esperado eh: "accX:1.0 accY:2.0" e por ai vai... Em resumo eh "nome_da_variavel:valor_float ", espaco serve para delimitar o fim de uma variavel e inicio da outra. Sobre o final da linha, pode ser tanto LF como CRLF
      for (int i = 0; i < dados.length(); i++) {
         //Vamos avancar o iterador ate encontrar o "dois pontos"
         if (dados.charAt(i) == ':') {
            while (true) {
               //Vamos fazer um apendice no SB até encontrarmos um espaco ou \n ou \r
               i = i + 1;
               if (dados.charAt(i)==' ' || dados.charAt(i)=='\r' || dados.charAt(i)=='\n') {
                  break;
               }
               sb.append(dados.charAt(i)); // SB recebe o char/numero da posicao atual e faz um apendice
            }//END while (true)
            dadosSeparados.add(sb.toString()); //Hora de salvar os dados/numeros que nos fizemos apendice no SB
            sb.setLength(0); //Vamos resetar o SB para uma nova leitura
         }//END if (dados.charAt(i) == ':')
      } //END for (int i = 0; i < dados.length(); i++)
      //IF a seguir para evitar overflow de dados
      if (dadosSeparados.size() > 27) {
         // Calcular quantos elementos excedentes existem
         int excesso = dadosSeparados.size() - 27;

         // Remover os elementos excedentes
         for (int i = 0; i < excesso; i++) {
            dadosSeparados.remove(dadosSeparados.size() - 1); // Remove os ultimos elementos
         }
     }
   }

   public void atualizaDados(){
      //Nesse metodo vamos atualizar todos TextField das nossas variaveis
      String leitura = ReceptorUart.getUltimaMensagemRecebida();  //Pega Ultima mensagem recebida
      bruto_COM.setText(leitura); //Coloca no campo "Resposta Bruta" o que recebemos para fim de debug
      atualizaListaDadosSeparados(leitura);  //Separa cada valor da ultima mensagem, jogando-os para o ArrayList de dadosSeparados
      for (int i = 0; i < dadosSeparados.size(); i++) {
         widgetsTFields[i].setText(dadosSeparados.get(i)); //Vai setando cada TextField com os valores dos dadosSeparados, observe que nao precisamos ter 27 dados lidos totais na porta Serial
      }
      //PARA SALVAR OS DADOS CONTINUAMENTE NO BANCO DE DADOS, DESCOMENTE ABAIXO
      salvarForcadamente(null);
   }

   public void sinalizadorNovosDados(){
      //Metodo que sinaliza para o thread que gerencia as janelas do JavaFX, informando para atualizar os dados num proximo momento oportuno
      //Quem acessa esse metodo eh o ThreadUartAerospace
      Platform.runLater(() -> this.atualizaDados());
   }

   public void desconexao(){
      chkbox_COM_status.setSelected(false); //Desativa Checkbox "Status Conexao"
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Erro");
      alert.setHeaderText(null);
      alert.setContentText("PORTA COM DESCONECTADA");
      alert.showAndWait();
   }

   public void sinalizaDesconexao(){
      //Metodo que sinaliza para o thread que gerencia as janelas do JavaFX, informando para mostrar que houve desconexao num proximo momento oportuno
      //Quem acessa esse metodo eh o ThreadUartAerospace
      Platform.runLater(() -> this.desconexao());
   }

}

