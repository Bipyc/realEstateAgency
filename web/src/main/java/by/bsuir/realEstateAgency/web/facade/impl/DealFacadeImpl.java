package by.bsuir.realEstateAgency.web.facade.impl;

import by.bsuir.realEstateAgency.core.model.*;
import by.bsuir.realEstateAgency.core.service.ApplicationService;
import by.bsuir.realEstateAgency.core.service.DealService;
import by.bsuir.realEstateAgency.core.service.UserService;
import by.bsuir.realEstateAgency.web.bean.DealDto;
import by.bsuir.realEstateAgency.web.facade.DealFacade;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Service
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
        if (deal == null) {
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
    public boolean saveOrUpdate(DealDto dealDto, User user, BindingResult bindingResult) {
        Deal deal = null;
        if (dealDto.getId() != null) {
            deal = dealService.get(dealDto.getId());
            if (deal == null) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying update a nonexistent object", e);
                throw e;
            }
            if (!user.equals(deal.getApplication().getRealtor()) && !(user instanceof Admin)) {
                RuntimeException e = new IllegalStateException();
                log.error("Trying update immobility by not the owner or admin", e);
                throw e;
            }
        } else {
            deal = new Deal();
            deal.setDate(new Date());
        }

        deal.setId(dealDto.getId());
        deal.setPrice(dealDto.getPrice());

        User client = userService.getByLoginOrEmail(dealDto.getClientName());
        if (client == null || !(client instanceof Client)) {
            bindingResult.addError(new FieldError("dealDto", "clientName", dealDto.getClientName(), false,
                    new String[]{"NotFound.dealDto.clientName"}, null, "client not found"));

        } else {
            deal.setClient((Client) client);
        }

        Application application = applicationService.get(dealDto.getApplicationId());
        if (application == null) {
            bindingResult.addError(new FieldError("dealDto", "applicationId", dealDto.getApplicationId(), false,
                    new String[]{"NotFound.dealDto.applicationId"}, null, "immobility not found"));

        } else {
            deal.setApplication(application);
            if (dealDto.getCommission() == null) {
                BigDecimal comission = deal.getApplication().getType().getCommission().multiply(dealDto.getPrice());
                comission = comission.divide(new BigDecimal(100)).setScale(2, RoundingMode.UP);
                deal.setCommission(comission);
            } else {
                deal.setCommission(dealDto.getCommission());
            }
        }

        if (!bindingResult.hasErrors()) {
            dealService.save(deal);
            deal.getApplication().setStatus(ApplicationStatus.CLOSE);
            applicationService.save(deal.getApplication());
            dealDto.setId(deal.getId());
        }
        return bindingResult.hasErrors();
    }
}
