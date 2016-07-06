package com.mycollab.rest.server.resource;

import com.esofthead.mycollab.common.dao.LiveInstanceMapper;
import com.esofthead.mycollab.common.domain.LiveInstance;
import com.esofthead.mycollab.common.domain.LiveInstanceExample;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@RestController
public class LiveInstanceMonitorController {
    @Autowired
    private LiveInstanceMapper liveInstanceMapper;

    @RequestMapping(value = "/checkInstance", method = RequestMethod.POST)
    public String checkInstance(@RequestBody LiveInstance instance) {
        String sysId = instance.getSysid();
        LiveInstanceExample ex = new LiveInstanceExample();
        ex.createCriteria().andSysidEqualTo(sysId);
        if (liveInstanceMapper.countByExample(ex) == 0) {
            instance.setInstalleddate(new DateTime().toDate());
            instance.setLastupdateddate(new DateTime().toDate());
            liveInstanceMapper.insert(instance);
        } else {
            List<LiveInstance> liveInstances = liveInstanceMapper.selectByExample(ex);
            if (liveInstances.size() > 0) {
                LiveInstance oldInstance = liveInstances.get(0);
                instance.setId(oldInstance.getId());
                instance.setInstalleddate(oldInstance.getInstalleddate());
                instance.setLastupdateddate(new DateTime().toDate());
                liveInstanceMapper.updateByPrimaryKey(instance);
            }
        }
        return "Ok";
    }
}
