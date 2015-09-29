/* 
* To change this license header, choose License Headers in Project Properties. 
* To change this template file, choose Tools | Templates 
* and open the template in the editor. 
*/ 
package com.tz.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
/**
 * 
 * 
 * MyFreeMakerView
 * 创建人:xuchengfei 
 * 时间：2015年6月9日-下午6:55:54 
 * @version 1.0.0
 *
 */
public class MyFreeMakerView extends FreeMarkerView {

    private static final String CONTEXT_PATH = "rootPath";
    private static final String currentURL = "currentURL";

    @Override 
    protected void exposeHelpers(Map model, HttpServletRequest request) throws Exception { 
        model.put(CONTEXT_PATH, request.getContextPath());
        model.put(currentURL, request.getRequestURI());
        super.exposeHelpers(model, request); 
    } 
}