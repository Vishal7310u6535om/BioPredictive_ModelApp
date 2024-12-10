package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.core.view.WindowInsetsCompat;

import com.example.healthcare_app.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorsDetails extends AppCompatActivity {
    private String[][] doctor_details1={
            {"Doctar Name : Ajeet Singh","Hospital Address :Gorakhpur","Exp :5 Year","Mobile No : 2512475212","600"},
            {"Doctar Name : Paras Singh","Hospital Address :Lucknow","Exp :5 Year","Mobile No : 25124752","600"},
            {"Doctar Name : Vikas Singh","Hospital Address :Siddhath","Exp :5 Year","Mobile No : 25124712","600"},
            {"Doctar Name : Vinay Singh","Hospital Address :Banaras","Exp :5 Year","Mobile No : 25124712","600"}
    };
    private String[][] doctor_details2={
            {"Doctar Name : A Singh","Hospital Address :Gorakhpur","Exp :8 Year","Mobile No : 25124712","600"},
            {"Doctar Name : P Singh","Hospital Address :Lucknow","Exp :9 Year","Mobile No : 25124712","600"},
            {"Doctar Name : V Singh","Hospital Address :Siddhath","Exp :4 Year","Mobile No : 25175212","600"},
            {"Doctar Name : Vi Singh","Hospital Address :Banaras","Exp :5 Year","Mobile No : 22475212","600"},
    };
    private String[][] doctor_details3={
            {"Doctar Name : Aj Singh","Hospital Address :Gorakhpur","Exp :1 Year","Mobile No : 25175212","600"},
            {"Doctar Name : Par Singh","Hospital Address :Lucknow","Exp :2 Year","Mobile No : 25475212","600"},
            {"Doctar Name : Vik Singh","Hospital Address :Siddhath","Exp :6 Year","Mobile No : 12475212","600"},
            {"Doctar Name : Vin Singh","Hospital Address :Banaras","Exp :7 Year","Mobile No : 12475212","600"},
    };
    private String[][] doctor_details4={
            {"Doctar Name : Ajee Singh","Hospital Address :Gorakhpur","Exp :0 Year","Mobile No : 22475212","600"},
            {"Doctar Name : Para Singh","Hospital Address :Lucknow","Exp :3 Year","Mobile No : 22475212","600"},
            {"Doctar Name : Vika Singh","Hospital Address :Siddhath","Exp :7 Year","Mobile No : 512475212","600"},
            {"Doctar Name : Viny Singh","Hospital Address :Banaras","Exp :44 Year","Mobile No : 512475212","600"},
    };    private String[][] doctor_details5={
            {"Doctar Name : Aje Singh","Hospital Address :Gorakhpur","Exp :12 Year","Mobile No : 12475212","600"},
            {"Doctar Name : Par Singh","Hospital Address :Lucknow","Exp :14 Year","Mobile No : 22475212","600"},
            {"Doctar Name : Vik Singh","Hospital Address :Siddhath","Exp :58 Year","Mobile No : 512475212","600"},
            {"Doctar Name : Vin Singh","Hospital Address :Banaras","Exp :33 Year","Mobile No : 12475212","600"},
    };

   TextView tv;
   Button btn;
   String[][] doctor_details={};
   ArrayList list;
   HashMap<String,String> item;
   SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctors_details);
        tv=findViewById(R.id.textViewDDTitle);
        Intent it=getIntent();
        String title=it.getStringExtra("title");
        tv.setText(title);
        if(title.compareTo("Family Physicians")==0)
            doctor_details=doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else

        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
        doctor_details=doctor_details5;
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
            list =new ArrayList();
            for(int i=0;i<doctor_details.length;i++){
                item=new HashMap<String,String>();
                item.put("line1",doctor_details[i][0]);
                item.put("line2",doctor_details[i][1]);
                item.put("line3",doctor_details[i][2]);
                item.put("line4",doctor_details[i][3]);
                item.put("line5","Cons Fees:"+doctor_details[i][4]+"/-");
                list.add(item);
            }
            sa=new SimpleAdapter(this,list,
                    R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e,}
                    );
        ListView lst=findViewById(R.id.listViewDD);
        lst.setAdapter(sa);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(DoctorsDetails.this, BookAppointmentActivity.class);
                for (int i = 0; i < doctor_details.length; i++) {
                    it.putExtra("text1", title);
                    it.putExtra("text2", doctor_details[i][0]);
                    it.putExtra("text3", doctor_details[i][1]);
                    it.putExtra("text4", doctor_details[i][3]);
                    it.putExtra("text5", doctor_details[i][4]);
                    startActivity(it);
                }
            }
        });
    }
}