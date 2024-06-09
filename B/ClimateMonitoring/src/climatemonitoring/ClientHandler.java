/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package climatemonitoring;

import java.lang.System.Logger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.logging.Level;

/**
 *
 * @author Payd3r
 */
public class ClientHandler extends UnicastRemoteObject {

    /**
     * L'attributo <code> PORT </code> indica la porta su cui il client si
     * connetterà al server.
     * <p>
     * Questo valore rappresenta la porta di rete utilizzata per la
     * comunicazione con il server. Il valore predefinito è 1234.
     */
    private static int PORT = 1234;
    /**
     * <code> stub </code> per l'interfaccia del server.
     * <p>
     * Questo oggetto rappresenta il riferimento remoto all'interfaccia del
     * server, consentendo al client di invocare metodi remoti sul server.
     */
    private ServerInterface stub;
    /**
     * <code> instance </code> è un'istanza singleton della classe
     * ClientHandler.
     * <p>
     * Questa variabile tiene traccia dell'unica istanza della classe
     * ClientHandler, assicurando che venga utilizzato il pattern singleton.
     */
    private static ClientHandler instance = null;

    /**
     * Costruttore della classe ClientHandler.
     * <p>
     * Questo costruttore inizializza lo stub per l'interfaccia del server,
     * cercando il registro sul localhost alla porta specificata e ottenendo il
     * riferimento allo stub remoto denominato "Stub".
     *
     * @throws RemoteException se si verifica un errore di comunicazione durante
     * l'accesso al registro RMI.
     * @throws NotBoundException se lo stub remoto con il nome specificato non è
     * legato nel registro.
     * @throws InterruptedException se l'operazione di lookup viene interrotta.
     */
    public ClientHandler() throws RemoteException, NotBoundException, InterruptedException {
        stub = (ServerInterface) LocateRegistry.getRegistry("127.0.0.1", PORT).lookup("Stub");
    }

    /**
     * Restituisce lo stub per l'interfaccia del server.
     * <p>
     * Questo metodo fornisce l'accesso al riferimento remoto dello stub del
     * server, che consente al client di invocare metodi remoti sul server.
     *
     * @return il riferimento allo stub del server
     */
    public ServerInterface getStub() {
        return stub;
    }

    /**
     * Restituisce l'istanza singleton della classe ClientHandler.
     * <p>
     * Questo metodo crea e restituisce l'unica istanza della classe
     * ClientHandler, assicurandosi che il costruttore venga chiamato solo una
     * volta. Se l'istanza non esiste, viene creata e inizializzata gestendo
     * eventuali eccezioni.
     *
     * @return l'unica istanza della classe ClientHandler
     * @throws RemoteException se si verifica un errore di comunicazione durante
     * l'accesso al registro RMI
     * @throws NotBoundException se lo stub remoto con il nome specificato non è
     * legato nel registro
     * @throws InterruptedException se l'operazione di lookup viene interrotta
     */
    public static ClientHandler getInstance() {
        if (instance == null) {
            try {
                instance = new ClientHandler();
            } catch (RemoteException | NotBoundException | InterruptedException ex) {
                java.util.logging.Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return instance;
    }

}
