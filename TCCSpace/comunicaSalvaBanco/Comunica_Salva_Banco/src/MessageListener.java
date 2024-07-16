import java.nio.charset.StandardCharsets;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListenerWithExceptions;

public final class MessageListener implements SerialPortMessageListenerWithExceptions
{

    //Ativando Triggers para eventos de informacao recebida e porta desconectada
    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED | SerialPort.LISTENING_EVENT_PORT_DISCONNECTED; }

    @Override
    public byte[] getMessageDelimiter() { return new byte[] { (byte)0x0D,(byte)0x0A }; } //Na tabela ASC2 vamos usar CR+LF para identificar fim da linha

    @Override
    public boolean delimiterIndicatesEndOfMessage() { return true; }
        
    @Override
    public void serialEvent(SerialPortEvent event)
    {
        if (event.getEventType() == SerialPort.LISTENING_EVENT_PORT_DISCONNECTED){
            ReceptorUart.fechaPortaSerial();
            ReceptorUart.notificarTodasThreads();
        }
        else{
            byte[] delimitedMessage = event.getReceivedData();  //Para Receber os dados lidos
            String str = new String(delimitedMessage, StandardCharsets.US_ASCII);   //Vamos converter os dados para ASC2
            ReceptorUart.setUltimaMensagemRecebida(str); //Passa informacao para o atributo UltimaMensagemRecebida da classe ReceptorUart
            ReceptorUart.setRecebeuMensagem(true);  //Sinaliza que houve recebimento de novos dados
        }
    }

    @Override
    public void catchException(Exception e) {
        System.out.println(e);
    }
}
