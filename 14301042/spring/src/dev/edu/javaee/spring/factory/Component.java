 package dev.edu.javaee.spring.factory;  
      
    import java.lang.annotation.ElementType;  
    import java.lang.annotation.Retention;  
    import java.lang.annotation.RetentionPolicy;  
    import java.lang.annotation.Target;  
      
    /** 
     * @Description:����ע�� 
     * @ClassName: ZxfResource 
     * @Project: spring-aop 
     * @Author: zxf 
     * @Date: 2011-6-7 
     */  
    // ������ʱִ��  
    @Retention(RetentionPolicy.RUNTIME)  
    // ע�����õط�(�ֶκͷ���)  
    @Target({ ElementType.FIELD, ElementType.METHOD })  
    public @interface Component{  
      
        //ע���name����  
        public String name() default "";  
    }  