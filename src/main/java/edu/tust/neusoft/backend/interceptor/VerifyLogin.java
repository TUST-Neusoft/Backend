package edu.tust.neusoft.backend.interceptor;

import com.alibaba.fastjson2.JSON;
import edu.tust.neusoft.backend.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.StringRedisTemplate;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class VerifyLogin implements LoginInterceptor {
    @Resource
    private StringRedisTemplate redis;
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        return true;
//        String corpId_userId = "";
//        String token = "";
//        try {
//            for (Cookie ck : request.getCookies()) {
//                if (Objects.equals(ck.getName(), "corpId_userId")) {
//                    corpId_userId = ck.getValue();
//                } else if (Objects.equals(ck.getName(), "token")) {
//                    token = ck.getValue();
//                }
//            }
//            log.info("Verify,corpId_userId:" + corpId_userId + ",token:" + token);
//            if (isLogin(corpId_userId, token)) {
//                return true;
//            } else {
//                response.setContentType("application/json;charset=UTF-8");
//                Result result = new Result();
//                result.setCode(-2);
//                result.setMsg("验证错误");
//                response.getWriter().println(JSON.toJSONString(result));
//                return false;
//            }
//        } catch (Exception e) {
//            response.setContentType("application/json;charset=UTF-8");
//            Result result = new Result();
//            result.setCode(-3);
//            result.setMsg("其他异常");
//            response.getWriter().println(JSON.toJSONString(result));
//            return false;
//        }
    }

    public boolean isLogin(String corpId_userId, String token) {
        String r = redis.opsForValue().get(corpId_userId);
        log.info("Verify login,redis result:" + r);
        // 在这里根据业务逻辑检查用户登录状态和team是否匹配，这里只是一个简单的示例
        return Objects.equals(r, token);
    }
}