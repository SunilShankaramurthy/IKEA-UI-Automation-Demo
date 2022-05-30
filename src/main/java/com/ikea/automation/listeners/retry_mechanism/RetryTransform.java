package com.ikea.automation.listeners.retry_mechanism;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
//import org.yaml.snakeyaml.constructor.Constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryTransform implements IAnnotationTransformer {
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor,
                          Method testMethod){
        annotation.setRetryAnalyzer(MyRetry.class);
    }
}
