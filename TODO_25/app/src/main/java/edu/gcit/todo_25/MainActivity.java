package edu.gcit.todo_25;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    private EditText editText1, editText2, editText3, editText4;

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
    }

    public void addData(View view) {
        boolean isInserted = myDB.insertData(editText4.getText().toString(),
                editText1.getText().toString(),
                editText2.getText().toString(),
                editText3.getText().toString());

        if (isInserted == true){
            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
        }
    }


    public void viewData(View view) {
        Cursor res = myDB.getAllData();

        if (res.getCount() == 0){
            showMessage("Error", "Nothing found!");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Student Id : "+ res.getString(0)+ "\n");
            buffer.append("First Name : "+ res.getString(1)+ "\n");
            buffer.append("Last Name : "+ res.getString(2)+ "\n");
            buffer.append("ITW202 Marks : "+ res.getString(3)+ "\n \n");

        }
        showMessage("List of Students", buffer.toString());
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updataData(View view) {
        boolean isUpdate = myDB.updateData(editText4.getText().toString(),
                editText1.getText().toString(),
                editText2.getText().toString(),
                editText3.getText().toString());

        if (isUpdate == true){
            Toast.makeText(MainActivity.this, "Data Updated!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Data not Updated!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(View view) {
        Integer isDeleted = myDB.deleteData(editText4.getText().toString());

        if (isDeleted > 0){
            Toast.makeText(MainActivity.this, "Data Deleted!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Data not Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

}