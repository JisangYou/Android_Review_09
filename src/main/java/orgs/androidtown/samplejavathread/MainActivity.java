package orgs.androidtown.samplejavathread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button, button2;
    TextView textView;
//    int value = 0;

    ValueHandler handler = new ValueHandler();
    Handler handler2 = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                BackgroundThread thread = new BackgroundThread();
//                thread.start();

                new Thread(new Runnable() {
                    int value = 0;
                    boolean running = false;

                    public void run() {
                        running = true;
                        while (running) {
                            value += 1;

                            handler2.post(new Runnable() {
                                public void run() {
                                    textView.setText("현재 값 : " + value);
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

//    class BackgroundThread extends Thread {
//        boolean running = false;
//        int value = 0;
//
//        public void run() {
//            running = true;
//            while (running) {
//                value += 1;
//
//                Message message = handler.obtainMessage();
//                Bundle bundle = new Bundle();
//                bundle.putInt("value", value);
//                message.setData(bundle); //메시지 클래스에 있는 메소드에 인자에 번들을 넣음.
//                handler.sendMessage(message);// 그리고 메소드에 인자에 번들을 넣은 클래스 자체를 핸들러 클래스에 인자로 던져줌,
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }

    class ValueHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText(" 현재 값 :" + value);
        }
    }
}
