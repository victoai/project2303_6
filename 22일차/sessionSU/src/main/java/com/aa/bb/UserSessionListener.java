package com.aa.bb;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    	
    	System.out.println("dkdkdkkdfdf");
        // 세션이 생성될 때 호출되는 메서드
        updateLoggedInUserCount(se);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 세션이 소멸될 때 호출되는 메서드
        updateLoggedInUserCount(se);
        System.out.println("------");
    }

    private void updateLoggedInUserCount(HttpSessionEvent se) {
        // 세션에서 현재 사용자 수를 가져옴
        Integer userCount = (Integer) se.getSession().getServletContext().getAttribute("userCount");

        // 사용자 수가 없으면 0으로 초기화
        if (userCount == null) {
            userCount = 0;
        }

        // 세션의 생성 또는 소멸에 따라 사용자 수 업데이트
        if (se.getSession().isNew()) {
            userCount++;
        } else {
            userCount--;
        }

        // 업데이트된 사용자 수를 서블릿 컨텍스트에 저장
        se.getSession().getServletContext().setAttribute("userCount", userCount);
    }
}
