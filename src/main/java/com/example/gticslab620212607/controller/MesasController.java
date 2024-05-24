package com.example.gticslab620212607.controller;

import com.example.gticslab620212607.entity.Mesas;
import com.example.gticslab620212607.entity.Rol;
import com.example.gticslab620212607.repository.MesasRepository;
import com.example.gticslab620212607.repository.ReservasRepository;
import com.example.gticslab620212607.repository.RolRepository;
import com.example.gticslab620212607.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MesasController {

    @Autowired
    MesasRepository mesasRepository;
    @Autowired
    ReservasRepository reservasRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping(value = {"/mesas/list"})
    public String principal(Model model){

        List<Mesas> listaMesas= mesasRepository.findAll();

        model.addAttribute("listaMesas",listaMesas);
        return "mesas/list";
    }

    /*

    @GetMapping("/personaje/new")
    public String nuevoPersonaje(Model model, @ModelAttribute("personaje") Characters personaje) {

        return "personajes/frm";
    }

    @GetMapping("/personaje/edit")
    public String editarPersonaje(Model model, @RequestParam("id") int id, @ModelAttribute("personaje") Characters personaje) {

        Optional<Characters> optional = charactersRepository.findById(id);

        if (optional.isPresent()) {
            personaje = optional.get();
            model.addAttribute("personaje", optional.get());
            return "personajes/frm";
        } else {
            return "redirect:/personaje/list";
        }
    }

    @PostMapping("/personaje/save")
    public String guardarPersonaje(@ModelAttribute("personaje") @Valid Characters personaje, BindingResult bindingResult,
                                   RedirectAttributes attr,
                                   Model model) {

        if(bindingResult.hasErrors()){

            return "personajes/frm";
        }else {

            if (personaje.getId() == 0) {
                attr.addFlashAttribute("msg", "Personaje creado exitosamente");

                charactersRepository.save(personaje);

                return "redirect:/personaje/list";
            } else {


                charactersRepository.save(personaje);
                attr.addFlashAttribute("msg", "Personaje actualizado exitosamente");
                return "redirect:/personaje/list";
            }
        }
    }

    @GetMapping(value = {"/personaje/delete"})
    public String borrarPersonaje(@RequestParam("id") Integer id){
        Optional<Characters> opt = charactersRepository.findById(id);
        if(opt.isPresent()){
            charactersRepository.deleteById(id);
        }
        return "redirect:/personaje/list";
    }

    @PostMapping(value = "/personaje/buscar")
    public String buscarPersonaje(@RequestParam("palabra") String palabra,Model model){

        return "redirect:/personaje/list?palabra="+palabra;
    }

*/

}