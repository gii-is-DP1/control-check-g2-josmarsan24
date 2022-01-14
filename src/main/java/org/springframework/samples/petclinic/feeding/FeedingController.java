package org.springframework.samples.petclinic.feeding;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {

    private static final String FORM = "feedings/createOrUpdateFeedingForm";
    
    private final FeedingService feedingService;
    private final PetService petService;

    @Autowired
    public FeedingController(FeedingService feedingService,PetService petService){
        this.feedingService = feedingService;
        this.petService = petService;
    }

    @GetMapping(value = "/create")
    public String initCreationForm(ModelMap modelMap){
        Feeding f = new Feeding();
        modelMap.put("feeding",f);
        return FORM;
    }

    @PostMapping(value = "/new")
    public String processCreationForm(@Valid Feeding f, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            model.put("feeding", f);
            return FORM;
        } else {
            try {
                this.feedingService.save(f);
            } catch (UnfeasibleFeedingException ex){
                result.rejectValue("pet", "not valid");
                return FORM;
            }
            return "redirect:/welcome";
        }
    }


    
}
