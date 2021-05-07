package com.apps.yeltssin.examen.DescargaArchvo;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Download {



    public boolean Descarga(String url, String ruta) throws Exception {

        File file=new File(ruta);
        if(file.exists()) file.delete();

        FileOutputStream fos = null;
        InputStream in = null;

        URL _url = new URL(url);
        in = _url.openStream();
        fos = new FileOutputStream(new File(ruta));

        int length = -1;
        byte[] buffer = new byte[1024];// buffer for portion of data from connection
        while ((length = in.read(buffer)) > -1) {
            fos.write(buffer, 0, length);
        }

        if (fos != null)
            fos.close();
        if (in != null)
            in.close();
        return true;
    }


    public boolean DescargaQ(String url, Uri uri, Context ctx) throws Exception {

        File file=new File(uri.getPath());
        if(file.exists()) file.delete();
        // FileOutputStream fos=null;
        OutputStream outputStream = null;
        InputStream in = null;


        URL _url = new URL(url);
        in = _url.openStream();
        outputStream = ctx.getContentResolver().openOutputStream(uri);

        int length = -1;
        byte[] buffer = new byte[1024];// buffer for portion of data from connection
        while ((length = in.read(buffer)) > -1) {
            outputStream.write(buffer, 0, length);
        }
        if (outputStream != null)
            outputStream.close();
        if (in != null)
            in.close();

        return true;
    }
}
