package com.xq.controller;

import com.xq.annotation.EduSystemLogger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;

@Controller
public class CommonController {

    /**
     * 跳转到member-type.html页面
     * http://localhost:8083/member-type.html
     * @return
     */
    @RequestMapping("member-type.html")
    public String toMemberType(){
        return "member-type";
    }

    /**
     * 调整到equipment.html页面
     * @return
     */
    @RequestMapping("equipment.html")
    public String toEquipmentPage(){
        return "equipment";
    }

    /**
     * 调整到subject.html
     * @return
     */
    @RequestMapping("subject.html")
    public String toSubjectPage(){
        return "subject";
    }

    /**
     * 跳转到index.html页面
     * @return
     */
    //@RolesAllowed(value = {"ROLE_管理员","ROLE_教师","ROLE_前台"})
    //@Secured(value = {"ROLE_管理员","ROLE_教师","ROLE_前台"})
    //@PreAuthorize(value = "hasAnyRole('ROLE_管理员','ROLE_教师','ROLE_前台')")
    @EduSystemLogger(logDescription = "跳转到首页的方法",methodReturnType = "java.lang.String")
    @RequestMapping({"index.html","/"})
    public String toIndexPage(){
        return "index";
    }

    /**
     * 跳转到home.html页面
     * @return
     */
    @RequestMapping("home.html")
    public String toHomePage(){
        return "home";
    }

    /**
     * 跳转到member.html页面
     * @return
     */
    @RequestMapping("member.html")
    public String toMemberPage(){
        return "member";
    }

    /**
     * 调转到member-expire.html
     * @return
     */
    @RequestMapping("memeber-expire.html")
    public String toMemberExpirePage(){
        return "memeber-expire";
    }

    /**
     * 跳转到member-charge.html
     * @return
     */
    @RequestMapping("member-charge.html")
    public String toMemberChargePage(){
        return "member-charge";
    }

    /**
     * 跳转到member-charge-records.html页面
     * @return
     */
    @RequestMapping("member-charge-records.html")
    public String toMemberChargeRecordsPage(){
        return "member-charge-records";
    }

    /**
     * 跳转到member-card.html页面
     * @return
     */
    @RequestMapping("member-card.html")
    public String toMemberCardPage(){
        return "member-card";
    }

    /**
     * 跳转到member-cardextend-records.html页面
     * @return
     */
    @RequestMapping("member-cardextend-records.html")
    public String toCardExtendRecordsPage(){
        return "member-cardextend-records";
    }

    /**
     * 跳转到coach.html页面
     * @return
     */
    //@Secured(value = {"ROLE_管理员","ROLE_教师"})
    @RequestMapping("coach.html")
    public String toCoachPage(){
        return "coach";
    }

    /**
     * 跳转到coach-subject.html页面
     * @return
     */
    @RequestMapping("coach-subject.html")
    public String coachSubjectPage(){
        return "coach-subject";
    }

    /**
     * 跳转到goods-list.html页面
     * @return
     */
    //@RolesAllowed(value = {"ROLE_管理员","ROLE_教师"})
    //@Secured(value = {"ROLE_管理员","ROLE_教师"})
    //@PreAuthorize(value = "hasAnyRole('ROLE_管理员','ROLE_教师','ROLE_前台')")
    @EduSystemLogger(logDescription = "跳转到商品页面的方法",methodReturnType = "java.lang.String")
    @RequestMapping("goods-list.html")
    public String toGoodsListPage(){
        return "goods-list";
    }

    /**
     * 跳转到goods-sales.html页面
     * @return
     */
    @RequestMapping("goods-sales.html")
    public String toGoodsSalesPage(){
        return "goods-sales";
    }

    /**
     * 跳转到loos.html页面
     * @return
     */
    @RequestMapping("loss.html")
    public String toLostPage(){
        return "loos";
    }

    /**
     * 跳转到statistics.html页面
     * @return
     */
    @RequestMapping("statistics.html")
    public String toStatisticsPage(){
        return "statistics";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping("/login.html")
    public String toLoginPage(){
        return "login";
    }

    /**
     * 跳转到修改密码的页面
     * @return
     */
    @RequestMapping("/updpassword.html")
    public String tpUpdatePasswordPage(){
        System.out.println(100 / 0);
        return "updpassword";
    }

    /**
     * 跳转到用户管理页面
     * @return
     */
    @RequestMapping("/adminusers.html")
    public String toUsersPage(){
        return "adminusers";
    }

    /**
     * 跳转到adminroles.html页面
     * @return
     */
    @RequestMapping("/adminroles.html")
    public String toAdminRolesPage(){
        return "adminroles";
    }

    /**
     * 跳转到403页面的方法
     * @return
     */
    @RequestMapping("/403.html")
    public String to403HtmlPage(){
        return "403";
    }


    /**
     * 跳转到日志信息展示页面
     * @return
     */
    @RequestMapping("/systemlog.html")
    public String tpSystemlogPage(){
        return "systemlog";
    }

    /**
     * 跳转到404页面
     * @return
     */
    @RequestMapping("/404.html")
    public String to404HtmlPage(){
        return "404";
    }


    /**
     * 跳转到500页面
     * @return
     */
    @RequestMapping("/500.html")
    public String to500HtmlPage(){
        return "500";
    }
}
