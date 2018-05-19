package by.bsuir.realEstateAgency.web.controller;

import by.bsuir.realEstateAgency.core.model.Admin;
import by.bsuir.realEstateAgency.core.service.DealService;
import by.bsuir.realEstateAgency.web.bean.DealDto;
import by.bsuir.realEstateAgency.web.bean.pagedList.CheckedItem;
import by.bsuir.realEstateAgency.web.bean.pagedList.CheckedList;
import by.bsuir.realEstateAgency.web.exceptions.AccessDeniedException;
import by.bsuir.realEstateAgency.web.exceptions.BadRequestException;
import by.bsuir.realEstateAgency.web.exceptions.NotFoundException;
import by.bsuir.realEstateAgency.web.facade.DealFacade;
import by.bsuir.realEstateAgency.web.security.AuthUserDetails;
import by.bsuir.realEstateAgency.web.service.page.PageService;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.stream.Collectors;

@Component
@RequestMapping("/deals")
public class DealController {
    static Logger log = Logger.getLogger(DealController.class.getName());

    private static final String PAGE_NUMBER_REQUEST_PARAM = "page";

    private static final String PAGED_LIST_ATTRIBUTE = "pagedList";

    private static final String CREATE_ATTRIBUTE = "create";

    private static final String DEAL_DTO_ATTRIBUTE = "dealDto";

    @Resource
    private DealFacade dealFacade;

    @Resource
    private DealService dealService;

    @Resource
    private PageService pageService;

    @GetMapping
    private String getApplications(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                                   Model model) {
        model.addAttribute(PAGED_LIST_ATTRIBUTE, pageService.getPagedList(pageNumber, dealService));
        return "dealsList";
    }

    @PostMapping(params = "remove")
    private String removeDeal(@RequestParam(name = PAGE_NUMBER_REQUEST_PARAM, defaultValue = "1") int pageNumber,
                              CheckedList checkedList, BindingResult bindingResult,
                              Authentication authentication, Model model) throws Exception {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (!(userDetails.getUser() instanceof Admin)) {
            log.error("Trying delete deal not by admin");
            throw new AccessDeniedException();
        }
        if (bindingResult.hasErrors()) {
            log.error("Bad request params for deleting Deal. HTTP400");
            throw new BadRequestException();
        }
        if (checkedList.getCheckedList() != null) {
            dealService.removeList(checkedList.getCheckedList()
                    .stream().filter(CheckedItem::isChecked)
                    .map(CheckedItem::getId).collect(Collectors.toList()));
        }
        return "redirect:/deals?page=" + pageNumber;
    }

    @GetMapping("/new")
    public String getNewForm(Authentication authentication, Model model) {
        model.addAttribute(CREATE_ATTRIBUTE, true);
        model.addAttribute(DEAL_DTO_ATTRIBUTE, new DealDto());
        return "dealDetails";
    }

    @PostMapping("/new")
    public String createImmobility(@Valid DealDto dealDto, BindingResult bindingResult,
                                   Authentication authentication, Model model) {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()
                || dealFacade.saveOrUpdate(dealDto, userDetails.getUser(), bindingResult)) {
            model.addAttribute(CREATE_ATTRIBUTE, true);
            return "dealDetails";
        }
        return "redirect:/deals";
    }

    @GetMapping("/{id}")
    public String getImmobility(@PathVariable("id") long id, Model model) {
        DealDto dealDto = dealFacade.getDeal(id);
        if (dealDto == null) {
            log.error("Deal not found. HTTP404");
            throw new NotFoundException();
        }
        model.addAttribute(DEAL_DTO_ATTRIBUTE, dealDto);
        return "dealDetails";
    }

    @PostMapping(value = "/{id}", params = "save")
    public String updateImmobility(@PathVariable("id") long id,
                                   @Valid DealDto dealDto, BindingResult bindingResult,
                                   Authentication authentication, Model model) {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (bindingResult.hasErrors()
                || dealFacade.saveOrUpdate(dealDto, userDetails.getUser(), bindingResult)) {
            return "dealDetails";
        }
        return "redirect:/deals";
    }

    @PostMapping(value = "/{id}", params = "remove")
    public String removeImmobility(@PathVariable("id") long id, Authentication authentication, Model model) {
        AuthUserDetails userDetails = (AuthUserDetails) authentication.getPrincipal();
        if (!(userDetails.getUser() instanceof Admin)) {
            log.error("Trying delete deal not by admin");
            throw new AccessDeniedException();
        }
        dealService.remove(id);
        return "redirect:/deals";
    }
}
