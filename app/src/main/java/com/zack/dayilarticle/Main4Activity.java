package com.zack.dayilarticle;

import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    private CardView cd3,cd4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        cd3 = (CardView) findViewById(R.id.help3);
        cd4 = (CardView) findViewById(R.id.help4);
        cd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText("958059970@qq.com");
                Toast.makeText(Main4Activity.this,"邮箱已复制到剪贴板！",Toast.LENGTH_SHORT).show();
            }
        });
        cd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText("15680487267");
                Toast.makeText(Main4Activity.this,"账号已复制到剪贴板！",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
