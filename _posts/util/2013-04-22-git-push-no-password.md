---
layout: post
title: 让git push命令不再需要密码
category: util
tags: Git
description: 使用git push命令远程提交库时，总是要输入帐号和密码，这样特别麻烦，找了一个方法解决掉
---


GitHub获得远程库时，有ssh方式和https方式。


两个方式的url地址不同，认证方式也不同。使用ssh时保存密钥对以后可以不再输入帐号密码，而https却不能。所以如果想要不再输入帐号密码，一种方式就是在git clone的时候使用ssh方式，另一种方式就是去修改已有项目.git目录下的config文件中的url，如下：

    [remote "origin"]
        url = git@github.com:xiongfeng165/xiongfeng165.github.io.git
        fetch = +refs/heads/*:refs/remotes/origin/*



