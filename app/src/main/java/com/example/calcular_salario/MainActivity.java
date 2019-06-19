package com.example.calcular_salario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtSalBruto, editDep, editDescINSS, edtAliIRPF, edtBaseINSS, edtBaseIRPF, edtValINSS, edtValIRPF, edtDedu, edtSalLiqui;
    Button btnGraf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
