package antunovic.tvz.hr;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.resultField) TextView result;
    @BindView(R.id.heightText) EditText enteredHeight;
    @BindView(R.id.weightText) EditText enteredWeight;
    @BindView(R.id.button) Button calculate;
    @BindView(R.id.spinner) Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        String spinnerArray[] = getResources().getStringArray(R.array.background_colors);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,spinnerArray);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                View root = parentView.getRootView();

                switch(position){
                    case 0:
                        root.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                        calculate.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                        break;
                    case 1:
                        root.setBackgroundColor(getResources().getColor(R.color.colorGrey));
                        calculate.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                        break;
                    case 2:
                        root.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                        calculate.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                View root = parentView.getRootView();

                root.setBackgroundColor(getResources().getColor(R.color.colorWhite));
            }

        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Double height = Double.parseDouble(enteredHeight.getText().toString());
                Double weight = Double.parseDouble(enteredWeight.getText().toString());
                String calculated = String.format("%.2f", weight/Math.pow((height/100),2));
                result.setText(getString(R.string.result_text) + String.valueOf(calculated));
            }
        });
    }
}
