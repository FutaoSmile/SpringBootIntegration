package com.futao.springbootdemo.controller.business;

import com.futao.springbootdemo.annotation.LoginUser;
import com.futao.springbootdemo.model.entity.Article;
import com.futao.springbootdemo.service.ArticleService;
import com.futao.springbootdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章操作接口
 *
 * @author futao
 * Created on 2018/10/20.
 */
@Api("文章")
@RestController
@RequestMapping(path = "article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {
    @Resource
    private ArticleService articleService;
    @Resource
    private UserService userService;

    /**
     * 新增文章
     *
     * @param title   标题
     * @param desc    简介
     * @param content 内容
     * @return
     */
    @LoginUser
    @ApiOperation("新增文章")
    @PostMapping(path = "add")
    public Article add(
            @RequestParam("title") String title,
            @RequestParam("desc") String desc,
            @RequestParam("content") String content
    ) {
        return articleService.add(title, desc, content, userService.currentLoginUser());
    }

    /**
     * 获取文章详情
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Article get(@PathVariable("id") String id) {
        return articleService.getById(id);
    }


    /**
     * 文章列表
     *
     * @return
     */
    @ApiOperation("文章列表")
    @GetMapping("list")
    public List<Article> list() {
        return articleService.list();
    }

    /**
     * 全文检索
     *
     * @param key 关键字
     * @return
     */
    @ApiOperation("全文检索")
    @GetMapping("search")
    public List<Article> search(@RequestParam("key") String key,
                                @RequestParam("from") int fromRange,
                                @RequestParam("to") int toRange,
                                @RequestParam("from") int from,
                                @RequestParam("size") int size
    ) {
        return articleService.search(key, fromRange, toRange, size, from);
    }

    /**
     * 我发表的文章列表
     *
     * @return
     */
    @ApiOperation("我发表的文章列表")
    @GetMapping("my")
    @LoginUser
    public List<Article> my() {
        return articleService.my();
    }
}
