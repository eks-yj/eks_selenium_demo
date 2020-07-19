package com.eks.utils;

import com.eks.enumeration.DatabaseDriverEnum;
import com.google.gson.JsonArray;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JdbcUtilsTest {
    @Test
    public void test1() throws Exception {
        String filePathString = EksFileUtils.getPathBaseProject("extra/db/sqlite.db");
        String urlString = "jdbc:sqlite:" + filePathString;
        String sqlString = "SELECT * from tbl_dictum order by id";
        JsonArray jsonArray = JdbcUtils.queryJsonArrayByJdbc(DatabaseDriverEnum.SQLITE,urlString,null,null,sqlString);
        System.out.println(jsonArray.toString());
    }
    @Test
    public void test2() throws Exception {
        String filePathString = EksFileUtils.getPathBaseProject("extra/db/sqlite.db");
        String urlString = "jdbc:sqlite:" + filePathString;
        String sqlString = "INSERT INTO 'main'.'tbl_dictum'('id', 'author', 'dictum') VALUES (1,'author','dictum')";
//        String sqlString = "create table tbl_dictum (id integer not null, author varchar(255), dictum varchar(255), primary key (id))')";
        List<String> sqlStringList = new ArrayList<>();
        sqlStringList.add(sqlString);
        JdbcUtils.updateByJdbc(DatabaseDriverEnum.SQLITE,urlString,null,null,sqlStringList);
    }
    @Test
    public void test3() throws Exception {
        String urlString = "jdbc:mysql://127.0.0.1:3306/eks_ad_demo?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
        String sqlString = "SELECT id,goods_id_string,mini_program_image_url_string,we_chat_long_url_string FROM eks_ad_demo";
        JsonArray jsonArray = JdbcUtils.queryJsonArrayByJdbc(DatabaseDriverEnum.MYSQL,urlString,"root","root",sqlString);
        System.out.println(jsonArray.toString());
    }
}
