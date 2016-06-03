package labprogiii.wardriving;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import static android.view.View.*;

public class MainActivity extends Activity {

    Button play;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.buttonPlay);

        play.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SecondaryActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog AlertDialog;
        AlertDialog = new AlertDialog.Builder(this).create();
        AlertDialog.setTitle("Atenção!!");
        AlertDialog.setMessage("Este aplicativo foi desenvolvido unicamente para fins educacionais. Seu uso para outros fins é de sua inteira responsabilidade.");
        AlertDialog.show();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

}
