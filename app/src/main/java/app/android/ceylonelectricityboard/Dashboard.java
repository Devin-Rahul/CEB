package app.android.ceylonelectricityboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;
    private static final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (!checkCallPermission()) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);
        }

        if (!checkSmsPermission()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

    }

    public void sos_btn(View view) {

        String number = "0112324471";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + number));
        startActivity(intent);


        try {
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(number, null, "\"https://www.google.com/maps/@?api=1&map_action=map&AIzaSyBKN1uQEd-Saq99wM8OQXGXBw9WeoHITec",
                    null, null);
        } catch (Exception e) {
            Toast.makeText(Dashboard.this, "Sms not Send", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
    private boolean checkCallPermission() {
        return ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkSmsPermission(){
        return ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MAKE_CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "Permission granted for making calls", Toast.LENGTH_SHORT).show();

            }
        }
        if (requestCode == SEND_SMS_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "Permission granted for send sms", Toast.LENGTH_SHORT).show();
            }
        }

    }
}