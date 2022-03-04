package org.launchcode.javawebdevtechjobsmvc.controllers;

import org.launchcode.javawebdevtechjobsmvc.models.Job;
import org.launchcode.javawebdevtechjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import static org.launchcode.javawebdevtechjobsmvc.controllers.ListController.columnChoices;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @GetMapping(value="results")
    public String search(Model model,
                         @RequestParam String searchTerm,
                         @RequestParam String searchType){
        ArrayList<Job> filteredJobs;

        if(searchType.equals("all")) {
            filteredJobs = JobData.findByValue(searchTerm);
        } else {
            filteredJobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobPosts", filteredJobs);

        return "search";
    }

    @RequestMapping("results")
public String displaySearchResults(Model model, @RequestParam String searchType,
                                   @RequestParam String searchTerm){
        ArrayList<Job> jobs;

        if(searchTerm.toLowerCase().equals("all") || searchTerm.equals(" "))  {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);

        return "search";
}

}
