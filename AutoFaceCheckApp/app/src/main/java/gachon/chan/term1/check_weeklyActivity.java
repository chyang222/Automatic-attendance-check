package gachon.chan.term1;


import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class check_weeklyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_weekly);
        setTitle("주차별 출결현황");

    }
    public void click1(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (v.getId()){

            case R.id.button7:
                intent.setData(Uri.parse("https://www.inflearn.com/course/%EC%9E%90%EB%B0%94java-%EC%96%B8%EC%96%B4-%EA%B8%B0%EB%B3%B8-%EA%B0%95%EC%A2%8C#reviews"));
                startActivity(intent);
                break;
    }
    }




}