package org.vz.finance.integration.net.ui.modules.controller.sys;

import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.vz.finance.integration.model.util.OnlyCodeGenerator;
import org.vz.finance.integration.net.ui.core.utils.HttpCode;
import org.vz.finance.integration.net.ui.modules.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.vz.finance.integration.model.SysUser;
import org.vz.finance.integration.net.ui.core.shiro.ShiroUtils;
import org.vz.finance.integration.net.ui.core.utils.WebUtil;
import org.vz.finance.integration.net.ui.modules.vo.sys.SysLoginVo;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * 登录相关
 * 
 * @author jaden
 */
@Controller
public class SysLoginController extends BaseController {

	/**
	 * 登录
	 */
	@ResponseBody
	@PostMapping("/sys/login")
	public Object login(ModelMap modelMap, @RequestBody SysLoginVo sysLoginVo) {
		// String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		// if(!sysLoginVo.getCaptcha().equalsIgnoreCase(kaptcha)){
			// return setModelMap(modelMap, HttpCode.LOGIN_FAIL, "验证码不正确");
		// }

		try{
			Subject subject = ShiroUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(sysLoginVo.getUserName(), sysLoginVo.getPasswd());
			subject.login(token);
		} catch (UnknownAccountException e) {
			return setModelMap(modelMap, HttpCode.LOGIN_FAIL, "用户名不存在");
		} catch (IncorrectCredentialsException e) {
			return setModelMap(modelMap, HttpCode.LOGIN_FAIL, "密码有误");
		} catch (LockedAccountException e) {
			return setModelMap(modelMap, HttpCode.LOGIN_FAIL, "用户无效，请联系管理员");
		} catch (AuthenticationException e) {
			return setModelMap(modelMap, HttpCode.LOGIN_FAIL, "用户无效，请联系管理员");
		}
		
		String secureKey = OnlyCodeGenerator.sessionSecuretKey();
        SysUser sysUser = (SysUser)WebUtil.getCurrentUser();
		sysUser.setSecretKey(secureKey);
		
		WebUtil.saveCurrentUser(sysUser);
		
		return setSuccessModelMap(secureKey);
	}
	
	/**
	 * 退出
	 */
	@ResponseBody
	@RequestMapping(value = "/sys/logout", method = RequestMethod.GET)
	public Object logout(HttpServletRequest request) {
		WebUtil.removeCurrentUser(request);
		ShiroUtils.logout();
		return setSuccessModelMap(1);
	}
	
}
