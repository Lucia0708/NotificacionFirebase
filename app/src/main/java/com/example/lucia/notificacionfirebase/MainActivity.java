package com.example.lucia.notificacionfirebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lucia.notificacionfirebase.RestAPI.Endpoints;
import com.example.lucia.notificacionfirebase.RestAPI.adapter.RestApiAdapter;
import com.example.lucia.notificacionfirebase.RestAPI.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void enviarToken (View v){
    /*    Intent i = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
       Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.icons8_user_48)
                .setContentTitle("Notificacion")
                .setContentText("Hello World")
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                ;

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificacion.build());
        */

       String token =  FirebaseInstanceId.getInstance().getToken();
       enviarTokenRegistro(token);

       }

       private void enviarTokenRegistro(String token){

           Log.d("TOKEN", token);
           RestApiAdapter restApiAdapter = new RestApiAdapter();
           Endpoints endpoints = restApiAdapter.establecerConexionRestAPI();
           Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenID(token);

           usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
               @Override
               public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                   UsuarioResponse usuarioResponse = response.body();
                   Log.d("ID_FIREBASE", usuarioResponse.getId());
                   Log.d("TOKEN_FIREBASE", usuarioResponse.getToken());
               }

               @Override
               public void onFailure(Call<UsuarioResponse> call, Throwable t) {

               }
           });

       }
}
