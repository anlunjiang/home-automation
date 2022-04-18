package com.aj2814.homeAutomation.services.deviceRegistry.registry;

import com.aj2814.homeAutomation.services.deviceRegistry.common.io;
import com.aj2814.homeAutomation.services.deviceRegistry.common.markdownhtml.HtmlServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class registryController {
    private final HtmlServiceImpl htmlService;

    public registryController(HtmlServiceImpl htmlService) {
        this.htmlService = htmlService;
    }

    @GetMapping("/")
    public ModelAndView registryDashboard() throws IOException {
        String markdown = io.readFile(System.getProperty("user.dir") + "/services/deviceRegistry/README.md", StandardCharsets.UTF_8);
        String htmlContent = this.htmlService.markdownToHtml(markdown);
        ModelAndView modelAndView = new ModelAndView("registryDashboard");
        modelAndView.addObject("htmlContent", htmlContent);
        return modelAndView;
    }
}
