package com.example.crmsystem.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;

import com.example.crmsystem.R;
import com.google.android.material.textfield.TextInputEditText;

public class CreateNewProduct extends AppCompatActivity {


    private static final String SELLPRODUCTNAME ="sellproductname" ;
    private static final String SELLSTOCKAVAILABILITY ="sellstockavailability" ;
    private static final String SELLPRICE = "sellprice";
    private static final String SELLPRODUCTDESCRIPTION = "sellproductdescription";
    public  static final String  IMGURL = "imgurl";
    private static final String MINTYPE ="mintype" ;

    ImageView productsellimg;
    TextInputEditText createnewproname,createnewproprice,createnewproquantity,createnewprodec;
    Button createnewprobtn;
    Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_product);

        productsellimg = (ImageView) findViewById(R.id.productsellimg);
        createnewproname = (TextInputEditText)findViewById(R.id.createnewproname);
        createnewproprice = (TextInputEditText)findViewById(R.id.createnewproprice);
        createnewproquantity = (TextInputEditText)findViewById(R.id.createnewproquantity);
        createnewprodec = (TextInputEditText)findViewById(R.id.createnewprodec);
        createnewprobtn = (Button) findViewById(R.id.createnewprobtn);

        productsellimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startGalleryForImagePicking();
            }
        });

        createnewprobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strcreatenewproname =createnewproname.getText().toString();
                String strcreatenewproprice =createnewproprice.getText().toString();
                String strcreatenewproquantity =createnewproquantity.getText().toString();
                String strcreatenewprodec =createnewprodec.getText().toString();

                if (!strcreatenewproname.isEmpty() && !strcreatenewproprice.isEmpty() && !strcreatenewproquantity.isEmpty() && !strcreatenewprodec.isEmpty()){

                    String mimeType = MimeTypeMap.getSingleton()
                            .getExtensionFromMimeType(
                                    getContentResolver().getType(imageuri));

                    Intent intent =  new Intent(CreateNewProduct.this, ProductUploadService.class);

                    Bundle bundle1 = new Bundle();


                    bundle1.putString(SELLPRODUCTNAME,strcreatenewproname);
                    bundle1.putString(SELLSTOCKAVAILABILITY,strcreatenewproquantity);
                    bundle1.putString(SELLPRICE,strcreatenewproprice);
                    bundle1.putString(SELLPRODUCTDESCRIPTION,strcreatenewprodec);
                    bundle1.putString(MINTYPE,mimeType);
                    bundle1.putString(IMGURL, String.valueOf(imageuri));
                    intent.putExtras(bundle1);
                    startService(intent);




                }



            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 0 && data != null) {
            imageuri = data.getData();
            productsellimg.setImageURI(imageuri);
        }
    }
    private void startGalleryForImagePicking() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }
}