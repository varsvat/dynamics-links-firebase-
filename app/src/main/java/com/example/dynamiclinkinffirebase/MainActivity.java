package com.example.dynamiclinkinffirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class MainActivity extends AppCompatActivity {
    private Button button , button2;
    String variable = "varun jain ", url = "https://celebrare.in/";
//    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                url = editText.getText().toString();
                generateContentLink();
            }
        });

//        editText = findViewById(R.id.edittext);

    }
// there os some problem i think
    //this is the first change
    public void generateContentLink() {
        Uri baseUrl = Uri.parse("https://dynamiclinkinffirebase.page.link");
        String domain = "https://dynamiclinkinffirebase.page.link";
        String query = "?sharedimageurl=" + "https://avatars3.githubusercontent.com/u/53621853?s=400&u=de320b6f522f3324586c977c54aad9af3de890fb&v=4";

        FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse("https://celebrare.in/" + query))
                .setDomainUriPrefix(domain)
                .setAndroidParameters(new
                        DynamicLink.AndroidParameters.Builder().build())
                .setSocialMetaTagParameters(
                        new DynamicLink.SocialMetaTagParameters.Builder()
                                .setTitle("Varun's App")
                                .setDescription("This is a dynamic link")
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
                                intent.putExtra(Intent.EXTRA_TEXT, "Hi friend , this is an awesome app and i hope you like it , i am sharing you a card that you can see by opening the app at this link  \n" + task.getResult().getShortLink().toString() + "\n and if you want to open it in the mobile browser , then click on this link \n" + Uri.parse(url));
                                intent.setType("text/plain");
                                startActivity(intent);
                            } else {

                                Log.d("Error", "there is a error" + task.getResult().getShortLink().toString());
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

