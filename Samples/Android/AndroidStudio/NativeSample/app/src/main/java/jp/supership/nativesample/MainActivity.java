package jp.supership.nativesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static jp.supership.nativesample.definitons.AdTypesConsts.RECTANGLE_NATIVEAD;

public class MainActivity extends AppCompatActivity {

    private ListView adgListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] adTypes = {"ネイティブ広告"};

        adgListView = (ListView) findViewById(R.id.adgListView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, adTypes);
        adgListView.setAdapter(adapter);

        adgListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moveActivity(position);
            }
        });
    }


    private void moveActivity(int position) {
        switch (position) {
            case RECTANGLE_NATIVEAD:
                Intent intent = new Intent(getApplicationContext(), RectangleNativeAdViewActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
