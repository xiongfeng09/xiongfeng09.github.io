---
layout: post
title: spring boot中validator的使用
category: java
tags: java
description: spring boot中validator的使用
---

平时项目中，难免需要对参数 进行一些参数正确性的校验，这些校验出现在业务代码中，让我们的业务代码显得臃肿，而且，频繁的编写这类参数校验代码很无聊。

#### 先看一张图让我们来看下spring boot的请求处理流程
![img](https://upload-images.jianshu.io/upload_images/4702716-793d55323dd8cd66.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/1080/format/webp)


#### 获取标记为Validated的参数 或者 以Valid打头的注解

```
protected void validateIfApplicable(WebDataBinder binder, MethodParameter parameter) {
		Annotation[] annotations = parameter.getParameterAnnotations();
		for (Annotation ann : annotations) {
			Validated validatedAnn = AnnotationUtils.getAnnotation(ann, Validated.class);
			if (validatedAnn != null || ann.annotationType().getSimpleName().startsWith("Valid")) {
				Object hints = (validatedAnn != null ? validatedAnn.value() : AnnotationUtils.getValue(ann));
				Object[] validationHints = (hints instanceof Object[] ? (Object[]) hints : new Object[] {hints});
				binder.validate(validationHints);
				break;
			}
		}
	}
```


#### 看下验证逻辑
```
public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

    parameter = parameter.nestedIfOptional();
    Object arg = readWithMessageConverters(webRequest, parameter, parameter.getNestedGenericParameterType());
    String name = Conventions.getVariableNameForParameter(parameter);

    if (binderFactory != null) {
        WebDataBinder binder = binderFactory.createBinder(webRequest, arg, name);
        if (arg != null) {
            validateIfApplicable(binder, parameter);
            if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
                throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
            }
        }
        if (mavContainer != null) {
            mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());
        }
    }

    return adaptArgumentIfNecessary(arg, parameter);
}
```

```
protected boolean isBindExceptionRequired(WebDataBinder binder, MethodParameter parameter) {
    int i = parameter.getParameterIndex();
    Class<?>[] paramTypes = parameter.getExecutable().getParameterTypes();
    boolean hasBindingResult = (paramTypes.length > (i + 1) && Errors.class.isAssignableFrom(paramTypes[i + 1]));
    return !hasBindingResult;
}
```

` if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) `

当参数校验异常下，有两种结果
* 1.被校验参数后没有绑定`Errors`类型的参数，会抛异常（这种方案需要自定义全局通用的异常处理）
* 2.被校验参数后有绑定`Errors`类型的参数，会把异常信息传递给`Errors`(方法内部决定如何异常)

#### Validated,Valid的区别
Spring Validation验证框架对参数的验证机制提供了@Validated（Spring's JSR-303规范，是标准JSR-303的一个变种），javax提供了@Valid（标准JSR-303规范），配合BindingResult可以直接提供参数验证结果。其中对于字段的特定验证注解比如@NotNull等网上到处都有，这里不详述
在检验Controller的入参是否符合规范时，使用@Validated或者@Valid在基本验证功能上没有太多区别。但是在分组、注解地方、嵌套验证等功能上两个有所不同：
##### 1. 分组
@Validated：提供了一个分组功能，可以在入参验证时，根据不同的分组采用不同的验证机制，这个网上也有资料，不详述。@Valid：作为标准JSR-303规范，还没有吸收分组的功能。

##### 2. 注解地方
* @Validated：可以用在类型、方法和方法参数上。但是不能用在成员属性（字段）上
* @Valid：可以用在方法、构造函数、方法参数和成员属性（字段）上

##### 3. @Valid可以用来标记嵌套验证

图片转载自 https://www.jianshu.com/p/e45e7550235c

