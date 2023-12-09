package gachon.chan.term1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class check_attendance_statueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_attendance_statue);
        setTitle("학생별 출결현황 확인");
    }

    public void click1(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (v.getId()){

            case R.id.button7:
                intent.setData(Uri.parse("https://www.inflearn.com/course/%ED%95%98%EB%A3%A8-10%EB%B6%84-%EC%94%A8%EC%81%A0%EC%81%A0#reviews"));
                startActivity(intent);
                break;



        }
    }





}