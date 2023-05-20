package com.example.seminario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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
                int colorFrom = getResources().getColor(R.color.start_color);
                int colorTo = getResources().getColor(R.color.end_color);
                Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cardview_scale);
                cardAppointment.startAnimation(scaleAnimation);

                Intent agendamentoTime = new Intent(MainActivity.this, AgendamentoTime.class);
                startActivity(agendamentoTime);
            }
        });
    }
}