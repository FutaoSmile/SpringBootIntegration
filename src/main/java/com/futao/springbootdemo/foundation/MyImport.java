package com.futao.springbootdemo.foundation;

import com.futao.springbootdemo.model.entity.Article;
import com.futao.springbootdemo.model.entity.User;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author futao
 * Created on ${date}.
 */
public class MyImport implements ImportSelector {

    public MyImport() {
        System.out.println("MyImport开始构造");
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                User.class.getName(), Article.class.getName(), EntityConfiguration.class.getName()
        };
    }
}
