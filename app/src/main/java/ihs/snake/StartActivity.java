package ihs.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity implements View.OnClickListener
{
    private Button nextAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        nextAct = (Button) findViewById(R.id.playGameButton);
        nextAct.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(nextAct == v){
            startActivity(new Intent(this, MultiplayerGame.class));
        }
    }
}
