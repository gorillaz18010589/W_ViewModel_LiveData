package com.example.livedata;
//ViewModel 中間人處理畫面要做的演算
//目的:有+1按鈕 歸0按鈕 ,跟一開始為0
//3段演化:1.第一段傳統方法,依著生命週期結束 2.用ViewModel. addScore字寫方法 3.LiveData.MutableLiveData<T>方法,用setValue跟getValue來處理
//1.第一步用傳統方式
//2.寫一個ScoreViewModel 繼承ViewModel來做演算並且給View玩
//3.因為方法寫在ViewModel所以不會依生命週期引響,在手機轉換時質不見


//ViewModelProvider.of(@NonNull FragmentActivity activity)://ViewModel提供者相依於(activity/fragment)(回傳值ViewModelProvider )
//ViewModelProvider.get(@NonNull Class<T> modelClass)://取得類別(ViewModel類別)(回傳值<T extends ViewModel> T)
//LiveData<T>.observe(LifecycleOwner owner,Observer<? super T> observer):1.這個lifecle頁面 2,Observer<你的資料結構>監聽

        import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.livedata.ViewModel.ScoreViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Button addScoreButton, resetScoreButton;
    TextView scoreTextView;
    ScoreViewModel scoreViewModel;
//    Integer score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.init()
        scoreTextView = findViewById(R.id.scoreTextView);
        addScoreButton = findViewById(R.id.addScoreButton);
        resetScoreButton = findViewById(R.id.resetScoreButton);


        //2.按鈕設定
        addScoreButton.setOnClickListener(view -> addScore());
        resetScoreButton.setOnClickListener(view -> resetScore());


        //3.取得ViewModel物件跟類別
        scoreViewModel = ViewModelProviders.of(this) //ViewModel提供者相依於(activity/fragment)
                .get(ScoreViewModel.class);//取得類別(類別)

        //**簡寫方式
//       LiveData<T>.observe(LifecycleOwner owner,Observer<? super T> observer):
//        scoreViewModel.getScore().observe(this, integer -> {
//
//            scoreTextView.setText(String.valueOf(integer));
//            Log.v("hank","Observer<Interge>:" + integer.toString());
//        });

        //第三種方法
        scoreViewModel.getScore().observe(this, new Observer<Integer>() {
            //當質的方法改變時呼叫
            @Override
            public void onChanged(Integer integer) {
                scoreTextView.setText(String.valueOf(integer));
                Log.v("hank", "Observer<Interge>:" + integer.toString());
            }
        });


        //4.設定開始為0 (socreViewNodel.取得0方法); (第二種方式)
//        scoreTextView.setText(String.valueOf(scoreViewModel.getScore()));


        //1/(第一種方法)一開始為0
//        score = 0;
//        scoreTextView.setText(String.valueOf(score));

        Log.v("hank", "onCreate");
    }

    //質+1
    public void addScore() {
        //3.(第三種方法)MutableLiveData<Interge>
        scoreViewModel.addScore();


        //1.第一種方法
//        score += 1;
//        scoreTextView.setText(String.valueOf(score));

        //2.第二種方法ViewModel
//        scoreViewModel.addScore();
//        scoreTextView.setText(String.valueOf(scoreViewModel.getScore()));
        Log.v("hank", "addScore");

    }

    //歸0
    public void resetScore() {

        //3.(第三種方法)MutableLiveData<Interge>
        scoreViewModel.resetScore();

        //1.第一種方法
//          score = 0;
//          scoreTextView.setText(String.valueOf(score));

        //2/第二種方法ViewModel
//        scoreViewModel.resetScore();//設定為0
//        scoreTextView.setText(String.valueOf(scoreViewModel.getScore()));//設定文字為(取得質)

        Log.v("hank", "resetScore");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("hank", "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("hank", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("hank", "onPasue");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("hank", "onStart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("hank", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("hank", "onStop");
    }
}