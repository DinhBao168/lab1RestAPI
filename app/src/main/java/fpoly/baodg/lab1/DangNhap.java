package fpoly.baodg.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangNhap extends AppCompatActivity {
    private EditText edtName, edtPass;
    private Button btnLogin, btnRegister;
    private FirebaseAuth auth;
    private TextView tvDangKi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtName = findViewById(R.id.edtName);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);
        tvDangKi = findViewById(R.id.tvDangki);

        tvDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this, DangKy.class));
                finish();
            }
        });

auth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(view ->{
     String name = edtName.getText().toString();
     String pass = edtPass.getText().toString();
        auth.signInWithEmailAndPassword(name, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         FirebaseUser user = auth.getCurrentUser();
                         Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(DangNhap.this, DangXuat.class));
                         finish();

                     }else {
                         Toast.makeText(DangNhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                     }
                    }
                });
        });

    }
}