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



    @GetMapping("/mesas/new")
    public String nuevo(Model model, @ModelAttribute("mesas") Mesas mesas) {

        return "mesas/frm";
    }

    @GetMapping("/mesas/edit")
    public String editar(Model model, @RequestParam("id") int id, @ModelAttribute("mesas") Mesas mesas) {

        Optional<Mesas> optional = mesasRepository.findById(id);

        if (optional.isPresent()) {
            mesas = optional.get();
            model.addAttribute("mesas", optional.get());
            return "mesas/frm";
        } else {
            return "redirect:/mesas/list";
        }
    }

    @PostMapping("/mesas/save")
    public String guardar(@ModelAttribute("mesas") @Valid Mesas mesas, BindingResult bindingResult,
                                   RedirectAttributes attr,
                                   Model model) {

        if(bindingResult.hasErrors()){

            return "mesas/frm";
        }else {

            if (mesas.getId() == 0) {
                attr.addFlashAttribute("msg", "Mesa creada exitosamente");

                mesasRepository.save(mesas);

                return "redirect:/mesas/list";
            } else {


                mesasRepository.save(mesas);
                attr.addFlashAttribute("msg", "Mesa actualizada exitosamente");
                return "redirect:/mesas/list";
            }
        }
    }

    @GetMapping(value = {"/mesas/delete"})
    public String borrar(@RequestParam("id") Integer id){
        Optional<Mesas> opt = mesasRepository.findById(id);
        if(opt.isPresent()){
            mesasRepository.deleteById(id);
        }
        return "redirect:/mesas/list";
    }



}