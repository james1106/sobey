package com.magic.sangha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.magic.sangha.util.FileUpload;
import com.magic.sangha.util.StatusConstant;
import com.magic.sangha.util.UuidUtil;
import com.magic.sangha.util.ViewData;

import javax.servlet.http.HttpServletRequest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flyong86 on 2016/5/6.
 */
@Controller
@RequestMapping("/res")
public class ResourceController extends BaseController {

	
    @RequestMapping("/upload")
    @ResponseBody
    public ViewData upload(@RequestParam(value = "type" ,defaultValue = "other") String type,HttpServletRequest request){

        Calendar ca = Calendar.getInstance();
        if (request instanceof MultipartHttpServletRequest) {
            String url = "";
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> multipartFileMap = multipartHttpServletRequest.getFileMap();
            if (multipartFileMap != null) {
                for (Map.Entry<String, MultipartFile> entry : multipartFileMap.entrySet()) {
                    MultipartFile multipartFile = entry.getValue();
//                    String filename = multipartFile.getOriginalFilename();
                    String filePath = "upload/" + ca.get(Calendar.YEAR) + "/" + ca.get(Calendar.MONTH) + "/" + ca.get(Calendar.DAY_OF_MONTH);
                    String resName = FileUpload.fileUp(multipartFile, filePath, UuidUtil.get32UUID());

                    StringBuffer picURL = new StringBuffer();
//                    picURL.append(request.getScheme() + "://");
//                    picURL.append(request.getServerName() + ":");
//                    picURL.append(request.getServerPort() + "");
//                    picURL.append(request.getContextPath() + "/");
                    picURL.append(filePath + resName);
                    url = picURL.toString();
                }
            }
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("url", url);
            return buildSuccessJson(StatusConstant.SUCCESS_CODE,"上传成功", data);
        }
            return buildFailureJson(ViewData.FlagEnum.ERROR, -1,"上传失败");

    }
    
    @RequestMapping("/rrr")
    public String getuuu(){
    	return "index";
    }

}
