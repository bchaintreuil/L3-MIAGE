package com.unice.benchai.android_tp02;

import android.app.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

public class ChatActivity extends Activity implements Chat {
    private Préférences settings;

    private Button sendButton;
    private Ecouteur listeners;
    private EditText textBox;
    private Editable message;
    protected TextView chatHistory;
    protected ScrollView chat;
    private Switch connectionSwitch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // On load les préférences
        this.settings = new Préférences();

        // On setup la listener
        this.listeners = new Ecouteur(this, this.settings);

        // Gestion du bouton d'envoi
        this.sendButton = findViewById(R.id.envoyer);
        this.sendButton.setOnClickListener(this.listeners);

        // Gestion du du message input
        this.textBox = findViewById(R.id.message);
        this.message = textBox.getText();

        // Gestion de la textView
        this.chatHistory = findViewById(R.id.chat);

        // Gestion de la ScrollView
        this.chat = findViewById(R.id.scroll);

        // Gestion du switch
        this.connectionSwitch = findViewById(R.id.connexion);
        this.connectionSwitch.setOnCheckedChangeListener(this.listeners);

        // Associer l'écouter à envoyer.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.listesconnectes) {
            //this.listeners.getUsersList();
        }
        return true;
    }

    public String obtenirTextTapé() {
        String msg = this.message.toString();
        this.message.clear();
        return msg;
    }

    public void ajouterMessage(String msg) {
        runOnUiThread(() -> {
            chatHistory.append(msg);
            chat.fullScroll(View.FOCUS_DOWN);
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        listeners.disconnect();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.connectionSwitch.isChecked()) {
            listeners.connect();
        }
    }
}