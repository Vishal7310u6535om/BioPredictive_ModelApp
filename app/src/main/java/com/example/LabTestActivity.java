package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthcare_app.R;

import java.util.HashMap;
import java.util.ArrayList;

public class LabTestActivity extends AppCompatActivity {
    private String[][] packages={
            {"Hospitals :Visit hospitals site","","","",""},
    };
    private String[] package_details={
            "Blood Glucouse Fasting\n"+"ChatGpt\n"
    };
    HashMap<String,String>item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart,btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);
        btnGoToCart=findViewById(R.id.buttonLTGoToCart);
        btnBack=findViewById(R.id.buttonLTBack);
        listView=findViewById(R.id.listViewLT);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // startActivity(new Intent(LabTestActivity.this,HomeActivity.class));
            }
        });
        list=new ArrayList();
        for (int i = 0; i <packages.length; i++) {
            item=new HashMap<String,String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][3]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e,});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                for (int i = 0; i < package_details.length; i++) {
                    it.putExtra("text1", packages[i][0]);
                    it.putExtra("text2", package_details[i]);
                    it.putExtra("text3", packages[i][4]);
                    startActivity(it);
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}