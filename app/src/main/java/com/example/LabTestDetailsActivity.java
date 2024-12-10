package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthcare_app.R;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestDetailsActivity extends AppCompatActivity {
    private String[][] HOSPITAL_URL = {
            {"Hospital 1: AIIMS Hospital", "https://www.aiims.edu/", "", "", ""},
            {"Hospital 2: LifeCare Hospital", "https://www.lifecarehospitalindia.com/", "", "", ""},
            {"Hospital 3: Park Hospital", "https://www.parkhospitalgorakhpur.com/", "", "", ""},
            {"Hospital 4: Rachit Healthcare", "https://rachithealthcare.com/", "", "", ""},
            {"ChatGpt: ChatGpt", "https://chatgpt.com/", "", "", ""},
    };

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);  // Ensure this matches the XML file name

        btnGoToCart = findViewById(R.id.buttonLTGoToCart); // Fixed the button ID to match XML
        btnBack = findViewById(R.id.buttonLTBack);
        listView = findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Just close the current activity to go back
            }
        });

        list = new ArrayList<>();
        for (String[] hospital : HOSPITAL_URL) {
            item = new HashMap<>();
            item.put("line1", hospital[0]);
            item.put("line2", hospital[1]);
            item.put("line3", hospital[2]);
            item.put("line4", hospital[3]);
            item.put("line5", hospital[4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = HOSPITAL_URL[position][1]; // Get the URL for the clicked item
                openHospitalWebsite(url);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void openHospitalWebsite(String url) {
        if (!url.isEmpty()) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } else {
            // Optionally show a message that the URL is not available
        }
    }
}
