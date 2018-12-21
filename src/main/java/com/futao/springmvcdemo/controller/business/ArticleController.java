package com.futao.springmvcdemo.controller.business;

import com.futao.springmvcdemo.model.entity.Article;
import com.futao.springmvcdemo.model.entity.SingleValueResult;
import com.futao.springmvcdemo.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author futao
 * Created on 2018/10/20.
 */
@ApiIgnore
@Api("文章")
@RestController
@RequestMapping(path = "article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**
     * 新增文章
     *
     * @param title   标题
     * @param desc    简介
     * @param content 内容
     * @return
     */
    @ApiOperation("新增文章")
    @PostMapping(path = "add")
    public SingleValueResult add(
            @RequestParam("title") String title,
            @RequestParam("desc") String desc,
            @RequestParam("content") String content
    ) {
        articleService.add(title, desc, content);
        return new SingleValueResult("success");
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
    public List<Article> search(@RequestParam("key") String key) {
        return articleService.search(key);
    }
}
