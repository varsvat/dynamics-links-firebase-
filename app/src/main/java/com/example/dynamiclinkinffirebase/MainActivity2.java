package com.example.dynamiclinkinffirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

import java.util.Objects;

public class MainActivity2 extends AppCompatActivity {
    String queryurl;
    private TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView3 = findViewById(R.id.textview2);
        FirebaseDynamicLinks.getInstance().getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        if (pendingDynamicLinkData == null) {
                            Log.d("pendicdynamiclink", " is null");
                        } else {
                            Toast.makeText(MainActivity2.this, "Resolving Link, Please Wait...", Toast.LENGTH_LONG).show();
                            if (pendingDynamicLinkData.getLink().getQueryParameter("sharedimageurl") != null) {
                                if (Objects.requireNonNull(pendingDynamicLinkData.getLink().getQueryParameter("sharedimageurl")).equalsIgnoreCase("profile")) {
                                    try {
                                        queryurl = pendingDynamicLinkData.getLink().getQueryParameter("sharedimageurl");
                                        textView3.setText(queryurl);
                                        Uri uri = pendingDynamicLinkData.getLink();
                                        Log.d("Deeplink", uri.toString());
                                        String permLink = uri.toString().split("\\?")[0];
                                        Intent intent = new Intent(MainActivity2.this, MainActivity2.class);
//                                        intent.putExtra(MainActivity2.PROFILE_NAME, uri.getQueryParameter("name"));
//                                        intent.putExtra(MainActivity2.PROFILE_CATEGORY, uri.getQueryParameter("category"));
//                                        intent.putExtra(MainActivity2.PROFILE_PICTURE, uri.getQueryParameter("picture"));
//                                        intent.putExtra(MainActivity2.POST_PERMLINK, permLink);
                                        MainActivity2.this.startActivity(intent);
                                        MainActivity2.this.finish();
                                    } catch (NullPointerException e) {
                                        Toast.makeText(MainActivity2.this, "Unable to View Shared Image", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Log.d("Error: ", "The query parameter is not present");
                            }
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("Error: ", "GetdyanmicLink: " + "Onfailure");
                            }
                        }

                );
    }
}
