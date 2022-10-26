package kyk.practice_spring.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect  // aop를 사용하기 위함
@Component // 스프링 빈 등록
public class TimeTraceAop {

    @Around("execution(* kyk.practice_spring..*(..))")  // 적용해줄 패키지 지정 (지정된 패키지 하위까지 모두 적용)
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메서드 실행 중간에서 인터셉팅해서 푸는 것
        // 시간 측정
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed(); // Object result = joinPoint.proceed(); 인라인 형태
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
