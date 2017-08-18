package kodluyoruz.bootcamp.kadir.com.dovizcevirici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Main3Activity extends AppCompatActivity {

    private TextView tvTitle, tvSubTitle, tvOutputName, tvOutputRate,  cTitle;
    private EditText etInput;
    private Button btnCalculate;
    private String currencyBase;
    private String currencyName;
    private double currencyRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSubTitle = (TextView) findViewById(R.id.tvSubTitle);
        tvOutputName = (TextView) findViewById(R.id.tvOutputName);
        tvOutputRate = (TextView) findViewById(R.id.tvOutputRate);
        cTitle = (TextView) findViewById(R.id.cTitle);

        etInput = (EditText) findViewById(R.id.etInput);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        Intent intent = getIntent();
        currencyBase = intent.getStringExtra(Main2Activity.BASE_C);
        currencyName = intent.getStringExtra("currency_name");
        currencyRate = intent.getDoubleExtra("currency_rate", 0);
        tvTitle.setText(currencyBase.toUpperCase() + " -> " + currencyName.toUpperCase());
        tvSubTitle.setText("1 : " + currencyRate);
        cTitle.setText(currencyBase.toUpperCase()+ ": ");
        tvOutputName.setText(currencyName.toUpperCase() + ": ");

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etInput.getText().toString().length() == 0){
                    return;
                }

                double input;
                try{
                    input = Double.parseDouble(etInput.getText().toString());
                }
                catch (NumberFormatException e){
                    etInput.setText("");
                    return;
                }

                double output = input * currencyRate;
                DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
                tvOutputRate.setText(decimalFormat.format(output));
            }
        });
    }
}
