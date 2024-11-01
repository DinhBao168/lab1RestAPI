package fpoly.baodg.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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

public class DangKy extends AppCompatActivity {
   private EditText edtName, edtPass;
   private Button btnSignUp;
   private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        auth = FirebaseAuth.getInstance();
        btnSignUp.setOnClickListener(view ->{
            String name = edtName.getText().toString();
            String pass = edtPass.getText().toString();
            auth.createUserWithEmailAndPassword(name, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = auth.getCurrentUser();
                                Toast.makeText(DangKy.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DangKy.this, DangNhap.class));
                                finish();
                            }else {
                                Toast.makeText(DangKy.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });

    }
    private void init(){
        edtName = findViewById(R.id.edtNameDK);
        edtPass = findViewById(R.id.edtPassDK);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}