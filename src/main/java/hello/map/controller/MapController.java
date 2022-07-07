package hello.map.controller;

import hello.map.entity.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MapController {

    @GetMapping("/map")
    public String showMap(Model model){
        Member member = new Member("박세헌", 0);
        model.addAttribute("member", member);
        return "/home/map";
    }


}
