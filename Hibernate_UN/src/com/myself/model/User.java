package com.myself.model;

import com.oracle.webservices.internal.api.message.PropertySet;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/6/16 0016.
 */
@Entity
@Table(name="user")
@SequenceGenerator(name="idSeq",sequenceName="idSeq_db")
/*Table生成主键:
 *可为含相同key的不同表生成Id/可跨不同数据库平台*/
@TableGenerator(
        name="idTab",
        table="user",
        pkColumnName = "key",
        valueColumnName = "value",
        pkColumnValue = "zhangsan",
        allocationSize = 1
)
public class User {

    private String name;
    private int age;
    private Date date;
    private Enum rank;

    /*针对AUTO_INCREMENT*/
    private int id;
    public void setId(int id){
        this.id = id;
    }

    @Id
    /*@GeneratedValue(strategy = GenerationType.IDENTITY)//默认auto方式*/
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="idSeq")*/
    @GeneratedValue(strategy = GenerationType.TABLE, generator="idTab")
    public int getId() {
        return id;
    }

    /*针对ID生成策略<generator class="uuid">
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }*/

    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    @Column
    public String getName() {
        return name;
    }

    @Column
    public int getAge() {
        return age;
    }

    public void setDate(Date date) {

        this.date = date;
    }

    @Column
    @Temporal(TemporalType.DATE)
    public Date getDate() {

        return date;
    }

    public void setRank(Enum rank) {
        this.rank = rank;
    }

    @Column
    @Enumerated(EnumType.STRING)
    public Enum getRank() {
        return rank;
    }
}
