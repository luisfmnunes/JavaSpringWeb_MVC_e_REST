package br.com.globallabs.springwebmvc.controller;

import br.com.globallabs.springwebmvc.model.Jedi;
import br.com.globallabs.springwebmvc.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class JediController {

    @Autowired
    private JediRepository repository;

    @GetMapping("/jedi")
    public ModelAndView jedi() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("jedi");
        final Jedi luke = new Jedi("Luke", "Skywalker");
        modelAndView.addObject("allJedi", repository.findAll());
        return modelAndView;
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi(){
        final ModelAndView modeAndView = new ModelAndView();
        modeAndView.setViewName("new-jedi");
        modeAndView.addObject("jedi",new Jedi());
        return modeAndView;
    }

    @PostMapping("/jedi")
    public String createJedi(@Validated @ModelAttribute Jedi jedi, BindingResult result, RedirectAttributes redirectAttributes) {

        if(result.hasErrors()) {
            return "new-jedi";
        }

        repository.save(jedi);
        redirectAttributes.addFlashAttribute("message","Jedi Cadastrado com Sucesso");

        return "redirect:jedi";
    }
}
