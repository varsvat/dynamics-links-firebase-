package com.example.dynamiclinkinffirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;

public class MainActivity extends AppCompatActivity {
    private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShareClicked();
            }
        });

    }

    public static Uri generateContentLink() {
        Uri baseUrl = Uri.parse("https://your-custom-name.page.link");
        String domain = "https://your-app.page.link";

        DynamicLink link = FirebaseDynamicLinks.getInstance()
                .createDynamicLink()
                .setLink(baseUrl)
                .setDomainUriPrefix(domain)
                .setIosParameters(new DynamicLink.IosParameters.Builder("com.your.bundleid").build())
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder("com.your.packageName").build())
                .buildDynamicLink();

        return link.getUri();
    }


    private void onShareClicked() {
        Uri link = generateContentLink();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, link.toString());

        startActivity(Intent.createChooser(intent, "Share Link"));
    }

}