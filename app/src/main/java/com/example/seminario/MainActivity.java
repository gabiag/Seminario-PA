package com.example.seminario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        CardView cardAllDay = findViewById(R.id.allDay);
        CardView cardAppointment = findViewById(R.id.appointment);

        cardAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agendamentoTime = new Intent(MainActivity.this, AgendamentoTime.class);
                startActivity(agendamentoTime);
            }
        });

    }
}