package org.wlgzs.agro_achievement.service;

import org.wlgzs.agro_achievement.entity.Demand;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wlgzs.agro_achievement.util.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡亚星
 * @since 2019-01-19
 */
public interface IDemandService extends IService<Demand> {

    //发布需求
    Result addDemand(Demand demand);

    //删除需求
    Result deleteDemand(Integer demandId);

    //修改需求
    Result modifyDemand(Demand demand,String time);

    //按照用户查询所有需求（状态码）
    Result selectDemand(Integer userId, String statusCode, int current, int limit);

    //查看需求详情
    Result demandDetails(Integer demandId);

    /**
     * 管理员
     */
    //搜索所有需求
    Result adminDemandList(String findName,int current, int limit);

    //添加需求
    Result saveDemand(Demand demand,String time);

    //按需求状态查询需求
    Result selectDemandByCode(String statusCode,int current,int limit);

}
