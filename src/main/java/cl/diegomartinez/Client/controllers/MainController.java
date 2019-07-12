package cl.diegomartinez.Client.controllers;
import cl.diegomartinez.Client.*;
import cl.vicenterivera.SOALib.JsonMapper;
import cl.vicenterivera.SOALib.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cl.diegomartinez.Client.Chat;

import java.sql.Timestamp;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private ChatClient busConnection;

    @GetMapping("/")
    public String main() { return "redirect:/chat";
    }

    @GetMapping("/chat")
    public String chat(Model model) {


        //Chat[] elements = new String[] { "Hola", "Como estas?", "jiji xd", "contesta mierda" };
        String Json=busConnection.request("juCha", "get_messages");
        boolean ok =!JsonMapper.jsonAsMap(Json).containsKey("error");
        if (!ok){
            System.out.println(JsonMapper.jsonAsMap(Json).get("error").toString());
        }

        Chat[] chat = ok ? JsonMapper.fromJsonString(Json, Chat[].class):null ;
        //Chat item = new Chat();
        //item.setMsj("hola");
        //item.setId_usr1(1L);
        //item.setId_usr2(2L);
        //item.setTimestamp(new Timestamp(System.currentTimeMillis()));
        //Chat[] chat = new Chat[] {item};

        //model.addAttribute("games", elements != null ? String.join(", ", elements) : "null");
        model.addAttribute("messages",chat);

        //model.addAttribute("message", busConnection.request("juCha", "get_messages"));

        return "chat";
    }

    @PostMapping("/newmessage")
    @ResponseBody
    public void addMessage(@RequestBody MessageDto message) {

        message.setId_usr1(1L);
        message.setId_usr2(2L);
        String var =JsonMapper.asJsonString(message);
        System.out.println(message.getId_usr1());
        System.out.println(message.getContent());

        String Json=busConnection.request("juCha", "send_message:"+var);




    }

    @ModelAttribute("sessionUser")
    public User getSessionUser() {
        return new User();
    }
    @GetMapping("/login")
    public String loginGet(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("sessionUser") User sessionUser, @RequestParam("email") String email, Model model) {
        deleteSessionUser(model);

        String response = RequestBuilder
                .withJsonParameters("juUse", "get_user").addParameter("email", email)
                .send(busConnection);
        if (response == null) {
            return "redirect:/login?error";
        }

        Map<String, Object> map = JsonMapper.jsonAsMap(response);

        if (map.containsKey("error")) {
            System.out.println(map.get("error"));
            return "redirect:/login?error";
        }

        User user = JsonMapper.fromJsonString(response, User.class);
        if (user == null) {
            return "redirect:/login?not_found";
        }

        sessionUser = user;
        sessionUser.setUsername(user.getUsername());
        model.addAttribute("sessionUser", sessionUser);

        return "redirect:/chat";
    }
    private void deleteSessionUser(Model model) {
        model.addAttribute("sessionUser", null);
    }


}
