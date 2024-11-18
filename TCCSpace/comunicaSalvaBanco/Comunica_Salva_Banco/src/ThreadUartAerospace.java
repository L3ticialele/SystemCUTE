
public class ThreadUartAerospace extends Thread {

    private static boolean encerrar;    //Variavel que indica para a thread a hora de encerrar/finalizar
    private final ControladorWidgets controlador; //Precisamos de uma referencia do controlador pois vamos usar alguns metodos de que sinalizam de novos dados ou de desconexao da porta

    private ThreadUartAerospace(ControladorWidgets controladorWidgets) {
        super("ThreadUartRecepcao"); //Apenas para fim de debug
        encerrar=false; //Inicializacao para evitar erros com variaveis estaticas
        //Define o Controlador de Widgets para passar valores para interface
        this.controlador = controladorWidgets;
    }

    public static ThreadUartAerospace criarInstancia(ControladorWidgets controladorWidgets,String comSelecionada){
        ReceptorUart.configuraPortaSerial(9600,comSelecionada); //Mude a taxa de Baud se for necessario
        if (ReceptorUart.abrePortaSerial()) {
            return new ThreadUartAerospace(controladorWidgets); //Se foi aberta a conexao retorne um objeto do tipo ThreadUartAerospace
        }
        else {
            ReceptorUart.fechaPortaSerial(); //Fecha porta Serial para evitar problemas
            return null;//Retorna um objeto null para sinalizar erros
        }
    }

    public static synchronized void setEncerrar(boolean estado) {
        ThreadUartAerospace.encerrar = estado;
    }
    public static synchronized boolean getEncerrar() {
        return encerrar;
    }

    @Override
    public void run() {
        try {
            ReceptorUart.inicia_leitura();  //Inicializa Evento assincrono de leitura do MessageListener
            //Syncronized para podermos ficar parados/sem executar nada quando chamarmos o metodo wait
            synchronized (ReceptorUart.class) {
                //O while a seguir verifica se outras classes pediram o encerramento do thread, ou se a conexao foi fechada/desconectada da porta USB
                while (!getEncerrar() && ReceptorUart.statusPortaSerial()) {
                    //A mesma ideia que o while acima, porem queremos ficar presos no while enquanto nao recebemos novas mensagens. Nao tente entender a logica abaixo, ja que foi feita uma tabela verdade para a logica do while abaixo
                    while ((!ReceptorUart.getRecebeuMensagem())&&(!getEncerrar())&&ReceptorUart.statusPortaSerial() ) {
                        ReceptorUart.class.wait(); // Espera até que recebeuMensagem seja verdadeiro
                    }
                    if(!getEncerrar())//If para evitar o salvamento de dados ao encerrar a thread
                    {
                        // Reseta o flag/sinalizador RecebeuMensagem e sinaliza para o controlador que esta na hora de atualizar os dados da interface
                        ReceptorUart.setRecebeuMensagem(false);
                        controlador.sinalizadorNovosDados();
                    }
                }
            }
            ReceptorUart.fechaPortaSerial();    //Se o usuario fechar o thread pelo encerrar, a porta serial continua aberta. Portanto precisamos fechar
            if (!ReceptorUart.statusPortaSerial()) {
                controlador.sinalizaDesconexao();   //Sinaliza desconexao
            }
        } catch (Exception e) {
            System.out.println("Exception is caught");
            e.printStackTrace();
        }
    }
}
