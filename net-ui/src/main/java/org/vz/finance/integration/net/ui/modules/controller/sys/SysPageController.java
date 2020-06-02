package org.vz.finance.integration.net.ui.modules.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.vz.finance.integration.model.SysUser;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统页面视图
 * 
 * @author jaden
 */
@Controller
public class SysPageController {
	
	/*@RequestMapping("/modules/{module}/{url}")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		return module + "/" + url;
	}*/

	@RequestMapping(value = {"/", "/index.html"})
	public String index(){
		return "index";
	}

	@RequestMapping("index1.html")
	public String index1(){
		return "index1.html";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request){
        SysUser userEntity = ShiroUtils.getUserEntity();
        return userEntity == null ? "login" : "redirect:/";
	}

	@RequestMapping("main.html")
	public String main(){
		return "main.html";
	}

	@RequestMapping("/404")
	public String notFound(){
		return "404";
	}

	@RequestMapping("/forbidden")
    public String forbidden(){
	    return "forbidden";
    }

    @RequestMapping("/home")
	public String home(){
		return "/home/home";
	}
}
