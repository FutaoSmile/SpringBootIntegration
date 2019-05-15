package com.futao.springbootdemo.controller.business;

import com.futao.springbootdemo.annotation.LoginUser;
import com.futao.springbootdemo.annotation.Role;
import com.futao.springbootdemo.model.entity.Review;
import com.futao.springbootdemo.model.entity.SingleValueResult;
import com.futao.springbootdemo.model.enums.UserRoleEnum;
import com.futao.springbootdemo.service.ReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论操作接口
 *
 * @author futao
 * Created on 2019-03-18.
 */
@Api("评论")
@RestController
@RequestMapping(path = "review", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@ApiIgnore
public class ReviewController {

    @Resource
    private ReviewService reviewService;

    /**
     * 新增评论
     *
     * @return
     */
    @ApiOperation("新增评论")
    @PostMapping(path = "add")
    @LoginUser
    public Review add(
            @RequestParam("articleId") String articleId,
            @RequestParam("content") String content,
            @RequestParam(value = "parentReview", required = false) String parentReview
    ) {
        return reviewService.add(articleId, content, parentReview);
    }

    /**
     * 删除评论
     *
     * @param id 要删除的评论的id
     * @return
     */
    @Role(UserRoleEnum.ADMIN)
    @DeleteMapping("{id}")
    public SingleValueResult delete(
            @PathVariable("id") String id
    ) {
        reviewService.delete(id);
        return new SingleValueResult<>("");
    }

    /**
     * 更新评论
     *
     * @param id 要更新的评论的id
     * @return
     */
    @PutMapping("{id}")
    public Review update(
            @PathVariable("id") String id
    ) {
        //TODO("not implement")
        return reviewService.update(id);
    }


    /**
     * 查询评论列表
     *
     * @return
     */
    @GetMapping("list")
    public List<Review> list() {
        return reviewService.list();
    }

    /**
     * 获取评论详情
     *
     * @param id 评论id
     * @return
     */
    @GetMapping("{id}")
    public Review byId(@PathVariable("id") String id) {
        return reviewService.byId(id);
    }

}
