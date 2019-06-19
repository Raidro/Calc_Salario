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

    //variaveis do INSS
    double SalaBruto = 0;
    double Aliq = 0;
    double BaseInss = 0;
    double ValorInss = 0;
    //------------------

    //variaveis do IRPF
    double AliqIRPF = 0;
    double qtDep = 0;
    double BaseIrpf = 0;
    double deduc = 0;
    double ValorIRPF = 0;
    double SalaLiqui = 0;

    //-----------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitComponents();
        //salario bruto
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

        // numero de dependentes

        editDep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                qtDep = Double.parseDouble(editable.toString());
                IRPF();
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

    public void IRPF() {

        BaseIrpf = (SalaBruto - ValorInss - (qtDep * 189.59));

        if (BaseIrpf < 1903.98) {
            AliqIRPF = 0;
            deduc = 0;
        } else if (BaseIrpf > 1903.98 && BaseIrpf <= 2826.05) {
            AliqIRPF = 0.075;
            deduc = 142.80;


        } else if (BaseIrpf > 2836.05 && BaseIrpf <= 3751.05) {
            AliqIRPF = 0.15;
            deduc = 354.80;

        } else if (BaseIrpf > 3751.05 && BaseIrpf <= 4664.08) {
            AliqIRPF = 0.225;
            deduc = 636.13;
        } else {
            AliqIRPF = 0.275;
            deduc = 869.36;
        }

        ValorIRPF = (BaseIrpf * AliqIRPF) - deduc; // valor do IRPF

        SalaLiqui = SalaBruto - ValorInss - ValorIRPF; // Valor do Salario Liquido


        edtAliIRPF.setText(String.format("%.2f", AliqIRPF));
        edtBaseIRPF.setText(String.format("%.2f", BaseIrpf));
        edtValIRPF.setText(String.format("%,2f", ValorIRPF));
        edtDedu.setText(String.format("%.2f", deduc));
        edtSalLiqui.setText(String.format("%.2f", SalaLiqui));


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
