package by.bsuir.realEstateAgency.web.controller;

import by.bsuir.realEstateAgency.core.bean.SearchForm;
import by.bsuir.realEstateAgency.core.model.TypeImmobility;
import by.bsuir.realEstateAgency.core.service.ApplicationService;
import by.bsuir.realEstateAgency.core.service.ImmobilityService;
import by.bsuir.realEstateAgency.core.service.TypeApplicationService;
import by.bsuir.realEstateAgency.web.bean.immobility.ImmobilityDto;
import by.bsuir.realEstateAgency.web.exceptions.BadRequestException;
import by.bsuir.realEstateAgency.web.exceptions.NotFoundException;
import by.bsuir.realEstateAgency.web.facade.ImmobilityFacade;
import by.bsuir.realEstateAgency.web.service.page.PageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    static Logger log = Logger.getLogger(CatalogController.class.getName());

    private static final String PAGE_NUMBER_REQUEST_PARAM = "page";

    private static final String PAGED_LIST_ATTRIBUTE = "pagedList";

    private static final String TYPE_APPLICATIONS_ATTRIBUTE = "typeApplications";

    private static final String PAGINATION_PARAMS_ATTRIBUTE = "paginationParams";

    private static final String IMMOBILITY_DTO_ATTRIBUTE = "immobilityDto";

    @Resource
    private ImmobilityService immobilityService;

    @Resource
    private PageService pageService;

    @Resource
    private TypeApplicationService typeApplicationService;

    @Resource
    private ImmobilityFacade immobilityFacade;

    @GetMapping
    public String getCatalog(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                            @ModelAttribute("sf") SearchForm searchForm, Model model){
        model.addAttribute(PAGED_LIST_ATTRIBUTE, pageService.getPagedList(pageNumber, immobilityService, searchForm));
        model.addAttribute(TYPE_APPLICATIONS_ATTRIBUTE, typeApplicationService.getAll());
        model.addAttribute(PAGINATION_PARAMS_ATTRIBUTE, getPaginationString(searchForm));
        return "catalogList";
    }

    @GetMapping("/{id}")
    public String getImmobility(@PathVariable("id") long id, Model model) {
        ImmobilityDto immobilityDto = immobilityFacade.getImmobility(id);
        if (immobilityDto == null) {
            log.error("Immobility not found. HTTP404");
            throw new NotFoundException();
        }
        model.addAttribute(IMMOBILITY_DTO_ATTRIBUTE, immobilityDto);
        return "catalogDetails";
    }

    @GetMapping(value = "/{id}", params = "inspection")
    public void getInspectionForm(){

    }

    @PostMapping(value = "/inspection/{id}")
    public void saveInspectionForm(){

    }

    private String getPaginationString(SearchForm searchForm){
        StringBuilder sb = new StringBuilder();
        if( searchForm.getCityName()!= null){
            sb.append("&cityName=");
            sb.append(searchForm.getCityName());
        }
        if(searchForm.getTypeImmobility() != null){
            sb.append("&typeImmobility=");
            sb.append(searchForm.getTypeImmobility().name());
        }
        if(searchForm.getTypeApplication() != null){
            sb.append("&typeApplication=");
            sb.append(searchForm.getTypeApplication());
        }
        if(searchForm.getMinPrice() != null){
            sb.append("&minPrice=");
            sb.append(searchForm.getMinPrice());
        }
        if(searchForm.getMaxPrice() != null){
            sb.append("&maxPrice=");
            sb.append(searchForm.getMaxPrice());
        }
        return sb.toString();
    }
}
