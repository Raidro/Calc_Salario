package com.example.calcular_salario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtSalBruto, editDep, editDescINSS, edtAliIRPF, edtBaseINSS, edtBaseIRPF, edtValINSS, edtValIRPF, edtDedu, edtSalLiqui;
    Button btnGraf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitComponents();
        edtSalBruto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void InitComponents() {
        // inicia os componentes criados

        edtSalBruto = (EditText) findViewById(R.id.edtSalBruto);
        editDep = (EditText) findViewById(R.id.editDep);
        editDescINSS = (EditText) findViewById(R.id.editDescINSS);
        edtAliIRPF = (EditText) findViewById(R.id.edtAliIRPF);
        edtBaseINSS = (EditText) findViewById(R.id.edtBaseINSS);
        edtBaseIRPF = (EditText) findViewById(R.id.edtBaseIRPF);
        edtValINSS = (EditText) findViewById(R.id.edtValINSS);
        edtValIRPF = (EditText) findViewById(R.id.edtValIRPF);
        edtDedu = (EditText) findViewById(R.id.edtDedu);
        edtSalLiqui = (EditText) findViewById(R.id.edtSalLiqui);
        btnGraf = (Button) findViewById(R.id.btnGraf);


    }
}
