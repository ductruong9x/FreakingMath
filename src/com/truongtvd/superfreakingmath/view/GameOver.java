package com.truongtvd.superfreakingmath.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.truongtvd.superfreakingmath.R;
import com.truongtvd.superfreakingmath.config.BaseApplication;
import com.truongtvd.superfreakingmath.config.PrefStore;
import com.truongtvd.superfreakingmath.utils.UIUtils;

public class GameOver extends Activity {
    ImageView newGameBtn;
    private Typeface font_number;
    private TextView game_over,news,best;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        UIUtils.setOrientationLockPortrait(this);

        setContentView(R.layout.gameover);
        font_number=Typeface.createFromAsset(getAssets(),
				"fonts/number.ttf");

        int highScore = getIntent().getIntExtra("score", 0);

        TextView currentScoreTxt = (TextView) findViewById(R.id.curent_score);
        TextView bestScoreTxt  = (TextView) findViewById(R.id.best_score);
        game_over=(TextView)findViewById(R.id.game_over);
        news=(TextView)findViewById(R.id.new_s);
        best=(TextView)findViewById(R.id.best_s);
        game_over.setTypeface(font_number);
        news.setTypeface(font_number);
        best.setTypeface(font_number);
        
        currentScoreTxt.setTypeface(font_number);
        bestScoreTxt.setTypeface(font_number);
        newGameBtn = (ImageView) findViewById(R.id.play_btn);

        currentScoreTxt.setText(highScore + "");
        bestScoreTxt.setText(PrefStore.getMaxScore() + "");
        if (highScore == PrefStore.getMaxScore()) bestScoreTxt.setTextColor(Color.YELLOW);

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseApplication.soundWhenPlay();
                Intent intent = new Intent(GameOver.this, MyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        newGameBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    newGameBtn.setBackgroundResource(R.drawable.play_press);
                } else if (action == MotionEvent.ACTION_UP) {
                    newGameBtn.setBackgroundResource(R.drawable.play);
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}