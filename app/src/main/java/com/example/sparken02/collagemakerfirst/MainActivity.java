package com.example.sparken02.collagemakerfirst;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sparken02.collage.CollageView;
import com.github.siyamed.shapeimageview.StarImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int PICK_IMAGE_GALLARY = 1;
    private int PICK_IMAGE_CAMERA = 0;
    private Bitmap bitmap;
    private CollageView collage;
    private List<Bitmap> listBit;
    private RecyclerView mRecyclerView,frameRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;

    private static int i = 0;
//    RelativeLayout layoutCollage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collage = (CollageView) findViewById(R.id.collage);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        frameRecyclerView = (RecyclerView) findViewById(R.id.framerecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        frameRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList items = new ArrayList<>(Arrays.asList(
                R.drawable.square,
                R.drawable.circle,
                R.drawable.diamond,
                R.drawable.heart,
                R.drawable.hexagone,
                R.drawable.octagone,
                R.drawable.pentagone,
                R.drawable.roundrectangle));
        RecyclerViewAdapter mAdapter = new RecyclerViewAdapter(MainActivity.this,items);
        mRecyclerView.setAdapter(mAdapter);

//        if(bitmap!=null){
//            setBitmapImage();
//        }





    }



    private void setBitmapImage() {
        Log.i("TAG", "setBitmapImage: ");

        if(bitmap!=null) {
            StarImageView imageView = new StarImageView(this);
            Log.i("TAG", "setBitmapImage: " + bitmap);
            imageView.setImageBitmap(bitmap);
            collage.addCard(imageView);
            collage.setViewRefresh(false);

            collage.requestLayout();
            Log.i("TAG", "set All BITMAP: " + i);
            i++;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addPhoto:
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setPositiveButton("CAMERA", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Add your code for the button here.
                        activeCamera();
                    }
                });
                alertDialog.setNegativeButton("GALLARY",new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        activeGallary();
                    }
                });
                alertDialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void activeCamera() {

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PICK_IMAGE_CAMERA);
    }

    private void activeGallary() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select image"), PICK_IMAGE_GALLARY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_GALLARY && resultCode == RESULT_OK ){
            onSelectFromGallaryResult(data);
        }
        else if(requestCode == PICK_IMAGE_CAMERA && resultCode ==RESULT_OK){
            onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {

        try{
            bitmap = (Bitmap) data.getExtras().get("data");
            Log.i("TAG", "onSelectFromImageResult: ");

        }catch(Exception e){
            e.printStackTrace();
        }
        Log.i("TAG", " Camera out: ");
//        if(bitmap!=null) {
            setBitmapImage();
//        }
//        drawview.setmyBitmap(bitmap);
    }

    private void onSelectFromGallaryResult(Intent data) {
        Uri uri = data.getData();
        try{
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            Log.i("TAG", "onSelectFromGallaryResult: ");
        }catch(Exception e){
            e.printStackTrace();
        }
        Log.i("TAG", " Gallery out: ");
        setBitmapImage();


//        if(bitmap!=null) {





    }
}
