package com.example.controller;

import org.apache.catalina.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/")
public class InsertAdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    /**
     * 管理者登録画面の表示
     * 
     * @param insertAdministratorForm
     * @return
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm insertAdministratorForm) {

        return "/administrator/insert";
    }

    /**
     * 管理者登録処理
     * 
     * @param insertAdministratorForm
     * @return
     */
    @PostMapping("/insert")
    public String inset(InsertAdministratorForm insertAdministratorForm) {

        Administrator administrator = new Administrator();

        administrator.setId(administrator.getId());
        administrator.setName(insertAdministratorForm.getName());
        administrator.setMailAddress(insertAdministratorForm.getMailAddress());
        administrator.setPassword(insertAdministratorForm.getPassword());

        BeanUtils.copyProperties(insertAdministratorForm, administrator);
        administratorService.insert(administrator);

        return "redirect:/";
    }

    /**
     * ログイン画面の表示
     * 
     * @param loginForm
     * @return
     */
    @GetMapping("/")
    public String toLogin(LoginForm loginForm) {

        return "/administrator/login";
    }

    /**
     * ログイン処理
     * 
     * @param loginForm
     * @param model
     * @return
     */
    @PostMapping("/login")
    public String login(LoginForm loginForm, Model model) {

        Administrator administrator = administratorService.login(loginForm.getMailAddress(), loginForm.getPassword());

        if (administrator == null) {

            model.addAttribute("message", "メールアドレスまたはパスワードが間違っています");

            // return toLogin(loginForm);
            return "/administrator/login";
        } else {

            session.setAttribute("administratorName", administrator.getName());
            return "redirect:/employee/showList";
        }

    }

    /**
     * ログアウト処理.
     * @param loginForm
     * @return
     */
    @GetMapping("/logout")
    public String logout(LoginForm loginForm) {

    session.invalidate();
        return "redirect:/";
    }

}
