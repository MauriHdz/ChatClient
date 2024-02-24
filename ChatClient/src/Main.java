import java.io.*;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Main {

    public static void leerMensaje(BufferedReader entrada) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Código para leer desde el flujo de entrada del socket
                while (true) {

                    try {
                        System.out.println(entrada.readLine());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public static void escribirMensaje(Scanner consola, PrintWriter salida) {
        String lectura;
        while (true) {
            lectura = consola.nextLine();
            salida.println(lectura);
        }
    }

    public static void main(String[] args) {

        byte[] byteIp = new byte[]{127, 0, 0, 1};
        int port = 1063;
        InetAddress ip = null;
        Socket sock;
        BufferedReader sockInput;
        PrintWriter sockOutput;

        try {
            ip = InetAddress.getByAddress(byteIp);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            sock = new Socket(ip, port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            sockInput = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            sockOutput = new PrintWriter(sock.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner consola = new Scanner(System.in);

        leerMensaje(sockInput);
        escribirMensaje(consola, sockOutput);
    }
}