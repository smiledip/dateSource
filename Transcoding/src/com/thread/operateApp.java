package thread;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author smile_dip
 * @Date 2017/4/18 12:09
 * @Describe 操作oracle数据库，读取数据，加工完再保存到数据库
 */
public class operateApp {

    private static myCollection mycollection = new myCollection();
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    int i = 0;
    public boolean stat = true;
    private static Map<String, String> maps = new HashMap<>();
    Map<String, String> mm = new HashMap<String, String>();

    public operateApp(myCollection myCollection) {
        con = myCollection.getCon();
    }

    public operateApp() {
    }

    /**
     * 按批查询数据
     */
    public synchronized void set() {
        if (mm.size() > 0) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "：等待还未被加工:" + System.currentTimeMillis());
            return;
        }
        i++;
        try {
            ps = con.prepareStatement("SELECT * FROM (SELECT t.*, ROWNUM AS CON FROM zxy_department_test t WHERE ROWNUM <= " + (i * 100) + ") WHERE CON >=" + (i - 1) * 100);
            rs = ps.executeQuery();

            if (rs.next()) {
                while (rs.next()) {
                    String upName = rs.getString("orgname").trim();
                    //更改表的数据
                    mm.put(rs.getString("orgid"), upName);
                }
            } else {
                stat = false;
            }
            System.out.println(Thread.currentThread().getName() + "：取数中:" + System.currentTimeMillis());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

    /**
     * 获取map进行转码，并批量修改
     * @param name
     * @return
     */

    /**
     *   主要用于转码
     * @param name
     * @return
     */
    public String findcode(String name) {


        if (name.contains("香港") || name.contains("澳门")) {
            name = name.substring(0, 2);

        } else if (name.contains("作废")) {
            name = "未知";
        } else if (name.contains("经济技术开发区")) {
            name = name.split("经济技术开发区")[0];
        } else if (name.contains("市）") && name.contains("（")) {
            name = name.split("\\）")[0].split("\\（")[1];
        }
        if (name.contains("中心支公司")) {
            name = name.split("中心支公司")[0];
        } else if (name.contains("本部营销服务部")) {
            name = name.split("本部营销服务部")[0];
        }
        if (name.contains("本部")) {
            name = name.split("本部")[0];
        }
        if (name.contains("支公司")) {
            name = name.split("支公司")[0];
        }
        if (name.contains("州")) {
            name = name.split("州")[0] + "州";
        }
        if (name.contains("省")) {
            name = name.split("省")[0] + "省";
        }
        if (name.contains("区")) {
            if (name.equals("市辖区")) {
                name = "未知";

            } else if (name.contains("市辖区")) {

                name = name.split("市辖区")[0];
            } else if (name.contains("地区")) {

                name = name.split("地区")[0];
            } else {
                name = name.split("区")[0] + "区";
            }
            if (name.contains("市") && name.contains("区")) {
                if (name.equals("开发区")) {
                    name = name.split("市")[0];
                } else {
                    name = name.split("市")[1];
                }
            }

        }
        if (name.contains("市")) {

            name = name.split("市")[0] + "市";

        } else if (name.contains("县")) {
            name = name.split("县")[0] + "县";
        }
        if (name.contains("分公司")) {
            name = name.split("分公司")[0];
        }
        return maps.get(name);

    }

    /**
     * 其实完全没有必要分多步
     */
    public synchronized void get() {
        if (mm.size() <= 0) {
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": 为0:" + System.currentTimeMillis());
        }
        try {
            String sql = "update zxy_department_test set EFFPREFECTURECODE=?  where orgid=?";
            ps = con.prepareStatement(sql);

            for (String key : mm.keySet()) {
                ps.setString(2, key);

                ps.setString(1, findcode(mm.get(key)));
                ps.addBatch();
            }
            ps.executeBatch();
            con.commit();
            System.out.println(Thread.currentThread().getName() + "：加工中" + System.currentTimeMillis());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
        }
        mm.clear();
        notifyAll();
    }

    public static void main(String[] args) {
        //测试下时间
        long start = System.currentTimeMillis();   //获取开始时间
        System.out.println("开始时间：" + start);

        //1.将excel中的数据读入到list中
        loadExcl load = new loadExcl();

        //List lis= load.findlist();
        maps = load.findlist();
        //采用多线程的方式处理数据库中的表
        System.out.println("begin --------------------");
        operateApp operate = new operateApp(mycollection);

        Consumer consumer = new Consumer(operate);
        Producer pro = new Producer(operate);
        Thread p1 = new Thread(pro);
        /**
         * 这里可以设计开几个线程
         */
        p1.setName("生产者1");
        Thread p2 = new Thread(pro);
        p2.setName("生产者2");

        Thread c1 = new Thread(consumer);
        c1.setName("消费者1");
        Thread c2 = new Thread(consumer);
        c2.setName("消费者2");

        p1.start();
        p2.start();
        c1.start();
        c2.start();


    }


}
