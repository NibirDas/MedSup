package com.example.medicinesupply;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class DistributerMainActivity extends AppCompatActivity {

    private TextView name,shopname,email;
    private ImageButton logout,addProduct;
    private ImageView profile;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distributer_main);

        name = findViewById(R.id.name);
        shopname = findViewById(R.id.shopName);
        logout = findViewById(R.id.logout);
        addProduct = findViewById(R.id.addProduct);
        email = findViewById(R.id.emailName);

        firebaseAuth =  FirebaseAuth.getInstance();

        logout.setOnClickListener((v) -> {
            makingOffline();
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DistributerMainActivity.this,AddProductActivity.class);
                startActivity(intent);
            }
        });

        private void makingOffline() {
            HashMap<String,Object> hashMap = new hashMap<>();
            hashMap.put("online",false);

            DatabaseReference ref  = FirebaseDatabase.getInstance().getReference("Users");
            ref.child(firebaseAuth.getUid()).updateChildren(hashMap)
                    .addOnSuccessListener((OnSuccessListener) (aVoid) -> {
                        firebaseAuth.signOut();
                        checkUser();
                    })
                    .addOnFailure((e) -> {
                        Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
        private void checkUser() {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user == null)
            {
                Intent intent = new Intent(DistributerMainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                loadMyInfo();
            }
        }
        private void loadMyInfo() {
            DatabaseReference ref  = FirebaseDatabase.getInstance().getReference("Users");
            ref.orderByChild("uid").equalsTo(firebaseAuth.getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapShot)
                        {
                            for(DataSnapShot ds: dataSnapShot.getChildren())
                            { //fetching data from database
                                String name1 = "" + ds.child("name").getValue();
                                String accountType = ""+ds.child("accountType").getValue();
                                String email1 = ""+ds.child("email").getValue();
                                String shopname1 = ""+ds.child("shopname1").getValue();
                                String profileImage = ""+ds.child("profileImage").getValue();

                                name.setText(name1);
                                shopname.setText(shopname1);
                                email.setText(email1);
                                try {
                                    Picasso.get().load(profileImage).placeholder(R.drawable.baseline_account_circle_24).into(profileimg);
                                }
                                catch(Exception e){
                                    
                                }
                            }
                        }
                    })
        }

    }
}
