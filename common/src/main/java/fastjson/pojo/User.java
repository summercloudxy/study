package fastjson.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class User {
    /**
     * 不序列化该字段
     */
    @JSONField(serialize = false)
    private int id;
    @JSONField(ordinal=1,name = "name_1")
    private String name;
    @JSONField(ordinal=2)
    private int age;
    @JSONField(ordinal=3)
    private String gender;
    /**
     * 该字段为null时，也进行序列化（默认为null的字段是不进行序列化的）
     */
    @JSONField(ordinal=4,serialzeFeatures = SerializerFeature.WriteMapNullValue)
    private Integer phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
