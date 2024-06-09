/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/package-info.java to edit this template
 */

/**
 * Questo package fornisce un insieme di classi e risorse per la gestione delle previsioni meteorologiche
 * in un'applicazione client-server.
 * 
 * Il progetto è basato su un modello client-server, dove il server è gestito dalla classe {@link ServerMain}
 * e il client è gestito dalla classe {@link ClientHandler}. Il server fornisce metodi per l'accesso e la manipolazione
 * dei dati meteorologici, mentre il client si occupa di interagire con l'utente e inviare richieste al server.
 * 
 * Le classi principali all'interno di questo package includono:
 * - {@link User}: rappresenta un utente del sistema, che può accedere alle previsioni meteorologiche e gestire le proprie impostazioni.
 * - {@link MonitoringStation}: definisce una stazione di monitoraggio meteorologico, che raccoglie dati dalle aree circostanti.
 * - {@link Forecast}: rappresenta una previsione meteorologica per una specifica località e un determinato periodo di tempo.
 * - {@link DBManager}: gestisce le operazioni di accesso al database per la memorizzazione e il recupero dei dati meteorologici e degli utenti.
 * - {@link ServerMain}: implementa l'interfaccia remota per il server delle previsioni meteorologiche, fornendo metodi per l'accesso e la manipolazione dei dati.
 * - {@link ClientHandler}: gestisce la comunicazione tra il client e il server, inviando richieste al server e ricevendo risposte per l'aggiornamento dei dati sul client.
 * 
 * @see User
 * @see MonitoringStation
 * @see Forecast
 * @see DBManager
 * @see ServerMain
 * @see ClientHandler
 */
package climatemonitoring;


