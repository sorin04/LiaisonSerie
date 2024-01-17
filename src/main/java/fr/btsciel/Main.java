package fr.btsciel;

import jssc.SerialPortException;

import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws SerialPortException {
        Application app = new Application();
        app.listeDesCom().forEach(leCom -> System.out.println(leCom));
        try {
            app.initialisation("com10");
        } catch (SerialPortException e) {
            System.err.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true){
            System.out.println("votre message");
            app.ecrire("test Pirgari".getBytes(StandardCharsets.US_ASCII));
            app.ecrire("\r\n".getBytes());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw  new RuntimeException(e);
            }
        }

        /*try {
            app.initialisation("COM10");
            app.configurerParametres(9600,8,0,2);
            app.ecrire("test".getBytes(StandardCharsets.US_ASCII));
            Thread.sleep((2000));


            if (app.detecteSiReception()>0){
                app.lireTrame(app.detecteSiReception());
            }


        } catch (SerialPortException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

        */
    }
}