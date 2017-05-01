package com.qheeshow.eway.service.service.impl;

import com.google.zxing.WriterException;
import com.qheeshow.eway.common.util.Config;
import com.qheeshow.eway.common.util.StrUtil;
import com.qheeshow.eway.service.dao.ActivityMapper;
import com.qheeshow.eway.service.dao.GoodsMapper;
import com.qheeshow.eway.service.model.Activity;
import com.qheeshow.eway.service.model.ActivityExample;
import com.qheeshow.eway.service.model.Goods;
import com.qheeshow.eway.service.service.ActivityService;
import com.qheeshow.eway.service.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * Created by lihuajun on 2017/3/24.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private PayService payService;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Activity> listByCodition(Activity activity, Integer pageIndex, Integer pageSize) {
        activity.setPageSize(pageSize);
        activity.setStartRow((pageIndex - 1) * activity.getPageSize());
        return activityMapper.listByCondition(activity);
    }

    @Override
    public List<Activity> listByClass(Integer cls) {

        List<Integer> list = new ArrayList<>();
        list.add(30);

        ActivityExample example = new ActivityExample();
        ActivityExample.Criteria criteria = example.createCriteria();
        criteria.andActivityClassEqualTo(cls);
        criteria.andDocStatusNotIn(list);
        criteria.andIsHeadEqualTo(0);

        return activityMapper.selectByExample(example);
    }

    @Override
    public Map<String, Object> listByClassAndPage(Activity activity) {
        Map<String, Object> map = new HashMap<>();
        List<Activity> activities = activityMapper.listByClassAndPage(activity);
        map.put("activities", activities);
        map.put("count", activityMapper.countByClassAndPage(activity));
        return map;
    }

    @Override
    public Activity get(Integer id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public void save(Activity activity) throws IOException, WriterException {
        //添加一个新商品
        Goods goods = new Goods();
        goods.setTitle("报名费-" + activity.getTitle());
        goods.setPrice(activity.getCost());
        goods.setGtype(2);
        goodsMapper.insert(goods);
        //生成支付二维码
        Map<String, String> params = new TreeMap<>();
        params.put("appid", Config.get("wechat.appid"));
        params.put("mch_id", Config.get("wechat.mchid"));
        params.put("product_id", String.valueOf(goods.getId()));
        params.put("nonce_str", StrUtil.getRandomString(32));
        params.put("time_stamp", String.valueOf(System.currentTimeMillis() / 1000));
        params.put("sign", StrUtil.sign(params));
        String url = StrUtil.map2Url("weixin://wxpay/bizpayurl", params);
        String qrcodeurl = payService.createWechatORCode(url, "actcode");
        //保存活动
        activity.setQrcode(qrcodeurl);
        if (activity.getId() == null) {
            activityMapper.insert(activity);
        } else {
            activityMapper.updateByPrimaryKeySelective(activity);
        }
    }

    /**
     * 首页最新活动
     *
     * @return
     */
    @Override
    public List<Activity> latest(Integer num) {
        return activityMapper.latest(num);
    }

}
