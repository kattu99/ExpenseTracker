package com.example.rahulkataria.expensetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ManageExpenses extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expenses);

        final Button addExpense = (Button) findViewById(R.id.add_expense);
        final Button scanReceipt = (Button) findViewById(R.id.scan_receipt);


        final ArrayList<Expense> expenseList = new ArrayList<>();
        final ExpenseAdapter adapter = new ExpenseAdapter(this,expenseList);
        final ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManageExpenses.this, AddExpenseActivity.class));
            }
        });

        scanReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManageExpenses.this,ScanReceipt.class));
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot expenseSnapshot = dataSnapshot.child("users").child(mAuth.getUid()).child("expenses");
                Iterable<DataSnapshot>  expenseChildren = expenseSnapshot.getChildren();
                for (DataSnapshot expenseShot:expenseChildren) {
                    String place = expenseShot.child("location").getValue(String.class);
                    Double amount = expenseShot.child("amount").getValue(Double.class);
                    String date = expenseShot.child("date").getValue(String.class);
                    String phonenumber = expenseShot.child("phone_number").getValue(String.class);
                    String type = expenseShot.child("type").getValue(String.class);
                    Expense expense = new Expense(place,phonenumber,amount,type,date);
                    expenseList.add(expense);
                    Log.d(TAG,"Added expense");
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ManageExpenses.this, "Database error. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
