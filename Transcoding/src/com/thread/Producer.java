package thread;

/**
 * @Author smile_dip
 * @Date 2017/4/18 13:03
 * @Describe 读取数据，每100条处理一次
 */
public class Producer implements Runnable {

    operateApp threadApp;

    public Producer(operateApp threadApp) {
        this.threadApp = threadApp;
    }

    @Override
    public void run() {
        while (threadApp.stat) {
            threadApp.set();
        }
    }
}
