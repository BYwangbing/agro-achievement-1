package org.wlgzs.agro_achievement.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.wlgzs.agro_achievement.base.BaseController;
import org.wlgzs.agro_achievement.entity.Experts;
import org.wlgzs.agro_achievement.entity.Type;
import org.wlgzs.agro_achievement.util.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 * 专家
 *
 * @author 胡亚星
 * @since 2019-01-19
 */
@RestController
@RequestMapping("/experts")
public class ExpertsController extends BaseController {

    //去成为专家页面
    @RequestMapping(value = "/toAddExperts")
    public ModelAndView toAddExperts(Model model) {
        //查询所有类型
        Result result1 = iTypeService.selectAllType();
        List<Type> typeList = (List<Type>) result1.getData();
        model.addAttribute("typeList", typeList);
        return new ModelAndView("information/addExperts");
    }

    //申请成为专家
    @RequestMapping(value = "/addExperts")
    public Result addExperts(Model model,@RequestParam(value = "file", required = false) MultipartFile myFileName,
                                   HttpServletRequest request, String time, Experts experts) throws FileNotFoundException {
        Result result = iExpertsService.addExperts(request, time, experts,myFileName);
        return result;
    }

    //查看(个人中心)专家信息
    @RequestMapping(value = "/expertsUserDetails")
    public ModelAndView expertsUserDetails(Model model, HttpServletRequest request) {
        Experts experts = iExpertsService.expertsUserDetails(request);
        if(experts != null){
            experts.setPageView(experts.getPageView() + 1);
            iExpertsService.updateById(experts);
        }
        model.addAttribute("experts", experts);//专家信息
        //查询所有类型
        Result result1 = iTypeService.selectAllType();
        List<Type> typeList = (List<Type>) result1.getData();
        model.addAttribute("typeList", typeList);
        if (experts == null) {
            model.addAttribute("msg", "请先申请成为专家！");
        } else if (experts.getStatusCode().equals("0")) {
            model.addAttribute("msg", "请您耐心等待审核！");
        } else {
            model.addAttribute("msg", "审核失败！");
        }
        return new ModelAndView("information/expertsUserDetails");
    }

}
