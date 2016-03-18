package com.example.ahmed.bucstudent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.support.v7.widget.Toolbar;


public class Map extends ActionBarActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        WebView map = (WebView)findViewById(R.id.Map);
        WebSettings webSettings = map.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String html = "<iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3638.1979414116236!2d55.89742829843251!3d24.234854748929003!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0000000000000000%3A0x8fe4494edb75347b!2z2YPZhNmK2Kkg2KfZhNio2LHZitmF2Yog2KfZhNis2KfZhdi52YrYqQ!5e0!3m2!1sar!2som!4v1428711347382\" width=\"600\" height=\"450\" frameborder=\"0\" style=\"border:0\"></iframe>";
        map.loadData(html , "text/html" , null);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == android.R.id.home){
            Intent homeIntent = new Intent(this, MainActivity.class);
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
