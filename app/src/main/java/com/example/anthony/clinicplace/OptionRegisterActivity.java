package com.example.anthony.clinicplace;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.bumptech.glide.util.Util;

import java.io.File;
import java.net.URISyntaxException;

import de.hdodenhof.circleimageview.CircleImageView;

public class OptionRegisterActivity extends AppCompatActivity {
    private static final int PHOTO_SELECTED = 1;
    private Button guardar;
    private CircleImageView profile_picture;
    private Uri imagen;
    private TextView elegir;
    private EditText nombres;
    private EditText apellidos;
    private EditText dni;
    private EditText ubicacion;
    private TransferUtility transferUtility;

    private String BUCKET_NAME = "clinicplace-deployments-mobilehub-2110396219";
    String correo;
    String usuario;
    String path;

    private void beginUpload(String filePath) {
        if (filePath == null) {
            Toast.makeText(this, "Could not find the filepath of the selected file",
                    Toast.LENGTH_LONG).show();
            return;
        }
        File file = new File(filePath);
        TransferObserver observer = transferUtility.upload(MapperHelper.BUCKET_NAME, file.getName(),
                file);
    }

    private boolean isEmpty(EditText editText)
    {
        if (editText.getText().toString().isEmpty())
            return true;
            else
                return false;
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PHOTO_SELECTED);
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK && requestCode == PHOTO_SELECTED){
            imagen = data.getData();
            profile_picture.setImageURI(imagen);
            try {
                String path = getPath(imagen);
                beginUpload(path);
            } catch (URISyntaxException e) {
                Toast.makeText(this,
                        "Unable to get the file from the given URI.  See error log for details",
                        Toast.LENGTH_LONG).show();
                Log.e( "Unable to upload file from the given uri", String.valueOf(e));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_register);
        //Llamada bbdd

        final MapperHelper helper = new MapperHelper(getApplicationContext());
        //llamada s3
        transferUtility = helper.getTransferUtility(this);
        //Inicializo variables
        profile_picture=(CircleImageView)(findViewById(R.id.profile_picture));
        nombres= (EditText)findViewById(R.id.nombre);
        apellidos=(EditText)findViewById(R.id.apellidos);
        dni=(EditText)findViewById(R.id.dni);
        ubicacion=(EditText)findViewById(R.id.ubicacion);
        //Cambiar foto
        elegir= (TextView)(findViewById(R.id.elegirFoto));
        elegir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openGallery();
            };


        });
        //Datos anteriores
        correo =(getIntent().getExtras().getString("correo"));
        usuario=(getIntent().getExtras().getString("usuario"));
        guardar = (Button) findViewById(R.id.save);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(nombres)) nombres.setError("Ingrese su nombre");
                else if (isEmpty(apellidos)) nombres.setError("Ingrese sus apellidos");
                else if (isEmpty(dni)) nombres.setError("Ingrese su dni");
                else if (isEmpty(ubicacion)) nombres.setError("Ingrese su distrito");
                else    {



                    Usuario objUsuario = helper.mapper.load(Usuario.class, "1",correo);
                    objUsuario.setNombre(nombres.getText().toString());
                    objUsuario.setApellido(nombres.getText().toString());
                    objUsuario.setDni(dni.getText().toString());
                    objUsuario.setDistrito(ubicacion.getText().toString());
                    helper.mapper.save(objUsuario);
                Intent profileIntent = new Intent(OptionRegisterActivity.this, AdminProfileActivity.class);
                profileIntent.putExtra("username",usuario);
                profileIntent.setData(imagen);
                startActivity(profileIntent);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {

    }

    @SuppressLint("NewApi")
    private String getPath(Uri uri) throws URISyntaxException {
        final boolean needToCheckUri = Build.VERSION.SDK_INT >= 19;
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[] {
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

}
