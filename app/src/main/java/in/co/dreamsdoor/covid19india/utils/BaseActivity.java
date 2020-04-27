package in.co.dreamsdoor.covid19india.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import in.co.dreamsdoor.covid19india.AboutActivity;
import in.co.dreamsdoor.covid19india.MainActivity;
import in.co.dreamsdoor.covid19india.R;
import in.co.dreamsdoor.covid19india.pojo.Regional;

public class BaseActivity extends AppCompatActivity {
    static final String TAG = "BaseActivity";
    ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_options, menu);
        rootLayout = findViewById(R.id.LayoutRootId);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {

            case R.id.about_menu_id:
                Log.d(TAG, String.valueOf(R.string.menu_about_label));
                intent = new Intent(this, AboutActivity.class);
                this.startActivity(intent);
                break;

            case R.id.home_menu_id:
                intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                break;

            case R.id.refreshData_menu_id:
                requestCovidData();
                Snackbar.make(rootLayout,
                    "Refreshing Data...",
                    BaseTransientBottomBar.LENGTH_SHORT
                ).setAction("Action", null).show();

                break;

            case R.id.quit_menu_id:
                Log.d(TAG, String.valueOf(R.string.menu_quit_label));
                this.onBackPressed();
                break;

            default:
                return super.onOptionsItemSelected(item);

        }
        return super.

            onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }

    private void prepareRegionalData(List<Regional> regionals) {
    }

    private void requestCovidData() {
    }
}
