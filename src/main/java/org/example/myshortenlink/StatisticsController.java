package org.example.myshortenlink;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {
    private final UrlService urlService;

    public StatisticsController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/statistics")
    public String statisticsPage(Model model){
        model.addAttribute("stats", urlService.getStatistics());

        return "statistics";
    }
}
