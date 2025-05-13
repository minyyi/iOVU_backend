package com.iovu.iovuback.controller;


import com.iovu.iovuback.domain.DomainLink;
import com.iovu.iovuback.domain.LinkList;
import com.iovu.iovuback.service.DomainLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DomainLinkController {
    @Autowired
    private DomainLinkService service;

    @GetMapping("/reports/{report_detail_id}/links")
    public List<DomainLink> getDomainBYDetail(@PathVariable int report_detail_id) {
        return service.getDomainBYDetail(report_detail_id);
    }
//    @GetMapping("/reports/{id}/links")
//    public List<LinkList> getFindbyId(@PathVariable Integer id){
//        List<LinkList> link = service.getFindById(id);
////        if(linkList == null){
////            throw new NotFoundException(String.format("The Domain & Link is not found", linkList);
////        }
//        return link;
//    }
//
//    @GetMapping("/reports/links")
//    public List<LinkList> getAllLinkList(){
//        return service.getAllLinkList();
//    }
//
//
//    @GetMapping("/reports/{id}/links")
//    public List<DomainLink> getfindById(@PathVariable int id) {
////        List<DomainLink> domainLink = service.findById(id);
////        return domainLink;
//        return null;
//    }
//
//    @GetMapping("/reports/")
}
