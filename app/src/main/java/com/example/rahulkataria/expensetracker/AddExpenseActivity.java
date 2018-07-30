package com.example.rahulkataria.expensetracker;

import android.app.DatePickerDialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddExpenseActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final EditText place = (EditText) findViewById(R.id.placeText);
        final EditText amount = (EditText) findViewById(R.id.amountText);
        final EditText phoneNumber = (EditText) findViewById(R.id.phoneText);
        final EditText dateText = (EditText) findViewById(R.id.dateText);
        final Button addExpense = (Button) findViewById(R.id.add_button);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.typeArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR,year);
                myCalendar.set(Calendar.MONTH,monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
                String myFormat = "MM/dd/YY";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateText.setText(sdf.format(myCalendar.getTime()));
            }
        };

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddExpenseActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String place_text = place.getText().toString();
                double amt = Double.parseDouble(amount.getText().toString());
                String phone_text = phoneNumber.getText().toString();
                String type_text = spinner.getSelectedItem().toString();
                String date_text = dateText.getText().toString();

                Expense expense = new Expense(place_text,phone_text,amt,type_text,date_text);

                mAuth = FirebaseAuth.getInstance();

                mDatabase = FirebaseDatabase.getInstance().getReference();

                mDatabase.child("users").child(mAuth.getUid()).child("expenses").push().setValue(expense);

            }
        });

    }

}
