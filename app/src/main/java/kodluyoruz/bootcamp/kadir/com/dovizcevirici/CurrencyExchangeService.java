package kodluyoruz.bootcamp.kadir.com.dovizcevirici;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by KadirDogan on 8/17/2017.
 */

public interface CurrencyExchangeService {
    @GET("latest")
    Call<CurrencyExchange> loadCurrencyExchange();
}
