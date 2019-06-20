package com.example.calcular_salario;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    //definindo tudo que é editText e Button
    EditText edtSalBruto, editDep, editDescINSS, edtAliIRPF, edtBaseINSS, edtBaseIRPF, edtValINSS, edtValIRPF, edtDedu, edtSalLiqui;
    Button btnGraf;

    //variaveis globais do INSS
    double SalaBruto = 0;
    double Aliq = 0;
    double BaseInss = 0;
    double ValorInss = 0;
    //------------------

    //variaveis globais do IRPF
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

        InitComponents(); // inicia os componentes chamando a função initcomponents
        //salario bruto
        // fica verificando se ouve mudança no editDep
        edtSalBruto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //apos a mudanã, realiza os 2 comandos, pegando oque foi escrito e iniciando a função INSS

                SalaBruto = Double.parseDouble(editable.toString());
                INSS();


            }
        });

        // numero de dependentes
        // fica verificando se ouve mudança no editDep
        editDep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //apos a mudanã, realiza os 2 comandos, pegando oque foi escrito e iniciando a função IRPF
                qtDep = Double.parseDouble(editable.toString());
                IRPF();
            }
        });

        btnGraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String edtSB = edtSalBruto.getText().toString();
                String edtDep = editDep.getText().toString();

                //se os valores forem 0 ou nulo ele vai da uma msg de erro dizendo que 'obrigatorio que tenham valores acima de 0
                if (edtSB == null && edtDep == null || edtSB.equals("") && edtDep.equals("")) {

                    edtSalBruto.setError("Campo Obrigatorio!"); //msg de erro
                    editDep.setError("Campo Obrigatorio!"); //msg de erro

                } else {

                    gerarGrafico(); // gera o grafico, se não tiver erro (no caso, valores acima de 1)
                }
                //aqui termina o if
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
        } else if (SalaBruto > 2919.72) {
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


        editDescINSS.setText(String.format("%.2f", Aliq * 100) + " %");  //mostra na tela o valor da Aliq e multiplica por 100 pra dar o valor em %

        edtBaseINSS.setText(String.format("%.2f", BaseInss)); // mostra na tela o valor de BaseInss
        edtValINSS.setText(String.format("%.2f", ValorInss)); // mostra na tela o valor de ValorInss
        // até duas casas decimais
        edtBaseINSS.setText(String.format("%.2f", BaseInss)); // mostra na tela o valor de BaseInss
        edtValINSS.setText(String.format("%.2f", ValorInss)); // mostra na tela o valor de ValorInss


    }

    public void IRPF() {

        //calculo da base do irpf
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


        edtAliIRPF.setText(String.format("%.3f", AliqIRPF * 100) + " %");  //multiplico por 100 pra dar o valor em %, e aceito até 3 casas decimais

        edtBaseIRPF.setText(String.format("%.2f", BaseIrpf));
        edtValIRPF.setText(String.format("%.2f", ValorIRPF));
        edtDedu.setText(String.format("%.2f", deduc));
        edtSalLiqui.setText(String.format("%.2f", SalaLiqui));

        edtBaseIRPF.setText(String.format("%.2f", BaseIrpf));
        edtValIRPF.setText(String.format("%.2f", ValorIRPF));
        edtDedu.setText(String.format("%.2f", deduc));
        edtSalLiqui.setText(String.format("%.2f", SalaLiqui));


    }

    public void gerarGrafico() {

        // converter e pegar os valores de salario liquido, inss e irpf

        float sal_liqui = 0, inss = 0, irpf = 0;

        try {
            // exection
            //trasforma os valores em float

            sal_liqui = Float.parseFloat(edtSalLiqui.getText().toString());
            inss = Float.parseFloat(edtValINSS.getText().toString());
            irpf = Float.parseFloat(edtValIRPF.getText().toString());


        } catch (Exception e) {

        }

        // enviando os dados para o grafico

        Intent grafico = new Intent(MainActivity.this, GraficoActivity.class); // está pegando a referencia da segunda activity, no caso do grafico
        grafico.putExtra("Salario", sal_liqui);
        grafico.putExtra("INSS", inss);
        grafico.putExtra("IRPF", irpf);
        startActivity(grafico); // inicia a activity do grafico


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
