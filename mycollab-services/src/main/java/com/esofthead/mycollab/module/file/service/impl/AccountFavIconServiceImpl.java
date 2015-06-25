package com.esofthead.mycollab.module.file.service.impl;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.PathUtils;
import com.esofthead.mycollab.module.file.service.AccountFavIconService;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import net.sf.image4j.codec.ico.ICOEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
@Service
public class AccountFavIconServiceImpl implements AccountFavIconService {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private BillingAccountService billingAccountService;

    @Override
    public String upload(String uploadedUser, BufferedImage logo, Integer sAccountId) {
        BillingAccount account = billingAccountService.getAccountById(sAccountId);
        if (account == null) {
            throw new MyCollabException(
                    "There's no account associated with provided id " + sAccountId);
        }

        // Construct new logoid
        String newLogoId = UUID.randomUUID().toString();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            ICOEncoder.write(logo, outStream);
        } catch (IOException e) {
            throw new UserInvalidInputException("Can not convert file to ico format", e);
        }
        Content logoContent = new Content();
        logoContent.setPath(PathUtils.buildFavIconPath(sAccountId, newLogoId));
        logoContent.setName(newLogoId);
        resourceService.saveContent(logoContent, uploadedUser, new ByteArrayInputStream(outStream.toByteArray()), null);
        return newLogoId;
    }
}
