package com.anthonytepach.coprisjal;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.anthonytepach.coprisjal.data.RetrofitClient;
import com.anthonytepach.coprisjal.data.interfaz.ComputerFormsAPI;
import com.anthonytepach.coprisjal.data.interfaz.CoprisjalAPI;
import com.anthonytepach.coprisjal.data.model.Alfanum;
import com.anthonytepach.coprisjal.data.model.DataCoprisjal;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private TextView textView_folio;
    private TextView textView_alfanum;
    private Button btn_validar;
    private Retrofit retrofit;
    private ImageView imageView;
    private EditText etAlfanum;
    private String folioOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textView_folio = (TextView)findViewById(R.id.tv_folio);

        imageView = (ImageView)findViewById(R.id.imageView5);
        int fol=Integer.parseInt(getIntent().getStringExtra("folio"));
        String a= String.format("%04d",fol);
        etAlfanum = findViewById(R.id.et_alfanum);
        btn_validar = findViewById(R.id.btn_validar);
        folioOK=a;

        btn_validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etAlfanum.getText().toString().isEmpty()){
                    if (etAlfanum.getText().toString().length()==4){
                        getAlfanum(folioOK,etAlfanum.getText().toString());
                    }else{
                        Toast.makeText(MainActivity.this, "El alfanúmerico debe tener una longitud de 4 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


    void getAlfanum(String folio,String alfanum){
        String api_url="https://intelligentforms.mx/App/api/v1/";
        retrofit = RetrofitClient.getClient(api_url);

        CoprisjalAPI coprisjalAPI = retrofit.create(CoprisjalAPI.class);
        Call<DataCoprisjal> call= coprisjalAPI.getDatos(folio,alfanum);
        call.enqueue(new Callback<DataCoprisjal>() {
            @Override
            public void onResponse(Call<DataCoprisjal> call, Response<DataCoprisjal> response) {
                //
                if (response.isSuccessful()){
                    if (response.code()==200){
                        DataCoprisjal dataCoprisjal= response.body();
                        String alfa = dataCoprisjal.getAlfanum();
                        if (!alfa.equals(alfanum)){
                            Toast.makeText(MainActivity.this, "Debes respetar las mayúsculas", Toast.LENGTH_SHORT).show();
                        }else{
                            loadImageGlide(Uri.parse("https://intelligentforms.mx/coprisjal/"+alfa+".png"));
                           // Toast.makeText(MainActivity.this, "Descargando.....", Toast.LENGTH_SHORT).show();
                        }
                    }

                }else{
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onFailure(Call<DataCoprisjal> call, Throwable t) {
                //Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "df<b", Toast.LENGTH_SHORT).show();
                loadImageGlide(Uri.parse("https://cdn.icon-icons.com/icons2/1380/PNG/512/vcsconflicting_93497.png"));
            }
        });


    }

    private void loadImageGlide(Uri photoUrl){
        RequestOptions options = new RequestOptions()
                .autoClone()
                .placeholder(R.drawable.ic_previa)
                .error(R.drawable.ic_no_found)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(this).load(photoUrl)
                .apply(options)
                .into(imageView);

    }

}