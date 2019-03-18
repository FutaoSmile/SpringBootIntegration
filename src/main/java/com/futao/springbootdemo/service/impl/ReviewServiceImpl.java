package com.futao.springbootdemo.service.impl;

import com.futao.springbootdemo.dao.ArticleDao;
import com.futao.springbootdemo.dao.ReviewDao;
import com.futao.springbootdemo.foundation.configuration.HibernateValidatorConfiguration;
import com.futao.springbootdemo.model.entity.Article;
import com.futao.springbootdemo.model.entity.Review;
import com.futao.springbootdemo.model.entity.User;
import com.futao.springbootdemo.model.system.SystemConfig;
import com.futao.springbootdemo.service.ReviewService;
import com.futao.springbootdemo.utils.ServiceTools;
import com.futao.springbootdemo.utils.ThreadLocalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * 评论
 *
 * @author futao
 * Created on 2019-03-18.
 */
@Transactional(isolation = Isolation.DEFAULT, timeout = SystemConfig.SERVICE_TRANSACTION_TIMEOUT_SECOND, rollbackFor = Exception.class)
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Resource
    private ArticleDao articleDao;

    @Resource
    private ThreadLocalUtils<User> threadLocal;

    /**
     * 新增评论
     *
     * @return 评论
     */
    @Override
    public Review add(String articleId, String content, String parentReviewId) {

        //评论的文章
        Article article = ServiceTools.checkResultNullAndThrow(articleDao.byId(articleId), "文章");
        //当前登录用户
        User user = threadLocal.get();
        Review parentReview = null;
        if (StringUtils.isNotBlank(parentReviewId)) {
            parentReview = ServiceTools.checkResultNullAndThrow(reviewDao.byId(parentReviewId), "父评论");
        }

        //参数封装成对象
        Review review = new Review();
        review.setArticle(article);
        review.setContent(content);
        review.setUser(user);
        review.setParentReview(parentReview);
        ServiceTools.setCreateAndLastModiftTimeNow(review);

        //参数合法性校验
        HibernateValidatorConfiguration.validate(review);
        //调用dao层
        reviewDao.add(review);
        return review;
    }

    /**
     * 删除评论
     *
     * @param id 要删除的评论主键
     * @return 评论
     */
    @Override
    public void delete(String id) {
//调用dao层
        reviewDao.delete(reviewDao.byId(id).getId());
    }

    /**
     * 修改评论
     *
     * @param id 要修改的评论主键
     * @return 评论
     */
    @Override
    public Review update(String id) {
//先查询数据是否存在
        Review review = ServiceTools.checkResultNullAndThrow(reviewDao.byId(id), "评论");
//TODO("赋用户修改之后的值")

//参数合法性校验
        HibernateValidatorConfiguration.validate(review);
//调用dao层
        reviewDao.update(review);
        return review;
    }

    /**
     * 查询评论列表
     *
     * @return 评论列表
     */
    @Override
    public List<Review> list() {
//调用dao层
        return reviewDao.list();
    }


    /**
     * 获取评论详情
     *
     * @param id 要查询的评论主键
     * @return 评论
     */
    @Override
    public Review byId(String id) {
//调用dao层
        return ServiceTools.checkResultNullAndThrow(reviewDao.byId(id), "评论");
    }

}
