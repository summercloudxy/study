package math;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.util.StringUtil;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

import java.util.*;

public class ExpressionUtil {

    /**
     * 计算List<Bean>配置了公式的值
     *
     * @param beanList
     * @return
     */
//    public static List<Map> workOutListBean(List<? extends BaseEntity> beanList) {
//        if (CollectionUtils.isEmpty(beanList)) {
//            return Collections.EMPTY_LIST;
//        } else {
//            Class<? extends BaseEntity> beanClass = beanList.get(0).getClass();
//            Table table = beanClass.getAnnotation(Table.class);//在实体中通过JPA注解获取表名
//            String tableCode = table.name();
//            if (StringUtils.isEmpty(tableCode)) {
//                System.out.println("无法计算,该bean没有表名信息");
//            }
//            List<Map> mapList = BeanUtil.convertBeansToMaps(beanList);
//            FormulaService formulaService = SpringContext.getBean(FormulaService.class);
//            List<Formula> formulas = formulaService.queryByTableCode(tableCode);
//            for (Map map : mapList) {
//                JEP jep = getJEP(map);
//                for (Formula formula : formulas) {
//                    String conditionExpr = formula.getConditionExpr();
//                    String formulaExpr = formula.getFormulaExpr();
//                    String evaluate = formula.getEvaluate();
//                    workOutKey(jep, map, conditionExpr, formulaExpr, evaluate);
//                }
//            }
//            return mapList;
//        }
//    }

    /**
     * 非动态配置公式方式
     *
     * @param mapList   list<Map> 可以传多组参数进行计算   Map: key- 参数名   value- 参数值
     * @param conditionExpr  条件表达式，满足条件才计算
     * @param formulaExpr   计算表达式
     * @param evaluate    计算表达式的名称，会将它作为key，表达式计算结果作为value，填充到参数的map中
     */
    public static void workOutListMap(List<Map> mapList, String conditionExpr, String formulaExpr, String evaluate) {
        for (Map map : mapList) {
            JEP jep = getJEP(map);
            workOutKey(jep, map, conditionExpr, formulaExpr, evaluate);
        }
    }

    /**
     * 计算出表达式并填充
     *
     * @param jep
     * @param map
     * @param conditionExpr
     * @param formulaExpr
     * @param evaluate
     */
    private static void workOutKey(JEP jep, Map map, String conditionExpr, String formulaExpr, String evaluate) {
        //如果没有条件
        if (StringUtils.isEmpty(conditionExpr)) {
            map.put(evaluate, workOutSingle(jep, formulaExpr));
            //如果有条件 且条件为true
        } else if (workOutBool(jep, conditionExpr)) {
            map.put(evaluate, workOutSingle(jep, formulaExpr));
        }
    }

    /**
     * 判断条件表达式
     *
     * @param jep
     * @param expression
     * @return
     */
    private static boolean workOutBool(JEP jep, String expression) {
        return (Double) workOutSingle(jep, expression) > 0;
    }

    /**
     * 计算表达式的值
     *
     * @param jep
     * @param expression
     * @return
     */
    private static Object workOutSingle(JEP jep, String expression) {
        Object result = null;
        try { //执行
            Node parse = jep.parse(expression);
            result = jep.evaluate(parse);
        } catch (ParseException e) {
            throw new RuntimeException("公式表达式解析失败", e);
        }
        if (result == null) {
            throw new RuntimeException("公式表达式解析失败");
        }
        return result;
    }

    /**
     * 获取填充好变量的JEP对象
     *
     * @param param
     * @return
     */
    private static JEP getJEP(Map param) {
        JEP jep = new JEP();
        Set<Map.Entry> set = param.entrySet();
        for (Map.Entry entry : set) {
            Object entryValue = entry.getValue();
            String entryKey = (String) entry.getKey();
            jep.addVariable(entryKey, entryValue);
        }
        return jep;
    }

    public static void main(String[] args) {
        Map<String,Object> paramMap1 = new HashMap();
        paramMap1.put("a",3);
        paramMap1.put("b",3);
        Map<String,Object> paramMap2 = new HashMap();
        paramMap2.put("a",3);
        paramMap2.put("b",4);
        List<Map> list = new ArrayList<>();
        list.add(paramMap1);
        list.add(paramMap2);
        workOutListMap(list,"a==b","a*a","面积");
        workOutListMap(list,"a!=b","a*b","面积");
        System.out.println(list);

    }


}
