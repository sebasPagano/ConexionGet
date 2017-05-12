package com.example.alumno.conexionweb;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  Handler.Callback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Handler handler = new Handler(this);

        ThreadConexion s1 = new ThreadConexion("http://imagenpng.com/wp-content/uploads/2015/09/eldesastredemaria.blogspot.com-pngnofamosos-63.png",handler);
        Thread t = new Thread(s1);
        ImageView img =(ImageView) findViewById(R.id.imageView);
        TextView txt = (TextView) findViewById(R.id.textView);

        t.start();


    }

    @Override
    public boolean handleMessage(Message msg) {

        Log.d("Respuesta","Llego la respuesta del hilo");
        ImageView img =(ImageView) findViewById(R.id.imageView);
        TextView txt = (TextView) findViewById(R.id.textView);
       switch (msg.arg1){
            case 0:
                Log.d("Activity ","Error");
                break;
            case 1:
                Log.d("Activity","Recibiendo bytes");
               byte[] bytes = (byte[]) msg.obj;
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                img.setImageBitmap(bitmap);
                break;
            case 2:
                txt.setText((String) msg.obj);
                break;

        }

        return false;
    }
}
