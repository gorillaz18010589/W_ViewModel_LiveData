package com.example.livedata.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
    //LiveData<T>.getValue()://取得質(回傳你犯行的資料T)
    //LiveData<T>.setValue(T value)://設定質(可以依照你范型的資料型態)

    //2.可以依照你范型的資料型態,直接用setValue給質
    private MutableLiveData<Integer> score; //宣告MutableLiveData<你要的資料結構宣告>

    //2.1取得MutableLiveData<你的資料型態>物件實體初始化給質
    public MutableLiveData<Integer> getScore() {
        if (score == null) {
            score = new MutableLiveData<>();
            score.setValue(0);//設定質(可以依照你范型的資料型態)
        }

        return score;
    }


    //2.2讓質+1
    public void addScore() {
        if (score != null) {
            score.setValue(score.getValue() + 1);//取得質(回傳你犯行的資料T)
        }
    }

    //2.3讓質歸0
    public void resetScore() {
        score.setValue(0);

    }




    //1.舊有的寫方法方式
//    private Integer score;
//
//    //1.get取質,如果一開始為空質給0
//    public Integer getScore() {
//        if (score == null) {
//            score = 0;
//        }
//        return score;
//    }
//    //1.
//    public void addScore() {
//        if (score == null) {
//            score = 0;
//        }
//        score += 1;
//    }
//    //1.
//    public void resetScore() {
//        score = 0;
//    }
}
