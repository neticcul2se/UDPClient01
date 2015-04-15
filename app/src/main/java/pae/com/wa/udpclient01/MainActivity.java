package pae.com.wa.udpclient01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class MainActivity extends Activity {
    Button b;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Thread t = new Thread(new Second());
                t.start();
            }
        });


    }

}
     class Second implements Runnable {
        Second()
        {
            run();
        }
        public void run() {
            // TODO Auto-generated method stub
            try {

                String messageStr = "Hello Android!";
                int server_port = 1234;
                DatagramSocket s = new DatagramSocket();
                InetAddress local = InetAddress.getByName("192.168.56.1");
                int msg_length = messageStr.length();
                byte[] message = messageStr.getBytes();
                DatagramPacket p = new DatagramPacket(message, msg_length, local,
                        server_port);
                s.send(p);
            } catch (Exception e) {

            }
        }
    }