package gachon.chan.term1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class main_activityActivity extends AppCompatActivity {
    private String studentId;  // 학번을 저장할 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setTitle("오늘은 12월 12일 화요일입니다.");

        // Intent에서 학번을 받아오기
        Intent intent = getIntent();
        if (intent != null) {
            studentId = intent.getStringExtra(MainActivity.EXTRA_STUDENT_ID);
        }
    }

    public void onclick2(View v){
        Intent check_attendance_statue = new Intent(main_activityActivity.this, check_weeklyActivity.class);
        Toast.makeText(this, "주차별 출결현황을 확인해보세요", Toast.LENGTH_LONG).show();
        // Check_attendance1Activity로 학번 전달
        check_attendance_statue.putExtra(MainActivity.EXTRA_STUDENT_ID, studentId);
        startActivity(check_attendance_statue);
    }

    public void onclick3(View v){
        Intent clang = new Intent(main_activityActivity.this, check_attendance_statueActivity.class);
        Toast.makeText(this, "학생별 출결 현황을 확인해보세요", Toast.LENGTH_LONG).show();
        // Check_attendance1Activity로 학번 전달
        clang.putExtra(MainActivity.EXTRA_STUDENT_ID, studentId);
        startActivity(clang);
    }

    public void onclick1(View v){
        Intent Check_attendance1 = new Intent(main_activityActivity.this, Check_attendance1Activity.class);
        Toast.makeText(this, "출석체크 할 과목을 선택해주세요", Toast.LENGTH_LONG).show();
        // Check_attendance1Activity로 학번 전달
        Check_attendance1.putExtra(MainActivity.EXTRA_STUDENT_ID, studentId);
        startActivity(Check_attendance1);
    }

    public void click25(View v){
        Intent Check_attendance1 = new Intent(main_activityActivity.this, Introduce.class);
        Toast.makeText(this, "소개페이지입니다.", Toast.LENGTH_LONG).show();
        // Check_attendance1Activity로 학번 전달
        Check_attendance1.putExtra(MainActivity.EXTRA_STUDENT_ID, studentId);
        startActivity(Check_attendance1);
    }
}
