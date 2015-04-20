package pae.com.wa.udpclient01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class MainActivity extends Activity {

    Button b;
    EditText sms;
    String msgs="";
    TextView ss;


    public void udps() {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                try {

                    int server_port = 4444;
                    DatagramSocket s = new DatagramSocket();
                    InetAddress local = InetAddress.getByName("128.199.230.75");
                    String data=sms.getText().toString();
                    int msg_length = data.length();
                    byte[] message = data.getBytes();
                    DatagramPacket p = new DatagramPacket(message, msg_length, local,server_port);
                    s.send(p);
                    byte[] message2 = new byte[1500];
                    p = new DatagramPacket(message2, message2.length);
                    s.receive(p);
                    msgs = new String(message2, 0, p.getLength());



                } catch (Exception e) {

                }
                handler.post(new Runnable(){
                    public void run() {
                        ss.setText(msgs);
                    }
                });

            }
        };
        new Thread(runnable).start();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = (Button) findViewById(R.id.button1);
        sms = (EditText) findViewById(R.id.input1);
        ss=(TextView)findViewById(R.id.txt1);
//                msgs = sms.getText().toString();
        //
        //  Thread t = new Thread(new Second(msgs));

        /// t.start();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                udps();

            }
        });


    }
}

/*
  class Second implements Runnable {

      private String data;
      public  Second(String data)
        {
            this.data=data;
            run();

        }

        public void run() {
            // TODO Auto-generated method stub
            try {

                int server_port = 4444;
                DatagramSocket s = new DatagramSocket();
                InetAddress local = InetAddress.getByName("128.199.230.75");

                int msg_length = data.length();
                byte[] message = data.getBytes();
                DatagramPacket p = new DatagramPacket(message, msg_length, local,
                        server_port);
                s.send(p);

                p = new DatagramPacket(message, message.length);
                s.receive(p);
                String txt = new String(message, 0, p.getLength());



            } catch (Exception e) {

            }
        }
    }*/