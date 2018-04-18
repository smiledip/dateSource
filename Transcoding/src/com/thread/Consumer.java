package thread;

/**
 * @Author smile_dip
 * @Date 2017/4/18 13:03
 * @Describe 数据加工并存入数据库
 */
public class Consumer implements Runnable {

    operateApp threadApp;

    public Consumer(operateApp threadApp) {
        this.threadApp = threadApp;
    }

    @Override
    public void run() {
        while (threadApp.stat) {
            threadApp.get();
        }
    }
}