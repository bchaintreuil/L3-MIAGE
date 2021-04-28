package com.unice.benchai.android_tp02;

import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import io.socket.*;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import org.json.*;

import java.net.URISyntaxException;

public class Ecouteur implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, Emitter.Listener {
    private Chat chat;
    private Socket s;
    private Préférences chatSettings;

    public Ecouteur(Chat chat, Préférences settings) {
        this.chat = chat;
        this.chatSettings = settings;

        this.chatSettings.setUserName("Benjamin");

        // Initialisation de la socket
        s = IO.socket(settings.getServerURI());
        Log.i("TP2", s.toString());
        s.on("chatevent", this);
    }

    @Override
    public void onClick(View v) {
        String msgBuffer = chat.obtenirTextTapé();

        if (s != null) {
            JSONObject obj = new JSONObject();
            try {
                obj.put("userName", this.chatSettings.getUserName());
                obj.put("message", msgBuffer);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            s.emit("chatevent", obj);
        }
    }

    public void disconnect() {
        if (s != null) {
            s.disconnect();
        }
    }

    public void connect() {
        if (s != null) {
            s.connect();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            this.connect();
        } else {
            this.disconnect();
        }
    }

    @Override
    public void call(Object... args) {
        JSONObject data = (JSONObject) args[0];
        try {
            String userName = data.getString("userName");
            String message = data.getString("message");

            this.chat.ajouterMessage(userName + " > " + message + "\n");
        } catch (JSONException e) {
            Log.e("ChatActivity Error", "JSONException");
        }
    }
}
