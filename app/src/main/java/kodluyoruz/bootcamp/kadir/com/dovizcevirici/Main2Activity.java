package kodluyoruz.bootcamp.kadir.com.dovizcevirici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity implements Callback<Currency2Exchange>, CurrencyItemClickListener {
    public static final String BASE_C = "kodluyoruz.bootcamp.kadir.com.dovizcevirici.message";
    private ListView lvCurrency;
    private String currencyBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lvCurrency = (ListView) findViewById(R.id.lvCurrency);
        Intent intent = getIntent();
        currencyBase = intent.getStringExtra(MainActivity.BASE_NAME);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadCurrencyExchangeData();
    }

    private void loadCurrencyExchangeData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Currency2ExchangeService service = retrofit.create(Currency2ExchangeService.class);
        Call<Currency2Exchange> call = service.loadCurrencyExchange(currencyBase);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Currency2Exchange> call, Response<Currency2Exchange> response) {
        //Toast.makeText(this, response.body().getBase(), Toast.LENGTH_LONG).show();
        Currency2Exchange currencyExchange = response.body();
        lvCurrency.setAdapter(new Currency2Adapter(this, currencyExchange.getCurrencyList(), this));
    }

    @Override
    public void onFailure(Call<Currency2Exchange> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCurrencyItemClick(Currency c) {
        //Toast.makeText(this, c.getName(), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Main3Activity.class);
        intent.putExtra(BASE_C, currencyBase.toUpperCase());
        intent.putExtra("currency_name", c.getName());
        intent.putExtra("currency_rate", c.getRate());

        startActivity(intent);
    }
}
