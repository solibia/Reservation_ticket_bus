package com.ensat.controllers;

import com.ensat.entities.Voyage;
import com.ensat.services.VilleService;
import com.ensat.services.VoyageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Voyage controller.
 */
@Controller
public class VoyageController {

    private VoyageService voyageService;
    private VilleService vs;
    
    @Autowired
    public void setVoyageService(VoyageService voyageService, VilleService vs) {
        this.voyageService = voyageService;
        this.vs = vs;
    }

    /**
     * List all voyages.
     *
     * @param model
     * @return
     */
    
    @RequestMapping(value = "/voyages", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("voyages", voyageService.listAllVoyages());
        return "voyages";
    }

    /**
     * View a specific voyage by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("voyage/{idvoyage}")
    public String showVoyage(@PathVariable Integer idvoyage, Model model) {
        model.addAttribute("voyage", voyageService.getVoyageById(idvoyage));
        return "voyageshow";
    }

    @RequestMapping("voyage/edit/{idvoyage}")
    public String edit(@PathVariable Integer idvoyage, Model model) {
        model.addAttribute("voyage", voyageService.getVoyageById(idvoyage));
        model.addAttribute("villes",vs.listAllVilles());
        return "voyageform";
    }

    /**
     * New voyage.
     *
     * @param model
     * @return
     */
    @RequestMapping("voyage/new")
    public String newVoyage(Model model) {
    	model.addAttribute("voyage", new Voyage());
        model.addAttribute("villes",vs.listAllVilles());
        return "voyageform";
    }

    /**
     * Save voyage to database.
     *
     * @param voyage
     * @return
     */
    
    @RequestMapping(value = "voyage", method = RequestMethod.POST)
    public String saveVoyage(Voyage voyage) {
    	voyage = voyageService.saveVoyage(voyage);
        return "redirect:/voyage/"+voyage.getIdvoyage();
    }

    /**
     * Delete voyage by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("voyage/delete/{idvoyage}")
    public String delete(@PathVariable Integer idvoyage) {
    	voyageService.deleteVoyage(idvoyage);
        return "redirect:/voyages";
    }

}
