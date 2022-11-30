package com.example.practiceforandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.practiceforandroid.database.AppDatabase;
import com.example.practiceforandroid.entidades.CuentaEnt;
import com.example.practiceforandroid.entidades.Image;
import com.example.practiceforandroid.entidades.ImageResponse;
import com.example.practiceforandroid.entidades.Movimientos;
import com.example.practiceforandroid.entidades.PokemonEnt;
import com.example.practiceforandroid.servicios.ImageService;
import com.example.practiceforandroid.servicios.PokeService;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarMovimiento extends AppCompatActivity {

    Spinner spTipo;
    Button galeria,registrar,ubicacion;
    EditText monto,motivo,url,latitud,longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_movimiento);

        CuentaEnt cuentaux=new CuentaEnt();
        cuentaux.id=getIntent().getIntExtra("id",0);
        cuentaux.name=getIntent().getStringExtra("name");
        cuentaux.saldo=getIntent().getIntExtra("saldo",0);

        spTipo = findViewById(R.id.spTipo2);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_movimiento, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        spTipo.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        galeria=findViewById(R.id.btnOpenGallery2);
        registrar=findViewById(R.id.btnOpenGallery3);
        ubicacion=findViewById(R.id.btnOpenGallery4);
        monto=findViewById(R.id.etNombre4);
        motivo=findViewById(R.id.etNombre3);
        url=findViewById(R.id.etimgURL2);
        latitud=findViewById(R.id.etNombre2);
        longitud=findViewById(R.id.etNombre5);

        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(spTipo.getSelectedItem().toString().equals("Ingreso")){
                    cuentaux.saldo+=Integer.parseInt(monto.getText().toString());
                }else{
                    cuentaux.saldo-=Integer.parseInt(monto.getText().toString());
                }

                AppDatabase db = AppDatabase.getInstance(RegistrarMovimiento.this);
                Movimientos movi=new Movimientos();
                movi.id= cuentaux.id;
                movi.tipo=spTipo.getSelectedItem().toString();
                movi.motivo=motivo.getText().toString();
                movi.latitud=latitud.getText().toString();
                movi.longitud=longitud.getText().toString();
                movi.url=url.getText().toString();
                db.userDao().create(movi);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://635a26bcff3d7bddb9b03cc6.mockapi.io/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                PokeService service = retrofit.create(PokeService.class);
                service.update(cuentaux,cuentaux.id).enqueue(new Callback<PokemonEnt>() {
                    @Override
                    public void onResponse(Call<PokemonEnt> call, Response<PokemonEnt> response) {
                        Log.i("MAIN_APP", "RESPONSE" + response.code());
                    }

                    @Override
                    public void onFailure(Call<PokemonEnt> call, Throwable t) {}
                });
                Intent intent = new Intent(RegistrarMovimiento.this, ListarPokemones.class);
                startActivity(intent);
            }
        });
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    openGallery();
                }
                else{
                    requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 101 );
                }

            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1001);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1000 && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //ivPokemonImg.setImageBitmap(imageBitmap);
        }

        if(requestCode==1001){
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(data.getData(),filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap imageBitmap = BitmapFactory.decodeFile(picturePath);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            String imgBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.imgur.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ImageService imageService = retrofit.create(ImageService.class);
            Image image = new Image();
            image.image = imgBase64;

            imageService.sendImage(image).enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    String imR = response.body().data.link;
                    url.setText(imR);
                }

                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {

                }
            });
        }
    }
}