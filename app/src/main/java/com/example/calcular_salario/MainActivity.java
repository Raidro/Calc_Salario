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

    double SalaBruto = 0;
    double Aliq = 0;
    double BaseInss = 0;
    double ValorInss = 0;

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
                SalaBruto = Double.parseDouble(editable.toString());
                INSS();
            }
        });

    }

    public void INSS() {


        // se salario bruto menor ou igual ao valor definido, aliquota será de 8%
        if (SalaBruto <= 1751.81) {

            Aliq = 0.08;

            // se não salario bruto  maior que 1700 e menor que 3000, aliquota será de 9%
        } else if (SalaBruto > 1751.81 && SalaBruto <= 2919.72) {
            Aliq = 0.09;
            // se não aliquota = a 11%
        } else if (SalaBruto > 5839.45) {
            Aliq = 0.11;
        }
        //se salario bruto igual ao valor definido, então BI == a esse valor
        if (SalaBruto > 5839.45) {
            BaseInss = 5839.45;

            // se não, BI == SalaBruto
        } else {
            BaseInss = SalaBruto;
        }

        // valor do inss será o calculo entre a aliquota * a base do inss

        ValorInss = Aliq * BaseInss;


        editDescINSS.setText(String.format("%.2f", Aliq));  //mostra na tela o valor da Aliq
        edtBaseINSS.setText(String.format("%.2f", BaseInss)); // mostra na tela o valor de BaseInss
        edtValINSS.setText(String.format("%.2f", ValorInss)); // mostra na tela o valor de ValorInss


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
