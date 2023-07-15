package hafizcaniago.my.id.papb_final.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;

import hafizcaniago.my.id.papb_final.R;

public class ProfileActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    TextInputEditText dateEditText;
    Button showDateToSelect;

    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupBackButton();
        setupGenderAutoComplete();
        setupDateButton();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setupGenderAutoComplete() {
        String[] genderList = new String[]{"Male", "Female", "Prefer Not to Say"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.drop_down_item,
                genderList
        );

        autoCompleteTextView = findViewById(R.id.txtGender);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnTouchListener((view, motionEvent) -> {
            if (!autoCompleteTextView.isPopupShowing()) {
                autoCompleteTextView.showDropDown();
            }
            return false;
        });

        autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> Toast.makeText(getApplicationContext(), adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show());
    }

    public void setupDateButton() {
        dateEditText = findViewById(R.id.edtDateText);
        showDateToSelect = findViewById(R.id.btnShowDate);

        MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date Of Birth").setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build();

        showDateToSelect.setOnClickListener(view -> {
            datePicker.show(getSupportFragmentManager(), "Material_Date_Picker");
            datePicker.addOnPositiveButtonClickListener(selection -> dateEditText.setText(datePicker.getHeaderText()));
        });
    }

    private void setupBackButton()
    {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> super.onBackPressed());
    }
}