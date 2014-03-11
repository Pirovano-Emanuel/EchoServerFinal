import java.net.*;
import java.io.*;
 
class Server {      // Classe server
 
    public static void main(String args[]) throws IOException {     // Main che gestisce le eccezioni con il throws
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(95);
        } catch (IOException ioe) {
            System.out.println("Impossibile trovare porta");
            System.exit(1);
        }
        Socket soc = null;
        try {
            soc = ss.accept();
            System.out.println("Connessione accettata a :" + soc);
        } catch (IOException ioe) {
            System.out.println("Il server non ha accettato");
            System.exit(1);
        }
        DataOutputStream data = new DataOutputStream(soc.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        String s;
        System.out.println("Il server sta aspettando il messaggio dal client");
        boolean quit = false;
        do {
            String msg = "";
            s = br.readLine();
            int lunghezza = s.length();
            if (s.equals("Fine")) {
                quit = true;
            }
            
            
            
            
            int i;      // Contatore for
            for (i = 0; i < lunghezza; i++) {
                msg = msg + s.charAt(i);
                data.write((byte) s.charAt(i));
            }
 
            System.out.println("Dal client :" + msg);       // Messaggio immesso dal utente nel client
            data.write(13);
            data.write(10);
            data.flush();            // Flush
        } while (!quit);
        data.close();
        soc.close();
 
    }
}