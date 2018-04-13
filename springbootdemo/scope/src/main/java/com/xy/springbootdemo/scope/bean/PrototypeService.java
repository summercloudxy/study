package com.xy.springbootdemo.scope.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
//每次调用的时候新建一个Bean的实例
public class PrototypeService {
}
