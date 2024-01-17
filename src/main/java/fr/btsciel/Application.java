package fr.btsciel;

import jssc.SerialPortEvent;
import jssc.SerialPortException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Application extends LiaisonSerie {

    public Application() {
    }

    public ArrayList<String> listeDesCom() {
        return super.listerLesPorts();
    }

    public void serialEvent(SerialPortEvent spe) {
        super.serialEvent(spe);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        byte[] laTrame;
         int longueur = spe.getEventValue();
        laTrame = lireTrame(longueur);
        for (byte b : laTrame) {
            sb1.append((b & 0xff) + " ");
            sb2.append(String.format("%02x", b));
        }
        System.out.println(String.format("""
            Réception
            Format ASCII : %s
            Format Hexa : %s
            Format Chaîne de caractères : %s
            """));
    }

    public void initialisation(String portDeTravail) throws SerialPortException, InterruptedException {
        super.initCom(portDeTravail);
        super.configurerParametres(9600, 8, 0, 2);
        super.ecrire("test pirgari ".getBytes(StandardCharsets.US_ASCII));
        Thread.sleep(2000);
        if (super.detecteSiReception() > 0) {
            System.out.println(new String(super.lireTrame(super.detecteSiReception())));
        }
    }

}
