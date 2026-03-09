package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SimpleAop {
    @Pointcut("execution(* com.example.demo.board..*.*(..))")
    private void cut() {    // 포인트 컷을 적용할 이름을 설정
    }

//    @Before("execution(* com.example.demo.board..*.*(..))")   // PointCut을 안만들어두면 이렇게 설정해야 됨
    @Before("cut()") // 포인트 컷에서 지정한 위치의 클래스의 메소드가 실행되기 전에 현재 메소드 실행
    public void before(JoinPoint joinPoint) {   // JoinPoint joinPoint: 위치나 시점, 특정 클래스, 특정 메소드라는 정보
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();

        System.out.println(joinPoint.getSignature());
        System.out.println(method.getName() + "메소드 실행 전");
    }

    @After("cut()") // 포인트 컷에서 지정한 위치의 클래스의 메소드가 실행된 후에 현재 메소드 실행
    public void after(JoinPoint joinPoint) {   // JoinPoint joinPoint: 위치나 시점, 특정 클래스, 특정 메소드라는 정보
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();

        System.out.println(joinPoint.getSignature());
        System.out.println(method.getName() + "메소드 실행 후");
    }
}
