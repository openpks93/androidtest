package com.example.user.dbtest;

public class Item {//listView에 데이터가 들어갈 공간을 정의함

    private String[] borditem;//String 배열 타입에 데이터를 넣기위해 선언

    public Item(String[] obj02){
        borditem = obj02;//다른곳에서 값을 넣어줄 수 있게함
    }

    public String[] borditemdate(){
        return borditem;
    }//다른곳에서 이 값을 쓸수있게 설정

    public String borditemdate(int index){//배열 값을 받아쓸때 if문을 한번거침 값이 없거나 인덱스 값이 배열이 가지고 있는 데이터 갯수보다 같거나 크면 null값을 return 해줌
        if (borditem == null || index >= borditem.length) {
            return null;
        }
        return borditem[index];
    }

}