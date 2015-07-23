package pl.mb.birthday;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;


public class TipActivity extends Activity {

    private String [] descArray = new String[]{
            "dom",
            "basen"
    };

    private int [] imageIdsActual = new int [] {
            R.drawable.dom,
            R.drawable.basen
    };

    private int [] imageIdsNext = new int [] {
            R.drawable.basen,
            R.drawable.mapka
    };

    private Map<String,Integer> imageIdMapActual = new HashMap<String, Integer>();
    private Map<String,Integer> imageIdMapNext = new HashMap<String, Integer>();

    {
        for(int i = 0 ; i < imageIdsActual.length ; i++){
            imageIdMapActual.put(descArray[i],new Integer(imageIdsActual[i]));
            imageIdMapNext.put(descArray[i],new Integer(imageIdsNext[i]));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        ImageView actualImageView = (ImageView) findViewById(R.id.actual_image_view);
        actualImageView.setImageResource(imageIdMapActual.get(title).intValue());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip, menu);
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
