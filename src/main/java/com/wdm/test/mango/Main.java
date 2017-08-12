package com.wdm.test.mango;

import java.util.Random;

import javax.sql.DataSource;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;
import org.jfaster.mango.datasource.DriverManagerDataSource;
import org.jfaster.mango.operator.Mango;

/**
 * Created by wdmyong on 2017/7/27.
 */
public class Main {
    public static void main(String[] args) {
        String driverClassName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root"; // 这里请使用您自己的用户名
        String password = ""; // 这里请使用您自己的密码
        DataSource ds = new DriverManagerDataSource(driverClassName, url, username, password);
        Mango mango = Mango.newInstance(ds); // 使用数据源初始化mango

        TestDao dao = mango.create(TestDao.class);
        String name = "apple";
        int num = new Random().nextInt() % 1000;
        dao.add(num, name);
        System.out.println(dao.getTotalNum(name));
    }

    @DB
    interface TestDao {

        // 插入数据
        @SQL("insert into test(num, name) values(:1, :2)")
        public void add(int num, String name);

        // 根据name取num的总和
        @SQL("select sum(num) from test where name=:1")
        public int getTotalNum(String name);

    }

}
