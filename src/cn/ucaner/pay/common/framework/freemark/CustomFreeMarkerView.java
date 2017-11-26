/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JsonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.pay.common.framework.freemark;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import cn.ucaner.pay.common.framework.constant.GlobalConstant;
import cn.ucaner.pay.common.framework.system.SystemConfig;
import cn.ucaner.pay.common.framework.utils.CommonUtil;
/**
* @Package：cn.ucaner.framework.common.freemarker   
* @ClassName：CustomFreeMarkerView   
* @Description：   <p> 用户自定义视图解析器 </p>
* @Author： - DaoDou 
* @CreatTime：2017年8月28日 下午4:39:36   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public class CustomFreeMarkerView extends FreeMarkerView {
	public static Logger logger = LoggerFactory.getLogger(CustomFreeMarkerView.class);

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String basePath = CommonUtil.getRequestContextPath(request);
		if (logger.isDebugEnabled()) {
			logger.debug("CustomFreeMarkerView exposeHelpers's basePath:{}", basePath);
		}
		String imgPath = SystemConfig.getStringValue("file_virtual_path");
		String css_version = SystemConfig.getStringValue(GlobalConstant.CSS_VERSION);
		if (StringUtils.isBlank(css_version)) {
			css_version = "1.0";
		}
		String js_version = SystemConfig.getStringValue(GlobalConstant.JS_VERSION);
		if (StringUtils.isBlank(js_version)) {
			js_version = "1.0";
		}

		// 设置项目路径为全局变量
		model.put("path", path);
		model.put("basePath", basePath);
		model.put("imgPath", imgPath);
		
		model.put(GlobalConstant.CSS_VERSION, css_version);
		model.put(GlobalConstant.JS_VERSION, js_version);
		super.exposeHelpers(model, request);
	}
}
