package b.android.untitled.com.notiftester;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =".MainActivity" ;
    private AtomicInteger msgId = new AtomicInteger(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);
        TextView text = (TextView) findViewById(R.id.text) ;
        text.setText(token);
        EditText editText = (EditText)findViewById(R.id.edittext);
        TokenService tokenService =new TokenService(this);
        tokenService.registerTokenInDB(token);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseMessaging fm = FirebaseMessaging.getInstance();
                fm.send(new RemoteMessage.Builder("157978564117" + "@gcm.googleapis.com")
                        .setMessageId(Integer.toString(msgId.incrementAndGet()))
                        .addData("my_message", "Hello World")
                        .addData("my_action","SAY_HELLO")
                        .build());
            }
        });
    }
}
