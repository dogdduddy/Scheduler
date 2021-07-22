package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MakeDiary extends AppCompatActivity {

    private static final int REQUEST_CODE = 0; // 사진 요청 코드
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_diary);

        imageView = findViewById(R.id.image);

        imageView.setOnClickListener(new View.OnClickListener() { // 갤러리에 요청코드 보내기
            @Override
            public void onClick(View v) { // 이미지 클릭 시 실행 -> 이미지를 고를 갤러리를 오픈
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 갤러리
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {

                    Uri uri = data.getData();
                    Glide.with(getApplicationContext()).load(uri)./*override(500,500). 이미지 사이즈 조정인데 안됨*/
                            into(imageView); // 이미지 사진 넣기

                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}