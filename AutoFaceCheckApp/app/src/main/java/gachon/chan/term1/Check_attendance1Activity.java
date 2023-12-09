package gachon.chan.term1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Check_attendance1Activity extends AppCompatActivity {

    private String studentId;  // 학번을 저장할 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_attendance1);
        setTitle("출석체크");

        // Intent에서 학번을 받아오기
        Intent intent = getIntent();
        if (intent != null) {
            studentId = intent.getStringExtra(MainActivity.EXTRA_STUDENT_ID);
        }
    }

    public void onClick34(View view) {
        Intent Check_attendance2 = new Intent(Check_attendance1Activity.this, Check_attendance2Activity.class);
        // Check_attendance2Activity로 학번 전달
        Check_attendance2.putExtra(MainActivity.EXTRA_STUDENT_ID, studentId);
        Toast.makeText(this, "출석체크 주차를 선택해주세요", Toast.LENGTH_LONG).show();
        startActivity(Check_attendance2);
    }

    public void click1(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (v.getId()){
            case R.id.button7:
                Intent mai = new Intent(Check_attendance1Activity.this, main_activityActivity.class);
                startActivity(mai);
                break;
            case R.id.button20:
                Intent mails = new Intent(Check_attendance1Activity.this, main_activityActivity.class);
                startActivity(mails);
                break;
            case R.id.button34:
                Intent mail = new Intent(Check_attendance1Activity.this, Check_attendance2Activity.class);
                // Check_attendance2Activity로 학번 전달
                mail.putExtra(MainActivity.EXTRA_STUDENT_ID, studentId);
                startActivity(mail);
                break;
        }
    }

    public void click4(View v){
        Intent intent4 = new Intent(Intent.ACTION_VIEW);
        switch (v.getId()){
            // Handle click4
        }
    }
}
