Prima di fare ogni cosa è necessario avere il server PostgresSQL avviato. Da shell avviare tramite comando Maven cosi da poter eseguire il pom.xml che automaticamente creerà il db in locale:
mvn clean install

In caso non si vuole usare Maven il ddl è fornito nella directory "\ClimateMonitoring\src\main\sql\db.sql"

L'applicazione è possibile eseguirla in ambiente windows mediante i file .jar del ServerCm.jar e del ClientCm.jar, mediante riga di comando come segue:
java -jar ServerCm.jar
java -jar ClientCm.jar

Come prima cosa andrebbe avviato il proprio server postgresql sul proprio pc, in seguito impostata come password per l'utente "postgres" la seguente password:"postgres" attraverso la shell di postgresql. (Qui il link che spiega come effettuare questa modifica: https://commandprompt.com/education/how-to-reset-forgotten-password-for-postgres-user/)

Una volta impostata la password per il relativo utente admin, si dovrà avviare prima l'eseguibile del server:
java -jar ServerCm.jar
E inserire le credenziali del database(es. localhost, porta 5432, postgres, postgres), così da avere il servizio attivo e poi quella del client così da potersi connettere al medesimo, tramite il seguente comando da console: 
java -jar ClientCm.jar
Il server è impostato staticamente sulla porta 1234.


