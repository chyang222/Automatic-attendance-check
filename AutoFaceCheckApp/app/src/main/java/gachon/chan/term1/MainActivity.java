package gachon.chan.term1;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENT_ID = "student_id";
    private static final String SERVER_URL = "http://192.9.67.107:5000/set_student_id?student_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        // 로그인 버튼 클릭 시
        Button loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText studentIdEditText = findViewById(R.id.idText);
                String studentId = studentIdEditText.getText().toString().trim();

                if (!studentId.isEmpty()) {
                    // 학번을 Flask 서버로 전송
                    new SetStudentIdTask().execute(studentId);
                } else {
                    Toast.makeText(MainActivity.this, "학번을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class SetStudentIdTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String studentId = params[0];
            setStudentIdOnServer(studentId);
            return null;
        }

        private void setStudentIdOnServer(String studentId) {
            try {
                URL url = new URL(SERVER_URL + studentId);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);

                int responseCode = urlConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // 서버 응답 처리
                    // 성공적으로 학번이 서버에 전송됨

                    // 이후에 원하는 동작 수행, 예를 들어 다음 화면으로 이동
                    Intent intent = new Intent(MainActivity.this, main_activityActivity.class);
                    intent.putExtra(EXTRA_STUDENT_ID, studentId);
                    startActivity(intent);
                    finish();  // 현재 화면 종료 (선택사항)
                } else {
                    // 오류 처리
                    Toast.makeText(MainActivity.this, "서버 응답 오류: " + responseCode, Toast.LENGTH_SHORT).show();
                }

                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
