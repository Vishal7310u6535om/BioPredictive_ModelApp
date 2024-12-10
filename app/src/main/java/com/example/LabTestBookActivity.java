package com.example;

import android.content.Intent;
import android.net.Uri; // Import Uri
import android.os.Bundle;
import android.view.View;
import android.widget.Button; // Import Button
import android.widget.EditText; // Import EditText
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthcare_app.R;

public class LabTestBookActivity extends AppCompatActivity {
    Button btnLTBook,btnBack;
    EditText userEmail; // Corrected to EditText

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test_book);

        userEmail = findViewById(R.id.editTextLTBuserEmail); // Ensure this ID matches your layout
        btnLTBook = findViewById(R.id.buttonLTBBooking);
        btnBack = findViewById(R.id.buttonBack);
        btnLTBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the actual user's email from the EditText
                String email = userEmail.getText().toString(); // Get user input from EditText

                // Send email
                sendEmail(email);

                // Start the next activity
                startActivity(new Intent(LabTestBookActivity.this, BookAppointmentActivity.class));
                Toast.makeText(getApplicationContext(), "Your booking is done successfully", Toast.LENGTH_SHORT).show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestBookActivity.this, BookAppointmentActivity.class));
                finish(); // Optionally finish this activity
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void sendEmail(String userEmail) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:")); // Only email apps should handle this
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{userEmail});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Booking Confirmation");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your booking is done successfully.");

        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        } else {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/"));
            startActivity(webIntent);
            Toast.makeText(this, "No email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
