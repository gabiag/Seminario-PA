package com.example.seminario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.os.Bundle;
import android.util.Log;
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
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class AgendamentoDate extends AppCompatActivity {

    private TextView dataSelecionada;
    private ImageButton selecionarSemana;
    private Button salvar;
    private DatePicker datePicker;
    private MaterialDatePicker<Pair<Long, Long>> materialDatePicker;
    private Calendar calendar = Calendar.getInstance();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.telaDate);
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

        // Instancia um novo DatePicker por meio de metodos da biblioteca.
        materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setTitleText("Select dates")
                .setSelection(
                        Pair.create(MaterialDatePicker.todayInUtcMilliseconds(),null )
                )
                .build();
        materialDatePicker.show(getSupportFragmentManager(), "Tag_picker");

        // Adiciona comportamento esperado ao clicar em button "salvar".
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                Pair<Long, Long> date = (Pair<Long, Long>) selection;
                long start = date.first + TimeUnit.DAYS.toMillis(1); // Pega primeiro dia e adiciona 1 dia (Correção de Fuso)
                long end = date.second + TimeUnit.DAYS.toMillis(1); // Pega ultimo dia e adiciona 1 dia (Correção de Fuso)


                dataSelecionada.setText(String.format("%s a %s", dateFormat.format(start), dateFormat.format(end)));
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
