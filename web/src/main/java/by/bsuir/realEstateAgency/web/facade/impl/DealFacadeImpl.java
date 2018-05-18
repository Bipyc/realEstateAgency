package by.bsuir.realEstateAgency.web.facade.impl;

import by.bsuir.realEstateAgency.core.model.*;
import by.bsuir.realEstateAgency.core.service.ApplicationService;
import by.bsuir.realEstateAgency.core.service.DealService;
import by.bsuir.realEstateAgency.core.service.UserService;
import by.bsuir.realEstateAgency.web.bean.DealDto;
import by.bsuir.realEstateAgency.web.facade.DealFacade;
import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.annotation.Resource;
import java.util.Date;

public class DealFacadeImpl implements DealFacade {

    static Logger log = Logger.getLogger(DealFacadeImpl.class.getName());

    @Resource
    private DealService dealService;

    @Resource
    private UserService userService;

    @Resource
    private ApplicationService applicationService;

    @Override
    public DealDto getDeal(Long key) {
        Deal deal = dealService.get(key);
        if(deal == null){
            return null;
        }
        DealDto dealDto = new DealDto();
        dealDto.setId(deal.getId());
        dealDto.setPrice(deal.getPrice());
        dealDto.setCommission(deal.getCommission());
        dealDto.setClientName(deal.getClient().getLogin());
        dealDto.setApplicationId(deal.getApplication().getId());
        dealDto.setDate(deal.getDate());
        return dealDto;
    }

    @Override
    public boolean saveOrUpdate(DealDto dealDto, BindingResult bindingResult) {
        Deal deal = null;
        if (dealDto.getId() != null) {
            deal = dealService.get(dealDto.getId());
            if (deal == null) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying update a nonexistent object", e);
                throw e;
            }
        } else {
            deal = new Deal();
            deal.setDate(new Date());
        }

        deal.setId(dealDto.getId());
        deal.setPrice(dealDto.getPrice());
        deal.setCommission(dealDto.getCommission());

        User client = userService.getByLoginOrEmail(dealDto.getClientName());
        if(client == null || !(client instanceof Client)){
            bindingResult.addError(new FieldError("dealDto", "clientName", dealDto.getClientName(), false,
                    new String[]{"NotFound.dealDto.clientName"}, null, "client not found"));

        }
        else{
            deal.setClient((Client) client);
        }

        Application application = applicationService.get(dealDto.getApplicationId());
        if(application == null){
            bindingResult.addError(new FieldError("applicationDto", "immobilityId", dealDto.getApplicationId(), false,
                    new String[]{"NotFound.applicationDto.immobilityId"}, null, "immobility not found"));

        }
        else {
            deal.setApplication(application);
        }

        if(!bindingResult.hasErrors()) {
            dealService.save(deal);
            dealDto.setId(deal.getId());
        }
        return bindingResult.hasErrors();
    }
}
