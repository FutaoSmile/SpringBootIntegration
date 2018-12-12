package com.futao.springmvcdemo.controller.business;

import com.futao.springmvcdemo.model.entity.Article;
import com.futao.springmvcdemo.model.entity.SingleValueResult;
import com.futao.springmvcdemo.service.ArticleService;
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
@RestController
@RequestMapping(path = "article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**
     * 新增文章
     *
     * @param title
     * @param desc
     * @param content
     * @return
     */
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
    @GetMapping("list")
    public List<Article> list() {
        return articleService.list();
    }

    @GetMapping("search")
    public List<Article> search(@RequestParam("key") String key) {
        return articleService.search(key);
    }
}
