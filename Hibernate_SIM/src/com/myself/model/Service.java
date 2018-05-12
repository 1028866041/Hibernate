package com.myself.model;

import com.mysql.jdbc.Connection;
import javax.annotation.PreDestroy;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;

import java.lang.reflect.Method;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class Service {

    private String table = "user";
    private Map<String,String> cfg = new HashMap<String,String>();
    private String[] methodName;

    public Service() {
        cfg.put("id", "id");
        cfg.put("name", "name");
        cfg.put("age", "age");
        methodName = new String[cfg.size()];
    }

    public void save(User user) throws Exception{

        String sql = createSql();

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = (Connection)DriverManager.getConnection( "jdbc:mysql://localhost:3306/test","mc", "meichao");
        java.sql.PreparedStatement ps = conn.prepareStatement(sql);
        user
        //关键/Reflection机制:
        for(int i=0; i<methodName.length;i++){
            /*meichao->getMethod实现：Recursive方式从methodArray获得方法*/
            Method m = user.getClass().getMethod(methodName[i]);
            Class re= m.getReturnType();

            if(re.getTypeName().equals("java.lang.String")){
                /*执行已注入method*/
                ps.setString(i+1, (String)m.invoke(user));
            }

            if(re.getTypeName().equals("int")){
                /*执行已注入method*/
                ps.setInt(i+1, (Integer) m.invoke(user));
            }
        }
        System.out.println("methodName:"+methodName);

        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public String createSql() {
        String sql;
        String key = "";
        String value = "";

        int index = 0;
        for(String s: cfg.keySet()){
            String v =  cfg.get(s);
            v = Character.toUpperCase(v.charAt(0)) +v.substring(1);
            /*Rflection method*/
            methodName[index] = "get"+v;
            System.out.println("methodName["+index+"]:"+ methodName[index]);
            key += s+",";
            index++;
        }
        key = key.substring(0,key.length()-1);

        for(int i=0;i<cfg.size(); i++){
            value +=  "?,";
        }
        value = value.substring(0, value.length()-1);

        sql = "insert into"+" "+table+"("+ key +")"+" "+"values"+"("+ value+")";

        System.out.println("key:"+key+" "+"value:"+" "+value+" "+"sql:"+sql);

        return sql;
    }
}
