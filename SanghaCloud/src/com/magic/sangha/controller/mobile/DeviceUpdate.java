package com.magic.sangha.controller.mobile;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.controller.BaseController;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  软件升级 通知 控制器
 * @author QimouXie
 *
 */

@Controller
@RequestMapping("/updateVersion")
public class DeviceUpdate extends BaseController {
	

	@RequestMapping("/apk")
	@ResponseBody
	public ViewData updateApk(){
		JSONObject obj = new JSONObject();
		obj.put("apkUrl", "http://7xnfyf.com1.z0.glb.clouddn.com/sobey1.0.09.apk");
		obj.put("appName", "SOBEY");
		obj.put("versionCode", "7");
		obj.put("versionName", "1.0.09");
		obj.put("changeLog", "1.修复了少量Bug<br/>2.现在APP启动更快了<br/>3.更新到1.0.09版<br/>");
		obj.put("updateTips", "更新");
		obj.put("status", 1);
		obj.put("created_at", "2016-07-28");
		obj.put("size", "13.5M");
		obj.put("isForce", 0);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "获取成功", obj);
	}

}
