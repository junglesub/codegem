package com.thc.realspr.interceptor;


import com.thc.realspr.dto.TbuserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 컨트롤러 진입 전에 호출되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle / RequestURI [{}] / Method [{}]", request.getRequestURI(), request.getMethod());

        // 리퀘스트에서 필요한 정보를 추출하여 TbuserDto의 적절한 내부 클래스를 사용
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        TbuserDto.LoginReqDto loginReqDto = TbuserDto.LoginReqDto.builder()
                .username(username)
                .password(password)
                .build();

        // 추출된 DTO 객체를 로깅
        logger.info("preHandle / LoginReqDto [{}]", loginReqDto);

        // 리퀘스트 속성에 DTO 객체를 저장하여 컨트롤러에서 사용할 수 있도록 설정
        request.setAttribute("loginReqDto", loginReqDto);

        return true;
    }

    // 컨트롤러 실행 후에 호출되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        logger.info("postHandle / Interceptor processed, Request [{}]", request);

        // DTO 객체 확인
        TbuserDto.LoginReqDto loginReqDto = (TbuserDto.LoginReqDto) request.getAttribute("loginReqDto");
        if (loginReqDto != null) {
            logger.info("postHandle / LoginReqDto [{}]", loginReqDto);
        }
    }

    // 모든 것이 완료된 후 호출되는 메서드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("afterCompletion / Request [{}]", request);

        // 예외가 발생한 경우 처리
        if (ex != null) {
            logger.error("afterCompletion / Exception [{}]", ex.getMessage());
        }
    }
}
