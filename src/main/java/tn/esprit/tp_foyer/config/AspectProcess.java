package tn.esprit.tp_foyer.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j

public class AspectProcess {

    @Before("execution (* tn.esprit.tp_foyer.service.FoyerServiceImpl.*(...))")
    public void logMethodEntry(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("**** In method" + name + ":");
    }

    //log aprés l'execution de chaque methode du service FoyerServiceImpl
    //Pour afficher cet message "**** Exiting methode nomMethod
    @After("execution (* tn.esprit.tp_foyer.service.FoyerServiceImpl.*(...))")
    public void logMethodExit(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        log.info("**** Exiting methode " + name );
    }

    //Profilage des methodes de la couche service
    @Around("execution (* tn.esprit.tp_foyer.service.FoyerServiceImpl.*(...))")
    public Object Profile(ProceedingJoinPoint pjp) throws Throwable {
        Long start = System.currentTimeMillis();
        try {
            //Execution de la methode cible
            Object obj = pjp.proceed();
            return obj;
        } finally {
            Long elapsedTime = System.currentTimeMillis() - start;
            String methodeName = pjp.getSignature().getName();
            log.info("Method [{}] execution time : {} ms", methodeName, elapsedTime);
        }
    }
}
