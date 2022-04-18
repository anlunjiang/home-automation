package com.aj2814.homeAutomation.services.deviceRegistry.registry;

import com.aj2814.homeAutomation.services.deviceRegistry.common.markdownhtml.HtmlServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class registryController {
    private final HtmlServiceImpl htmlService;

    public registryController(HtmlServiceImpl htmlService) {
        this.htmlService = htmlService;
    }

    @GetMapping("/")
    public ModelAndView registryDashboard() {
        //String htmlContent = this.htmlService.markdownToHtml("Hello");
        ModelAndView modelAndView = new ModelAndView("registryDashboard");
        modelAndView.addObject("htmlContent", "<h1>h1 Heading</h2>");
        return modelAndView;
    }

}
