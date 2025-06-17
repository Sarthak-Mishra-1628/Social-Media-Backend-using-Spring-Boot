package com.project.project.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.project.Entity.User;
import com.project.project.Payload.AnalyticsResponse;
import com.project.project.Service.Analytics.AnalyticsService;
import com.project.project.Service.User.UserService;

@RestController
public class AnalyticController {

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private UserService userService;

    @GetMapping("/premium/user/analytics")
    public AnalyticsResponse getAnalytics(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserbyJWT(jwt);
        return analyticsService.getAnalyticsResponse(user.getId());
    } 

}