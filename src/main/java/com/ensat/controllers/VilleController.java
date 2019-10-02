package com.ensat.controllers;

import com.ensat.entities.Ville;
//import com.ensat.repositories.VilleRepository;
import com.ensat.services.VilleService;
//import com.ensat.services.VilleServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Ville controller.
 */

@Controller
public class VilleController {
	
	@Autowired
	private VilleService villeService;

    public VilleController(VilleService villeService) {
		this.villeService = villeService;
	}

	/**
     * List all villes.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/villes", method = RequestMethod.GET)
    public String list(Model model) {
    	try{
    		model.addAttribute("villes", villeService.listAllVilles());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return "villes";
    }

    /**
     * View a specific ville by its id.
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("ville/{id}")
    public String showVille(@PathVariable Integer id, Model model) {
        model.addAttribute("ville", villeService.getVilleById(id));
        return "villeshow";
    }

    @RequestMapping("ville/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ville", villeService.getVilleById(id));
        return "villeform";
    }

    /**
     * New product.
     *
     * @param model
     * @return
     */
    @RequestMapping("ville/new")
    public String newVille(Model model) {
        model.addAttribute("ville", new Ville());
        return "villeform";
    }

    /**
     * Save product to database.
     *
     * @param ville
     * @return
     */
    @RequestMapping(value = "ville", method = RequestMethod.POST)
    public String saveVille(Ville ville) {
    	ville = villeService.saveVille(ville);    	
        return "redirect:/ville/" + ville.getId();
    }

    /**
     * Delete product by its id.
     *
     * @param id
     * @return
     */
    @RequestMapping("ville/delete/{id}")
    public String delete(@PathVariable Integer id) {
        villeService.deleteVille(id);
        return "redirect:/villes";
    }
}
