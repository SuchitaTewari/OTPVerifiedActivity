package project.suchita.com.otpverifiedactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {

    Button sendBtn;
    EditText txtphoneNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button)findViewById(R.id.btnSendSMS);

        txtphoneNo = (EditText) findViewById(R.id.editText);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendSMSMessage(txtphoneNo.getText().toString(),"");
            }
        });

    }
    public void sendSMSMessage(String phoneNo, String msg){
        try {

            SmsManager smsManager = SmsManager.getDefault();
            int otpNumber = generatePin();
            String message = "Your OTP for the app is " + otpNumber;
            smsManager.sendTextMessage(phoneNo, null, message, null, null);

            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
            //create shared preferance
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();

            //put values in shared preferance
            editor.putInt("otp", otpNumber);
            editor.commit();
            Intent intent = new Intent(getBaseContext(),OtpActivity.class);
            startActivity(intent);

        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();

        }
    }
    public static int generatePin() throws Exception {
        Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());

        int num = generator.nextInt(99999) + 99999;
        if (num < 100000 || num > 999999) {
            num = generator.nextInt(99999) + 99999;
            if (num < 100000 || num > 999999) {
                throw new Exception("Unable to generate PIN at this time..");
            }
        }
        return num;
    }
}