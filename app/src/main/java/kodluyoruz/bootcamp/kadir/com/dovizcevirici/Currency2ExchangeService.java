package kodluyoruz.bootcamp.kadir.com.dovizcevirici;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by KadirDogan on 8/16/2017.
 */

public interface Currency2ExchangeService {
    @GET("latest")
    Call<Currency2Exchange> loadCurrencyExchange(@Query("base") String base);
}
