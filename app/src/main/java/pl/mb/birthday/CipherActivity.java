package pl.mb.birthday;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pl.mb.birthday.pl.mb.mirthday.encryption.AES;


public class CipherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher);

        String message = "Prezent znajduje się w domu u niejakiej Natalii";
        String resultCipher = "";
        try {
            byte [] cipher = AES.encrypt(message,"0123456789abcdef");
            StringBuilder builder = new StringBuilder();
            for(byte b : cipher)
                builder.append(new Integer(b));

            resultCipher = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            resultCipher = e.getMessage();
        }

        TextView cipherTextView = (TextView) findViewById(R.id.cipher_text_view);
        cipherTextView.setText(resultCipher);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cipher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
