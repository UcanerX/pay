package cn.ucaner.pay.common.api;

import java.util.Map;

/**
 * 回调，可用于类型转换
 * @author: egan
 * <pre>
 *     email egzosn@gmail.com
 *     date 2017/3/7 18:55
 *  </pre>
 */
public interface Callback<T> {
     /**
      * 执行者
      * @param map 需要转化的map
      * @return 处理过后的类型对象
      */
     T perform(Map<String, Object> map);

}
