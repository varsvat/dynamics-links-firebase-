package com.example.dynamiclinkinffirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;

public class MainActivity extends AppCompatActivity {
    private Button button;
    String variable = "varun jain " , url;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = editText.getText().toString();
                onShareClicked();
            }
        });

        editText = findViewById(R.id.edittext);

    }

    public static Uri generateContentLink() {
        Uri baseUrl = Uri.parse("https://dynamiclinkinffirebase.page.link");
        String domain = "dynamiclinkinffirebase.page.link";

        DynamicLink link = FirebaseDynamicLinks.getInstance()
                .createDynamicLink()
                .setLink(Uri.parse("https://celebrare.in/"))
                .setDomainUriPrefix(domain)
//                .setIosParameters(new DynamicLink.IosParameters.Builder("com.your.bundleid").build())
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder("com.example.dynamiclinkinffirebase").build())
                .setSocialMetaTagParameters(new DynamicLink.SocialMetaTagParameters.Builder().setTitle("Varun's App").setDescription("trying out some crzy things").setImageUrl(Uri.parse("https://avatars3.githubusercontent.com/u/53621853?s=400&u=de320b6f522f3324586c977c54aad9af3de890fb&v=4")).build())
                .buildDynamicLink();

        return link.getUri();
    }


    private void onShareClicked() {
        Uri link = generateContentLink();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, variable +" something is coming over here wohooo , suspense is awaiting your presence " + link.toString() + Uri.parse(url));

        startActivity(Intent.createChooser(intent, "Share Link"));
    }

}

