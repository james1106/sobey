package com.magic.sangha.controller.mobile;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.sangha.controller.BaseController;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.ViewData;

/**
 *  ������� ֪ͨ ������
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
		obj.put("changeLog", "1.�޸�������Bug<br/>2.����APP����������<br/>3.���µ�1.0.09��<br/>");
		obj.put("updateTips", "����");
		obj.put("status", 1);
		obj.put("created_at", "2016-07-28");
		obj.put("size", "13.5M");
		obj.put("isForce", 0);
		return buildSuccessJson(StatusConstant.SUCCESS_CODE, "��ȡ�ɹ�", obj);
	}

}
