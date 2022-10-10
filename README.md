# StringUtils过时
1. ObjectUtils.isEmpty()
2. 引入apache commons-lang包
```xml
        <!-- StringUtils工具类 -->
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.5</version>
        </dependency>
```
```apl
		StringUtils.isBlank(params);//空判断
        StringUtils.isNotBlank(params);//非空判断
```

# 邮件发送(注册功能使用)
[SpringBoot整合邮箱](https://www.cnblogs.com/rain-me/p/16774779.html)
