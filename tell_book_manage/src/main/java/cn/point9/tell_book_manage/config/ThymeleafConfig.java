package cn.point9.tell_book_manage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;


/**
 * Created by limengwei on 2019-08-27
 * 1、ThymeleafViewResolver 接收逻辑视图名称将它解析为视图
 * 2、SpringTemplateEngine会在Spring中启用Thymeleaf引擎，用来解析模板，并基于这些模板渲染结果
 * 3、TemplateResolver会最终定位和查找模板。
 **/
@Configuration
public class ThymeleafConfig {

    /**
     * 配置 Thymeleaf 视图解析器 —— 将逻辑视图名称解析为 Thymeleaf 模板视图
     *
     * @param springTemplateEngine 模板引擎
     * @return
     */
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine springTemplateEngine){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(springTemplateEngine);
        return resolver;
    }

    /**
     * 模板引擎 —— 处理模板并渲染结果
     *
     * @param templateResolver 模板解析器
     * @return
     */
    @Bean
    public SpringTemplateEngine springTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver);
        return springTemplateEngine;
    }

    /**
     * 模板解析器 —— 加载 Thymeleaf 模板
     *
     * @return
     */
    @Bean
    public ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }
}
