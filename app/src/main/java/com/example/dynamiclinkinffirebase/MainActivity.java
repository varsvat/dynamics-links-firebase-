package com.example.dynamiclinkinffirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class MainActivity extends AppCompatActivity {
    private Button button;
    String variable = "varun jain " , url = "https://celebrare.in/";
//    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                url = editText.getText().toString();
                generateContentLink();
            }
        });

//        editText = findViewById(R.id.edittext);

    }

    public  void  generateContentLink() {
        Uri baseUrl = Uri.parse("https://dynamiclinkinffirebase.page.link");
        String domain = "https://dynamiclinkinffirebase.page.link";

        FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://celebrare.in/"))
                .setDomainUriPrefix(domain)
                .setAndroidParameters(new
                        DynamicLink.AndroidParameters.Builder().build())
                .setSocialMetaTagParameters(
                        new DynamicLink.SocialMetaTagParameters.Builder()
                                .setTitle("Enter Title")
                                .setDescription("Enter Desc here")
                                .setImageUrl(Uri.parse("https://avatars3.githubusercontent.com/u/53621853?s=400&u=de320b6f522f3324586c977c54aad9af3de890fb&v=4"))
                                .build())
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent();
                                intent.setAction(Intent.ACTION_SEND);
                                intent.putExtra(Intent.EXTRA_TEXT,task.getResult().getShortLink().toString());
                                intent.setType("text/plain");
                                startActivity(intent);
                            } else {

                                Log.d("Error" , "there is a error"+task.getResult().getShortLink().toString());
                            }
                        }
                    }
                });
    }

//
//    private void onShareClicked() {
////        Uri link = generateContentLink();
//
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("text/plain");
//        intent.putExtra(Intent.EXTRA_TEXT, variable +" something is coming over here wohooo , suspense is awaiting your presence " + link.toString() + Uri.parse(url));
//
//        startActivity(Intent.createChooser(intent, "Share Link"));
//    }

}

