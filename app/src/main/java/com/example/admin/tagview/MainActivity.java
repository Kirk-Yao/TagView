package com.example.admin.tagview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TagView tagView;

    private OnDeleteClickListener deleteListener = new OnDeleteClickListener() {
        @Override
        public void onDeleteClick() {
            Toast.makeText(MainActivity.this,"删除啦",Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tagView = (TagView) findViewById(R.id.tagView);
        tagView.setOnDeleteClickListener(deleteListener);
    }
}
