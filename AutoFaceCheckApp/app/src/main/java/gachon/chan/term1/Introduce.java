package gachon.chan.term1;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Introduce extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce);
        setTitle("출석체크");



    }
    public void click25(View v){
        Intent Introduce = new Intent(Introduce.this, main_activityActivity.class);
        startActivity(Introduce);
    }






}