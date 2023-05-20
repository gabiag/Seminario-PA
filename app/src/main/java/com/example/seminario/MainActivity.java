package com.example.seminario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        CardView cardAllDay = findViewById(R.id.allDay);
        CardView cardAppointment = findViewById(R.id.appointment);

        //Alterando a cor da status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusbar));
        }

        cardAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cardview_scale);
                cardAppointment.startAnimation(scaleAnimation);

                Intent agendamentoTime = new Intent(MainActivity.this, AgendamentoTime.class);
                startActivity(agendamentoTime);
            }
        });

        cardAllDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Animation scaleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cardview_scale);
              cardAllDay.startAnimation(scaleAnimation);

                Intent agendamentoDate = new Intent(MainActivity.this, AgendamentoDate.class);
                startActivity(agendamentoDate);
            }
        });
    }
}