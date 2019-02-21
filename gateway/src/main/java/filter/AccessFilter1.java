package filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter1 extends ZuulFilter {

    private static Logger logger      = LoggerFactory.getLogger(AccessFilter1.class);

    private AntPathMatcher pathMatcher = new AntPathMatcher();
//    private TokenCache     tokenCache;
//    @Autowired
//    TokenUtils             tokenUtils;

    private String[]       acceptUrls  = {};
    private String[]       deniedUrls  = {};

    public String[] getAcceptUrls() {
        return acceptUrls;
    }

    public void setAcceptUrls(String[] acceptUrls) {
        this.acceptUrls = acceptUrls;
    }

    public String[] getDeniedUrls() {
        return deniedUrls;
    }

    public void setDeniedUrls(String[] deniedUrls) {
        this.deniedUrls = deniedUrls;
    }

//    public TokenCache getTokenCache() {
//        return tokenCache;
//    }
//
//    public void setTokenCache(TokenCache tokenCache) {
//        this.tokenCache = tokenCache;
//    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String prefilter02 = request.getParameter("prefilter02");
        System.out.println("执行pre02Filter .....prefilter02=" + prefilter02 	);

        //如果用户名和密码都正确，则继续执行下一个filter
        if("true".equals(prefilter02) ){
            ctx.setSendZuulResponse(true);//会进行路由，也就是会调用api服务提供者
            ctx.setResponseStatusCode(200);
            ctx.set("isOK",true);//可以把一些值放到ctx中，便于后面的filter获取使用
        }else{
            ctx.setSendZuulResponse(false);//不需要进行路由，也就是不会调用api服务提供者
            ctx.setResponseStatusCode(401);
            ctx.set("isOK",false);//可以把一些值放到ctx中，便于后面的filter获取使用
            //返回内容给客户端
            ctx.setResponseBody("{\"result\":\"pre02Filter auth not correct!\"}");// 返回错误内容
        }



        return null;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

}
