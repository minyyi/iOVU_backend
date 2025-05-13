package com.iovu.iovuback.service;

import com.iovu.iovuback.domain.DomainLink;
import com.iovu.iovuback.domain.LinkList;
import com.iovu.iovuback.mapper.DomainLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class DomainLinkService {
    @Autowired
    public DomainLinkMapper mapper;

//    public List<LinkList> getAllLinkList(){
//        return mapper.findAll();
//    }
//
//    public List<LinkList> getFindById(Integer link_id){
//
//     return mapper.findById(link_id);
//    }
//    public List<DomainLink> findById(Integer domain_link_id) {
//       return  mapper.getDomainLinkById(domain_link_id);
//    }
    public List<DomainLink> getDomainBYDetail(Integer report_detail_id) {
        return mapper.findDetail(report_detail_id);
    }
}
