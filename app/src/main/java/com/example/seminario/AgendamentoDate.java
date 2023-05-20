package com.example.seminario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AgendamentoDate extends AppCompatActivity {

    private TextView dataSelecionada;
    private ImageButton selecionarSemana;
    private Button salvar;
    private DatePicker datePicker;
    private MaterialDatePicker materialDatePicker;
    private Calendar calendar = Calendar.getInstance();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        datePicker = findViewById(R.id.date_picker);
        selecionarSemana = findViewById(R.id.selecionar_semana);
        dataSelecionada = findViewById(R.id.data_selecionada);
        salvar = findViewById(R.id.select_data);

        dataSelecionada.setText(dateFormat.format(calendar.getTime())); // Realiza set da data inicial

        // Verifica se botão de seleção semanal foi clicado.
        selecionarSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seletorSemana();
            }
        });

        seletorDia(); // Adiciona trecho responsável por verificar mudança de data.

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AgendamentoDate.this, "Data selecionada: " + dataSelecionada.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Exemplo de como gerar um seletor de diversos dias.
     */
    protected void seletorSemana(){
        materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Select dates")
                .setSelection(
                        Pair.create(MaterialDatePicker.todayInUtcMilliseconds(),null )
                )
                .build();
        materialDatePicker.show(getSupportFragmentManager(), "Tag_picker");

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Pair<Long, Long> date = (Pair<Long, Long>) selection;
                long start = date.first;
                long end = date.second;

                dataSelecionada.setText(dateFormat.format(start) + " a " + dateFormat.format(end));
            }
        });
    }

    /**
     * Exemplo de seletor unico de data
     */
    protected void seletorDia(){
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int ano, int mes, int dia) {
                calendar.set(ano, mes, dia);
                dataSelecionada.setText(dateFormat.format(calendar.getTime()));
            }
        });
    }
}
