package com.apps.yeltssin.examen.PantallaPrincipal;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.apps.yeltssin.examen.ApiSimple;
import com.apps.yeltssin.examen.DescargaArchvo.Download;
import com.apps.yeltssin.examen.Preferences;
import com.apps.yeltssin.examen.Response;
import com.apps.yeltssin.examen.response.data;
import com.apps.yeltssin.examen.response.root;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;

public class MVPPantallaPrincipalModel implements MVPPantallaPrincipal.model {
    final String TAG = MVPPantallaPrincipalModel.class.getSimpleName();

    MVPPantallaPrincipalPresenter presenter;
    Context context;
    String name_file_zip = "employees_data.json.zip";
    String name_file = "employees_data.json";
    Download download;


    Preferences preferences;
    private final String NAME_SHARED="USERS";
    private final String NAME_KEY_USER="USER";


    public MVPPantallaPrincipalModel(MVPPantallaPrincipalPresenter presenter,
                                     Context context) {
        this.presenter = presenter;
        this.context = context;
        download = new Download();
        preferences=new Preferences(context,NAME_SHARED);
    }

    @Override
    public void getResponse() {
        ApiSimple.Create().getResponse().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Response dataresponse = response.body();
                    Log.d(TAG, "onResponse: " + dataresponse.getData().getFile());
                    if (dataresponse.getSuccess()) {
                        presenter.SuccesResponse(response.body().getData().getFile());
                    } else presenter.ErrorResponse("Error code");
                } else
                    presenter.ErrorResponse("Error desconocido");
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                presenter.ErrorResponse(t.getMessage());
            }
        });
    }

    @Override
    public Single<Boolean> Download(String url) {

        return Single.create(emitter -> {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    emitter.onSuccess(download.DescargaQ(url, getUri(), context));
                else
                    emitter.onSuccess(download.Descarga(url, getRuta()+"/"+name_file_zip));
            } catch (Exception e) {
                emitter.tryOnError(e);
            }
        });

    }

    @Override
    public Single<Boolean> Descomprime(String path) {
        return Single.create(emitter -> {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    emitter.onSuccess(descomprime(null, getUri()));
                else
                    emitter.onSuccess(descomprime(getRuta(), null));
            } catch (Exception e) {
                emitter.tryOnError(e);
            }
        });
    }

    @Override
    public void procesaArchivo()  {
        try {
            FileInputStream inputStream=new FileInputStream(getRuta()+"/"+name_file);
            String linea="";
            InputStreamReader reader=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(reader);
            linea=bufferedReader.readLine();
            String json="";
            while (linea != null){
                json=json+linea;
                linea=bufferedReader.readLine();
            }

           inputStream.close();
           reader.close();
           bufferedReader.close();
           SaveShared(json);
           presenter.Succesempleado("Datos guardados");

        }catch (Exception e){
            presenter.Errorempleado(e.getMessage());
        }
    }


    private boolean descomprime(String path, Uri uri) throws Exception {
        //String _nombreArchivo = "";
                File file;
        ZipInputStream zis;
        if (uri != null) {
            file=new File(getUri().getPath()+"/"+name_file);
            if(file.exists()) file.delete();
            zis = new ZipInputStream(new FileInputStream(uri.getPath()));
        } else {
            file=new File(getRuta()+"/"+name_file);
            if(file.exists()) file.delete();
            //crea un buffer temporal para el archivo que se va descomprimir
            zis = new ZipInputStream(new FileInputStream(path+"/"+name_file_zip));
        }


        ZipEntry salida;
        salida = zis.getNextEntry();
        ZipEntry salida2 = new ZipEntry(salida.getName());
        //recorre tod o el buffer extrayendo uno a uno cada archivo.zip y
        // creándolos de nuevo en su archivo original
        while (salida2 != null) {
            //_nombreArchivo = salida2.getName();
            FileOutputStream fos = new FileOutputStream(getRuta() + "/" + name_file);
            int leer;
            byte[] buffer = new byte[4096];
            while (0 < (leer = zis.read(buffer))) {
                fos.write(buffer, 0, leer);
            }
            fos.close();
            zis.closeEntry();
            salida2 = zis.getNextEntry();
        }

        return true;
    }

    private Uri getUri() {
        ContentResolver resolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name_file_zip);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "application/zip");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH,
                Environment.DIRECTORY_DOWNLOADS);
        return resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

    }

    private String getRuta() {
        return Environment.getExternalStorageDirectory()
                .getAbsolutePath();
    }

    private void SaveShared(String jsonUsers){
        preferences.setString(NAME_KEY_USER,jsonUsers);
    }

    private String getJsonUsers(){
        return preferences.getString(NAME_KEY_USER,"");
    }

    private data getData(){
        Gson gson= new Gson();
        root data =gson.fromJson(getJsonUsers(), root.class);
        return data.getData();
    }
}
