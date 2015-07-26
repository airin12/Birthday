package pl.mb.birthday;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class TipActivity extends Activity {

    private String [] testDescArray = new String[]{
            "dom",
            "basen"
    };

    private String [] descArray = new String[]{
            "m1",
            "m2",
            "m3",
            "m4",
            "m5",
            "m6",
            "m7",
            "m8",
            "m9",
            "m10"
    };

    private int [] testImageIdsActual = new int [] {
            R.drawable.dom,
            R.drawable.basen
    };

    private int [] testImageIdsNext = new int [] {
            R.drawable.basen,
            R.drawable.mapka
    };

    private int [] imageIdsActual = new int [] {
            R.drawable.webapp,
            R.drawable.next_0,
            R.drawable.next_1,
            R.drawable.next_2,
            R.drawable.next_3,
            R.drawable.next_4,
            R.drawable.next_5,
            R.drawable.next_6,
            R.drawable.next_7,
            R.drawable.next_8

    };

    private int [] imageIdsNext = new int [] {
            R.drawable.next_0,
            R.drawable.next_1,
            R.drawable.next_2,
            R.drawable.next_3,
            R.drawable.next_4,
            R.drawable.next_5,
            R.drawable.next_6,
            R.drawable.next_7,
            R.drawable.next_8,
            R.drawable.mapka
    };

    private Map<String,Integer> testImageIdMapActual = new HashMap<String, Integer>();
    private Map<String,Integer> testImageIdMapNext = new HashMap<String, Integer>();

    private Map<String,Integer> imageIdMapActual = new HashMap<String, Integer>();
    private Map<String,Integer> imageIdMapNext = new HashMap<String, Integer>();


    private int [] testTipIdsActual = new int [] {
            R.string.test_actual_tip_0,
            R.string.test_actual_tip_1
    };

    private int [] testTipIdsNext = new int [] {
            R.string.test_next_tip_0,
            R.string.test_next_tip_1
    };

    private int [] tipIdsActual = new int [] {
            R.string.actual_tip_0,
            R.string.actual_tip_1,
            R.string.actual_tip_2,
            R.string.actual_tip_3,
            R.string.actual_tip_4,
            R.string.actual_tip_5,
            R.string.actual_tip_6,
            R.string.actual_tip_7,
            R.string.actual_tip_8,
            R.string.actual_tip_9,
    };

    private int [] tipIdsNext = new int [] {
            R.string.next_tip_0,
            R.string.next_tip_1,
            R.string.next_tip_2,
            R.string.next_tip_3,
            R.string.next_tip_4,
            R.string.next_tip_5,
            R.string.next_tip_6,
            R.string.next_tip_7,
            R.string.next_tip_8,
            R.string.next_tip_9
    };

    private Map<String,Integer> testTipIdMapActual = new HashMap<String, Integer>();
    private Map<String,Integer> testTipIdMapNext = new HashMap<String, Integer>();

    private Map<String,Integer> tipIdMapActual = new HashMap<String, Integer>();
    private Map<String,Integer> tipIdMapNext = new HashMap<String, Integer>();

    {
//        for(int i = 0 ; i < testImageIdsActual.length ; i++){
//            testImageIdMapActual.put(testDescArray[i], new Integer(testImageIdsActual[i]));
//            testImageIdMapNext.put(testDescArray[i], new Integer(testImageIdsNext[i]));
//
//            testTipIdMapActual.put(testDescArray[i], new Integer(testTipIdsActual[i]));
//            testTipIdMapNext.put(testDescArray[i], new Integer(testTipIdsNext[i]));
//        }

        for(int i = 0 ; i < imageIdsActual.length ; i++){
            imageIdMapActual.put(descArray[i], new Integer(imageIdsActual[i]));
            imageIdMapNext.put(descArray[i], new Integer(imageIdsNext[i]));

            tipIdMapActual.put(descArray[i], new Integer(tipIdsActual[i]));
            tipIdMapNext.put(descArray[i], new Integer(tipIdsNext[i]));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

//        ImageView actualImageView = (ImageView) findViewById(R.id.actual_image_view);
//        actualImageView.setImageResource(testImageIdMapActual.get(title).intValue());
//        TextView actualTextView = (TextView) findViewById(R.id.actual_tip_view);
//        actualTextView.setText(testTipIdMapActual.get(title));
//
//
//        ImageView nextImageView = (ImageView) findViewById(R.id.next_image_view);
//        nextImageView.setImageResource(testImageIdMapNext.get(title).intValue());
//        TextView nextTextView = (TextView) findViewById(R.id.next_tip_view);
//        nextTextView.setText(testTipIdMapNext.get(title));

        ImageView actualImageView = (ImageView) findViewById(R.id.actual_image_view);
        actualImageView.setImageResource(imageIdMapActual.get(title).intValue());
        TextView actualTextView = (TextView) findViewById(R.id.actual_tip_view);
        actualTextView.setText(tipIdMapActual.get(title));


        ImageView nextImageView = (ImageView) findViewById(R.id.next_image_view);
        nextImageView.setImageResource(imageIdMapNext.get(title).intValue());
        TextView nextTextView = (TextView) findViewById(R.id.next_tip_view);
        nextTextView.setText(tipIdMapNext.get(title));
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
