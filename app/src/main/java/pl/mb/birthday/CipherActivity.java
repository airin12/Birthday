package pl.mb.birthday;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import pl.mb.birthday.pl.mb.mirthday.encryption.AES;


public class CipherActivity extends Activity {

    private final String message = "Prezent znajduje się przy ul. Reymonta 29/5 strzeżony " +
            "przez niejakich Natalię i Kamila. Niestety strażnicy nie zawsze są na posterunku. " +
            "Aby upewnić się czy można ich zastać możesz zadzwonić na 668 165 236 (Natalia) lub " +
            "664 688 743 (Kamil).\0\0\0\0\0\0";
    private EditText keyEditText;
    private TextView messageTextView;
    private byte [] cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cipher);

        keyEditText = (EditText) findViewById(R.id.key_cipher_edit_text);
        messageTextView = (TextView) findViewById(R.id.decoded_cipher_text_view);

        String resultCipher = "";
        try {
            cipher = AES.encrypt(message,"21@zr7IVAq196249");
            resultCipher = new String(cipher,"UTF-8");
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

    public void decode(View view) {
        try {
            String key = keyEditText.getText().toString();

            if(key.length() != 16){
                Toast.makeText(getApplicationContext(),
                        getResources().getString(R.string.cipher_key_length_invalid), Toast.LENGTH_LONG).show();
                return;
            }

            String decodedMessage = AES.decrypt(cipher,key);
            messageTextView.setText(decodedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
