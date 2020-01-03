package com.example.addresses;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerlist;
    ArrayList<DataModel> datas;
    public FirebaseFirestore mFirestore;
    public String nametitle,desc,addr,imageurl;
   public double addlat,addlong;

    public StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerlist=findViewById(R.id.recyclerlist);


        datas = new ArrayList<>();
        for(int i=0;i<10;i++)
        {

        }

        DataModelAdapter adapter = new DataModelAdapter(MainActivity.this, datas);
        recyclerlist.setAdapter(adapter);
        recyclerlist.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onStart() {
        super.onStart();
        mFirestore=FirebaseFirestore.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference().child("Images/");



        mFirestore.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {



                datas.clear();
                if(e!=null)
                {
                    System.out.println(e.getMessage());
                }


                for(DocumentSnapshot doc:queryDocumentSnapshots ) {
                    try {
                        DataModel dataModel = doc.toObject(DataModel.class);

                        datas.add(dataModel);


                        if (imageurl != null) {
                            ImageView imageView = findViewById(R.id.idpic);
                            System.out.println(nametitle+"\n"+desc+"\n"+addlat+"\n"+addlong+"\n"+imageurl);
                        } else {
                          System.out.println(nametitle+"\n"+desc+"\n"+addlat+"\n"+addlong+"\n");

                        }

                    } catch (Exception ex) {
                        System.out.println("Exception : "+ex.getMessage());
                    }
                }

            }
        });

    }
}
