package pikss86.game1.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import pikss86.game1.R;
import pikss86.game1.controller.ActionChessBoard;
import pikss86.game1.model.ChessBoardData;
import pikss86.game1.model.Logic;

public class MyActivity extends Activity {

    private ChessBoardData data;
    private ChessBoard cBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_banner_code_ad_listener);
        LinearLayout layout = findViewById(R.id.mainLayout);

        Logic logic = new Logic();
        logic.data.setRussianLang();
        logic.data.setUSERvsUSER();

        data = logic.data;
        cBoard = new ChessBoard(this, data);

        ActionChessBoard act = new ActionChessBoard(logic.data, logic, this);
        logic.data.addDataListener(act);
        cBoard.setOnTouchListener(act);

        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);

        int spaceColor = getResources().getColor(R.color.COLOR_SPACE);
        tv1.setBackgroundColor(spaceColor);
        tv2.setBackgroundColor(spaceColor);
        tv1.setLayoutParams( new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2));
        tv2.setLayoutParams( new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2));

        layout.addView(tv1);
        layout.addView(cBoard);
        layout.addView(tv2);
    }

    public void updateGui() {
        Log.d("GUI", "updateGui");
        cBoard.invalidate();
    }

    public void noBlackCkeckersLeft() {
        Log.d("T", "noBlackCkeckersLeft");
    }

    public void noWhiteCkeckersLeft() {
        Log.d("T", "noWhiteCkeckersLeft");
    }

    public void blackIsBlocked() {
        Log.d("T", "blackIsBlocked");
    }

    public void whiteIsBlocked() {
        Log.d("T", "whiteIsBlocked");
    }

    public void updateTextGuiLanguageInfo() {
        Log.d("T", "updateTextGuiLanguageInfo");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
