package com.example.scheduler;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListView_Notice extends AppCompatActivity {

    private ListView notice; // 공지사항 리스트뷰 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        notice = (ListView)findViewById(R.id.list1); // 리스트뷰 아이디 가져옴
        // (ListView)를 적어주는 이유 : findviewbyid 앞에 선언한 변수의 타입이랑 일치시켜야함

        List<String> data = new ArrayList<>(); // ArrayList 배열안에 String 형태로 넣겠다 // 데이터를 저장하는 리스트 생성

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        // 리스트뷰랑 리스트를 연결해주는 다리역할을 해주는 어뎁터 생성
        // android.R.layout.simple_list_item_1 : 안드로이드에서 제공해주는 디자인

        notice.setAdapter(adapter); //리스트뷰에다가 다리역할을 하는 어뎁터를 세팅해줌

        //아이템 추가
        data.add("스티커 추가! 0.0.2 업데이트");
        data.add("여름과 함께 다가온 0.0.1 업데이트");

        adapter.notifyDataSetChanged(); // 아이템 저장

    }
}
