package by.bsuir.realEstateAgency.web.controller.panel;

import by.bsuir.realEstateAgency.core.exception.LinkedObjectDeletingException;
import by.bsuir.realEstateAgency.core.exception.ValueNotUniqueException;
import by.bsuir.realEstateAgency.core.service.TypeApplicationService;
import by.bsuir.realEstateAgency.web.bean.application.TypeApplicationDto;
import by.bsuir.realEstateAgency.web.exceptions.NotFoundException;
import by.bsuir.realEstateAgency.web.facade.TypeApplicationFacade;
import by.bsuir.realEstateAgency.web.service.page.PageService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping("/panel/typeApplications")
public class TypeApplicationController {
    static Logger log = Logger.getLogger(ImmobilityController.class.getName());

    private static final String PAGE_NUMBER_REQUEST_PARAM = "page";

    private static final String PAGED_LIST_ATTRIBUTE = "pagedList";

    private static final String CREATE_ATTRIBUTE = "create";

    private static final String TYPE_APPLICATION_DTO_ATTRIBUTE = "typeApplicationDto";

    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";

    @Resource
    private TypeApplicationService typeApplicationService;

    @Resource
    private TypeApplicationFacade typeApplicationFacade;

    @Resource
    private PageService pageService;

    @GetMapping
    private String getTypeApplications(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                                       @RequestParam(name = "count", defaultValue = "10") int countOnPage,
                                       Model model) {
        model.addAttribute(PAGED_LIST_ATTRIBUTE, pageService.getPagedList(pageNumber, countOnPage, typeApplicationService));
        return "typeApplicationList";
    }

    @GetMapping("/new")
    public String getNewForm(Model model) {
        model.addAttribute(CREATE_ATTRIBUTE, true);
        model.addAttribute(TYPE_APPLICATION_DTO_ATTRIBUTE, new TypeApplicationDto());
        return "typeApplicationDetails";
    }

    @PostMapping("/new")
    public String createTypeApplication(@Valid TypeApplicationDto typeApplicationDto, BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(CREATE_ATTRIBUTE, true);
            return "typeApplicationDetails";
        }
        try {
            typeApplicationFacade.saveOrUpdate(typeApplicationDto);
        } catch (ValueNotUniqueException e) {
            bingErrorNotUnqiue(bindingResult);
            return "typeApplicationDetails";
        }
        return "redirect:/panel/typeApplications";
    }

    @GetMapping("/{id}")
    public String getTypeApplication(@PathVariable("id") long id, Model model) {
        TypeApplicationDto typeApplicationDto = typeApplicationFacade.getTypeApplication(id);
        if (typeApplicationDto == null) {
            log.error("typeNotFound not found. HTTP404");
            throw new NotFoundException();
        }
        model.addAttribute(TYPE_APPLICATION_DTO_ATTRIBUTE, typeApplicationDto);
        return "typeApplicationDetails";
    }

    @PostMapping(value = "/{id}", params = "save")
    public String updateTypeApplication(@PathVariable("id") long id,
                                        @Valid TypeApplicationDto typeApplicationDto, BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            return "typeApplicationDetails";
        }
        try {
            typeApplicationFacade.saveOrUpdate(typeApplicationDto);
        } catch (ValueNotUniqueException e) {
            bingErrorNotUnqiue(bindingResult);
            return "typeApplicationDetails";
        }
        return "redirect:/panel/typeApplications";
    }

    @PostMapping(value = "/{id}", params = "remove")
    public String removeTypeApplication(@PathVariable("id") long id, Model model) {
        try {
            typeApplicationService.remove(id);
        } catch (LinkedObjectDeletingException e) {
            log.error("Catch LinkedObjectDeletingException exception");
            TypeApplicationDto typeApplicationDto = typeApplicationFacade.getTypeApplication(id);
            model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, "Trying delete linked type");
            model.addAttribute(TYPE_APPLICATION_DTO_ATTRIBUTE, typeApplicationDto);
            return "typeApplicationDetails";
        }
        return "redirect:/panel/typeApplications";
    }

    private void bingErrorNotUnqiue(BindingResult bindingResult) {
        bindingResult.addError(new FieldError("typeApplicationDto", "name", null,
                false, new String[]{"NotUnique.typeApplicationDto.name"}, null,
                "value not unique"));
    }
}
