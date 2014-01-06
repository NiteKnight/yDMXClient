/*
 * Copyright (c) 2014. by Michael "NiteKnight" Scheidl. Baden/AUSTRIA
 */

package at.niteknight.yDMXClient;

import android.os.Debug;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class tcpClient {
    private Socket clientSocket;
    private DataOutputStream outToServer;

    public void yDMXClient()
    {
    }

    public String connect()
    {
        new Thread(new ClientThread()).start();
        return "Alles klar.";
    }

    public void sendFaderValue(int faderID, int faderValue)
    {
        try {
            outToServer.writeBytes("F" + faderID + "|" + faderValue + '\n');
        } catch (Exception e) {
        }
    }

    public void sendBlackOut(boolean state)
    {
        try {
            String strState = "0";
            if (state == true) {
                strState = "1";
            } else {
                strState = "0";
            }
            outToServer.writeBytes("B|" + strState + '\n');
        } catch (Exception e) {
        }
    }

    public void close()
    {
        try {
            clientSocket.close();
        } catch (Exception e) {
        }
    }

    class ClientThread implements Runnable
    {
        @Override
        public void run()
        {
            try {
                InetAddress serverAddr = InetAddress.getByName("192.168.1.99");
                clientSocket = new Socket(serverAddr, 50000);
                System.out.println("Socket erzeugt");
            } catch (Exception e) {
                //return e.getMessage();
            }
            try {
                outToServer = new DataOutputStream(clientSocket.getOutputStream());
            } catch (Exception e) {
                //return e.getMessage();
            }
        }
    }
}
