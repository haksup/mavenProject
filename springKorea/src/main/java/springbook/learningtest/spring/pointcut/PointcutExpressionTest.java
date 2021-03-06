package springbook.learningtest.spring.pointcut;

import org.junit.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
public class PointcutExpressionTest {
	
	@Test
	public void methodSignaturePointcut() throws Exception{
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//		pointcut.setExpression("execution(public int springbook.learningtest.spring.pointcut.Target.minus(int,int)"
//				+ " throws java.lang.RuntimeException)");
		pointcut.setExpression("execution(* *(..))");
		
		assertThat(pointcut.getClassFilter().matches(Target.class) 
				&& pointcut.getMethodMatcher().matches(Target.class.getMethod("minus", int.class,  int.class), null), is(true));

		assertThat(pointcut.getClassFilter().matches(Target.class) 
				&& pointcut.getMethodMatcher().matches(Target.class.getMethod("plus", int.class,  int.class), null), is(true));

		assertThat(pointcut.getClassFilter().matches(Target.class) 
				&& pointcut.getMethodMatcher().matches(Target.class.getMethod("method"), null), is(true));
		
		
	}
}
