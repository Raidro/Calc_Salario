package com.example.calcular_salario;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class GraficoActivity extends AppCompatActivity {

    private PieChart myChart;
    List<PieEntry> valores = new ArrayList<>();
    PieDataSet dataSet1;
    PieData dados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);

        myChart = (PieChart) findViewById(R.id.GrafPerc);

        // Receber os parametros da activity Main//

        Bundle param = getIntent().getExtras();

        float sal_liqui, inss, irpf;

        sal_liqui = param.getFloat("Salario");
        inss = param.getFloat("INSS");
        irpf = param.getFloat("IRPF");


        myChart.setUsePercentValues(true);
        myChart.setExtraOffsets(5, 10, 5, 5);
        myChart.setDrawHoleEnabled(true);
        myChart.setHoleColor(Color.WHITE);
        myChart.setTransparentCircleRadius(31f);
        myChart.setHoleRadius(20f);

        //cor e fontes

        myChart.setEntryLabelColor(Color.DKGRAY);
        myChart.setCenterTextSize(18f);

        valores.add( new PieEntry(sal_liqui, "% Salario Liquido"));
        valores.add( new PieEntry(inss, "% INSS"));
        valores.add( new PieEntry(irpf, "% IRPF"));



    }
}
