package com.example.scheduler;

import androidx.appcompat.app.AppCompatActivity;

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

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MakeDiary extends AppCompatActivity {

    private static final int REQUEST_CODE = 0; // 사진 요청 코드
    private ImageView imageView;


    private Bitmap resize(Context context, Uri uri, int resize){
        Bitmap resizeBitmap=null;

        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); // 1번
            //uri는 선택한 이미지의 uri
            // 1번을 수행함으로써 options의 이미지의 넓이,높이,샘플사이즈 값이 입력, 여기서 샘플사이즈는 한 픽셀당 표현하는 픽셀의 수
            // 샘플사이즈의 숫자를 높일수록 한 픽셀안에 표현하는 픽셀수가 많아짐 -> 이미지의 크기가 작아짐
            // EX) 샘플사이즈가 '4' 인 경우 4개의 픽셀을 1개의 픽셀에 표현하므로 이미지 크기는 1/4로 작아집니다.

            int width = options.outWidth;
            int height = options.outHeight;
            int samplesize = 1;


            //인자값으로 받은 resize는 원하는 이미지 리사이즈 크기 입니다. '500' 을 인자값으로 받았다면 이미지가 500에 가장 가깝게 줄입니다.
            //예를들어 원본 이미지의 넓이가 3000, 높이가 1000일 경우 2번을 수행하면 넓이는 1500, 높이는 1000, 샘플사이즈는 2입니다.
            // 원하는 값으로 무조건 리사이즈하는게 아니라, 근사치로 줄입니다. 그 과정에서 샘플사이즈는 2배씩 커지게 되는데 그 이유는 샘플사이즈가 2배수만을 인식하기 때문입니다.

            while (true) {//2번
                if (width / 2 < resize || height / 2 < resize)
                    break;
                width /= 2;
                height /= 2;
                samplesize *= 2;
            }

            options.inSampleSize = samplesize;
            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options); //3번
            resizeBitmap=bitmap;
            //당 메소드로 리사이즈된 이미지의 넓이와 높이를 보면 핸드폰을 가로로 놓고 찍은 사진은 넓이가 넓고,
            // 세로로 놓고 찍은 사진은 높이가 높기때문에 같은 이미지로 2번에서 넓이와 높이가 원하는 사이즈보다 작아지기 전까지 수행합니다.
            //그래야 이미지의 원본 화질을 최대한 보존할 수 있습니다. ( 본래 크기보다 억지로 늘릴려고 한다면 사진은 깨집니다.



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resizeBitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_diary);

        imageView = findViewById(R.id.image);

        imageView.setOnClickListener(new View.OnClickListener(){ // 갤러리에 요청코드 보내기
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {

                    Uri uri = data.getData();
                    Glide.with(getApplicationContext()).load(uri).into(imageView); // 이미지 사진 넣기

                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}