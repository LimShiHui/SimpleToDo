package sg.edu.rp.c346.id20032316.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddRemove;
    EditText etElement;
    Button btnAdd;
    Button btnDelete;
    Button btnClear;
    ListView lvTodo;
    ArrayList<String> alTodo;
    ArrayAdapter <String> aaTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddRemove = findViewById(R.id.spinner);
        etElement = findViewById(R.id.editTextTasks);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnDelete = findViewById(R.id.buttonDeleteItem);
        btnClear = findViewById(R.id.buttonClearItem);
        lvTodo = findViewById(R.id.listViewTasks);

        alTodo = new ArrayList<>();
        aaTodo = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, alTodo);
        lvTodo.setAdapter(aaTodo);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("spinner clicked", String.valueOf(position));
                if (position == 0) {
                    etElement.setHint("Type in a new task here");
                    btnAdd.setEnabled(true);
                    btnDelete.setEnabled(false);
                } else if (position == 1) {
                    etElement.setHint("Type in the index of the task to be removed");
                    btnAdd.setEnabled(false);
                    btnDelete.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = etElement.getText().toString();
                alTodo.add(task);
                aaTodo.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTodo.size() < 1) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else {
                    int pos = Integer.parseInt(etElement.getText().toString());
                    if (pos > alTodo.size()-1) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    } else {
                        alTodo.remove(pos);
                        aaTodo.notifyDataSetChanged();
                    }

                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTodo.clear();
                aaTodo.notifyDataSetChanged();
            }
        });
    }
}