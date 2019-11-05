package piuwcreative.myunittesting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MainViewModel mainViewModel;

    private EditText edtWidth, edtHeight, edtLength;
    private TextView tvResult;
    private Button btnCalculateVolume, btnCalculateSurfaceArea, btnCalculateCircumference, btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new MainViewModel(new CuboidModel());

        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        edtWidth = findViewById(R.id.edt_width);
        tvResult = findViewById(R.id.tv_result);
        btnCalculateVolume = findViewById(R.id.btn_calculate_volume);
        btnCalculateCircumference = findViewById(R.id.btn_calculate_circumference);
        btnCalculateSurfaceArea = findViewById(R.id.btn_calculate_surface_area);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);
        btnCalculateSurfaceArea.setOnClickListener(this);
        btnCalculateVolume.setOnClickListener(this);
        btnCalculateCircumference.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String length = edtLength.getText().toString().trim();
        String width = edtWidth.getText().toString().trim();
        String height = edtHeight.getText().toString().trim();

        if (TextUtils.isEmpty(length)) {
            edtLength.setError("Field tidak boleh kosong");
        } else if (TextUtils.isEmpty(width)) {
            edtWidth.setError("Field tidak boleh kosong");
        } else if (TextUtils.isEmpty(height)) {
            edtHeight.setError("Field tidak boleh kosong");
        }else {
            double l = Double.parseDouble(length);
            double w = Double.parseDouble(width);
            double h = Double.parseDouble(height);

            if (view.getId() == R.id.btn_save) {
                mainViewModel.save(l,w,h);
                visible();
            } else if (view.getId() == R.id.btn_calculate_circumference) {
                tvResult.setText(String.valueOf(mainViewModel.getCircumference()));
                gone();
            } else if (view.getId() == R.id.btn_calculate_surface_area) {
                tvResult.setText(String.valueOf(mainViewModel.getSurfaceArea()));
                gone();
            } else if (view.getId() == R.id.btn_calculate_volume) {
                tvResult.setText(String.valueOf(mainViewModel.getVolume()));
                gone();
            }
        }


    }

    private void gone() {
        btnCalculateVolume.setVisibility(View.GONE);
        btnCalculateCircumference.setVisibility(View.GONE);
        btnCalculateSurfaceArea.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
    }

    private void visible() {
        btnSave.setVisibility(View.GONE);
        btnCalculateSurfaceArea.setVisibility(View.VISIBLE);
        btnCalculateCircumference.setVisibility(View.VISIBLE);
        btnCalculateVolume.setVisibility(View.VISIBLE);
    }
}
