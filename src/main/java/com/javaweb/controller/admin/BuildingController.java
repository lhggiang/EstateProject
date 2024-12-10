package com.javaweb.controller.admin;
import javax.servlet.http.HttpServletRequest;
import com.javaweb.constant.SystemConstant;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;
import com.javaweb.utils.DisplayTagUtils;
import com.javaweb.utils.MessageUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController(value = "buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private MessageUtils messageUtil;
    @Autowired
    private IUserService userService;
    @Autowired
    private IBuildingService buildingService;
    @RequestMapping(value = "/admin/building-list", method = RequestMethod.GET)
    public ModelAndView buildingList(@ModelAttribute("modelSearch") BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("district", districtCode.district());
        mav.addObject("typeCode", typeCode.typeCode());
        mav.addObject("staffs", userService.getListStaff());
        mav.addObject("modelSearch", buildingSearchRequest);
        if(SecurityUtils.getAuthorities().contains("ROLE_STAFF")){
            Long staffId = SecurityUtils.getPrincipal().getId();
            buildingSearchRequest.setStaffId(staffId);
            mav.addObject("building", buildingService.findAll(buildingSearchRequest));
        } else {
            mav.addObject("building", buildingService.findAll(buildingSearchRequest));
        }
        BuildingSearchResponse model = new BuildingSearchResponse();
        DisplayTagUtils.of(request, model);
        List<BuildingSearchResponse> buildingDTOs = buildingService.getBuildings(buildingSearchRequest, PageRequest.of(model.getPage() - 1, model.getMaxPageItems()));
        model.setListResult(buildingDTOs);
        model.setTotalItems(buildingService.findAll(buildingSearchRequest).size());
        mav.addObject("model", model);
        initMessageResponse(mav, request);
        return mav;
    }
    //thêm toà nhà
    @RequestMapping(value = "/admin/building-edit", method = RequestMethod.GET)
    public ModelAndView addBuilding(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("district", districtCode.district());
        mav.addObject("typeCode", typeCode.typeCode());
        return mav;
    }
    @RequestMapping(value = "/admin/building-edit-{id}", method = RequestMethod.GET)
    //tìm kiếm toà nhà theo id
    public ModelAndView updateBuilding(@PathVariable("id") Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        BuildingDTO model = buildingService.findByID(id);
        mav.addObject("buildingEdit",model);
        mav.addObject("district", districtCode.district());
        //mav.addObject("typeCode", typeCode.typeCode());
        return mav;
    }
    private void initMessageResponse(ModelAndView mav, HttpServletRequest request) {
        String message = request.getParameter("message");
        if (message != null && StringUtils.isNotEmpty(message)) {
            Map<String, String> messageMap = messageUtil.getMessage(message);
            mav.addObject(SystemConstant.ALERT, messageMap.get(SystemConstant.ALERT));
            mav.addObject(SystemConstant.MESSAGE_RESPONSE, messageMap.get(SystemConstant.MESSAGE_RESPONSE));
        }
    }
}
