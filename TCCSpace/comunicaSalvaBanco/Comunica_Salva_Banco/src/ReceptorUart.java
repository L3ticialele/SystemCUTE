import com.fazecast.jSerialComm.SerialPort;

public class ReceptorUart {

    private static MessageListener eventoListener;  //Classe responsavel pelo recebimento assincrono dos dados. Parecido com interrupcoes e eventos de callback
    private static String ultimaMensagemRecebida;   
    private static boolean recebeuMensagem = false; //Flag que indica o recebimento de novos dados, sendo setado pelo MessageListener
    private static SerialPort portaSerial; //Variaveis Static pq so queremos apenas uma conexao serial e queremos uma variavel "tipo global"
    
    public static void setPortaSerial(SerialPort portaSerial) {ReceptorUart.portaSerial = portaSerial;}
    public static SerialPort getPortaSerial() {return portaSerial;}

    public static void configuraPortaSerial(int taxaBaud,String portaCOM){
        ReceptorUart.portaSerial = SerialPort.getCommPort(portaCOM);    //Retorna um objeto para a conexao da portaserial escolhida
        ReceptorUart.portaSerial.setComPortParameters(taxaBaud, 8, SerialPort.ONE_STOP_BIT, SerialPort.NO_PARITY); //Configuracoes como taxaBaud, numero de bits, bits de parada e por fim bit de paridade
    }
    public static boolean abrePortaSerial(){
        //if para verificar se a porta ja esta aberta. Caso contrario vamos abrir a porta
        if (!ReceptorUart.portaSerial.isOpen()){
	        return ReceptorUart.portaSerial.openPort(); //Retorna verdadeiro caso seja possivel abrir a porta
        }
        else return false;  //Retorna falso caso a porta ja esteja aberta
    }

    public static boolean statusPortaSerial(){
        return ReceptorUart.portaSerial.isOpen();   //Retorna verdadeiro caso seja possivel abrir a porta
    }

    public static void fechaPortaSerial(){ReceptorUart.portaSerial.closePort();}
    public static void inicia_leitura(){
        eventoListener = new MessageListener(); //Cria um objeto da classe de Eventos com os triggers
        portaSerial.addDataListener(eventoListener);   //Adiciona os triggers de evento para a conexao serial estabelecida
    }
    //Metodos Syncronized para evitar problema de concorrencia/run
    public static synchronized void notificarTodasThreads() {ReceptorUart.class.notifyAll();} //Tira do estado wait todas as outras threads em execucao
    public static synchronized String getUltimaMensagemRecebida() {return ultimaMensagemRecebida;}
    public static synchronized void setUltimaMensagemRecebida(String ultimaMensagemRecebida) {ReceptorUart.ultimaMensagemRecebida = ultimaMensagemRecebida;}
    public static synchronized boolean getRecebeuMensagem(){return ReceptorUart.recebeuMensagem;}
    public static synchronized void setRecebeuMensagem(boolean status) {
        ReceptorUart.recebeuMensagem = status;
        if (status) {
            //Notifica todas outras threads que poderiam estar lockadas(wait state) esperando por essa informação
            ReceptorUart.notificarTodasThreads();
        }
    }
}

