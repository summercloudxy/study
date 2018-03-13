package fastjson.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import fastjson.pojo.Country;
import fastjson.pojo.User;

public class FastJsonService {
    public static void main(String[] args) {
        FastJsonService service = new FastJsonService();
        service.configByAnnotation();
        service.configByParam();
    }

    /**
     * 利用注解配置序列化行为
     */
    private void configByAnnotation() {
        User user = new User();
        user.setId(1);
        user.setAge(20);
        user.setName("张三");
        user.setGender("男");
        String s = JSON.toJSONString(user);
        System.out.println(s);
        //输出{"name_1":"张三","age":20,"gender":"男","phoneNumber":null}
        //没有id(serialize = false)
        //phoneNumber为null时依然进行序列化(serialzeFeatures = SerializerFeature.WriteMapNullValue)
        //按name、age、gender、phoneNumber的顺序进行输出(ordinal=1)
    }

    /**
     * 利用序列化时传入的参数配置序列化行为
     */
    private void configByParam() {
        Country country = new Country();
        country.setId(1);
        country.setName("China");
        country.setPosition("Asia");
        country.setLanguage("Chinese");
        //填入需要序列化的属性
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Country.class, "name", "position", "language", "population");
        //设置null字段也进行序列化
        String s = JSON.toJSONString(country, filter, SerializerFeature.WriteMapNullValue);
        System.out.println(s);
        //输出{"language":"Chinese","name":"China","population":null,"position":"Asia"}
    }
}
