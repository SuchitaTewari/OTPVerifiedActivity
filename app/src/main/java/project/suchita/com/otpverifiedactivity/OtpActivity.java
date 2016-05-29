package project.suchita.com.otpverifiedactivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OtpActivity extends AppCompatActivity {
    EditText otpMessage;
    Button verifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        verifyButton = (Button) findViewById(R.id.verifyButton);
        verifyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                otpMessage = (EditText) findViewById(R.id.etOtp);
                //create shared preferance
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                //getting values from shared preferance
                int savedOtp=pref.getInt("otp", 0);

                //comparing between the values
                int enteredOtp = Integer.parseInt(otpMessage.getText().toString());
                if(savedOtp == enteredOtp){
                    Toast.makeText(getApplicationContext(), "Registered Successfully",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                }
                else{
                    Toast.makeText(getApplicationContext(), "OTP do no match",
                            Toast.LENGTH_LONG).show();
                    otpMessage.setText("");

                }
            }
        });

    }
}